#include <stdio.h>
#include <stdlib.h>
#include "linkedlist.h"  //这里面有链表结点的定义和函数的声明
 /**
  * 第七周（第四章）作业的实现示例，此文件中包含main()
  * 第三题：双栈共存问题
  * 第四题：借助栈结构实现链表逆置
  * 第五题：计算循环链表中的元素个数
 */

int main(int argc, char const *argv[])
{
    char op;
    printf("A:第三题：双栈共存问题  B:第四题：借助栈结构实现链表逆置  C:第五题：计算循环链表中的元素个数\n输入回车结束");
    while((op = getchar())!= '\n'){
        switch(op){
            case 'a':case 'A':;
            case 'b':case 'B':;
            case 'c':case 'C':;
            case 'd':case 'D':;
            default:break;
        }
    }
    return 0;
}
