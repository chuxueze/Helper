1.Predicate 接口
2.实例








---------------------------------------------------------------------------------------------------------------------
1.Predicate 接口
接受1个输入参数，返回1个布尔值结果。
@FunctionalInterface
public interface Predicate<T> {

	boolean test(T t);

	default Predicate<T> and(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }

    default Predicate<T> negate() {
        return (t) -> !test(t);
    }

    default Predicate<T> or(Predicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }

    static <T> Predicate<T> isEqual(Object targetRef) {
        return (null == targetRef)
                ? Objects::isNull
                : object -> targetRef.equals(object);
    }

}





------------------------------------------------------------------
2.实例

2.1:判断输入的字符串是否大于10
String s = "asdf dqwerqwerad";
Predicate<String> predicate = s1 -> s1.length() > 10;
boolean flag = ListFor.test14(s, predicate);
System.out.println(flag);

private static boolean test14(String s, Predicate<String> predicate) {
    return predicate.test(s);
}

---------------------------------
2.2:将集合中为偶数的值输出
List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0);
ListFor.test15(numList, val -> val % 2 == 0);

private static void test15(List<Integer> list, Predicate<Integer> predicate) {
    list.forEach(l -> {
        if (predicate.test(l)) {
            System.out.println(l);
        }
    });
}

---------------------------------
2.3:其他默认的方法
找出列表中满足两个函数的值 val -> val % 2 == 0, val -> val > 4

ListFor.test16(numList, val -> val % 2 == 0, val -> val > 4);

private static void test16(List<Integer> list, Predicate<Integer> predicate1, Predicate<Integer> predicate2) {
    list.forEach(l -> {
        if (predicate1.and(predicate2).test(l)) {
            System.out.println(l);
        }
    });
}

--------------
函数条件取反

ListFor.test16(numList, val -> val % 2 == 0, val -> val > 4);

private static void test16(List<Integer> list, Predicate<Integer> predicate1, Predicate<Integer> predicate2) {
    list.forEach(l -> {
        if (predicate1.and(predicate2).negate().test(l)) {
            System.out.println(l);
        }
    });
}