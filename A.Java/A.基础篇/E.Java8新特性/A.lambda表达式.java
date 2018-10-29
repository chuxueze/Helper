1.简介:
2.语法:
3.作用:
(
    1.替代匿名内部类
    2.使用 lambda 表达式对集合进行迭代
    3.使用 lambda 表达式实现 map
)
4.遍历集合的例子与原理:


------------------------------------------------------------------------------------------------
1.简介:
Lambda 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性。

Lambda 允许把函数作为1个方法的参数（函数作为参数传递进方法中）。

使用 Lambda 表达式可以使代码变的更加简洁紧凑。

在Java中，Lambda 表达式是对象，他们必须依附于1类特别的对象类型--函数式接口

对于函数式编程来说，其只关心，定义输入数据和输出数据相关的关系，数学表达式里面其实是在做1种映射（mapping），
输入的数据和输出的数据关系是什么样的，是用函数来定义的。


-----------------------------------------------------------
2.语法:
lambda 表达式的语法格式如下：

(parameters) -> expression
或
(parameters) ->{ statements; }

parameters:参数
expression:表达式
statements:声明

示例:
(int a,int b) -> {return a+b;}
() -> System.out.println("Hello World");
(String s) -> {System.out.println(s);}
() -> 42
() -> {return 3.14}


-----------------------------------------------------------
3.作用:
1.替代匿名内部类
//1.1：匿名内部类
public class AnonymousInner {
    public static void main(String[] args) {
        fun(new Message() {
            //此括号内的内容，则为匿名内部类的实现
            //此处正常做法是定义一个实现类去实现message接口，而现在，去除Impl，直接在内部实现。此为匿名内部类
            @Override
            public void print() {
                System.out.println("匿名内部类实现");
            }
        });
    }

    public static void fun(Message message) {
        message.print();
    }
}

interface Message {
    void print();
}

//1.2：Lamba表达式
public class AnonymousInner2 {
    public static void main(String[] args) {
        fun(() -> System.out.println("Lamba表达式"));
    }
    public static void fun(Message2 message2) {
        message2.print();
    }
}
interface Message2 {
    void print();
}
输出：
Lamba表达式


//1.3：有参的Lamba表达式
public class AnonymousInner2 {
    public static void main(String[] args) {
        fun((x) -> System.out.println(x+"Lamba表达式"));
    }
    public static void fun(Message2 message2) {
        message2.print("---");
        message2.print("***");
    }
}

interface Message2 {
    void print(String str);
}
输出：
---Lamba表达式
***Lamba表达式


-------------------------
2.使用 lambda 表达式对集合进行迭代


    public static void getList() {
    	List<Integer> list = Arrays.asList(1,2,3);
    	list.forEach(l -> pringNum(l));
    }
    public static void pringNum(int a) {
    	System.out.println(a);
    }

-------------------------
3.使用 lambda 表达式实现 map
    @Test
    public void mapTest() {
        List<Double> cost = Arrays.asList(10.0, 20.0,30.0);
        cost.stream().map(x -> x + x*0.05).forEach(x -> System.out.println(x));
    }

    public static void getList2() {
    	List<Map<String,Object>> list = new ArrayList<>();
    	for(int i = 0;i<10;i++) {
    		Map<String,Object> map = new HashMap<>();
    		map.put(i+"", i+10);
    		list.add(map);
    	}
    	list.stream().map(m -> m).forEach(x -> System.out.println(x));
    }


-----------------------------------------------------------
4.遍历集合的例子与原理:

public class ListFor {
    public static void main(String[] args) {
        List<Integer> list = ListFor.addDataToList(10);
        ListFor.soutList(list);
    }
    //添加数据
    private static List<Integer> addDataToList(int len){
        List<Integer> list = new ArrayList<>();
        for(int i = 0;i < len;i++){
            list.add(i);
        }
        return list;
    }
    //输出数据
    private static void soutList(List<Integer> list){
        //第一种lamba表达式
        System.out.println("lamba表达式");
        list.forEach(val -> System.out.print(val));
        System.out.println("");
        //第二种匿名接口
        System.out.println("匿名接口");
        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer val) {
                System.out.print(val);
            }
        });
    }
}

default void forEach(Consumer<? super T> action) {
    Objects.requireNonNull(action);
    for (T t : this) {
        action.accept(t);
    }
}
输出:
lamba表达式
0123456789
匿名接口
0123456789

这里用到的是消费型接口，也就是接收入参，且无返回操作。
list.forEach(val -> System.out.print(val));
System.out.print(val):代表行为，想要对这些遍历的数据执行什么操作。