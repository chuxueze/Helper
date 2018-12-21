1:是否保证线程安全:
2:底层数据结构:
3:插入和删除是否受元素位置影响:
4:是否支持快速随机访问:
5:内存空间占用:
6:RandomAccess 接口:






---------------------------------------------------------------------------------------------------------------------------
1:是否保证线程安全:

    ArrayList 和 LinkedList 都是不同步的，也就是不保证线程安全；


---------------------------------------------------------------
2:底层数据结构:

    Arraylist 底层使用的是 Object 数组；LinkedList 底层使用的是双向链表数据结构。
（JDK1.6 之前为循环链表，JDK1.7 取消了循环。注意双向链表和双向循环链表的区别：）； 
详细可阅读JDK1.7-LinkedList 循环链表优化（https://www.cnblogs.com/xingele0917/p/3696593.html）


---------------------------------------------------------------
3:插入和删除是否受元素位置影响:

    ArrayList 采用数组存储，所以插入和删除元素的时间复杂度受元素位置的影响。 
比如：执行 add(E e) 方法的时候， ArrayList 会默认在将指定的元素追加到此列表的末尾，这种情况时间复杂度就是 O(1)。
但是如果要在指定位置 i 插入和删除元素的话（add(int index, E element) ）时间复杂度就为 O(n-i)。
因为在进行上述操作的时候集合中第 i 和第 i 个元素之后的 (n-i)个元素都要执行向后位/向前移1位的操作。 

    LinkedList 采用链表存储，所以插入，删除元素时间复杂度不受元素位置的影响，都是近似 O（1）而数组为近似 O（n）。


---------------------------------------------------------------
4:是否支持快速随机访问:

    LinkedList 不支持高效的随机元素访问，而 ArrayList 支持。
快速随机访问就是通过元素的序号快速获取元素对象(对应于 get(int index) 方法)。


---------------------------------------------------------------
5:内存空间占用:
  
    ArrayList 的空间浪费主要体现在 list 列表的结尾会预留1定的容量空间，
而 LinkedList 的空间花费则体现在它的每1个元素都需要消耗比 ArrayList 更多的空间（因为要存放直接后继和直接前驱以及数据）。


---------------------------------------------------------------
6:RandomAccess 接口:

public interface RandomAccess {

}

    查看源码我们发现实际上 RandomAccess 接口中什么都没有定义。
所以，在我看来 RandomAccess 接口不过是1个标识罢了。标识什么？ 标识实现这个接口的类具有随机访问功能。

    在 binarySearch() 方法中，它要判断传入的 list 是否 RamdomAccess 的实例，
如果是，调用 indexedBinarySearch() 方法，如果不是，那么调用 iteratorBinarySearch() 方法

    public static <T>
    int binarySearch(List<? extends Comparable<? super T>> list, T key) {
        if (list instanceof RandomAccess || list.size()<BINARYSEARCH_THRESHOLD)
            return Collections.indexedBinarySearch(list, key);
        else
            return Collections.iteratorBinarySearch(list, key);
    }

    ArraysList 实现了 RandomAccess 接口， 而 LinkedList 没有实现。为什么呢？
我觉得还是和底层数据结构有关！ArraysList 底层是数组，而 LinkedList 底层是链表。
数组天然支持随机访问，时间复杂度为 O（1），所以称为快速随机访问。
链表需要遍历到特定位置才能访问特定位置的元素，时间复杂度为 O（n），所以不支持快速随机访问。
ArraysList 实现了 RandomAccess 接口，就表明了他具有快速随机访问功能。 
RandomAccess 接口只是标识，并不是说 ArraysList 实现 RandomAccess 接口才具有快速随机访问功能的！






---------------------------------------------------------------