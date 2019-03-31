1.闭包概念:






----------------------------------------------------------------------------------------------
1.闭包概念:
它使得函数拥有私有变量变成可能。


function outer(){
	var localVal = 30;
	return localVal;
}
outer();//30
当outer函数调用之后，局部变量localVal就被释放掉了。

---------------------
返回一个函数

function outer(){
	var localVal = 30;
	return function(){
		return localVal;
	}
}
outer();//30
当outer函数调用之后，变量localVal没有被释放掉。



