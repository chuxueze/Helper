    Vector 类的所有方法都是同步的。
可以由两个线程安全地访问1个 Vector 对象、但是1个线程访问 Vector 的话代码要在同步操作上耗费大量的时间。

    Arraylist 不是同步的，所以在不需要保证线程安全时建议使用 Arraylist。