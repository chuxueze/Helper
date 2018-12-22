    在多线程下，进行 put 操作会导致 HashMap 死循环，
原因在于 HashMap 的扩容 resize()方法。由于扩容是新建1个数组，复制原数据到数组。
由于数组下标挂有链表，所以需要复制链表，但是多线程操作有可能导致环形链表。


复制链表过程如下:
以下模拟2个线程同时扩容。
    假设，当前 HashMap 的空间为2（临界值为1），hashcode 分别为 0 和 1，在散列地址 0 处有元素 A 和 B，这时候要添加元素 C，C 经过 hash 运算，
得到散列地址为 1，这时候由于超过了临界值，空间不够，需要调用 resize 方法进行扩容，那么在多线程条件下，会出现条件竞争，模拟过程如图：




注意： jdk1.8 已经解决了死循环的问题。