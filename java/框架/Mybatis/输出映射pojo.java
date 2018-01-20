1.输出映射：
  1.1：resultMap
	如果查询出来的列名和pojo的属性名不一致，通过定义一个resultMap对列名和pojo属性名之间作一个映射关系。
	1、定义resultMap
	2、使用resultMap作为statement的输出映射类型

  1.2：resultType
  	使用resultType进行输出映射，只有查询出来的列名和pojo中的属性名一致，该列才可以映射成功。
	如果查询出来的列名和pojo中的属性名全部不一致，没有创建pojo对象。
	只要查询出来的列名和pojo中的属性有一个一致，就会创建pojo对象。

resultMap:可以实现延迟加载
resultType:无法实现延迟加载






resultType:
若返回的是一个list，指定resultType为返回的pojo类，例子如下：
public interface ItemsMapper {
	public List<Item> selectAll(Item item);
}

<select id="selectAll" parameterType="Item"  resultType="Item">
	select * from items
</select>

@Test
public void testSelectList() throws Exception {
	
	SqlSession sqlSession = sqlSessionFactory.openSession();
	
	//创建ItemsMapper对象，mybatis自动生成mapper代理对象
	ItemsMapper itemsMapper = sqlSession.getMapper(ItemsMapper.class);
	
	//创建包装对象，设置查询条件
	ItemsVo itemsVo = new ItemsVo();
	Item item = new Item();
	List<Item> list =  new ArrayList<Item>();
	list = itemsMapper.selectAll(item);
	itemsVo.setItemList(list);
	System.out.println(itemsVo.getItemList());
}



resultMap:
用于对 dto 和 数据库字段的映射，使两者保持相同字段名，以便映射成功

1.定义resultMap:
<!-- 定义resultMap
将SELECT id id_,username username_ FROM USER 和User类中的属性作一个映射关系

type：resultMap最终映射的java对象类型,可以使用别名
id：对resultMap的唯一标识(如果是两个列组成唯一标识，则需要配置多个id)
 -->
 <resultMap type="user" id="userResultMap">
 	<!-- id表示查询结果集中唯一标识 
 	column：查询出来的列名
 	property：type指定的pojo类型中的属性名
 	最终resultMap对column和property作一个映射关系 （对应关系）
 	-->
 	<id column="id_" property="id"/>
 	<!-- 
 	result：对普通名映射定义
 	column：查询出来的列名
 	property：type指定的pojo类型中的属性名
 	最终resultMap对column和property作一个映射关系 （对应关系）
 	 -->
 	<result column="username_" property="username"/>
 </resultMap>

2.
<select id="findUserByIdResultMap" parameterType="int" resultMap="userResultMap">
	SELECT id id_,username username_ FROM USER WHERE id=#{value}
</select>
3.
//根据id查询用户信息，使用resultMap输出
public User findUserByIdResultMap(int id) throws Exception;




---------------
例子3： 一个pojo包含另外一个pojo，用resultMap怎么做。
<resultMap></resultMap>
---------------------
<select id="queryOrdersUserMap" resultMap="OrdersMap">
	SELECT
		a.id,
		a.user_id id2,
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
		b.address
	FROM
		orders a,
		USER b
	WHERE
		a.user_id = b.id
</select>
<resultMap type="cn.itcast.mybatis.po.Orders" id="OrdersMap">
	<id column="id" property="id"/>
	<result column="id2" property="user_id"/> 
	<result column="number" property="number"/>
	<result column="createtime" property="createtime"/>
	//association指定一个pojo
	//column指定sql查询语句里的名字，property指定pojo里的数据
	<association property="user" javaType="cn.itcast.mybatis.po.User">
		<id column="id" property="id"/>
		<result column="username" property="username"/>
		<result column="birthday" property="birthday"/>
		<result column="sex" property="sex"/>
		<result column="address" property="address"/>
	</association>
</resultMap>
------------------
//pojo
public class Orders {
	private String id;
	private String user_id;
	private String number;
	private String createtime;
	private User user;
}
------------------
@Test//测试
public void testQueryOrdersUserMap() throws Exception {
	
	SqlSession sqlSession = sqlSessionFactory.openSession();
	
	//创建ItemsMapper对象，mybatis自动生成mapper代理对象
	OrdersMapper ordersMapper = sqlSession.getMapper(OrdersMapper.class);
	List<Orders> list = ordersMapper.queryOrdersUserMap();
	
	for(int i = 0,len = list.size();i<len;++i){
		Orders dto = new Orders();
		dto =  list.get(i);
		System.out.println("dto"+dto.getId());
		System.out.println("dto"+dto.getUser());
	}

}	


需要注意：
mapper.xml中的查询语句，select 出来的字段需要和 resultType 的入参对应才能映射成功。
所以一般如果不一致，会将select的字段取别名，对应出差的类型字段。


生成的动态代理对象中是根据mapper方法的返回值类型确定是调用selectOne(返回单个对象调用)
还是selectList （返回集合对象调用 ）.


/x5-mini-platform/src/main/java/com/hotent/mobile/controller/BpmController.java