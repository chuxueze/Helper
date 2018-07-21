1.代码示例















-----------------------------------------------------
1.代码示例
1)CustomRealm.java
public class CustomRealm extends AuthorizingRealm {
    /*************认证，校验用户名与密码*************/
    Map<String,Object> map = new HashMap<String,Object>();
    {
        map.put("zhc","1020");
        super.setName("customRealm");
    }
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {//用来做认证，用户名密码
        //1.从主体传过来的认证信息中，获取用户名
        String userName = (String) authenticationToken.getPrincipal();
        //2.通过用户名从数据库中获取密码
        String password = getPasswordByUserName(userName);
        if(password == null){//如果不为空，则证明用户是存在的
            return null;
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo("zhc",password,"customRealm");
        return simpleAuthenticationInfo;
    }
    private String getPasswordByUserName(String userName){//模拟从数据库查找密码
        return (String)map.get(userName);
    }
    /*************认证，校验用户名与密码*************/


    /*************授权校验，校验角色权限*************/
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {//用来做授权,角色
        String userName = (String)principalCollection.getPrimaryPrincipal();
        //从数据库中或者缓存中获取角色数据
        Set<String> roles = getRolesByUserName();//实际情况需要根据用户去查找相关权限数据
        Set<String> permissions = getPermissionsByUserName();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }
    public Set<String> getRolesByUserName(){
        Set<String> set = new HashSet<String>();
        set.add("customer");
        set.add("admin");
        return set;
    }
    public Set<String> getPermissionsByUserName(){
        Set<String> set = new HashSet<String>();
        set.add("customer:add");
        set.add("customer:delete");
        return set;
    }
    /*************授权校验，校验角色权限*************/
}


2)CustomRealmTest.java
public class CustomRealmTest {
    @Test
    public void testCustomRealm(){
        try{
            CustomRealm customRealm = new CustomRealm();
            //1.创建SecurityManager
            DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
            defaultSecurityManager.setRealm(customRealm);
            //2.主体提交认证请求
            SecurityUtils.setSecurityManager(defaultSecurityManager);
            Subject subject = SecurityUtils.getSubject();

            UsernamePasswordToken token = new UsernamePasswordToken("zhc","1020");
            //校验认证信息
            subject.login(token);
            //校验授权信息
            subject.checkRoles("admin","customer");
            subject.checkPermissions("customer:add","customer:delete");

            System.out.println("isAuthenicated(是否认证)"+subject.isAuthenticated());
        }catch (Exception e){
            System.out.println("测试失败:"+ e);
        }
    }
}