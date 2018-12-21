1.JDK8之前:
2.JDK8之后:
3.hash 方法源码比较:









--------------------------------------------------------------------------------------------------------------------------------------
1.JDK8之前:
    
    JDK1.8 之前 HashMap 底层是 数组和链表结合在1起使用也就是链表散列。
HashMap 通过 key 的 hashCode 经过扰动函数处理过后得到 hash 值，然后通过 (n - 1) & hash 判断当前元素存放的位置（这里的 n 指的是数组的长度），
如果当前位置存在元素的话，就判断该元素与要存入的元素的 hash 值以及 key 是否相同，如果相同的话，直接覆盖，不相同就通过拉链法解决冲突。

    所谓扰动函数指的就是 HashMap 的 hash 方法。使用 hash 方法也就是扰动函数是为了防止1些实现比较差的 hashCode()。
方法换句话说使用扰动函数之后可以减少碰撞。

    所谓 “拉链法” 就是：将链表和数组相结合。也就是说创建1个链表数组，数组中每1格就是1个链表。若遇到哈希冲突，则将冲突的值加到链表中即可。


---------------------------------------------------------------------
2.JDK8之后:

    相比于之前的版本,JDK1.8 之后在解决哈希冲突时有了较大的变化，当链表长度大于阈值（默认为8）时，将链表转化为红黑树，以减少搜索时间。
TreeMap、TreeSet 以及 JDK1.8 之后的 HashMap 底层都用到了红黑树。红黑树就是为了解决二叉查找树的缺陷，因为二叉查找树在某些情况下会退化成1个线性结构。



---------------------------------------------------------------------
3.hash 方法源码比较:

JDK 1.8 HashMap 的 hash 方法源码:

JDK 1.8 的 hash方法 相比于 JDK 1.7 hash 方法更加简化，但是原理不变。

    static final int hash(Object key) {
      int h;
      // key.hashCode()：返回散列值也就是hashcode
      // ^ ：按位异或
      // >>>:无符号右移，忽略符号位，空位都以0补齐
      return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
  }

对比1下 JDK1.7 的 HashMap 的 hash 方法源码.

static int hash(int h) {
    // This function ensures that hashCodes that differ only by
    // constant multiples at each bit position have a bounded
    // number of collisions (approximately 8 at default load factor).

    h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
}

相比于 JDK1.8 的 hash 方法 ，JDK 1.7 的 hash 方法的性能会稍差1点点，因为毕竟扰动了 4 次。