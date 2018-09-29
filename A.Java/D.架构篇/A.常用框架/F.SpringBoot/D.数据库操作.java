1.SpringBoot JPA
2.步骤









----------------------------------------------------------------------
1.SpringBoot JPA
JPA(Java Persistence API) 定义了1系列对象持久化的标准。
将运行期的实体对象持久化到数据库中。

    也就是能够在实体中，配合注解的方式，将数据表的结构自动地在
数据库中生成。




------------------------------------
2.步骤

2.1 添加 pom 依赖 jpa 和 mysql
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>

--------------
2.2 添加属性配置

spring:
  profiles:
    #使用开发环境
    #active: dev
    #使用生产环境
    active: prod

  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/dbgirl
    username: root
    password: root

  jpa:
    hibernate:
      ddl-auto: update
      show-sql: true

其中: ddl-auto 可以指定为 create
create:每次加载 hibernate 时，都会先删除原有的相同名称的表，再创建1个新的空表
update:每次加载 hibernate 时，会在原有的基本上，更新表的内容，表数据不变

--------------
2.3 增加实体，生成表结构

@Entity
public class Girl {
    // 创建默认的构造方法，否则数据库操作时会报错
    public Girl(){}
    @Id
    @GeneratedValue
    private Integer id;
    private String cupSize;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}