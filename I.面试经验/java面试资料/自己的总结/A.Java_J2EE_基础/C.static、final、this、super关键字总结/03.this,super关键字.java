1.this 关键字:
2.super 关键字:
3.使用 this 和 super 要注意的问题:











------------------------------------------------------------------------------------------------------------------
1.this 关键字:
this 关键字用于引用类的当前实例。 例如：

class Manager {
    Employees[] employees;
     
    void manageEmployees() {
        int totalEmp = this.employees.length;
        System.out.println("Total employees: " + totalEmp);
        this.report();
    }
     
    void report() { }
}

在上面的示例中，this 关键字用于两个地方：

this.employees.length：访问类 Manager 的当前实例的变量。
this.report（）：调用类 Manager 的当前实例的方法。

此关键字是可选的，这意味着如果上面的示例在不使用此关键字的情况下表现相同。 但是，使用此关键字可能会使代码更易读或易懂。



-----------------------------------------------------------
2.super 关键字:

super 关键字用于从子类访问父类的变量和方法。 例如：

public class Super {
    protected int number;
     
    protected showNumber() {
        System.out.println("number = " + number);
    }
}
 
public class Sub extends Super {
    void bar() {
        super.number = 10;
        super.showNumber();
    }
}
在上面的例子中，Sub 类访问父类成员变量 number 并调用其其父类 Super 的 showNumber（） 方法。




-----------------------------------------------------------
3.使用 this 和 super 要注意的问题:

    super 调用父类中的其他构造方法时，调用时要放在构造方法的首行！this 调用本类中的其他构造方法时，也要放在首行。
this、super 不能用在 static 方法中。



简单解释1下：

    被 static 修饰的成员属于类，不属于单个这个类的某个对象，被类中所有对象共享。
而 this 代表对本类对象的引用，指向本类对象；而 super 代表对父类对象的引用，指向父类对象；
所以， this 和 super 是属于对象范畴的东西，而静态方法是属于类范畴的东西。