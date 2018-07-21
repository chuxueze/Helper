Pointcut expression 切面表达式
1.designators
2.wildcards
3.operators







------------------------------------------
1.designators 指示器
如:execution()
1)匹配方法:
execution()
2)匹配注解:
@target()
@args()
@within()
@annotation()
3)匹配包/类型
within()	
4)匹配对象
this()
bean()
target()
5)匹配参数
@args()


注解详说
@within()
//匹配 ProductService 类里头的所有方法
@Pointcut("within(com.imooc.service.ProductService)")
public void mathType(){}

//匹配 com.imooc 包及子包下所有类的方法
@Pointcut("within(com.imooc..*)")
public void matchPackage(){}



----
2.wildcards 通配符
如:*、+、..
*:匹配任意数量的字符
+:匹配指定类及其子类
..:一般用于匹配任意数的子包或参数





----
3.operators 运算符
如: &&、 ||、 ！