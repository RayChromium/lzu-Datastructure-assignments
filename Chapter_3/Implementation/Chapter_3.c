#include <stdio.h>
#include <stdlib.h>

typedef struct linkedlist{
    int data;
    struct linkedlist *next;
}linkedlist;

linkedlist* Create();            //第三题，顺序创建链表
linkedlist* Create_Ascending();  //第五题，升序创建链表
void delete_by_num(linkedlist *head,int a);       //第二题，删除数据项为a的结点
int getMinimun(linkedlist *head);                //第四题，获取链表数据中的最小值
void print_all(linkedlist *head);                //输出链表中的内容
void destroy(linkedlist* head);                  //完结之后销毁链表

int main()
{
    printf("单元测试：\n");
    printf("A：第三题（创建一个和输入顺序相同的链表\nB：第五题（创建一个结点数据是输入内容的升序的链表）\nC：第二题（删除数值为a的结点）\nD：第四题（获取链表中的结点数据最小值）\n");
    printf("\n\n开始测试第三题:输入一组数据以创建链表\n");
    linkedlist *normal = Create();
    printf("第三题的方法创建出的链表：\n");
    print_all(normal);
    printf("\n\n开始测试第五题:输入一组数据以升序创建链表\n");
    linkedlist *ascending = Create_Ascending();
    printf("第五题的方法创建出的链表：\n");
    print_all(ascending);
    printf("测试第二题的过程：删除链表中数据项等于a的结点\n");
    printf("输入a：\n");
    int a;
    scanf("%d",&a);
    delete_by_num(normal,a);
    delete_by_num(ascending,a);
    printf("删除结束后：\n");
    printf("顺序链表：");
    print_all(normal);
    printf("升序链表：");
    print_all(ascending);
    printf("\n\n现在测试求链表中元素最小值：\n");
    printf("顺序链表：");
    getMinimun(normal);
    printf("升序链表：");
    getMinimun(ascending);
    destroy(normal);
    destroy(ascending);
    return 0;
}

linkedlist* Create()   //第三题：创建一个结点值与输入顺序相同的链表
{
    int input_num;   //用来记录输入的数字
    char sep;        //用来记录输入的分隔符
    linkedlist *p,*head,*new_node;
    p = head = (linkedlist*)malloc(sizeof(linkedlist));
    new_node = (linkedlist*)malloc(sizeof(linkedlist));
    new_node->next = NULL;
    head->next = new_node;
    printf("输入一串数字：");
    scanf("%d",&input_num);
    sep = getchar();
    while(sep != '\n') //这个循环结束之后最后一个结点还没有赋值，表尾指针悬空，要在循环外做收尾
    {
        p = new_node;
        p->data = input_num;
        new_node = (linkedlist*)malloc(sizeof(linkedlist));
        p->next = new_node;
        scanf("%d",&input_num);
        sep = getchar();
    }
    p = p->next;
    p->data = input_num;
    p->next = NULL;
    return head;
}

linkedlist* Create_Ascending()   //第五题：创建一个结点值为输入数字升序的链表
{
    int input_num;              //保存输入的数据
    char sep;                   //保存输入的分隔符
    linkedlist *p,*new_node,*head;
    head = (linkedlist*)malloc(sizeof(linkedlist));
    head->next = NULL;
    scanf("%d",&input_num);
    sep = getchar();
    while(sep != '\n')          //此循环结束之后最后一个输入的数据还未加入链表，需要将循环体内的工作再做一次
    {
        p = head;
        while(p->next != NULL && p->next->data < input_num)
            p = p->next;
        new_node = (linkedlist*)malloc(sizeof(linkedlist));
        new_node->data = input_num;
        new_node->next = p->next;
        p->next = new_node;
        scanf("%d",&input_num);
        sep = getchar();
    }
    p = head;
    while(p->next != NULL && p->next->data < input_num)
        p = p->next;
    new_node = (linkedlist*)malloc(sizeof(linkedlist));
    new_node->data = input_num;
    new_node->next = p->next;
    p->next = new_node;

    return head;
}

void print_all(linkedlist* head)
{
    linkedlist *p = head->next;
    while(p!=NULL)
    {
        printf("%d  ",p->data);
        p = p->next;
    }
}

void delete_by_num(linkedlist* head,int a)
{
    linkedlist *p1,*p2;
    p1 = head;
    p2 = head->next;
    while(p2!=NULL && p2->data!=a)
    {
        p1 = p2;
        p2 = p2->next;
    }
    if(p2 == NULL)    //说明链表中没有数据项为a的结点
        return;
    p1->next = p2->next;
    free(p2);
}

int getMinimun(linkedlist* head)
{
    int min;
    linkedlist *p = head->next;
    if(p == NULL)      //链表为空就返回-1
    {
        printf("此链表为空\n");
        return -1;
    }
    min = p->data;
    while(p != NULL)
    {
        //p = p->next;      写在这里是一个易犯得错误：最后一趟循环会让p悬空引发异常
        if(p->data < min)
            min = p->data;
        p = p->next;
    }
    printf("最小值为%d\n",min);
    return min;
}

void destroy(linkedlist* head)
{
    linkedlist *p1,*p2;
    p1 = head;
    p2 = head->next;
    while(p2!=NULL)
    {
        free(p1);
        p1 = p2;
        p2 = p2->next;
    }
    free(p1);
}

