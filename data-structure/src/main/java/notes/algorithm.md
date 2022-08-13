##### 基础数据结构

- 数组：顺序存储，通过索引访问
- 链表：单向链表，双向链表，环形链表(约瑟夫问题)，通过指针访问下一个元素
- 队列：先进先出，在队尾添加元素，在队首取出元素
- 栈：后进先出，入栈出栈都在栈顶位置
- 树：前序遍历，中序遍历，后序遍历
- 图：深度优先搜索，广度优先搜索

##### 单链表的反转

从头到尾遍历链表，将每个元素添加到新链表的最前端

##### 中缀表达式转后缀

1. 准备操作数栈S1，操作符栈S2
2. 扫描表达式，将操作数压入S1,将操作符压如S2
3. 遇到操作符，比较与S2栈顶元素的优先级，如果高于栈顶元素，则压入栈中，否则弹出栈顶元素并压入S1中，再将操作符压入S2
4. 遇到左括号直接压入栈中，遇到右括号弹出括号包含的元素压入到S1
5. 最后依次弹出S2中的操作符压入到S1中

##### 经典排序算法

1. 冒泡排序：每趟排序将两两相邻的位置比较并交换位置，直到将最大的数字交换到最后面
2. 选择排序：每一趟排序从未排序的列表中选择出最小的数字，交换到最前面的位

```java
        private static int[]sort(int[]arrays){
        for(int i=0;i<arrays.length-1;i++){
        int minIndex=i;
        boolean f=false;
        for(int j=i+1;j<arrays.length;j++){
        if(arrays[j]<arrays[minIndex]){
        minIndex=j;
        f=true;
        }
        }
        if(f){
        int temp=arrays[i];
        arrays[i]=arrays[minIndex];
        arrays[minIndex]=temp;
        }
        }
        return arrays;
        }   
```