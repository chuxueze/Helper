Shiro注解大全
注解都会抛出异常，但这个异常不需要我们来刻意处理，shiro会来处理，跳转到登陆界面或者其他

@RequiresAuthentication
描述/例子
是否经过认证或者登陆，若没有的话会抛出异常UnauthenticatedException 
使用例子：

@RequiresAuthentication
public void updateAccount(Account userAccount) {
    //处理业务逻辑
    ...
}
上面的例子相当于：

public void updateAccount(Account userAccount) {
    if (!SecurityUtils.getSubject().isAuthenticated()) {
        throw new AuthorizationException(...);
    }

    //处理业务逻辑
    ...
}

-----------------------------------
@RequiresGuest
描述/例子
未认证或者叫未登陆，可能在remember me状态下，否则抛出异常UnauthenticatedException 
使用例子：

@RequiresGuest
public void signUp(User newUser) {
    //处理业务逻辑
    ...
}
上面的例子相当于：

public void signUp(User newUser) {
    Subject currentUser = SecurityUtils.getSubject();
    PrincipalCollection principals = currentUser.getPrincipals();
    if (principals != null && !principals.isEmpty()) {
        //known identity - not a guest:
        throw new AuthorizationException(...);
    }

    //Subject is guaranteed to be a 'guest' here
    ...
}
-----------------------------------
@RequiresPermissions
描述/例子
检查是否有该权限，没有抛出异常AuthorizationException

使用例子：

@RequiresPermissions("account:create")
public void createAccount(Account account) {
    //处理业务逻辑
    ...
}

上面的例子相当于：

public void createAccount(Account account) {
    Subject currentUser = SecurityUtils.getSubject();
    if (!subject.isPermitted("account:create")) {
        throw new AuthorizationException(...);
    }

    //处理业务逻辑
    ...
}
-----------------------------------
@RequiresRoles
描述/例子
检查是否有该角色，没有抛出异常AuthorizationException

使用例子：

@RequiresRoles("administrator")
public void deleteUser(User user) {
    //处理业务逻辑
    ...
}
上面的例子相当于：

public void deleteUser(User user) {
    Subject currentUser = SecurityUtils.getSubject();
    if (!subject.hasRole("administrator")) {
        throw new AuthorizationException(...);
    }

    //处理业务逻辑
    ...
}
-----------------------------------
@RequiresUser
描述/例子
这个刚好跟@RequiresGuest相反，这个必须经过认证，或者从rememberme进行登陆，这个没有RequiresAuthentication严格但类似，否则抛出异常AuthorizationException 
使用例子：

@RequiresUser
public void updateAccount(Account account) {
    //处理逻辑
    ...
}

上面的例子相当于：

public void updateAccount(Account account) {
    Subject currentUser = SecurityUtils.getSubject();
    PrincipalCollection principals = currentUser.getPrincipals();
    if (principals == null || principals.isEmpty()) {
        //no identity - they're anonymous, not allowed:
        throw new AuthorizationException(...);
    }

    //处理业务逻辑
    ...
}
-----------------------------------
配置
使用注解需要配置一些东西 
shiro提供了好几种配置支持：

AspectJ
Spring
Guice
由于笔者能力有限，了解甚浅，只介绍怎么配置spring 
securityManager就不介绍怎么配置了，另一章再说这个，那么spring 怎么配置呢

spring 配置,当然也可以看看官网怎么说，http://shiro.apache.org/spring.html

<bean id="lifecycleBeanPostProcessor" class="org.apache.shiro.spring.LifecycleBeanPostProcessor"/>

<!-- Enable Shiro Annotations for Spring-configured beans.  Only run after -->
<!-- the lifecycleBeanProcessor has run: -->
<bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator" depends-on="lifecycleBeanPostProcessor"/>
<bean class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
    <property name="securityManager" ref="securityManager"/>
</bean>