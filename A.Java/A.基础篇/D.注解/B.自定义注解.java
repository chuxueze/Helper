1.自定义注解的语法要求
2.注解的使用





----------------------------------------------
1.自定义注解的语法要求
@Target({ElementType.METHOD,ElementType.TYPE})
//CONSTRUCTOR:构造方法声明
//FIFLD:字段声明
//LOCAL_VAFIABLE:局部变量声明
//METHOD;方法声明
//PACKAGE:包声明
//PARAMETER:参数声明
//TYPE:类，接口
@Retention(RetentionPolicy.RUNTIME)
//SOURCE:只在源码显示，编译时会丢失
//CLASS:编译时会记录到class中，运行时忽略
//RUNTIMR:运行时存在，可以通过反射读取
@Inherited
//允许子类继承
@Documented
//生成javadoc
public @interface Log {//使用@interface关键字定义注解
    //成员类型是受限的，合法的类型包括原始类型及String,Class,Annotation,Enumeration
    //如果注解只有一个成员，则成员名必须为value()，在使用时可以忽略成员名和赋值号（=）
    //注解类可以没有成员，没有成员的注解称为标识注解
    String desc();//成员变量以无参无异常方式声明
    String author();
    int age() default 18;//可以用default为成员指定一个默认值
}


2.注解的使用
public class LogTest {
    @Log(desc = "this is a test case",author = "Haochun Zhang",age=18)
    public static void eyeColor(){
        System.out.print("红色");
    }

    public static void main(String[] args) {
        LogTest.eyeColor();
    }
}