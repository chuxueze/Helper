1.字符串是不可变的:
2.转换为字符串:








-----------------------------------------------------------------------------------------------

2.转换为字符串:

------------------------
2.1:
toString();

toString()可以输出以二进制、八进制、十六进制，乃至其他任意有效进制格
式表示的字符串值。

var num = 10; 
alert(num.toString()); // "10" 
alert(num.toString(2)); // "1010" 
alert(num.toString(8)); // "12" 
alert(num.toString(10)); // "10" 
alert(num.toString(16)); // "a" 


--------------------
String()
在不知道要转换的值是不是 null 或 undefined 的情况下,可以使用这个。


var value1 = 10; 
var value2 = true; 
var value3 = null; 
var value4; 
alert(String(value1)); // "10" 
alert(String(value2)); // "true" 
alert(String(value3)); // "null" 
alert(String(value4)); // "undefined" 