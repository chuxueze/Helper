1.简介:
2.四大核心功能接口:
3.注意点:
4.代码实例(自定义函数式表达式)
5.四大核心功能接口详解:








---------------------------------------------------------------------------------------------------------------
1.简介:
函数式接口 (Functional Interface)就是1个有且仅有1个抽象方法，但是可以有多个非抽象方法的接口。

函数式接口可以被隐式转换为 lambda 表达式。
函数式接口可以现有的函数友好地支持 lambda。


-----------------------------------------------------------
2.四大核心功能接口:
2.1:功能性接口
Function 
public interface Function<T, R> {
	R apply(T t);
}
此接口接收1个参数，并且返回1个处理结果

---------------
2.2:消费型接口
public interface Consumer<T> {
	void accept(T t);
}
此接口只负责接收数据(引用数据是不需要返回)，并且不返回处理结果。

---------------
2.3:供给型接口
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
此接口不接收参数，但是可以返回结果

---------------
2.4:断言型接口
public interface Predicate<T> {
    boolean test(T t);
}
进行操作判断


-----------------------------------------------------------
3.注意点:
如果接口定义了
java.lang.Object
的默认方法，则不计算在内。


如下:
此接口为1个函数式接口
package com.zhc.test;
@FunctionalInterface
public interface MyInterface {
   void test();
}

此接口虽然定义了三个方法，但是由于 toString和hashCode 是 Object 类的默认方法，所以也是可以编译通过的。
package com.zhc.test;
@FunctionalInterface
public interface MyInterface {
   void test();
   String toString();
   int hashCode();
}

为什么???
例如:
toString() 方法是 Object 类的默认方法，任何类都会直接或间接地继承 Object，也就相当于实现 toString 方法。
如果1个实现类实现了 MyInterface 这个接口，则它也会继承 Object 类并实现其方法。



-----------------------------------------------------------
4.代码实例(自定义函数式表达式)
public class MyInterfaceTest {
    public static void testInterface(MyInterface myInterface) {
        System.out.println("开始了");
        myInterface.test();
        System.out.println("结束了");
    }
    public static void main(String[] args) {
        testInterface(() -> System.out.println("表达式"));
        System.out.println("||||||||||||||||||||||||||");
        testInterface(new MyInterface() {
            @Override
            public void test() {
                System.out.println("内部类");
            }
        });
    }
}

输出:

开始了
表达式
结束了
||||||||||||||||||||||||||
开始了
内部类
结束了

testInterface(() -> System.out.println("表达式"));
此行代码代表了对 myInterface 接口下方法的实现，此方法为唯1的方法。


-----------------------------------------------------------
5.四大核心功能接口详解:

5.1:Function 接口
@FunctionalInterface
public interface Function<T, R> {
	
	R apply(T t);

	default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

	static <T> Function<T, T> identity() {
        return t -> t;
    }

}
功能接口，接收1个参数 t，返回 1 个数据 R