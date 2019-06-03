1.归约(reduce):
2.收集(averagingDouble,summingDouble):
3.分组:(groupingBy)







---------------------------------------------------------------------------------------------------------
1.归约:

	private static void test9(){
        //1.归约
        //可以将流中元素反复结合起来，得到一个值 ·
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer sum = list.stream().reduce(0,(x,y)->x+y);
        System.out.println(sum);

        List<Student> stuList = GenStream.genList();
        Optional<Integer> optional = stuList.stream().map(Student::getAge).reduce(Integer::sum);
        System.out.println(optional.get());
    }



--------------------------------------------------
2:收集:

	private static void test10(){
        //1.收集
        //collect--将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
        List<Student> list = GenStream.genList();
        //2.总数
        Long count = list.stream().collect(Collectors.counting());
        System.out.println(count);
        //3.平均值
        Double age = list.stream().collect(Collectors.averagingDouble(Student::getAge));
        System.out.println(age);
        //4.总和
        Double sumAge = list.stream().collect(Collectors.summingDouble(Student::getAge));
        System.out.println(sumAge);


    }




    private static void test12(){
        List<Student> list = GenStream.genList();
        String names = list.stream().map(Student::getName).collect(Collectors.joining(","));
        System.out.println(names);
    }

    输出:浩纯,丹慧,慧慧,丹丹

--------------------------------------------------
3.分组:

	private static void test11(){
        List<Student> list = GenStream.genList();
        //按年龄分组
        Map<Integer,List<Student>> stuMap = list.stream().collect(Collectors.groupingBy(Student::getAge));
        System.out.println(stuMap);
    }



{
 17=[Student{stuId=2, name='丹慧', age=17, tallllll=0}], 
 18=[Student{stuId=1, name='浩纯', age=18, tallllll=0}], 
  5=[Student{stuId=3, name='慧慧', age=5, tallllll=0}, 
     Student{stuId=4, name='丹丹', age=5, tallllll=0}]
 }

多级分组




---------------------------------------------------------------
public List<Integer> getNumber(){
    List<Integer> numList = Arrays.asList(1,2,3,4,5,6,7,8,9);
    //this::handleNumberMsg 流中map的数据可以通过调用方法进行处理
    List<Integer> handleNumList = numList.stream().map(this::handleNumberMsg).collect(Collectors.toList());
    return handleNumList;
}
private Integer handleNumberMsg(Integer num){
    //do more things
    num += 1;
    return num;
}