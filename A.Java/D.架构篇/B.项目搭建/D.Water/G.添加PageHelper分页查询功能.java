1.概念
2.步骤
3.代码示例







--------------------------------------------------
1.概念

PageHelper是一款好用的开源免费的Mybatis第三方物理分页插件

支持多种分页方式

支持常见的RowBounds(PageRowBounds)，PageHelper.startPage 方法调用，Mapper 接口参数调用

---------------------
2.步骤
1)添加依赖
<dependency>
   <groupId>com.github.pagehelper</groupId>
   <artifactId>pagehelper-spring-boot-starter</artifactId>
   <version>1.2.5</version>
</dependency>

--------
2)添加 PageHelper 配置
在 application.properties 中添加

logging.level.com.example.demo.dao=DEBUG
pagehelper.helperDialect=mysql
pagehelper.reasonable=true
pagehelper.supportMethodsArguments=true
pagehelper.params=count=countSql
pagehelper.page-size-zero=true

----------------------
3.代码示例

1)UserMapper.xml
<select id="selectUser" resultMap="UserResultMap">
    select
    <include refid="Base_Column_List"/>
    from js_sys_user
    <where>
        1 = 1
        <if test="userCode != null and ''!=userCode">
            and user_code = #{userCode,jdbcType=VARCHAR}
        </if>
    </where>
</select>

2)UserMapper.java
public interface UserMapper {
    List<User> selectUser(User user);
}

3)UserService.java
public interface UserService {
    PageInfo<User> selectUser(Integer page,Integer size,User user);
}

4)UserServiceImpl.java
public class UserServiceImpl implements UserService{
    @Resource
    private UserMapper userMapper;
    public PageInfo<User> selectUser(Integer page,Integer size,User user) {
        //开启分页查询，写在查询语句上方
        //只有紧跟在PageHelper.startPage方法后的第一个Mybatis的查询（Select）方法会被分页。
        PageHelper.startPage(page, size);
        List<User> userList = userMapper.selectUser(user);
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;

    }
}

5)UserController.java
@RestController
@RequestMapping("user")
@Api(tags = {"用户操作接口"}, description = "UserControler")
public class UserController {
    @Resource
    private UserService userService;
    @ApiOperation(value = "查询用户", notes = "用户数据分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "getUser", value = "分页参数与用户条件", required = true,
                    dataType = "Page", paramType = "query")
    })
    @PostMapping("getUser")
    public HttpEntity getUser(@RequestBody PageVo<User> pageVo){
        try{
            User user = pageVo.getEntity();
            PageInfo pageInfo = userService.selectUser( pageVo.getPageNo(),pageVo.getPageSize(),user);
            return new HttpEntity(HttpStatus.OK,true,"请求成功",pageInfo);
        }catch (Exception e){
            return new HttpEntity(HttpStatus.OK,false,e.getMessage(),"");
        }
    }
}

6)PageVo.java
public class PageVo<T> {
    T entity;
    Integer pageNo; 
    Integer pageSize;
	public T getEntity() {
		return entity;
	}
	public void setEntity(T entity) {
		this.entity = entity;
	}
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}

7)请求参数
{
	"pageSize":1,
	"pageNo":1,
	"entity": {
		
	}
}