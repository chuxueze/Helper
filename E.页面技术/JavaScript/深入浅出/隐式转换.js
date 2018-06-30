1.+和-
2.==和===


1.+:
-:
var x = 'The answer is' + 42;//此处x是字符串拼接
结果: 'The answer is 42'
var a = '37' - 7;//此处是减法去处
结果： 30
var b = '37' + 7;//此处是字符串拼接
结果：'377'

正由于javaScript这种特性，我们可以巧用规则去转换类型。
如：

字符串 num--数字 num1
var num = '12'; 
	num - 0;
结果 12

数字 num2 -- 字符串 num
var num2 = 100;
	num2 + '';
结果 '100'
------------------
2.==和===

==
'1.23' == 1.23;//字符串会先转换成数字之后再进行比较。
0 == false;
null == undefined;
以上一个都是true

===
new Object() == new Object();//返回false，因为不是同个对象
[1,2] == [1,2];//返回false，因为不是同个对象

类型不同：返回false
类型相同：
null === null; 
undefined === undefined;
NaN !== NaN;
new Object 