1.异常简介:
2.Error（错误）:
3.Exception（异常）:
4.Throwable 类常用方法:








-------------------------------------------------------------------------------------------------

1.异常简介:
    在 Java 中，所有的异常都有1个共同的祖先 java.lang 包中的 Throwable 类。
    Throwable： 有两个重要的子类：Exception（异常） 和 Error（错误） ，二者都是 Java 异常处理的重要子类，各自都包含大量子类。



------------------------------------------------

2.Error（错误）:

    Error（错误）:是程序无法处理的错误，表示运行应用程序中较严重问题。大多数错误与代码编写者执行的操作无关，而表示代码运行时 JVM（Java 虚拟机）出现的问题。
例如，Java 虚拟机运行错误（Virtual MachineError），当 JVM 不再有继续执行操作所需的内存资源时，将出现 OutOfMemoryError。这些异常发生时，Java 虚拟机（JVM
）一般会选择线程终止。

    这些错误表示故障发生于虚拟机自身、或者发生在虚拟机试图执行应用时，如 Java 虚拟机运行错误（Virtual MachineError）、类定义错误（NoClassDefFoundError）等。
这些错误是不可查的，因为它们在应用程序的控制和处理能力之外，而且绝大多数是程序运行时不允许出现的状况。对于设计合理的应用程序来说，即使确实发生了错误，
本质上也不应该试图去处理它所引起的异常状况。在 Java中，错误通过 Error 的子类描述。




------------------------------------------------

3.Exception（异常）:

    Exception（异常）:是程序本身可以处理的异常。Exception 类有1个重要的子类 RuntimeException。RuntimeException 异常由 Java 虚拟机抛出。NullPointerException
（要访问的变量没有引用任何对象时，抛出该异常）、ArithmeticException（算术运算异常，一个整数除以0时，抛出该异常）和 ArrayIndexOutOfBoundsException （
下标越界异常）。

注意：异常和错误的区别：异常能被程序本身可以处理，错误是无法处理。


------------------------------------------------

4.Throwable 类常用方法:

public string getMessage():返回异常发生时的详细信息
public string toString():返回异常发生时的简要描述
public string getLocalizedMessage():返回异常对象的本地化信息。使用 Throwable 的子类覆盖这个方法，可以声称本地化信息。如果子类没有覆盖该方法，
                   则该方法返回的信息与 getMessage（）返回的结果相同
public void printStackTrace():在控制台上打印 Throwable 对象封装的异常信息