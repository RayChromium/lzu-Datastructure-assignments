import java.util.Scanner;

/**
 * 不使用String类，字符串用char[]数组和一个length属性表示
 * 第四题（）：统计不同字符个数
 * 第五题（）：字符逆转（递归）
 *
 * 第四题思路：对于不同的字符串，所包含的不同字符个数可能也不一样，并且与字符串本身的长度关系不大
 *           比如"apple"包含4种不同字符，而"Wryyyyyyyyyyyyy"只包含3种不同字符
 *           我们统计不同字符的个数时，需要知道字符的种类和数量，也就是类似于{char c:int quantity}这样的键值对的形式
 *           也就是Dictionary或者map这种形式
 *           如果不使用java提供的类，那么我们可以使用一个链表来实现
 *
 * 第五题思路：过程表示： reverse(s:String):
 *           1) 基线条件：length = 1 时，将唯一的字符返回
 *           2) 递归：length > 1 时，返回 "'最后一个字符'+reverse(s.substring(0,length-1))"
 * */

public class String_type {

    char[] s;
    int length;

    //构造器
    String_type(){
        s = new char[1];
        length = 0;
    }

    String_type(int capacity){     //另一种构造器，但是给定了字符串的长度
        s = new char[capacity];
        length = 0;
    }

    public String_type subString(int from,int to)    //获取子串，这个method将在第五题(reverse()中)用到
    {
        String_type sub = new String_type(to - from + 1);
        for(;sub.length <= to - from;sub.length++)
        {
            sub.s[sub.length] = this.s[from+sub.length];
        }
        return sub;
    }

    /**
     * 以下这个resizing方法实现了改变字符数组的长度，
     * 具体方法是在原数组已满的时候创建一个长度是它两倍的新数组
     * 然后依次将每个元素复制过去
     * 如果需要输入n个字符的话就总共需要进行log(n)次新建数组的操作
     * 而如果将"访问数组元素"作为一个基本操作的话
     * 那么当字符串的长度为N时，总共需要进行的操作次数为
     * N + (2 + 4 + 8 + 16 +……+ N) ~ O(3N)
     * 时间复杂度为线性，这是可以接受的
     * (参考文献：Algorithms (4th edition)  by Robert Sedgewick & Kevin Wayne)
     * */
    private void resizing(int capacity){     //用来实现变长的字符型数组,将字符数组的长度改变为capacity
        char[] copy = new char[capacity];
        for(int i = 0;i < length;i++)
            copy[i] = s[i];
        s = copy;                          //随后原s指向的字符数组被GC回收
    }

    /**
     * 这个方法(函数)是第五题的解
     */
    private String_type reverse(String_type string)       //字符串逆置
    {
        if (string.length == 1)
            return string.subString(0,0);    //基线条件：如果字符串只有一个字符，直接返回这个单字符子串
                                           //递归条件：如果字符串长度大于1，返回“末尾字符+前面n-1个字符的逆置”
        else                               //由于这个自定义的String_type类不能直接用'+'实现字符串连接，需要自己实现一下
        {
            String_type result = new String_type(string.length);    //这是要返回的结果，也就是末尾字符+前面length-1个字符的逆
            String_type back_part = string.reverse(string.subString(0,string.length - 2));
            result.s[0] = string.s[string.length-1];
            result.length++;
            for(int i = 0;i < back_part.length;i++)
            {
                result.s[i+1] = back_part.s[i];
                result.length++;
            }
            return result;
        }
    }

    void inputString() {  //将键盘输入的字符串存入字符数组s
        Scanner keyin = new Scanner(System.in);
        String input;
        input  = keyin.nextLine();
        for(int i = 0;i < input.length();i++)
        {
            s[length] = input.charAt(i);
            length++;
            if (length == s.length)       //如果字符数组已满，就将素组长度变为原来的两倍
                resizing(length*2);
        }
    }


    /*这是测试的主方法*/
    public static void main(String[] args){
        String_type string = new String_type();
        System.out.println("输入字符串");
        string.inputString();
        System.out.println("字符串逆转后：");
        String_type reversed = string.reverse(string);
        for(int i = 0;i < string.length;i++)
            System.out.print(reversed.s[i]);
        System.out.println("\n统计字符：");
        String_stats stats = new String_stats(string);
        stats.Stats();
    }
}

/**
 * 这个类用来统计一个String_tyoe对象中的不同字符个数
 * 每统计一个字符就将这个字符出现位置的布尔值设为false，这样可以避免重复统计
 * */
class String_stats{

    private char[] chars;           //将待求的字符串复制到这数组里
    Node first;                     //链表头指针

    //内部类，表示链表的结点
    private class Node{
        char c;
        int count;
        Node next;       //指向下一个结点的引用变量(指针)
    }

    String_stats(String_type string){
        chars = new char[string.length];
        for(int i = 0;i < string.length;i++)
            chars[i] = string.s[i];
    }

    void Stats()     //统计
    {
        first = new Node();      //带表头结点的链表，头结点first用来存放字符串中出现的字符有几种
        first.count = 0;
        Node now = new Node();          //新的结点，存储某一种字符的是什么与数量
        now.count  = 0;
        now.next = null;
        first.next = now;     //头结点的next引用(指针)指向now
        for(int i = 0;i<chars.length;i++)
        {
            if(chars[i] == '\n') continue;        //将已经记录的相同字符替换成回车（不知道有没有别的办法）
            now.c = chars[i];              //存储字符
            now.next = null;               //next初值为null
            for(int j = i;j < chars.length;j++)
            {
                if (chars[j] == '\n') continue;   //如果遇到'\n'，说明这个字符已经记录过了
                if(chars[j] == now.c)
                {
                    now.count++;
                    chars[j] = '\n';        //重复出现的字符赋值为'\n'，下次就直接跳过
                }
            }
            first.count++;         //一种字符已经记录完成，头结点中的种类数+1

            now.next = new Node();      //新建一个结点，准备寻找下一种字符
            now = now.next;
            now.count = 0;
        }

        //上面的循环结束之后结点now中其实是没有有效信息的，或者说这时now.c的值为'\0'
        //输出统计信息：
        System.out.println("字符串中不同种类的字符有"+first.count+"种，分别是：");
        for(now = first.next;now.c != 0;now = now.next)
            System.out.println(now.c +"\t"+"数量："+now.count);
    }
}
/**
 * 第四题小结：这里没有使用Dictionary和map等方便的数据结构，
 *           如果认为“访问数组元素”为一种基本操作的话，那么对于一个长度为n的串
 *           最坏情况下，这个算法的时间复杂度大概是O(N^2)，效率是比较低的
 *           但其实也是可以改进的
 * */

