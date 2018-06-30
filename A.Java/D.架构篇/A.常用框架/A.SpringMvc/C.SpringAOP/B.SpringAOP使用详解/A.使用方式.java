1.使用方式
2.主要注解
3.五种 Advice(通知)
4.代码示例











------------------------------------
1.使用方式
1)XML配置
2)注解方式




---------------
2.主要注解
1)@Aspect
标注 Java 类之1个切面类
2)@Pointcut
声明要在哪些类的哪些方式上植入代码 
3)Advice  如:@Before("adminOnly()")
声明想要在方法执行的什么时间植入代码




----------------
3.五种 Advice(通知)

在连接点前面执行，前置通知不会影响连接点的执行，除非此处抛出异常。 
正常返回通知[After returning advice]：在连接点正常执行完成后执行，如果连接点抛出异常，则不会执行。 
异常返回通知[After throwing advice]：在连接点抛出异常后执行。 
返回通知[After (finally) advice]：在连接点执行完成后执行，不管是正常执行完成，还是抛出异常，都会执行返回通知中的内容。 
环绕通知[Around advice]：环绕通知围绕在连接点前后，比如一个方法调用的前后。这是最强大的通知类型，能在方法调用前后自定义一些操作。环绕通知还需要负责决定是继续处理join point(调用ProceedingJoinPoint的proceed方法)还是中断执行。


1)@Before(execution) 在方法执行前拦
2)@AfterReturning(execution)在方法正常return结束后拦截
3)@AfterThrowing(execution) 在方法抛出异常时拦截
4)@After(execution) 在方法结束后拦截,无论正常结束还是异常结束
5)@Around(execution)唯一可以使用ProceedingJoinPoint参数来控制流程的advice,在方法执行前拦截,可以在切面逻辑中手动释放拦截,且可以在其后加入逻辑代码,该代码段会在方法执行后执行.

五种通知的执行顺序为： 前置通知→环绕通知→正常返回通知/异常返回通知→返回通知，

----------------------------
4.代码示例

1)切面类:
@Aspect
@Component
public class LogInterceptor {

    //advice 切点语法规则,方法体不执行,其他advice可以直接将该方法作为参数
    //按照该语法来执行拦截
    @Pointcut("execution(* com.pindao.nine.dao.UserDao.show* (..))")
    public void pointCut1(){
        System.out.println("this is pointCut");
    }

    //拦截在方法执行之前
    @Before("pointCut1()")
    public void before(){
        System.out.println("method start...");
    }


    //拦截在方法体执行之后,无论正常执行结束或异常结束
    @After("execution(* com.pindao.nine.dao.UserDao.show* (..))")
    public void after(){
        System.out.println("method end!");
    }

    //拦截在方法正常return之后
    @AfterReturning(value = "pointCut1()")
    public void afterReturn(){
        System.out.println("this is afterReturing pointcut!");
    }

    //拦截在方法抛出异常之后
    @AfterThrowing("pointCut1()")
    public void afterThrowing(){
        System.out.println("this is afterThrowing pointcut!");
    }

    //拦截在方法体执行之前,插入代码,需要手动释放拦截,并且可以在继续运行之后插入代码
    //ProceedingJoinPoint 参数只能在@Around下用,在其他advice下用,会抛出异常
    @Around("pointCut1()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("this is around pointcut start..!");
        //拦截结束,程序继续运行, 继续下一个拦截或者进行正常逻辑代码
        Object obj = pjp.proceed();
        System.out.println("this is around pointcut end..!");
        return obj;

    }
}

----
2)执行切面的bean():
@Component("userDao")
public class UserDaoImpl implements UserDao {
    public void showUser() {
        System.out.println("this is the real method start...");
//        int i = 1/0;
        System.out.println("this is the real method end");
        return 0;
    }
}

----
3)spring启用注解和切面配置 beans.xml:
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/aop
       http://www.springframework.org/schema/aop/spring-aop-2.5.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">

       <context:annotation-config />
       <context:component-scan base-package="com.pindao.nine" />
<!--aspectj是一个面向切面的框架,spring的aop,是基于aspectj来实现的-->       
       <aop:aspectj-autoproxy />

</beans>

----
4)手动启动 spring 容器,调用 showUser()方法:
@Test
public void testShowUserDao() throws Exception {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    UserDao userDao = (UserDao) context.getBean("userDao");
    int result = userDao.showUser();
    System.out.println(result);
}

-----
5)执行结果: 
--正常流程:
method start...
this is around pointcut start..!
this is the real method start...
this is the real method end
method end!
this is afterReturing pointcut!
this is around pointcut end..!
0

--方法体抛出异常流程:
method start...
this is around pointcut start..!
this is the real method start...
method end!
this is afterThrowing pointcut!
java.lang.ArithmeticException: / by zero ......

----

6)maven 依赖:

<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aop</artifactId>
    <version>2.5.6</version>
    <exclusions>
        <exclusion>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjrt</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-aspects</artifactId>
    <version>2.5.6</version>
    <exclusions>
        <exclusion>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjrt</artifactId>
        </exclusion>
        <exclusion>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.8.7</version>
</dependency>
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjrt</artifactId>
    <version>1.8.7</version>
</dependency>
<!-- cglib通过二进制的方式生成代理类 -->
<dependency>
    <groupId>cglib</groupId>
    <artifactId>cglib</artifactId>
    <version>3.2.0</version>
</dependency>