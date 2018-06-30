1.输入映射：

2.传递pojo包装类型：Vo
Vo 下可以包装所有需要的查询条件，将需要的变量从前台到后台一条龙服务。
例如，可以包装不同的类对象，类对象里又有自己的属性。
hashMap同理
parameterType设置为hashMap，再将key值传入
适用于综合查询，与子查询，多表查询等操作。

如：
Item.java:
public class Item {
	private int itemsId;
	private String itemsName;
	private int price;
	private String detail;
	private Date cretetime;
	public int getItemsId() {
		return itemsId;
	}
	public void setItemsId(int itemsId) {
		this.itemsId = itemsId;
	}
	public String getItemsName() {
		return itemsName;
	}
	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Date getCretetime() {
		return cretetime;
	}
	public void setCretetime(Date cretetime) {
		this.cretetime = cretetime;
	}
}
ItemsVo.java
public class ItemsVo {
	private Item item;
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
}
ItemsMapper.xml:
<mapper namespace="cn.itcast.mybatis.mapper.ItemsMapper">
	<select id="selectById" parameterType="ItemsVo"  resultType="Item">
		select * from items
		<where>
			<if test="item!=null">
				<if test="item.itemsId!=null and item.itemsId!=''">
					and items.id = #{item.itemsId}
					<!-- and items.id = ${item.itemsId} -->
				</if>
			</if>
		</where>
	</select>
</mapper>

@Test
public void testFindUserList() throws Exception {
	
	SqlSession sqlSession = sqlSessionFactory.openSession();
	
	//创建ItemsMapper对象，mybatis自动生成mapper代理对象
	ItemsMapper itemsMapper = sqlSession.getMapper(ItemsMapper.class);
	
	//创建包装对象，设置查询条件
	ItemsVo itemsVo = new ItemsVo();
	Item item = new Item();
	item.setItemsId(1);
	itemsVo.setItem(item);
	item = itemsMapper.selectById(itemsVo);
	
	System.out.println(itemsVo);
}