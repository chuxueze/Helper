1.基本类型和包装类型


1.基本类型和包装类型
var str = 'string';//基本类型
var strObj = new String('string');//包装类型

var a = 'string';
alert(a.length);//6
a.t = 3;
alert(a.t);//undefined
当调用 a.length 方法之后，a 会被临时转换成一个包装类型（对象），
随即被毁掉，当执行 a.t=3 时，此时a已经恢复成基本类型了，
所以会赋值失败。

其他的类型如boolean也一样。