1.步骤:
2.注意:
3.说明:







---------------------------------------------
1.步骤:
  目前，在 pom.xml 中还没有任何的 Maven 依赖，随后会添加1些 JavaWeb 所需的依赖。
在添加这些依赖之前，要先将这个 Maven 项目调整为 Web 项目结构。
只需要三步
1)在 main 目录下，添加 webapp目录
2)在 webapp 目录下，添加 WEB-INF 目录
3)在 WEB-INF 目录下，添加 web.xml 文件

  此时，IDEA提示 Frameworks detected ，点击 Configure-->OK。







--------------------
2.注意:
source:源头
归类或目录里1般放的是源代码文件如Java、Cpp，

resource:资源
归类或目录里放的是资源文件如图片、图标、音频视频文件

1)需要将 Java 文件夹定义为 sources
步骤:
File --> Project Structure --> Modules --> 选中 Java，点击 Sources
2)需要将 webapp 文件夹定义为 resources
步骤:
File --> Project Structure --> Modules --> 选中 webapp，点击 Resources

3)如果 web.xml 中报 URI is not registered，则需要将标签加入到本地中。
步骤:
File --> Settings --> Languages & Frameworks --> Schemas and DTDs --> Ignored Schemas and DTDs +






--------------------
3.说明:
  可以简单将 Maven 依赖理解为 jar 包，只不过 Maven 依赖具有传递性，只需要配置某个
依赖，就能自动获取该依赖的相关依赖。