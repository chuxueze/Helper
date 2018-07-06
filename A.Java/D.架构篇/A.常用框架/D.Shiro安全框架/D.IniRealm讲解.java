内置 Realm:
1.IniRealm
2.JdbcRealm




----------------------------
1.IniRealm

从 ini 文件中读取信息

创建 user.ini

[users]
Mark=123456,admin
[roles]
admin=user:delete,user:update

编写 IniRealm 类，读取配置文件进行校验
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
public class IniRealmTest {
    @Test
    public void testAuthentication(){
        try{
            IniRealm iniRealm = new IniRealm("classpath:user.ini");
            //1.创建SecurityManager
            DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
            defaultSecurityManager.setRealm(iniRealm);
            //2.主体提交认证请求
            SecurityUtils.setSecurityManager(defaultSecurityManager);
            Subject subject = SecurityUtils.getSubject();

            UsernamePasswordToken token = new UsernamePasswordToken("Mark","123456");
            subject.login(token);
            System.out.println("isAuthenicated(是否认证)"+subject.isAuthenticated());
            //校验是否为admin角色
            subject.checkRole("admin");
            //校验是否有删除权限
            subject.checkPermission("user:update");
        }catch (Exception e){
            System.out.println("测试失败:"+ e);
        }
    }
}
