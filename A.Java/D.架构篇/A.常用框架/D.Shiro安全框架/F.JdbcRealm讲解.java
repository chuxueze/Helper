1.JdbcRealm
2.使用步骤






-----------------------------------------------
1.JdbcRealm
与数据库的 Jdbc 交互







----------------
2.使用步骤
1)引入依赖
 <dependency>
     <groupId>mysql</groupId>
     <artifactId>mysql-connector-java</artifactId>
     <version>5.1.38</version>
 </dependency>
 <dependency>
     <groupId>com.alibaba</groupId>
     <artifactId>druid</artifactId>
     <version>1.1.6</version>
 </dependency>


-----------------
2)JdbcRealmTest.java
验证信息数据从数据库中来，跟数据库表结果相关。
可以查看 JdbcRealm.java 查看默认数据库结构


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class JdbcRealmTest {

    DruidDataSource dataSource = new DruidDataSource();

    {//初始化块，在构造器之前执行
        dataSource.setUrl("jdbc:mysql://localhost:3306/shiro");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
    }


    @Test
    public void testAuthentication() {
        try {
            JdbcRealm jdbcRealm = new JdbcRealm();
            jdbcRealm.setDataSource(dataSource);
            //设置权限开关
            jdbcRealm.setPermissionsLookupEnabled(true);
            //1.创建SecurityManager
            DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
            defaultSecurityManager.setRealm(jdbcRealm);
            //2.主体提交认证请求
            SecurityUtils.setSecurityManager(defaultSecurityManager);
            Subject subject = SecurityUtils.getSubject();

            UsernamePasswordToken token = new UsernamePasswordToken("Mark", "123456");
            subject.login(token);
            System.out.println("isAuthenicated(是否认证)" + subject.isAuthenticated());
            //校验是否为admin角色
            subject.checkRole("admin");
            subject.checkRoles("admin","teacher");
            //校验是否有删除权限
            subject.checkPermission("user:select");
        } catch (Exception e) {
            System.out.println("测试失败:" + e);
        }
    }
}



3)自定义 Sql


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class JdbcRealmTest {
    DruidDataSource dataSource = new DruidDataSource();
    {//初始化块，在构造器之前执行
        dataSource.setUrl("jdbc:mysql://localhost:3306/shiro");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
    }
    @Test
    public void testAuthentication() {
        try {
            JdbcRealm jdbcRealm = new JdbcRealm();
            jdbcRealm.setDataSource(dataSource);
            //设置权限开关
            jdbcRealm.setPermissionsLookupEnabled(true);

            String pwdSql = "select password from shiro_user where user_name = ?";
            jdbcRealm.setAuthenticationQuery(pwdSql);

            String roleSql = "select role_name from shiro_user_role where user_name = ?";
            jdbcRealm.setUserRolesQuery(roleSql);
            
            //1.创建SecurityManager
            DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
            defaultSecurityManager.setRealm(jdbcRealm);
            //2.主体提交认证请求
            SecurityUtils.setSecurityManager(defaultSecurityManager);
            Subject subject = SecurityUtils.getSubject();

            UsernamePasswordToken token = new UsernamePasswordToken("zhc", "1020");
            subject.login(token);
            System.out.println("isAuthenicated(是否认证)" + subject.isAuthenticated());
            //校验是否为admin角色
            subject.checkRole("user");
            subject.checkRoles("user","teacher");
            //校验是否有删除权限
            //subject.checkPermission("user:select");
        } catch (Exception e) {
            System.out.println("测试失败:" + e);
        }
    }
}


对应数据库表
DROP TABLE IF EXISTS `shiro_user`;
CREATE TABLE `shiro_user`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shiro_user
-- ----------------------------
INSERT INTO `shiro_user` VALUES ('1', 'zhc', '1020');



DROP TABLE IF EXISTS `shiro_user_role`;
CREATE TABLE `shiro_user_role`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shiro_user_role
-- ----------------------------
INSERT INTO `shiro_user_role` VALUES ('1', 'zhc', 'user');
INSERT INTO `shiro_user_role` VALUES ('2', 'zhc', 'teacher');

