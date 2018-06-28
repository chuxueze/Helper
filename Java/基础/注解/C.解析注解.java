解释注解:

概念:
    通过反射获取类、函数或成员上的运行时注解信息，
从而实现动态控制程序运行的逻辑。



@Log(desc = "this is a test case",author = "Haochun Zhang",age=18)
public class LogTest {
    @Log(desc = "this is a test case",author = "Haochun Zhang",age=18)
    public static void eyeColor(){
        System.out.print("红色");
    }

    public static void main(String[] args) {
        LogTest.eyeColor();
    }
}



public class ParseAnnotation {
    public static void testClass() throws ClassNotFoundException {
        Class c = Class.forName("com.target.LogTest");
        c.isAnnotationPresent(Log.class);//查找类上是否有Log这个注解,找到类注解
        if(c != null){
            Log log = (Log)c.getAnnotation(Log.class);
            System.out.println(log.age());
            System.out.println(log.author());
            System.out.println(log.desc());
        }


    }
    public static void testMethod() throws ClassNotFoundException {
        Class c = Class.forName("com.target.LogTest");
        c.isAnnotationPresent(Log.class);//查找类上是否有Log这个注解,找到类注解
        Method[]  methods = c.getMethods();
        for(Method m : methods){
            //方法上是否有对应的注解
            boolean isEixtM = m.isAnnotationPresent(Log.class);
            if(isEixtM){
                Log logM = (Log)m.getAnnotation(Log.class);
                System.out.println(logM.age());
                System.out.println(logM.author());
                System.out.println(logM.desc());
            }
            //遍历方法的所有注解
            //方法的所有注解中是否有Log这个注解
            Annotation[] Annotations = m.getAnnotations();
            for(Annotation a : Annotations){
                if(a instanceof Log){
                    Log logM2 = (Log)a;
                    System.out.println(logM2.age());
                    System.out.println(logM2.author());
                    System.out.println(logM2.desc());
                }
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //ParseAnnotation.testClass();
        ParseAnnotation.testMethod();
    }
}
