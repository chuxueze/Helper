1.方法引用简介:
2.方法引用实例:
3.构造器引用实例:









--------------------------------------------------------------------------------
1.方法引用简介:
Lambda 体中的内容有方法已经实现了，我们就可以使用“方法引用”
方法引用实际上是个 Lambda 表达式的1种语法糖。
我们可以看作是1个函数指针，结合 Lambda 表达式去使用

分类:
1.1:方法引用
1)类名::静态方法名
2)引用名(对象名::实例方法名)
3)类名::实例方法名

注意:
Lambda 体中调用方法的参数列表与返回值类型，要与函数式接口中的抽象方法的函数列表和返回值类型保持1致



1.2:构造器引用
格式:
ClassName::new

--------------------------------------
2.方法引用实例:
可以看作是 Lambda 表达式的另外1种表现形式


//实例1
从 fieldsSetTypeDOList 中获取 modelType 生成新的 List
List<Integer> modelTypeList = fieldsSetTypeDOList.stream().map(fieldsSetTypeDO -> fieldsSetTypeDO.getId()).collect(Collectors.toList());
如下为方法引用
List<Integer> modelTypeList = fieldsSetTypeDOList.stream().map(FieldsSetTypeDO::getId).collect(Collectors.toList());


//实例2
private static void test17(){

    PrintStream ps = System.out;
    Consumer<String> con = x-> ps.println(x);
    con.accept("000000000000000000000000000000");

    PrintStream ps1 = System.out;
    Consumer<String> con1 = ps1::println;
    con1.accept("11111111111111111111111111111");

}


//实例3
private static void test18(){
    Person person = new Person("张浩纯",18);

    Supplier supplier = () -> person.getName();
    System.out.println(supplier.get());

    Supplier supplier1 =  person::getAge;
    System.out.println(supplier1.get());
}
//输出
张浩纯
18


------------------
类名::实例方法名

如果 Lambda 参数列表中的第1参数是实例方法的调用者，而第2个参数是实例方法的参数时，可以使用。

BiPredicate<String,String> biPredicate1 = (x,y)->x.equals(y);
BiPredicate<String,String> biPredicate2 = String::equals;

x 为调用者，y 为方法参数。




--------------------------------------
3.构造器引用实例:
调用的是哪个构造器，取决于，函数表达式与构造器方法的参数列表 。
例如:
Supplier 不接收函数，返回1个值。则调用无参构造函数

Supplier<Person> supplier = Person::new;
Function<String,Person> function = Person::new;
BiFunction<String,Integer,Person> function1 = Person::new;

这三个分别对应下面三个构造器方法
public Person(){
        
}
    
public Person(String name){
    this.name = name;
}
    
public Person(String name, Integer age) {
    this.name = name;
    this.age = age;
}


BiFunction<String,Integer,Person> function1 = Person::new;
入参:String、Integer
出参:Person