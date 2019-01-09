1.概念
2.在 Java 中参数是怎么传递的
3.总结





-----------------------------------------------------
1.概念
1)传值:
当方法参数是值传递时，意味着原参数的1个拷贝被传到了参数内部而不是原始参数，所以任何对于该参数的改变都只会影响这个拷贝值。
2)传引用:
当方法参数是引用传递时，意味着原始参数的引用或者说指针被传递到了方法内部，而不是这个原始参数的内容。



---------
2.在 Java 中参数是怎么传递的
    在 Java 中，不管原始参数的类型是什么，参数都是按值传递的。
每次当1个方法被执行的时候，在堆栈中就会为每个参数创建1个拷贝，这个拷贝会被传递到方法内部。

1)基本类型:
如果原始参数是基本类型，那么在堆栈中创建的便是这个参数的简单拷贝
2)非基本类型:
如果原始参数不是基本类型，那么在堆栈中创建的便是指向真正对象数据的新的引用或指针。
这个新的引用被传递到方法内部（在这种情况下，有2个引用指向了同1个对象数据）


---------
3.总结
1)在方法中，修改1个基础类型的参数永远不会影响原始参数值。
2)在方法中，改变1个对象参数的引用永远不会影响到原始引用。然而，它会在堆中创建了1个全新的对象。（译者注：指的是包装类和 immutable 对象）
3)在方法中，修改1个对象的属性会影响原始对象参数。
4)在方法中，修改集合和 Maps 会影响原始集合参数。

如果传递的是基本类型，则会影响原有的数据，
如果传递的是对象或者容器对象，则不会影响原有数据。








值传递和应用传递实例
1. 值传递
public static void main(String[] args) {
    int num1 = 10;
    int num2 = 20;

    swap(num1, num2);

    System.out.println("num1 = " + num1);
    System.out.println("num2 = " + num2);
}

public static void swap(int a, int b) {
    int temp = a;
    a = b;
    b = temp;

    System.out.println("a = " + a);
    System.out.println("b = " + b);
}
结果：

a = 20
b = 10
num1 = 10
num2 = 20
解析：

在swap方法中，a、b的值进行交换，并不会影响到num1、num2。因为，a、b中的值，只是从num1、num2的复制过来的。 也就是说，a、b相当于num1、num2的副本，副本的内容无论怎么修改，都不会影响到原件本身。

2. 引用传递
public static void main(String[] args) {
    int[] arr = {1,2,3,4,5};

    change(arr);

    System.out.println(arr[0]);
}

public static void change(int[] array) {
//将数组的第一个元素变为0
    array[0] = 0;
}
结果：

1
0
解析：

无论是主函数，还是change方法，操作的都是同一个地址值对应的数组。 。因此，外部对引用对象的改变会反映到所有的对象上。

一些特殊的例子
1. StringBuffer类型传递
	// 测试引用传递：StringBuffer
	@org.junit.Test
	public void method1() {
		StringBuffer str = new StringBuffer("公众号：Java面试通关手册");
		System.out.println(str);
		change1(str);
		System.out.println(str);
	}

	public static void change1(StringBuffer str) {
		str = new StringBuffer("abc");//输出：“公众号：Java面试通关手册”
		//str.append("欢迎大家关注");//输出：公众号：Java面试通关手册欢迎大家关注
		//str.insert(3, "(编程)");//输出：公众号(编程)：Java面试通关手册
		
	}
结果：

公众号：Java面试通关手册
公众号：Java面试通关手册
解析：

很多要这个时候要问了：StringBuffer创建的明明也是对象，那为什么输出结果依然是原来的值呢？

因为在change1方法内部我们是新建了一个StringBuffer对象，所以str指向了另外一个地址，相应的操作也同样是指向另外的地址的。

那么，如果将change1方法改成如下图所示，想必大家应该知道输出什么了，如果你还不知道，那可能就是我讲的有问题了，我反思（开个玩笑，上面程序中已经给出答案）：

	public static void change1(StringBuffer str) {

		str.append("欢迎大家关注");
		str.insert(3, "(编程)");
		
	}
2. String类型传递
	// 测试引用传递：Sring
	@org.junit.Test
	public void method2() {
		String str = new String("公众号：Java面试通关手册");
		System.out.println(str);
		change2(str);
		System.out.println(str);
	}

	public static void change2(String str) {
		// str="abc"; //输出：公众号：Java面试通关手册
		str = new String("abc"); //输出：公众号：Java面试通关手册
	}
结果：

公众号：Java面试通关手册
公众号：Java面试通关手册
可以看到不论是执行str="abc;"还是str = new String("abc");str的输出的值都不变。 按照我们上面讲“StringBuffer类型传递”的时候说的，str="abc;"应该会让str的输出的值都不变。为什么呢？因为String在创建之后是不可变的。

3. 一道类似的题目
下面的程序输出是什么？

public class Demo {
	public static void main(String[] args) {
		Person p = new Person("张三");

		change(p);

		System.out.println(p.name);
	}

	public static void change(Person p) {
		Person person = new Person("李四");
		p = person;
	}
}

class Person {
	String name;

	public Person(String name) {
		this.name = name;
	}
}
很明显仍然会输出张三。因为change方法中重新创建了一个Person对象。

那么，如果把change方法改为下图所示，输出结果又是什么呢？

	public static void change(Person p) {
		p.name="李四";
	}