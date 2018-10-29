1:Function 接口
2:拆解
3:与消费型接口的比较
4:Function 接口下的其他方法(高阶函数)
5:BiFunction<T,U,R>


-------------------------------------------------------------------------------------------------------------
1:Function 接口
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
------------------------------------------------------
2:拆解:
只需要接收你的行为即可。定义入参与处理的逻辑函数，传递到方法中。

private static int test5(int a,Function<Integer,Integer> function){
    return function.apply(a);
}

int ret = ListFor.test5(2,val -> val*5);
System.out.println(ret);
输出:10

此方法相当于下面的平常的做法
private static int test55(int a){
    return a*5;
}

int ret55 = ListFor.test55(2);
System.out.println(ret55);


平常的做法，是将具体的实现提出来，提前定义好，做为1个方法函数去定义，再去进行调用。
而函数式表达式，可以将具体的实现作为参数，传入方法中去得到结果。代码更简洁。

也可以是这样写，先将行为定义好，再传入方法中。
Function<Integer,Integer> function = val -> val*5;
ListFor.test5(1,function)


--------------
val -> val*5
此处将 val*5 这个行为传入参数
入参为2，出参为 val*5
--------------
function.apply(a);
此处的 a 相当于 2
function.apply 相当于 val*5，执行了 val*5 这个行为

就像数学中的函数式1样，y = kx;
y==出参
x==入参
k==行为，也就是函数方法
相当于将逻辑处理放在了入参上。

------------------------------------------------------
3:与消费型接口的比较
private static void test6(int a,Consumer<Integer> consumer){
    consumer.accept(a);
}

ListFor.test6(1,val -> System.out.println(val*5));

我们所需要做的就是定义好入参:x  和 行为函数:k

--------------
不同点:
功能型接口 Function 和消费型接口 Consumer

功能型接口 Function
可以想像成有返回值的接口方法。传入1个值，并定义值计算的规则函数，返回1个值出来。
而消费型接口 Consumer
可以想像成无返回值的接口方法 。传入1个值，并定义值计算的规则函数，你直接去处理不需要返回。


------------------------------------------------------
4:Function 接口下的其他方法(高阶函数)

高阶函数:
如果1个函数接收1个函数作为参数，或者返回1个函数作为返回值，那么该函数就叫做高阶函数。


4.1:compose 方法 andThen 方法
default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
    Objects.requireNonNull(before);
    return (V v) -> apply(before.apply(v));
}

default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
    Objects.requireNonNull(after);
    return (T t) -> after.apply(apply(t));
}

--------------------
//先执行function2再执行function1
//f2=2*10  f1=f2-1    结果为:19
int f1 = ListFor.test9(2,val->val-1,val->val*10);
System.out.println(f1);

//先执行function1再执行function2
//f1=2-1  f2=f1*10    结果为：10
int f2 = ListFor.test10(2,val->val-1,val->val*10);
System.out.println(f2);

private static int test9(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
    return function1.compose(function2).apply(a);
}

private static int test10(int a, Function<Integer, Integer> function1, Function<Integer, Integer> function2) {
    return function1.andThen(function2).apply(a);


可以重复调用，如下又调用了 andThen 方法
此处返回 function2->function1->function2.  f2 结果给 f1 做参数，得出的结果再给 f2 做参数。
return function1.compose(function2).andThen(function2).apply(a);

y1 = k1 * x;
y2 = k2 * y1;
y3 = k3 * y2;


------------------------------------------------------
5:BiFunction<T,U,R>
BiFunction<T,U,R>
代表了1个接受两个输入参数的方法，并且返回1个结果

private static int test11(int a, int b, BiFunction<Integer,Integer,Integer> biFunction){
    return biFunction.apply(a,b);
}

int i = ListFor.test11(10, 100, (val,val2) -> val * val2);
System.out.println(i);
输出:
1000