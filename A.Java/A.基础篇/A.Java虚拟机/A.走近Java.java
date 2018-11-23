1.Java 虚拟机的基本结构
2.JDK 与 JRE
3.可使用JVM监控工具去排除内存问题



--------------------------------------------------------------------------
1.Java 虚拟机的基本结构
1)类加载子系统
	负责从文件系统或者网络中加载 Class 信息，加载的信息存放在1块称之为方法区的内存空间
2)方法区
    就是存放类信息，常量信息，常量池信息，包括字符串字面量和数字常量等
3)Java堆
    在 Java 虚拟机启动的时候建立 Java 堆，它是 Java 程序最主要的内存工作区域，几乎所有的对象
都存放到 Java 堆中，堆空间是所有线程共享的。
4)直接内存
    Java 的 NIO 库允许 Java 程序使用直接内存，从而提高性能，通常是直接内存速度会优于 Java 堆。
读写频繁的场合可能会考虑使用。
5)Java栈
    每个虚拟机线程都有1个私有的栈，1个线程的 Java 栈在线程创建的时候被创建，Java 栈中
保存着局部变量、方法参数，同时 Java 的方法调用、返回值。
6)本地方法栈
    本地方法栈和 Java 栈非常类似，最大不同为本地方法栈用于本地方法调用。Java 虚拟机允许 Java
直接调用本地方法(通常使用C编写)
7)垃圾回收系统
	垃圾收集系统是 Java 的核心，也是必不可少的，Java 有1套自己进行垃圾清理的机制。开发人员
无需手工清理。
8)PC寄存器
	每个线程私有的空间，Java 虚拟机会为每个线程创建PC寄存器，在任意时刻，一个 Java 线程总
是在执行1个方法，这个方法被称为为当前方法，如果是本地方法，则PC寄存器值为 undefined，
寄存器存放如当前执行环境指针、程序计数器、操作栈指针、计算的变量指针等。
9)执行引擎
	负责执行虚拟机的字节码，一般会先进行编译成机器码后执行。



-------------------------------------
2.JDK 与 JRE
1)JDK组成:
Java 程序设计语言、Java 虚拟机、JavaAPI 类库
2)JRE:
JavaAPI 类库中的 JavaSE API 子集和 Java 虚拟机两部分统称为JRE

JDK包含JRE
JRE包含JVM

-------------------------------------
3.可使用JVM监控工具去排除内存问题