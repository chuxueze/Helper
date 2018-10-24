1.使用匿名内部的前提
2.例子
3.好处






----------------------------------------------------------------------
1.使用匿名内部的前提
使用匿名内部类的前提是必须要基于接口或抽象类的应用






-----------------------------
2.例子
public class AnonymousInner {
    public static void main(String[] args) {
        fun(new Message() {
            //此括号内的内容，则为匿名内部类的实现
            //此处正常做法是定义一个实现类去实现message接口，而现在，去除Impl，直接在内部实现。此为匿名内部类
            @Override
            public void print() {
                System.out.println("匿名内部类实现");
            }
        });
    }

    public static void fun(Message message) {
        message.print();
    }
}

interface Message {
    void print();
}


-----------------------------
3.好处
帮助用户减少了类的定义
如果不用匿名内部类，则上面的代码需要写成以下的样子。
需要增加多1个实现类
public class Inner {
    public static void main(String[] args) {
        fun(new MsgImpl());
    }
    static void fun(Msg msg){
        msg.printMsg();
    }
}

public interface Msg {
    public void printMsg();
}

public class MsgImpl implements Msg {
    @Override
    public void printMsg() {
        System.out.println("匿名内部类实现");
    }
}