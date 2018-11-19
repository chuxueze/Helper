1.简介:
2.例子:













-------------------------------------------------------------------------------------------
1.简介:
映射:
map -- 接收 Lambda
1)将元素转换为其他形式
2)提取信息
接收1个函数作为参数，该函数会被应用到每个元素上，并将其映射成1个新的元素。

flatMap -- 接收1个函数作为参数，将流中的每个值都换成另1个流，然后把所有流连接成1个流
相当于多个流的合并

---------------------------------------------

2.例子:
	private static void test7(){
        List<Student> list = Arrays.asList(
                new Student(1, "浩纯", 18),
                new Student(2, "丹", 17),
                new Student(3, "慧", 25)
        );
        //list.stream().map(student -> student.getName()).forEach(System.out::println);
        //list.stream().map(student -> student.getStuId()*10).forEach(System.out::println);

        list.stream().map(Student::getName).forEach(System.out::println);
        list.stream().map(Student::getStuId*10).forEach(System.out::println);
    }

浩纯
丹
慧
10
20
30