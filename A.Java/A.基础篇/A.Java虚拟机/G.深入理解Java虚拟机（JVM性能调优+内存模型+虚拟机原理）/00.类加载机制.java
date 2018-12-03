1:类的加载:
2:双亲委派机制:






---------------------------------------------------------------------------------------------------------------------
1:类的加载:
    类的加载指的是将类的.class 文件中的二进制数据读入内存中,将其放在运行时数据区域的方法去内,
然后在堆中创建 java.lang.Class 对象,用来封装类在方法区的数据结构.只有 java 虚拟机才会创建 class 对象,
并且是11对应关系.这样才能通过反射找到相应的类信息.

Java 虚拟机有预加载功能。


--------------------------------------------------------
2:双亲委派机制:
    比如 A 类的加载器是 AppClassLoader (其实我们自己写的类的加载器都是AppClassLoader)，
AppClassLoader 不会自己去加载类，而会委 ExtClassLoader 进行加载，那么到了 ExtClassLoader 类加载器的时候，
它也不会自己去加载，而是委托 BootStrap 类加载器进行加载，就这样1层1层往上委托，如果 Bootstrap 类加载器无法进行加载的话，再1层层往下走。

为何需要双亲委派机制:
    判断两个类相同的前提是这两个类都是同1个加载器进行加载的，如果使用不同的类加载器进行加载同1个类，也会有不同的结果。

如果没有双亲委派机制，会出现什么样的结果呢？
    比如我们在 rt.jar 中随便找1个类，如 java.util.HashMap, 那么我们同样也可以写1个1样的类，也叫 java.util.HashMap 存放在我们自己的路径下(ClassPath).
那样这两个相同的类采用的是不同的类加载器，系统中就会出现两个不同的 HashMap 类，这样引用程序就会出现1片混乱。
