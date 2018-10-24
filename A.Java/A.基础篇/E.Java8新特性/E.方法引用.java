1.简介:
2.使用方式:
3.实例:











---------------------------------------------------------------------------------------------------------------------------
1.简介:

方法引用通过方法的名字来指向1个方法。

方法引用可以使语言的构造更紧凑简洁，减少冗余代码。

方法引用指的是为1个方法设置别名，相当于1个方法定义了不同的名字。

使用条件:
如果想要实现函数引用，那么必须要有接口，接口里需要只存在1个普通方法。


---------------------------------------------------------------
2.使用方式:
2.1:引用静态方法
类名称::static 方法名称

2.2:引用某个对象的方法
实例化对象::普通方法

2.3:引用特定类型的方法
特定类::普通方法

2.4:引用构造方法
类名称::new 

---------------------------------------------------------------
3.实例:
3.1:引用静态方法(valueOf 方法是 String 类的静态方法)
//  将String.valueOf()方法变为IMessage接口里的change()方法
	@FunctionalInterface
    interface IMessage<P,R>{
        R change(P p);
    }
    public static void test3(){
        // 将String.valueOf()方法变为IMessage接口里的change()方法
        IMessage message = String::valueOf;
        String str = (String)message.change(1000000);
        System.out.println(str.getClass());
    }
输出:1000000
-------------------------
3.2:引用某个对象的方法 实例化对象::普通方法
	@FunctionalInterface
    interface IMsg<R>{
        R change();
    }
    public static void test4(){
        // hello字符串为String的一个实例对象
        //此语句相当于执行了"hello".toUpperCase();
        IMsg msg = "hello"::toUpperCase;
        String str = (String) msg.change();
        System.out.println(str);
    }
输出:HELLO
-------------------------
3.3:引用构造方法
类名称::new 
class Book {
    String name;
    double price;

    Book(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return "书名：" + name + "价格：" + price;
    }
}

	interface BookMsg<C>{
    	C createBook(String name,double price);
	}
    public static void test5(){
        //此操作相当于创建一个book对象并实例化
        BookMsg<Book> bookMsg = Book :: new;
        Book book = bookMsg.createBook("Java",120.12);
        System.out.println(book.toString());
    }
输出:
书名：Java价格：120.12



@FunctionalInterface
将接口声明为函数式接口。
如果此接口多个非默认或者非静态接口声明，则编译时会报语法错误。
该接口不是1个函数式接口。