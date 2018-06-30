	
	private SqlSessionFactory sqlSessionFactory;
	//构造注入
	public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public User findUserById(int id) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		User user = sqlSession.selectOne("test.findUserById", id);

		// 释放资源
		sqlSession.close();
		return user;

	}
原理：
//通过sqlSession和mapper.xml建立连接
里面有selectOne，selectList，seelctMap等方法,可以通过反射，将方法对应上。


传统中：
mapper代理开发规范：
1.在mapper.xml中namespace等于mapper接口地址
mapper等一个dao的接口，而mapper.xml相当于一个daoImpl
<mapper namespace="cn.itcast.mybatis.mapper.UserMapper"></mapper>
2.接口的方法名和mapper.xml中的id需要保持一致
3.mapper中的入参和出参，需要和mapper.xml中的parameterType和resultType一致
//根据id查询用户信息
mapper中：
public User findUserById(int id) throws Exception;
mapper.xml中：
<select id="findUserList" parameterType="cn.itcast.mybatis.po.UserQueryVo" 
			resultType="cn.itcast.mybatis.po.UserCustom">
	SELECT * FROM USER
</select>