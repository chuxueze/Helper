1.概念
2.步骤














------------------------------------------------------------
1.概念:

    后台接口返回1个实例，当你需要使用某个属性的值时，你还要判断下值是否为 null；接口返回1堆属性值为 null 的属性等
ok，消息转换器可以帮你解决这个问题。




------------------------
2.步骤:

1)添加 fastjson 依赖
<dependency>
   <groupId>com.alibaba</groupId>
   <artifactId>fastjson</artifactId>
   <version>1.2.22</version>
</dependency>

-------
2)创建配置文件
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
@Configuration
public class WebConfigurer extends WebMvcConfigurationSupport {
    /**
     * 修改自定义消息转换器
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter4 converter = new FastJsonHttpMessageConverter4();
        converter.setSupportedMediaTypes(getSupportedMediaTypes());
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(
                // String null -> ""
                SerializerFeature.WriteNullStringAsEmpty,
                // Number null -> 0
                SerializerFeature.WriteNullNumberAsZero,
                //禁止循环引用
                SerializerFeature.DisableCircularReferenceDetect
        );
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        converters.add(converter);
    }

    private List<MediaType> getSupportedMediaTypes() {
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
        supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
        supportedMediaTypes.add(MediaType.APPLICATION_PDF);
        supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
        supportedMediaTypes.add(MediaType.APPLICATION_XML);
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
        supportedMediaTypes.add(MediaType.TEXT_HTML);
        supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
        supportedMediaTypes.add(MediaType.TEXT_PLAIN);
        supportedMediaTypes.add(MediaType.TEXT_XML);
        return supportedMediaTypes;
    }
}

其中:
config.setSerializerFeatures() 方法中可以添加多个配置，以下列举出几个常用配置，更多配置请自行百度
WriteNullListAsEmpty  ：List字段如果为null,输出为[],而非null
WriteNullStringAsEmpty ： 字符类型字段如果为null,输出为"",而非null
DisableCircularReferenceDetect ：消除对同一对象循环引用的问题，默认为false（如果不配置有可能会进入死循环）
WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
WriteMapNullValue：是否输出值为null的字段,默认为false。

WebMvcConfigurationSupport 具体方法可以参考官网 API 说明文档


3)返回结果集
{
    "data": [
        {
            "email": "",
            "password": "",
            "userCode": "admin",
            "userName": "系统管理员"
        }
    ],
    "istrue": true,
    "msg": "请求成功",
    "status": "OK"
}