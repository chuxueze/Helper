1.简介:
2.语法:
3.作用:
(
    1.替代匿名内部类
    2.使用 lambda 表达式对集合进行迭代
    3.使用 lambda 表达式实现 map
)


------------------------------------------------------------------------------------------------
1.简介:
Lambda 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性。

Lambda 允许把函数作为1个方法的参数（函数作为参数传递进方法中）。

使用 Lambda 表达式可以使代码变的更加简洁紧凑。

----------------------------------------------
2.语法:
lambda 表达式的语法格式如下：

(parameters) -> expression
或
(parameters) ->{ statements; }

parameters:参数
expression:表达式
statements:声明

----------------------------------------------
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