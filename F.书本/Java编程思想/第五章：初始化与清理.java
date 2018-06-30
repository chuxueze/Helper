目录:
1.构造器确保初始化
2.方法重载
3.this关键字
4.清理:终结处理和垃圾回收
5.构造器初始化
6.数组初始化










---------------------------
1.构造器确保初始化
    每个类都会有一个默认无参构造器。无参构造器确保初始化的进行。
如果自定义构造器，则默认构造器会失效。	
  所以，如果想通过 new 创建一个新对象，则可以自定义一个默认无参构造器，
防止别人自定义的构造器影响你创建新对象。
----
2.方法重载
    用多种方法创建一个对象。方法名相同而方法用到的参数不同。
重载的构造器 
public class Tree {
    int heinght;
    Tree(){//无参构造器
        heinght = 0;
        System.out.println("种一颗树......."+heinght+"米");
    }
    Tree(int initHeinght){
        System.out.println("创建一颗树....."+initHeinght+"米");
    }
}

重载的方法:
几个方法有相同名字，怎么区分呢？
区分的规则就是每个重载的方法都必须有独一无二的参数类型列表。
    void getTreeInfo(){}
    void getTreeInfo(String initHeinght,int age){}
参数顺序不同也是可以区分的，但一般不这么做，会导致代码难以维护。
    void getTreeInfo(int age,String initHeinght){}

----
3.this关键字
    只能在方法内部使用，表示对“调用方法的那个对象”的引用。
    如果是在方法内部调用同一个类的另一个方法，则没有使用this的必要，
虽然没有关系。但是建议不要这么做，能不用this就不用。

public class TestThis {
    int i = 0;
    TestThis getTestThis(){
        ++i;
        return this;
    }
    void getValue(){
        System.out.println("i的值为："+i);
    }
    public static void main(String[] args) {
        TestThis tt = new TestThis();
        //由于this返回的是对当前对象的引用，所以可以对同一个对象执行多次操作。
        tt.getTestThis().getTestThis().getTestThis().getValue();
    }
}
i的值为：3


----
4.清理:终结处理和垃圾回收

垃圾回收器只知道释放那些经由new分配的内存。 
存储空间的释放影响存储空间的分配。


-----
5.构造器初始化

自动初始化会在构造器调用之前发生，如下：
public class Dog {
    int i;
    public Dog() {
        i = 1;
    }
}
i会被先置为0，而后调用构造器时，才置为1。
--

变量定义的先后顺序决定了初始化的顺序。

class Window{
	window(int marker){
		print("window"+marker);
	}
}
class House{
	Window w1 = new Windwo(1);//1
	House(){//4
		print("House");
		w3 = new Window(3);
	}
	Window w2 = new Window(2);//2
	void f(){print("f")}//5
	Window w3 = new Window(3);//3
}
public class Test{
	public static void main(String[] args){
		House h =new House();
		h.f();
	}
}
w1
w2
w3
House
w33
f

House中，w1,w2,w3都属于变量，所以其运行的顺序优于House的构造器。

------
6.数组初始化

一个数组赋值给另外一个数组，只是复制数组的一个引用，并不是将所有值赋值。如下：
public class Arr {
    public static void main(String[] args) {
        int[] a1s = {1,2,3,4,5};
        int[] a2s = a1s;
        for(int i = 0,len = a2s.length;i<len;++i) {
            a2s[i] = a2s[i] + 10;//对a2s进行重新赋值，由于只是复制了引用，所以其实a1s也在共用这里的数值
        }
        for(int i = 0,len = a1s.length;i<len;++i) {
            System.out.println("a1["+i+"]..."+a1s[i]);//因为只是复制了引用，这里的a1s和a2s其实是同一个数据
            System.out.println("a2["+i+"]..."+a2s[i]);
            
        }
    }
}
结果：
a2[0]...11
a1[0]...11
a2[1]...12
a1[1]...12
a2[2]...13
a1[2]...13
a2[3]...14
a1[3]...14
a2[4]...15
a1[4]...15
