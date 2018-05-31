1.堆、栈、方法区概念
2.堆详解
3.栈详解
4.方法区详解







-----------------------
1.堆、栈、方法区概念
1)堆解决的是数据存储的问题，即数据怎么放，放在哪儿
2)栈解决程序的运行问题，即程序如何执行，或者说如何处理数据(实例对象引用)
3)方法区则是辅助堆栈的快永久区，解决堆栈信息的产生，是先决条件
举例:
class User(){
	String id;
	String name;
	String age;
}
User user1 = new User();
User user2 = new User();

堆:user1,user2，一块内存空间
方法区:User类及其方法实例
栈:存放对象引用


---------
2.堆详解
1)Java堆是Java应用程序关系最密切的内存空间，几乎所有对象都放在其中，
并且Java堆完全是自动化管理的，通过垃圾回收机制，垃圾对象会自动清理，
不需要显式地释放。
2)根据垃圾回收机制不同，Java堆有可能拥有不同的结构。最为常见的就是将整个
Java堆分为新生代和老年代。其中新生代存放新生的对象或者年龄不大的对象，
老年代则存放老年对象。

新生代分为三个区:
eden  S0  S1
老年代只有一个区:
tenured

绝大多数情况下，对象首先分配在eden区，在一次新生代回收后，如果对象还存活，
则会进入S0或者S1区，之后每经过一次新生代回收，如果对象存活则它的年龄就加1，
当对象达到一定的年龄后，则进入老年代。





垃圾回收:
复制算法:
  其核心思想就是将内存空间分为两块，每次只使用其中一块，在垃圾回收时，将正在使用
的内存中的存留对象复制到未被使用的内存块中去，之后去清除之前正在使用的内存块中
所有的对象，反复去交换两个内存的角色，完成垃圾收集。

S0、S1两个区块大小相等，可以互相转换角色。 
例如:
S0(user1、user2)
检测时，发现user1不用了，user2引用还需要，则将user2复制到S1，再将S0整个清空
S0(user1、user2)--user1-->S1(user1)
结果:
S0()  S1(user1)

S0 与 S1
也称from区和to区

实例:
最大内存:981M
空闲内存:940M
总内存:981M
Heap
 PSYoungGen      total 305664K, used 47190K [0x00000000eab00000, 0x0000000100000000, 0x0000000100000000)
  eden space 262144K, 18% used [0x00000000eab00000,0x00000000ed915988,0x00000000fab00000)
  from space 43520K, 0% used [0x00000000fd580000,0x00000000fd580000,0x0000000100000000)
  to   space 43520K, 0% used [0x00000000fab00000,0x00000000fab00000,0x00000000fd580000)
 ParOldGen       total 699392K, used 0K [0x00000000c0000000, 0x00000000eab00000, 0x00000000eab00000)
  object space 699392K, 0% used [0x00000000c0000000,0x00000000c0000000,0x00000000eab00000)
 Metaspace       used 7919K, capacity 8218K, committed 8448K, reserved 1056768K
  class space    used 924K, capacity 987K, committed 1024K, reserved 1048576K

-------------
3.栈详解
1)Java栈是一块线程私有的内存空间，一个栈，一般由三部分组成:局部变量表，操作数栈和帧数据区。
2)
局部变量表:
用于报错函数的参数及局部变量
操作数栈:
主要保存计算过程的中间结果，同时作为计算过程中变量临时的存储空间。
帧数据区:
除了局部变量表和操作数栈以外，栈还需要一些数据来支持常量池的解析，这里帧数据区保存着
访问常量池的指针，方便程序访问常量池。另外，当函数返回或者出现异常时，虚拟机必须有一个
异常处理表，方便发送异常的时候找到异常的代码，因此异常处理表也是帧数据区的一部分。


-------------
4.方法区详解
    Java方法区和堆一样，方法区是一块所有线程共享的内存区域，它保存系统的类信息，比如类的
字段、方法、常量池等。方法区的大小决定了系统可以保存多少个类，如果系统定义太多的类，导致
方法区溢出。虚拟机同样会抛出内存溢出错误。方法区可以理解为永久区。