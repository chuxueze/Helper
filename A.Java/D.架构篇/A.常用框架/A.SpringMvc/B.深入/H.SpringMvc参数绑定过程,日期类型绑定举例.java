参数绑定过程:
客户端请求key/value数据，将数据绑定到controller方法的形参上。
简单绑定是将一个用户界面元素（控件）的属性绑定到一个类型（对象）实例上的某个属性的方法。
例如，如果一个开发者有一个Customer类型的实例，那么他就可以把Customer的“Name”属性绑定到一个TextBox的“Text”属性上。
“绑定”了这2个属性之后，对TextBox的Text属性的更改将“传播”到Customer的Name属性，而对Customer的Name属性的更改同样会“传播”到TextBox的Text属性。


步骤:
1.客户端请求key/value数据
2.处理器适配器调用springMvc提供参数绑定组件将key/value数据
  转成controller方法的形参（使用converter进行类型转换）
  也就是类型转换器。
  某些特殊情况下，需要自定义converter,
  例如:对日期数据的绑定就需要自定义converter


例子：
conversion-service="conversionService"
需要在处理器适配器中注入转换类

在springmvc.xml中
<mvc:annotation-driven conversion-service="conversionService"></mvc:annotation-driven>
<!-- 自定义参数绑定 -->
<bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
	<!-- 转换器 -->
	<property name="converters">
		<list>
			<!-- 日期类型转换 -->
			<bean class="cn.itcast.ssm.controller.converter.CustomDateConverter"/>
		</list>
	</property>
</bean>

public class CustomDateConverter implements Converter<String,Date>{

	@Override
	public Date convert(String source) {
		
		//实现 将日期串转成日期类型(格式是yyyy-MM-dd HH:mm:ss)
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			//转成直接返回
			return simpleDateFormat.parse(source);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//如果参数绑定失败返回null
		return null;
	}
}