 多个子类可能同时继承一个父类（基类）
一个基类包含其所有的子类所共享的特性和行为。


理解：
  当多个类同时拥有一些共同的属性或行为时，可以将这些共同的属性或行为抽取出来，形成一个基类。
这样做的好处是，利于程序的扩展，节省代码的编写。

例如：
  回收垃圾时，垃圾拥有的共有属性是重量，价值等。
而不同垃圾的销毁方式不同，如瓶子被分解，铝被熔化，铁被磁化等。
一个垃圾基类，可以导出更多具体的垃圾子类。


  如果某些子类不想用基类中的某些方法或属性，可以自定义与基类相同的方法或属性，这种操作称之为覆盖。
子类可以定义自己的方法或属性，而不受基类的影响。

基类：
public abstract class AbstractModel<T> {
	// 创建人ID
	protected String createBy;
	// 创建人
	protected String creator;
	// 更新人
	protected String updateBy;
	// 创建时间
	@XmlTransient
	protected Date createTime;
	// 更新时间
	protected Date updateTime;
	// 创建人组织ID
	protected String createOrgId;
	//是否已删除
	protected int isDelete;
}
子类：
public class InitBoEnt extends AbstractModel<String>{
	
	/**
	* 主键
	*/
	protected String id; 
	
	/**
	* 属性名称
	*/
	protected String name;
}