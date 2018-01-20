泛型机制用到了向下转型，
当定义一个具有泛型的容器时，
List<String> list = new ArrayList<String>();
Map<String,List> map = new HashMap<String,List>();
它知道自己所保存的对象类型，从而不需要向下转型以及消除犯错误的可能。

如果不定义泛型
List list = new ArrayList();
Map map = new HashMap();
  由于容器只存储Object，所以当将对象引用置入容器时，它必须被向上转型为Object，因此它会丢失身份。
当它取回时，就获取了一个对Object对象的引用，而不是对置入时的那个类型的对象的引用。