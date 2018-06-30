例子：
将关联查询的列映射到一个List<pojo>中
可以合理用 extends
<resultMap>
	<Collection></Collection>
</resultMap>
例子的业务：
	一个订单中，有多个明细，需要
	查找出订单中的用户和明细信息，包装在订单类中。
思路：
1.定义一个订单 pojo Orders
此 pojo 包含一个 User 类，包含一个 OrdersDetails List 列表
2.定义一个接口
此接口返回一个 Orders 包装类
3.定义一个 Mapper.xml
包含 Orders 的基本信息
包含一个<association></association>:用于包含User类信息
包含一个<collection></collection>:用于包含 oredersDetails 订单明细列表

代码如下：
------------pojo
Orders.java
public class Orders {
	private String id;
	private String user_id;
	private String number;
	private String createtime;
	private User user;
	private List<OrdersDetails> oredersDetailsList;
}
public class OrdersDetails {
	private String itemsId;
	private String itemsNum;
	private String orderdetailId;
}
-----------Mapper.java
public List<Orders> queryOrederUserDetailSMap();
-----------Mapper.xml
<select id="queryOrederUserDetailSMap" resultMap="OrdersMapDetails">
	SELECT
		a.id,
		a.user_id,
		a.number,
		a.createtime,
		b.username,
		b.birthday,
		(
			CASE
			WHEN b.sex = '1' THEN
				'男'
			WHEN b.sex = '2' THEN
				'女'
			ELSE
				'不知性别'
			END
		) sex,
		c.id orderdetailId,
		c.items_id,
		c.items_num,
		b.address
	FROM
		orders a,
		USER b,
		orderdetail c
	WHERE
		a.user_id = b.id
		and a.id = c.orders_id		
</select>

<resultMap type="cn.itcast.mybatis.po.Orders" id="OrdersMap">
	<id column="id" property="id"/>
	<result column="id2" property="user_id"/> 
	<result column="number" property="number"/>
	<result column="createtime" property="createtime"/>
	<association property="user" javaType="cn.itcast.mybatis.po.User">
		<id column="id" property="id"/>
		<result column="username" property="username"/>
		<result column="birthday" property="birthday"/>
		<result column="sex" property="sex"/>
		<result column="address" property="address"/>
	</association>
</resultMap>
<resultMap type="cn.itcast.mybatis.po.Orders" id="OrdersMapDetails" extends="OrdersMap">
//这里的extends类似于java的继承
	<collection property="oredersDetailsList" ofType="cn.itcast.mybatis.po.OrdersDetails">
		<id column="orderdetailId" property="orderdetailId"/>
		<result column="items_id" property="itemsId"/>
		<result column="items_num" property="itemsNum"/>
	</collection>
</resultMap>
--------------test测试类：
/**
 * 一对多查询
 * @throws Exception
 */
@Test
public void testQueryOrederUserDetailSMap() throws Exception {
	
	SqlSession sqlSession = sqlSessionFactory.openSession();
	
	//创建ItemsMapper对象，mybatis自动生成mapper代理对象
	OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);
	List<Orders> list = ordersMapper.queryOrederUserDetailSMap();

	System.out.println("list:"+list);
	
}	
//查询出来的数据可以使用双层循环去遍历