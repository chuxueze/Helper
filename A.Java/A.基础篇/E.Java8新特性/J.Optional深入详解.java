1.Optional 类介绍
2.实例









-----------------------------------------------------------------------------------------------------------------------------------------
1.Optional 类介绍

Optional 类是1个可以为 null 的容器对象。如果值存在则 isPresent() 方法会返回 true，调用 get() 方法会返回该对象。

Optional 是个容器：它可以保存类型T的值，或者仅仅保存 null。Optional 提供很多有用的方法，这样我们就不用显式进行空值检测。

Optional 类的引入很好的解决空指针异常。





--------------------------------------------------------------------
2.实例
不推荐此方式:惯有的面向对象的写法
public static void test2(){
    Optional optional = Optional.ofNullable("Hello World!");
    if(optional.isPresent()){
        System.out.println(optional.get());
    }
}


推荐方式:函数式表达式
public static void test3(){
    Optional optional = Optional.ofNullable("Hello World!");
    optional.ifPresent(item -> System.out.println(item));
}


应用:
	public static void test4(){
        School school = new School();
        school.setName("洪阳一中");

        Student student1 = new Student();
        student1.setName("张浩纯");
        Student student2 = new Student();
        student2.setName("菜丹慧");
        //如果为空，则返回新的空list，new ArrayList
        List<Student> list = Arrays.asList(student1,student2);
        Optional<School> optional = Optional.ofNullable(school);
        optional.map(sch -> school.getStudentList()).orElse(Collections.emptyList());

    }