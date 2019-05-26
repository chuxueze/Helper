1:创建 Object 的两种方式





---------------------------------------------------------------------------------------------------
1:创建 Object 的两种方式


var stu = new Object();
stu.name = "Bill";
stu.age = 18;
stu.action = function(){
	console.log("go to shcool");
}

-----------------------

var stu = {
	name : "Bill",
	age : 18,
	action : function(){
		console.log("go to shcool");
	}
}