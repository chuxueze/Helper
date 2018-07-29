guns-parent:
	<modules>
        <module>../guns-admin</module>
        <module>../guns-core</module>
        <module>../guns-rest</module>
        <module>../guns-generator</module>
    </modules>


guns-admin:
	<parent>
        <groupId>com.stylefeng</groupId>
        <artifactId>guns-parent</artifactId>
        <version>1.0.0-SNAPSHOT</version>
        <relativePath>../guns-parent/pom.xml</relativePath>
    </parent>
 	<dependency>
           <groupId>com.stylefeng</groupId>
           <artifactId>guns-core</artifactId>
    </dependency>
    <dependency>
           <groupId>com.stylefeng</groupId>
           <artifactId>guns-generator</artifactId>
    </dependency>  
    <!--spring boot依赖-->
    <!--shiro依赖-->


guns-core:
    <!--通用依赖-->
    <!--web-->
    <!--aop-->
    <!--二维码-->
    <!--缓存-->



guns-rest:
    <dependency>
		<groupId>com.stylefeng</groupId>
		<artifactId>guns-core</artifactId>
	</dependency>



guns-generator:
    <dependency>
        <groupId>com.stylefeng</groupId>
        <artifactId>guns-core</artifactId>
    </dependency>



guns-parent:
    此模块为整个项目的整合类，所有子模块的父模块都是此模块。
    一些系统属性也是在模块中配置。

guns-core:
    此模块为项目的系统级的基础模块，其他大部分子模块都基于此模块开发，需要用到此模块的功能。
  	集成大部分系统用到的功能，依赖外部jar包比较多。提供系统级别或者公用功能。

guns-admin:
    此模块是项目的主业务模块，springBoot启动类也是在此模块中编写。依赖于guns-core模块。

guns-rest:
    此模块为对外提供的rest接口功能模块，依赖于guns-core模块。

guns-generator:
    此模块用于系统自动生成代码，依赖于guns-core模块。