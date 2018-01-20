查询缓存:
mybatis提供的查询缓存，用于减轻数据库压力，提高数据库性能。
有一级缓存和二级缓存。

一级缓存是SqlSession级别的缓存。在操作数据库时需要构造 sqlSession对象，在对象中有一个数据结构（HashMap）用于存储缓存数据。不同的sqlSession之间的缓存数据区域（HashMap）是互相不影响的。

二级缓存是mapper级别的缓存，多个SqlSession去操作同一个Mapper的sql语句，多个SqlSession可以共用二级缓存，二级缓存是跨SqlSession的。

为什么要用缓存？
如果缓存中有数据就不用从数据库中获取，大大提高系统性能。
------------------------------

第一次发起查询用户id为1的用户信息，先去找缓存中是否有id为1的用户信息，如果没有，从数据库查询用户信息。
得到用户信息，将用户信息存储到一级缓存中。

如果sqlSession去执行commit操作（执行插入、更新、删除），清空SqlSession中的一级缓存，这样做的目的为了让缓存中存储的是最新的信息，避免脏读。

第二次发起查询用户id为1的用户信息，先去找缓存中是否有id为1的用户信息，缓存中有，直接从缓存中获取用户信息。

--
// 一级缓存测试
@Test
public void testCache1() throws Exception {
	SqlSession sqlSession = sqlSessionFactory.openSession();// 创建代理对象
	UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
	// 下边查询使用一个SqlSession
	// 第一次发起请求，查询id为1的用户
	User user1 = userMapper.findUserById(1);
	System.out.println(user1);
	// 如果sqlSession去执行commit操作（执行插入、更新、删除），清空SqlSession中的一级缓存，这样做的目的为了让缓存中存储的是最新的信息，避免脏读。
	// 更新user1的信息
	user1.setUsername("测试用户22");
	userMapper.updateUser(user1);
	// //执行commit操作去清空缓存
	sqlSession.commit();
	// 第二次发起请求，查询id为1的用户
	User user2 = userMapper.findUserById(1);
	System.out.println(user2);
	sqlSession.close();
}


正式开发，是将mybatis和spring进行整合开发，事务控制在service中。
一个service方法中包括 很多mapper方法调用。

service{
	//开始执行时，开启事务，创建SqlSession对象
	//第一次调用mapper的方法findUserById(1)
	
	//第二次调用mapper的方法findUserById(1)，从一级缓存中取数据
	//方法结束，sqlSession关闭
}

如果是执行两次service调用查询相同 的用户信息，不走一级缓存，因为session方法结束，sqlSession就关闭，一级缓存就清空。


------------------------------------------------------------
二级缓存：

首先开启mybatis的二级缓存。

sqlSession1去查询用户id为1的用户信息，查询到用户信息会将查询数据存储到二级缓存中。

如果SqlSession3去执行相同 mapper下sql，执行commit提交，清空该 mapper下的二级缓存区域的数据。

sqlSession2去查询用户id为1的用户信息，去缓存中找是否存在数据，如果存在直接从缓存中取出数据。

二级缓存与一级缓存区别，二级缓存的范围更大，多个sqlSession可以共享一个UserMapper的二级缓存区域。
UserMapper有一个二级缓存区域（按namespace分） ，其它mapper也有自己的二级缓存区域（按namespace分）。
每一个namespace的mapper都有一个二缓存区域，两个mapper的namespace如果相同，这两个mapper执行sql查询到数据将存在相同 的二级缓存区域中。

--

开启二级缓存：

mybaits的二级缓存是mapper范围级别，除了在SqlMapConfig.xml设置二级缓存的总开关，还要在具体的mapper.xml中开启二级缓存。

一：
在核心配置文件SqlMapConfig.xml中加入
<setting name="cacheEnabled" value="true"/>
如：
<settings>
	<!-- 打开延迟加载 的开关 -->
	<setting name="lazyLoadingEnabled" value="true"/>
	<!-- 将积极加载改为消极加载即按需要加载 -->
	<setting name="aggressiveLazyLoading" value="false"/>
	<!-- 开启二级缓存 -->
	<setting name="cacheEnabled" value="true"/>
</settings>

二：
在UserMapper.xml中开启二缓存，UserMapper.xml下的sql执行完成会存储到它的缓存区域（HashMap）。
<!-- 开启本mapper的namespace下的二缓存
type：指定cache接口的实现类的类型，mybatis默认使用PerpetualCache
要和ehcache整合，需要配置type为ehcache实现cache接口的类型
 -->
<cache type="org.mybatis.caches.ehcache.EhcacheCache"/>
还有其他属性，类似于 flushInterval 可以设置刷新缓存的频率，用到的时候，需要再去查看说明文档。


详情请看第二天课堂笔记：7.3
为了将缓存数据取出执行反序列化操作，因为二级缓存数据存储介质多种多样，不一样在内存。

// 二级缓存测试
@Test
public void testCache2() throws Exception {
	SqlSession sqlSession1 = sqlSessionFactory.openSession();
	SqlSession sqlSession2 = sqlSessionFactory.openSession();
	SqlSession sqlSession3 = sqlSessionFactory.openSession();
	// 创建代理对象
	UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
	// 第一次发起请求，查询id为1的用户
	User user1 = userMapper1.findUserById(1);
	System.out.println(user1);
	
	//这里执行关闭操作，将sqlsession中的数据写到二级缓存区域
	sqlSession1.close();
	
	
	//使用sqlSession3执行commit()操作
	UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
	User user  = userMapper3.findUserById(1);
	user.setUsername("张明明");
	userMapper3.updateUser(user);
	//执行提交，清空UserMapper下边的二级缓存
	sqlSession3.commit();
	sqlSession3.close();
	
	
	UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
	// 第二次发起请求，查询id为1的用户
	User user2 = userMapper2.findUserById(1);
	System.out.println(user2);
	sqlSession2.close();
}

<select id="findOrderListResultMap" resultMap="ordersUserMap" useCache="false">

总结：针对每次查询都需要最新的数据sql，要设置成useCache=false，禁用二级缓存。


<insert id="insertUser" parameterType="cn.itcast.mybatis.po.User" flushCache="true">

总结：一般下执行完commit操作都需要刷新缓存，flushCache=true表示刷新缓存，这样可以避免数据库脏读。
使用到缓存的时候，后台不会打印sql日志。

应用场景：
1.查询非常耗时的sql语句。
2.数据不会经常变化的sql语句。
3.二级缓存不适应用于细粒度的数据级别。
（如，用户下订单，保存在缓存中，如果缓存中已经积攒到10000个订单缓存的时候，
一个增删或改操作，就会将缓存清除，这时候还不如不用缓存。
解决方法是，三级缓存，只清除对应变化的缓存，不清除缓存区的全部缓存。）