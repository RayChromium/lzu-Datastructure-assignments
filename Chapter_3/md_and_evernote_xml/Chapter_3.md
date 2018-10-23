# 第六周（第三章）作业

## 声明
以下几题中链表的描述：
```pascal
TYPE pointer = ↑node
     node = RECORD
             data:datatype
             pointer:↑node
            END;
     linkedlist = pointer
```
符号：
```pascal
:=  //"冒号+等于号"相当于赋值符（向左的箭头）
=   //"等号" ：判断左右是否相等的关系运算符
```
### 第二题
平凡情况：
```pascal
VAR SAVE = linkedlist
VAR p = linkedlist
PROC DelLinkedListNode(VAR L:linkedlist; a:datatype)
BEGIN
    {授权搜索}
    p := L
    {遍历链表}
    while (p↑.data != a)and(p↑.next != nil) do:
        [SAVE := p
        p := p↑.next]
    if p↑.data = a then
        [SAVE↑.next := p↑next
        DISPOSE(p)]
END
```
边界情况：
1. $L = Nil$
2. $L↑.data = a$
加入以上两个条件的判断
最终变成：
```pascal
VAR SAVE = linkedlist
VAR p = linkedlist
PROC DelLinkedListNode(VAR L:linkedlist; a:datatype)
BEGIN
    
    {进行条件判断}
    case:
    L = nil : EXIT
    L↑.data = a:
        [SAVE = L;
        L = L↑.next
        Dispose(SAVE)]
    ELSE:
        {授权搜索}
        p := L
        {遍历链表}
        while (p↑.data != a)and(p↑.next != nil) do:
            [SAVE := p
            p := p↑.next]
        if p↑.data = a then {将结点删除}
            [SAVE↑.next := p↑next
            DISPOSE(p)] 
    ENDCASE;
END
```
### 第三题
不懂得怎么用PDL表示读取键盘输入……
我就用中括号扩起
```pascal
[读取键盘输入]
```
来表示吧
```pascal
VAR p = linkedlist {生成链表的时候用的哨兵指针}
FUNCTION Createlinkedlist():linkedlist
BEGIN
    NEW(H)  {用作表头}
    NEW(s)
    s↑.next = nil
    p := s
    H↑.next := s
    input := [读取键盘输入]
    while (input!='\n') do 
        {读取到回车符就跳出循环}
        [if (input = ' ')OR(input = '\t')THEN
            [input := [读取键盘输入]
            continue]
            {读取到的是分隔符就跳过}
         ELSE
         p :=s
         p↑.data := input
         NEW(s)
         s↑.data := nil
         p↑.next := s
         input = [读取键盘输入]]
     {输入回车跳出循环之后s指向的最后一个结点里面没有数据，将它释放，倒数第二个结点作为最后一个结点}
    DISPOSE(s)
    p↑.next := nil
    return(H)
    {最后将指向头结点的指针返回}
END
```
### 第四题
常规情况：
将第一个结点的数据作为默认最小值保存为min，遍历链表，每遇到比min更小的值就将min更新
<font color = blue>边界情况：</font>
$H = Nil$
```pascal
FUNCTION GetMinimum(H:linkedlist;):datatype
BEGIN
    VAR p:linkedlist
    VAR min:datatype;{用来保存最小值的变量}
    case
        H = nil:return (NULL);{链表为空的时候直接终止}
        ELSE：
        [min := H↑.data
        p := H↑.next
        while (p != nil) do
            [if(p↑.data < min)THEN
                min := p↑.data
            p := p↑.next]
        ]
    ENDCASE
END
```
### 第五题
```pascal
VAR p = linkedlist {生成链表的时候用的哨兵指针}
FUNCTION Createlinkedlist():linkedlist
BEGIN
    NEW(H)  {用作表头}
    H↑.next := nil
    input := [读取键盘输入]
    while (input!='\n') do 
        {读取到回车符就跳出循环}
        [if(input = ' ')OR(input = '\t')THEN
            [input := [读取键盘输入]
            continue]
        p := H
         while (p↑.next != nil)AND(p↑.next↑.data < input)do
             [p := p↑.next]{进行比较，输入的数字比当前的下一个结点中的数值大的话就往后移动指针p}
         NEW(s)
         s.data := input
         s↑.next := p↑.next
         p↑.next := s
         input := [读取键盘数据]]
         {循环结束后表尾有一个空的结点，需要释放}
    DISPOSE(s)
    p↑.next := nil
    {最后将指向头结点的指针返回}
    return(H)
END
```
