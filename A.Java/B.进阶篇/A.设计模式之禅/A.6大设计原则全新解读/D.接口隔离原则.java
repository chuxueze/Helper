1.接口概念:
2.隔离概念:
3.规范约束:









--------------------------------------------------------------------------------------------------------------------
1.接口概念:
    实例接口（Object Interface），在 Java中 声明1个类，然后用 new 关键字产生1个实例，
它是对1个类型的事物的描述，这是1种接口。比如你定义 Person 这个类，然后使用
Person zhangSan=new Person()
产生了1个实例，这个实例要遵从的标准就是 Person 这个类，Person 类就是 zhangSan 的接口。

实例接口:
也就是说，Java 中的类也是1种接口。

类接口:
Java 中经常使用的 interface 关键字定义的接口。




-----------------------------------------------------------
2.隔离概念:

2.1:
Clients should not be forced to depend upon interfaces that they do not use
（客户端不应该依赖它不需要的接口。）

   客户端需要什么接口就提供什么接口，把不需要的接口剔除掉，那就需要对接口进行细化，
保证其纯洁性。



2.2:
The dependency of one class to another one should depend on the smallest possible interface.
（类间的依赖关系应该建立在最小的接口上。）

　　要求“尽量使用多个专门的接口”
　　就是指提供给每个模块的都应该是单１接口，提供给几个模块就应该有几个接口，而不是建立１个庞大的臃肿的接口，
容纳所有的客户端访问。



-----------------------------------------------------------
3.规范约束:

    接口隔离原则是对接口进行规范约束，其包含以下4层含义
  
  3.1:接口要尽量小
  小也要有限度，那么限度的依据就是:
根据接口隔离原则拆分接口时，首先必须满足单1职责原则。

  3.2:接口要高内聚
