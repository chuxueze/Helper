步骤：
1.创建SecurityManager
2.主体提交认证
3.创建SecurityManager认证
4.Realm 领域 验证 
5.Authenticator认证

public class AuthenticationTest {
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
    public void addUser(){
        simpleAccountRealm.addAccount("Mark","123456");
    }
    @Test
    public void testAuth(){
        //1.创建SecurityManager
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);
        //2.主体提交认证
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();
        //3.创建SecurityManager认证
        addUser();
        UsernamePasswordToken token = new UsernamePasswordToken("Mark","123456");
        //4.Realm验证
        subject.login(token);
        //5.Authenticator认证
        System.out.println("是否认证："+subject.isAuthenticated());
        subject.logout();
    }
}