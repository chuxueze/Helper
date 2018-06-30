AngularJs:
4大核心特性
1:MVC
2:模块化
3:指令系统
4:双向数据绑定



指令：
1.ng-app 指令告诉 AngularJS，<div> 元素是 AngularJS 应用程序 的"所有者"。
2.ng-model 指令把输入域的值绑定到应用程序变量 name。
3.ng-bind 指令把应用程序变量 name 绑定到某个段落的 innerHTML。
4.ng-init="变量=值;变量='值'"  初始化变量的值，有多个变量时，中间用分号隔开；
5.ng-repeat 指令会重复一个 HTML 元素

ng-app
1.ng-app是一个特殊的指令，一个HTML文档只出现一次，如出现多次也只有第一个起作用；ng-app可以出现在html文档的任何一个元素上。
2.ng-app作用：告诉子元素指令是属于angularJs。
3.ng-app的值可以为空（练习），项目中一定要赋值，后面所说的模块。