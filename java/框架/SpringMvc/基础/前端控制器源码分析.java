详情请看：
springmvc第一天课堂笔记
6	源码分析（了解）





第一步：前端控制器接收请求

调用doDiapatch
 

第二步：前端控制器调用处理器映射器查找 Handler
 

第三步：调用处理器适配器执行Handler，得到执行结果ModelAndView



第四步：视图渲染，将model数据填充到request域。

视图解析，得到view:
 

调用view的渲染方法，将model数据填充到request域
model 其实就是一个map，视图渲染就是将model遍历完之后，装到request域中。

渲染方法：
 

 

