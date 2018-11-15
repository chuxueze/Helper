1.Stream 的中间操作:
2.筛选与分片:
3.例子:










-------------------------------------------------------------------------------------
1.Stream 的中间操作:
  多个中间操作可以连接起来形成1个流水线，除非流水线上触发终止操作，否则中间操作不会执行任何
的处理。而在终止操作时1次全部处理，称为 “惰性求值”。


----------------------------------------
2.筛选与分片:

filter:接收 Lambda,从流中排除某些元素
limit:截断流，使其元素不超过给定数量
skip(n):跳过元素，返回1个扔掉了前N个元素的流。若流中元素不足N个，则返回1个空流，与 limit(n) 互补
distinct:筛选，通过流所生成元素的 hashCode() 和 equals()去除重复元素
         注意使用 distinct 时，实体可能需要重新生成 hashCode 和 equals() 方法




----------------------------------------
3.例子:

	private void test6() {
        List<Student> list = Arrays.asList(
                new Student(1, "浩纯", 18),
                new Student(2, "丹", 17),
                new Student(3, "慧", 25)
        );
        //中间不会执行任何操作，直到终止操作sout时，才会一次性执行全部内容
        //查询list中，age>=20，去重，跳过前2个数据的10条数据
        list.stream()
                .filter((stu) -> stu.getAge() <= 18)
                .skip(2)
                .limit(10)
                .distinct()
                .forEach(System.out::println);
    }

