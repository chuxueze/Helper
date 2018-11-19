1.方法描述:
2.实例:












---------------------------------------------------------------------------------------------------
1.方法描述:
查找与匹配
allMatch--检查是否匹配所有元素
anyMatch--检查是否至少匹配1个元素
noneMatch--检查是否没有匹配所有元素
findFirst--返回第1个元素
findAny--返回当前流中的任意元素
count--返回流中元素的总个数
max--返回流中最大值 
min--返回流中最小值






----------------------------------
2.实例:

	private static void test8(List<Student> list){
        //1.集合中，是否所有学生的年龄都为18
        boolean b1 = list.stream().allMatch(student -> student.getAge()==18);
        System.out.println(b1);
        //2.集合中，是否有任意一个学生年龄为18
        boolean b2 = list.stream().anyMatch(student -> student.getAge()==18);
        System.out.println(b2);
        //3.集合中，是否没有一个学生年龄为18
        boolean b3 = list.stream().noneMatch(student -> student.getAge()==18);
        System.out.println(b3);
        //4.返回集合第一个元素
        Optional optional = list.stream().findFirst();
        System.out.println(optional.get());
        //5.返回集合任意一个元素
        Optional optional2 = list.stream().findAny();
        System.out.println(optional2.get());
        //6.查找集合中最年老的学生
        Optional optional3 = list.stream().max(Comparator.comparing(Student::getAge));
        System.out.println(optional3.get());
        //7.查找集合中最小的年龄是多少
        Optional optional4 = list.stream().map(Student::getAge).min(Integer::compareTo);
        System.out.println(optional4.get());

    }

    private static List<Student> genList(){
        List<Student> list = Arrays.asList(
                new Student(1, "浩纯", 18),
                new Student(2, "丹", 17),
                new Student(3, "慧", 25)
        );
        return list;
    }

结果:

false
true
false
Student{stuId=1, name='浩纯', age=18, tallllll=0}
Student{stuId=1, name='浩纯', age=18, tallllll=0}
Student{stuId=3, name='慧', age=25, tallllll=0}
17