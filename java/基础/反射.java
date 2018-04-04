反射是什么呢？
	能够运行时动态加载需要加载的对象。
	如：Spring的IOC
	new出来的对象，都是服务器启动的时候初始化好的，本身就在内存中，
而反射可以在你需要对象的时候，加载程序时去动态调用。


    当我们的程序在运行时，需要动态的加载一些类这些类可能之前用不到所以不用加载到jvm，而是在运行时根据需要才加载，
这样的好处对于服务器来说不言而喻，举个例子我们的项目底层有时是用mysql，有时用oracle，需要动态地根据实际情况加载驱动类，
这个时候反射就有用了，假设 com.java.dbtest.myqlConnection，com.java.dbtest.oracleConnection这两个类我们要用，
这时候我们的程序就写得比较动态化，通过Class tc = Class.forName("com.java.dbtest.TestConnection");
     通过类的全类名让jvm在服务器中找到并加载这个类，而如果是oracle则传入的参数就变成另一个了。
这时候就可以看到反射的好处了，这个动态性就体现出java的特性了！
举多个例子，大家如果接触过spring，会发现当你配置各种各样的bean时，是以配置文件的形式配置的，
你需要用到哪些bean就配哪些，spring容器就会根据你的需求去动态加载，你的程序就能健壮地运行。

相对地，这个运行效率会有要求。因为是在运行中创建对象。

应用场景：
springIOC  实例化对象时，如：
@Resource
HelpInfosManager helpInfosManager;




---------------------------------------
IOC的实现原理—反射与工厂模式之间的对比
反射机制的优缺点

        反射机制的优点：可以实现动态创建对象和编译，体现出很大的灵活性（特别是在J2EE的开发中它的灵活性就表现的十分明显）。通过反射机制我们可以获得类的各种内容，进行反编译。对于JAVA这种先编译再运行的语言来说，反射机制可以使代码更加灵活，更加容易实现面向对象。

        比如，一个大型的软件，不可能一次就把把它设计得很完美，把这个程序编译后，发布了，当发现需要更新某些功能时，我们不可能要用户把以前的卸载，再重新安装新的版本，假如这样的话，这个软件肯定是没有多少人用的。采用静态的话，需要把整个程序重新编译一次才可以实现功能的更新，而采用反射机制的话，它就可以不用卸载，只需要在运行时动态地创建和编译，就可以实现该功能。

        反射机制的缺点：对性能有影响。使用反射基本上是一种解释操作，我们可以告诉JVM，我们希望做什么并且让它满足我们的要求。这类操作总是慢于直接执行相同的操作。



反射与工厂模式实现IOC

        Spring中的IoC的实现原理就是工厂模式加反射机制。 我们首先看一下不用反射机制时的工厂模式：

[java] view plain copy
interface fruit{  
    public abstract void eat();  
}   
class Apple implements fruit{  	
     public void eat(){  
         System.out.println("Apple");  
     }  
}   
class Orange implements fruit{  
     public void eat(){  
         System.out.println("Orange");  
     }  
}  
//构造工厂类  
//也就是说以后如果我们在添加其他的实例的时候只需要修改工厂类就行了  
class Factory{  
     public static fruit getInstance(String fruitName){  
         fruit f=null;  
         if("Apple".equals(fruitName)){  
             f=new Apple();  
         }  
         if("Orange".equals(fruitName)){  
             f=new Orange();  
         }  
         return f;  
     }  
}  
class hello{  
     public static void main(String[] a){  
         fruit f=Factory.getInstance("Orange");  
         f.eat();  
     }  
}  
        上面写法的缺点是当我们再添加一个子类的时候，就需要修改工厂类了。如果我们添加太多的子类的时候，改动就会很多。下面用反射机制实现工厂模式：

[java] view plain copy
interface fruit{  
     public abstract void eat();  
}  
class Apple implements fruit{  
public void eat(){  
         System.out.println("Apple");  
     }  
}  
class Orange implements fruit{  
public void eat(){  
        System.out.println("Orange");  
    }  
}  
class Factory{  
    public static fruit getInstance(String ClassName){  
        fruit f=null;  
        try{  
            f=(fruit)Class.forName(ClassName).newInstance();  
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
        return f;  
    }  
}  
class hello{  
    public static void main(String[] a){  
        fruit f=Factory.getInstance("Reflect.Apple");  
        if(f!=null){  
            f.eat();  
        }  
    }  
}  
        现在就算我们添加任意多个子类的时候，工厂类都不需要修改。使用反射机制实现的工厂模式可以通过反射取得接口的实例，但是需要传入完整的包和类名。而且用户也无法知道一个接口有多少个可以使用的子类，所以我们通过属性文件的形式配置所需要的子类。

        下面编写使用反射机制并结合属性文件的工厂模式（即IoC）。首先创建一个fruit.properties的资源文件：

[html] view plain copy
apple=Reflect.Apple  
orange=Reflect.Orange  
        然后编写主类代码：

[java] view plain copy
interface fruit{  
    public abstract void eat();  
}  
class Apple implements fruit{  
    public void eat(){  
        System.out.println("Apple");  
    }  
}  
class Orange implements fruit{  
    public void eat(){  
        System.out.println("Orange");  
    }  
}  
//操作属性文件类  
class init{  
    public static Properties getPro() throws FileNotFoundException, IOException{  
        Properties pro=new Properties();  
        File f=new File("fruit.properties");  
        if(f.exists()){  
            pro.load(new FileInputStream(f));  
        }else{  
            pro.setProperty("apple", "Reflect.Apple");  
            pro.setProperty("orange", "Reflect.Orange");  
            pro.store(new FileOutputStream(f), "FRUIT CLASS");  
        }  
        return pro;  
    }  
}  
class Factory{  
    public static fruit getInstance(String ClassName){  
        fruit f=null;  
        try{  
            f=(fruit)Class.forName(ClassName).newInstance();  
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
        return f;  
    }  
}  
class hello{  
    public static void main(String[] a) throws FileNotFoundException, IOException{  
        Properties pro=init.getPro();  
        fruit f=Factory.getInstance(pro.getProperty("apple"));  
        if(f!=null){  
            f.eat();  
        }  
    }  
}  
        运行结果：Apple