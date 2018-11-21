1.Optional 类介绍
2.用法
3.实例









-----------------------------------------------------------------------------------------------------------------------------------------
1.Optional 类介绍

Optional 类是1个可以为 null 的容器对象。如果值存在则 isPresent() 方法会返回 true，调用 get() 方法会返回该对象。

Optional 是个容器：它可以保存类型T的值，或者仅仅保存 null。Optional 提供很多有用的方法，这样我们就不用显式进行空值检测。

Optional 类的引入很好的解决空指针异常。





--------------------------------------------------------------------
2.用法
private static void test22(){
    List<Integer> list = ListFor.addDataToList(10);
    Optional<List> optional2 = Optional.of(list);
    List list2 = optional2.get();
    list2.forEach(System.out::println);
    //此方法也报了空指针异常，只是报错的地方是在optianl3。
    //好处在于：如果我们用optional去接收对象，则可以快速定位到报异常位置为optional
    //只有容易出现空指针异常的地方才推荐使用optional。
    Optional<List> optional3 = Optional.of(null);
    List list3 = optional3.get();
    list3.forEach(System.out::println);

    Optional<List> optional4 = Optional.ofNullable(null);
    List list4 = optional4.get();
    list4.forEach(System.out::println);
}



--------------------------------------------------------------------
3.实例
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