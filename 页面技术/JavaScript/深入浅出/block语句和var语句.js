1.block 语句
2.var 语句

-------------------------------------
1.1.block 语句
块语句常用于组合0~多个语句，用一对花括号定义。
语法：
{
	语句1;
	语句2;
	...
}
{
	var str = 'hi';
	consloe.log(str);
}
if(true){
	consloe.log('hi');
}

错误定义：
{a:1,b:2}
正确定义：
var o = {a:1,b:2}

注意：没有块级作用域。
块语句外可以访问到块语句内的变量。
如：
for(var i = 0;i<10;i++){
	var str = 'hi';
	console.log(str);
}
=
var i = 0;
for(;i<10;i++){
	var str ='hi';
	console.log(str); 
}
再如：
{var x = 1;}  
==  
var x = 1;{}
函数就有作用域。
-------------------------------------
2.var 语句
var a = 1;
var a = b = 1;  --->  var a=1,b=1;
此时b会变成全局变量。