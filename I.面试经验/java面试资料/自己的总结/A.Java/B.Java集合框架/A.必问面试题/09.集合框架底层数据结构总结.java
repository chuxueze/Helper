1.Collection:
2.Map:





-------------------------------------------------------------------------------------------------------------------------------
1.Collection:

1)List
    Arraylist： Object 数组
    Vector： Object 数组
    LinkedList： 双向链表(JDK1.6 之前为循环链表，JDK1.7 取消了循环) 详细可阅读 JDK1.7-LinkedList 循环链表优化


2)Set
    HashSet（无序，唯一）: 基于 HashMap 实现的，底层采用 HashMap 来保存元素
    LinkedHashSet： LinkedHashSet 继承与 HashSet，并且其内部是通过 LinkedHashMap 来实现的。
有点类似于我们之前说的 LinkedHashMap 其内部是基于 Hashmap 实现1样，不过还是有1点点区别的。
    TreeSet（有序，唯一）： 红黑树(自平衡的排序二叉树。)



----------------------------------------------------------------
2.Map:

HashMap:
    JDK1.8 之前 HashMap 由数组+链表组成的，数组是 HashMap 的主体，
链表则是主要为了解决哈希冲突而存在的（“拉链法”解决冲突）.
JDK1.8 以后在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为8）时，将链表转化为红黑树，以减少搜索时间

LinkedHashMap: 
    LinkedHashMap 继承自 HashMap，所以它的底层仍然是基于拉链式散列结构即由数组和链表或红黑树组成。
另外，LinkedHashMap 在上面结构的基础上，增加了1条双向链表，使得上面的结构可以保持键值对的插入顺序。
同时通过对链表进行相应的操作，实现了访问顺序相关逻辑。详细可以查看：《LinkedHashMap 源码详细分析（JDK1.8）》

HashTable: 数组+链表组成的，数组是 HashMap 的主体，链表则是主要为了解决哈希冲突而存在的

TreeMap: 红黑树（自平衡的排序二叉树）