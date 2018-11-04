1.Supplier 接口
2.例子









----------------------------------------------------------------------------------------------------------------------------------------
1.Supplier 接口
@FunctionalInterface
public interface Supplier<T> {

    T get();

}

无参数，返回1个结果。
Supplier: 提供者


----------------------------------------------------------------------
2.例子
//输出字符串
public class Supper {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> "Hello World!!!";
        System.out.println(supplier.get());
    }
}

//通过 Supplier 创建对象
public static void test2(){
    Supplier<Student> studentSupplier = () -> new Student();
    System.out.println(studentSupplier.get().getName());
    Supplier<Student> supplier = Student::new;
    System.out.println(supplier.get().getAge());
}