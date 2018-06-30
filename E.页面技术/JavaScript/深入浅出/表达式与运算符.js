1.表达式：
2.运算符：

----------------------------------
1.表达式：
表达式是一种js短语，可以使用Js解释器用来产生一个值。
分类:

原始表达式：
	常量，直接量：3.14
	关键字：null,this,true,false
	变量：i,k,j

初始化表达式：
	(数组，对象)
	[1,2]     new Array(1,2)
	[1,,,4]   [1,undefined,undefined,4]
	{x:1,y:2} 	var o = new Object(); o.x=1;o.y=2; 

函数表达式：
	var fe = function(){}
	(function(){console.log('...');})();

属性访问表达式：
	var o = {x:1};
	o.x
	o['x']

调用表达式：
	fe();

对象创建表达式：
	new Func(1,2);
	new Object;
------------------------------------
2.运算符：