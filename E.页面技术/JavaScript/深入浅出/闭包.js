1.闭包概念:
2.例子:
3.官方说明:




----------------------------------------------------------------------------------------------
1.闭包概念:
  指一个函数或函数的引用，与一个引用环境绑定在一起。这个引用环境是一个
存储该函数每个非局部变量(也叫自由变量)的表。
  闭包，不同于一般的函数，它允许一个函数在立即词法作用域外调用时，仍可
访问非本地变量。


它使得函数拥有私有变量变成可能。


function outer(){
	var localVal = 30;
	return localVal;
}
outer();//30
当 outer 函数调用之后，局部变量 localVal 就被释放掉了。

---------------------
返回一个函数

function outer(){
	var localVal = 30;
	return function(){
		return localVal;
	}
}
outer();//30
当 outer 函数调用之后，变量 localVal 没有被释放掉。


函数里面返回另外一个函数


!function(){
	var loalData = "localData here";
	var url = "http://www.daidu.com";
	$.ajax({
		url: url,
		success: function(){
			console.log(localData);
		}
	});
}();


---------------------------------------------
2.例子:

//all are 4
document.body.innerHTML = "<div id='div1'>aaa</div>"
						+ "<div id='div2'>bbb</div>"
						+ "<div id='div3'>ccc</div>";
for(var i = 1; i < 4; i++){
	document.getElementById('div' + i).addEventListener('click', function(){
		// all are 4
		// 作用域是全局作用域，可以一直访问到
		console.log(i);
	});
}



//1，2，3
document.body.innerHTML = "<div id='div1'>aaa</div>"
						+ "<div id='div2'>bbb</div>"
						+ "<div id='div3'>ccc</div>";
for (var i = 1; i < 4; i++) {
	!function(i){
			document.getElementById('div' + i).addEventListener('click', function(){
			// all are 4
			// 此处的i已经作为匿名函数的参数，变成单独的函数作用域
			console.log(i);
		});
	}(i);
}



-----------------------------------------------
3.官方说明:
function createComparisonFunction(propertyName) { 
 
 return function(object1, object2){ 
	 var value1 = object1[propertyName]; 
	 var value2 = object2[propertyName]; 
	 
	 if (value1 < value2){ 
		 return -1; 
	 } else if (value1 > value2){ 
	 	return 1; 
	 } else { 
	 	return 0; 
	 } 
 }; 
}

    内部函数中的 propertyName 所处的作用域其实是属于 createComparisonFunction 函数的，访问时，只取
createComparisonFunction 函数的作用域。

    在另一个函数内部定义的函数会将包含函数（即外部函数）的活动对象添加到它的作用域链中。
因此，在 createComparisonFunction()函数内部定义的匿名函数的作用域链中，实际上将会包含外部
函数 createComparisonFunction()的活动对象。

    在匿名函数从 createComparisonFunction()中被返回后，它的作用域链被初始化为包含
createComparisonFunction()函数的活动对象和全局变量对象。

    更为重要的是，createComparisonFunction()
函数在执行完毕后，其活动对象也不会被销毁，因为匿名函数的作用域链仍然在引用这个活动对象。换
句话说，当 createComparisonFunction()函数返回后，其执行环境的作用域链会被销毁，但它的活
动对象仍然会留在内存中；直到匿名函数被销毁后，createComparisonFunction()的活动对象才会
被销毁。


-------
   作用域链的这种配置机制引出了一个值得注意的副作用，即闭包只能取得包含函数中任何变量的最
后一个值。别忘了闭包所保存的是整个变量对象，而不是某个特殊的变量。



function createFunctions(){ 
 var result = new Array(); 
 for (var i=0; i < 10; i++){ 
 	result[i] = function(){ 
 		return i; 
 	}; 
 } 
 return result; 
} 
每个函数都返回 10。因为每个函数的作用域链中
都保存着 createFunctions() 函数的活动对象，所以它们引用的都是同一个变量 i 。 当
createFunctions()函数返回后，变量 i 的值是 10，此时每个函数都引用着保存变量 i 的同一个变量
对象，所以在每个函数内部 i 的值都是 10。


我们可以通过创建另一个匿名函数强制让闭包的行为
符合预期，如下所示。
function createFunctions(){ 
 var result = new Array(); 
 	for (var i=0; i < 10; i++){ 
 		result[i] = function(num){ 
 			return function(){ 
 				return num; 
 		}; 
 	}(i);
 } 
 return result; 
} 