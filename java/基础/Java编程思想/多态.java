public interface Foo {
    public String enter();
}

public class Son1 implements Foo{
    @Override
    public String enter() {
        return "编辑器的enter功能是换行";
    }
}

public class Son2 implements Foo{
    @Override
    public String enter() {
        return "聊天软件的enter功能是发送消息";
    }
}

public class Main {
    public static void main(String[] args) {
        Foo son1 = new Son1();
        Foo son2 = new Son2();
        System.out.println(son1.enter());
        System.out.println(son2.enter());
    }
}

接口定义做什么，实现类定义怎么做。