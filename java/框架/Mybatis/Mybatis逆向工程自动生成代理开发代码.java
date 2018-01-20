逆向工程：
 mybatis官方提供逆向工程，针对单表生成代码，包括基础的增删改查操作
 （mapper.java,mapper.xml,pojo）
 由数据库的表，生成java代码

 步骤：
 一：下载逆向工程文件
 二：创建java工程：

 generatorConfig.xml文件需要指定需要生成的表，生成的mapper文件位置，po文件位置
 运行GeneratorSqlmap.java程序，生成逆向工程文件。
 运行结束后，点击刷新，可以查看生成后的代码，再直接将代码拷贝到你的工程中。

详情请看，第二天课堂笔记第9点：逆向工程。

生成的 po 中，支持自定义查询语句 。如下：
查询名称为"笔记本3"的商品信息。
最后sql相当于:
select * from items where name='笔记本3' 
select 
//自定义条件查询
@Test
public void testSelectByExample() {
	ItemsExample itemsExample = new ItemsExample();
	//通过criteria构造查询条件
	ItemsExample.Criteria criteria = itemsExample.createCriteria();
	criteria.andNameEqualTo("笔记本3");
	//可能返回多条记录
	List<Items> list = itemsMapper.selectByExample(itemsExample);
	
	System.out.println(list);
	
}
生成的代码中，方法有不同，如下更新语句：
updateByPrimaryKey//对所有字段进行更新，需要先查询出来再更新
updateByPrimaryKeySelective//如果传入字段不空为才更新，在批量更新中使用此方法，不需要先查询再更新