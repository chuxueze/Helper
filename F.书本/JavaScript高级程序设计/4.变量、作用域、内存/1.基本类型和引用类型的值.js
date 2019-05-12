1.简介:
2.动态的属性:
3.复制变量值:
4.检测类型:









--------------------------------------------------------------------------------------------------------------------------
1.简介:

    基本类型值指的是简单的数据段，
而引用类型值指那些可能由多个值构成的对象

    引用类型的值是保存在内存中的对象。与其他语言不同，JavaScript 不允许直接访问内存中的位置，
也就是说不能直接操作对象的内存空间。在操作对象时，实际上是在操作对象的引用而不是实际的对象。
为此，引用类型的值是按引用访问的。




-------------------------------------------------------------------
2.动态的属性:

    对于引用类型的值，我们可以为其添加属
性和方法，也可以改变和删除其属性和方法。
	
	//1.定义
    let obj = {};
    obj.name = "张浩纯";
    obj.eat = function(){
    	console.log("吃饭");
    }
    //2.调用
    obj.name;
    obj.eat();


-------------------------------------------------------------------
3.复制变量值:

    这个特性跟 Java 的值传递类似。

  基本变量赋值:
    如果从一个变量向另一个变量复制基本类型的值，会在变量对象上创建一个新值，然后把该值复制
到为新变量分配的位置上。各自之后的操作不影响。
  
  引用类型赋值:
  	 当从一个变量向另一个变量复制引用类型的值时，同样也会将存储在变量对象中的值复制一份放到
为新变量分配的空间中。不同的是，这个值的副本实际上是一个指针，而这个指针指向存储在堆中的一
个对象。复制操作结束后，两个变量实际上将引用同一个对象。因此，改变其中一个变量，就会影响另
一个变量。


-------------------------------------------------------------------
4.检测类型:

    检测一个变量是不是基本数据类型
    var s = "Nicholas"; 
	var b = true; 
	var i = 22; 
	var u; 
	var n = null; 
	var o = new Object(); 
	alert(typeof s); //string 
	alert(typeof i); //number 
	alert(typeof b); //boolean 
	alert(typeof u); //undefined 
	alert(typeof n); //object 
	alert(typeof o); //object 



	如果变量是给定引用类型（根据它的原型链来识别；第 6 章将介绍原型链）的实例，那么
instanceof 操作符就会返回 true。请看下面的例子：
	alert(person instanceof Object); // 变量 person 是 Object 吗？
	alert(colors instanceof Array); // 变量 colors 是 Array 吗？
	alert(pattern instanceof RegExp); // 变量 pattern 是 RegExp 吗？