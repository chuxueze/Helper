ConcurrentHashMap 和 Hashtable 的区别主要体现在实现线程安全的方式上不同。

1:底层数据结构: 
2:实现线程安全的方式（重要）:



---------------------------------------------------------------------------------------------------------------------------------
1:底层数据结构: 

    JDK1.7 的 ConcurrentHashMap 底层采用分段的数组+链表 实现，
    JDK1.8 采用的数据结构跟 HashMap1.8 的结构1样，数组+链表/红黑二叉树。

    Hashtable 和 JDK1.8 之前的 HashMap 的底层数据结构类似都是采用 数组+链表 的形式，数组是 HashMap 的主体，
链表则是主要为了解决哈希冲突而存在的；


-----------------------------------------------------------------
2:实现线程安全的方式（重要）:

    1)在 JDK1.7 的时候，ConcurrentHashMap（分段锁） 对整个桶数组进行了分割分段 (Segment)，每1把锁只锁容器其中1部分数据，
多线程访问容器里不同数据段的数据，就不会存在锁竞争，提高并发访问率。（默认分配16个 Segment，比 Hashtable 效率提高16倍。） 

    到了 JDK1.8 的时候已经摒弃了 Segment 的概念，而是直接用 Node 数组+链表+红黑树的数据结构来实现，并发控制使用 synchronized 和 CAS 来操作。
（JDK1.6 以后 对 synchronized 锁做了很多优化） 整个看起来就像是优化过且线程安全的 HashMap。
虽然在JDK1.8 中还能看到 Segment 的数据结构，但是已经简化了属性，只是为了兼容旧版本；
    

    2) Hashtable(同1把锁) :使用 synchronized 来保证线程安全，效率非常低下。当1个线程访问同步方法时，其他线程也访问同步方法，
可能会进入阻塞或轮询状态，如使用 put 添加元素，另1个线程不能使用 put 添加元素，也不能使用 get，竞争会越来越激烈效率越低。


----------------------------------
JDK1.7 实现方式:

    首先将数据分为1段1段的存储，然后给每1段数据配1把锁，当1个线程占用锁访问其中1段数据时，其他段的数据也能被其他线程访问。
ConcurrentHashMap 是由 Segment 数组结构和 HashEntry 数组结构组成。

    Segment 实现了 ReentrantLock,所以 Segment 是1种可重入锁，扮演锁的角色。HashEntry 用于存储键值对数据。
	static class Segment<K,V> extends ReentrantLock implements Serializable {

	}

    一个 ConcurrentHashMap 里包含1个 Segment 数组。Segment 的结构和 HashMap 类似，是1种数组和链表结构，
一个 Segment 包含1个 HashEntry 数组，每个 HashEntry 是1个链表结构的元素，每个 Segment 守护着1个 HashEntry 数组里的元素，
当对 HashEntry 数组的数据进行修改时，必须首先获得对应的 Segment 的锁.



-----------------
JDK1.8 实现方式:

    ConcurrentHashMap 取消了 Segment 分段锁，采用 CAS 和 synchronized 来保证并发安全。
数据结构跟 HashMap 1.8 的结构类似，数组+链表/红黑二叉树。

synchronized 只锁定当前链表或红黑二叉树的首节点，这样只要 hash 不冲突，就不会产生并发，效率又提升N倍。