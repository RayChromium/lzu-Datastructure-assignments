# 第七周（第四章）作业
符号：
↑  ：指针指向运算符
  
:=  ：赋值运算符
  
= ：关系运算符（相等）
## 第三题-双栈共存
描述：
```pascal
TYPE Stack:array[1..m] OF datatype  //保存栈中的数据，假定这个向量元素的下标是从1-m
VAR top:array[1..2] OF Integer  //记录两个栈的栈顶元素位置
```
令```Top[1]```保存的是靠近下标<font color = red>**1**</font>侧的栈顶元素下标
令```Top[2]```保存的是靠近下标<font color = red>**m**</font>侧的栈顶元素下标




平凡情况的出栈入栈很简单，来看看边界情况
判断栈空的过程(出栈的边界条件)：
```pascal
FUNC isEmpty(i:integer ; top:array):bool
BEGIN
    case
        i = 1 : if(top[i] = 0)THEN [return true]  //为了避免“栈空”与“栈中刚好有一个元素”产生二义性，令Top[i]中存放的是“栈基”的下标会比较方便
                ELSE [return false]
        i = 2 : if(top[i] = m+1)THEN [return true]
                ELSE [return false]
    ENDCASE;
END
```
判断栈满的过程（入栈的边界条件）:
```pascal
FUN isFull(top:array):bool
BEGIN
    if(top[2] - top[1] = 1)THEN [return true]
    ELSE [return false]
END
```
接下来就就可以将边界情况考虑进去了
```pascal
PROC doublePush(A:Stack ; Top:Array ; i:int x:datatype)
BEGIN
    case
        isFull(Top):[write('栈满')
                    abort]
        i = 1:[Top[i] := Top[i] + 1
               A[Top[i]] := x]
        i = 2:[Top[i] = Top[i] - 1
               A[Top[i]] := x]
    ENDCASE;
END
```
出栈：
```pascal 
PROC doublePop(A:Stack ; Top:Array ; i:int ; y:datatype)
BEGIN
    case
        isEmpty[i,Top]:[write('栈空')
                      abort]
        i = 1:[y := A[Top[i]]
               Top[i] := Top[i]-1]
        i = 2:[y := A[Top[i]]
               Top[i] := Top[i]+1]
    ENDCASE
END
```

## 第四题
使用链栈实现链表的逆置（先默认这个链表是一个无表头的链表）
**链栈**其实也是一个链表，只不过它的插入方式比较特殊：每次插入新结点（入栈）只能在表头进行操作，被称为<font color = red>"头插法"</font>
链表的描述：
```pascal
TYPE pointer = ↑Node
     Node = RECORD
           data : datatype
           next : pointer
           END
     linkedlist = pointer
```
平凡情况：
```pascal
VAR top,p,save:linkedlist  //top是在进行站操作时使用的栈顶指针，p 和 save 指向链表结点的指针
FUNC reverse(H:linkedlist):linkedlist //函数过程，返回的是将逆置之后的连表头指针
BEGIN
    save := H 
    NEW(Stack) //新建一个栈，第一个入栈的元素是链表第一个节点的元素
    Stack↑.data := save↑.data
    Stack↑.next := Nil
    p := save↑.next
    Dispose(save)  //入栈之后就将已经入栈的结点释放
    while p!=Nil DO
        [New(top) //申请一个结点空间作为新的栈顶
         top↑.data := p↑.data //链表结点入栈
         save := p
         p := p↑.next 
         top↑.next := Stack //将栈顶结点与Stack链接起来
         Dispose(save)]
    //这个时候，Stack其实就已经是指向一个链表的指针了，而且这个链表正好是原链表H的逆置，此时直接返回Stack就可以了
    return Stack
END
```
边界情况：链表H为空（即$H = Nil$）
所以最终应该是这样：
```pascal
VAR top,p,save:linkedlist  //top是在进行站操作时使用的栈顶指针，p 和 save 指向链表结点的指针
FUNC reverse(H:linkedlist):linkedlist //函数过程，返回的是将逆置之后的连表头指针
BEGIN
    save := H 
    case
        save = Nil:[write('链表为空');return(Null)]
        ELSE:
           [NEW(Stack) //新建一个栈，第一个入栈的元素是链表第一个节点的元素
            Stack↑.data := save↑.data
            Stack↑.next := Nil
            p := save↑.next
            Dispose(save)  //入栈之后就将已经入栈的结点释放
            while p!=Nil DO
                [New(top) //申请一个结点空间作为新的栈顶
                 top↑.data := p↑.data //链表结点入栈
                 save := p
                 p := p↑.next 
                 top↑.next := Stack //将栈顶结点与Stack链接起来
                 Stack := top//将Stack指向新的栈顶
                 Dispose(save)]
    //这个时候，Stack其实就已经是指向一个链表的指针了，而且这个链表正好是原链表H的逆置，此时直接返回Stack就可以了
            return Stack]
    ENDCASE
END
```
## 第五题
表示：
队首：first
队尾：last
  
```pascal
TYPE Queue:Array[m...n]OF datatype
VAR first,last:[m...n]
```
这里为了避免二义性，采取牺牲一个向量元素的设计
  
分三种情况：
1. $last > first$
2. $last < first$
3. $last = first$，这种情况为队空
```pascal
FUNC count(A:Queue):int
BEGIN
    case
        first < last:[return last - first]
        first > last:[return (n - first ) + (last - m +1)]
        first = last:retrn 0
    ENDCASE;
END
```
那么其实就可以归结为一种情况：
```pascal
FUNC count(A:Queue):int
BEGIN
    return mod((last - fisrt)+(n - m),(n-m))
END
```
使用模运算获取栈中元素个数
  
但其实这两种方法确实不太好比较那个更快，可能需要具体考虑系统是怎么实现模运算的吧，不然也不好说哪一个所做的运算次数更少。
