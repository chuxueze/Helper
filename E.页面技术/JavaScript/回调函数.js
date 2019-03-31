1.回调或者高阶函数:
2.作为函数方法调用函数:(call,apply)













---------------------------------------------------------------------------------------------------------
1.回调或者高阶函数:
    将函数作为一个参数，传递给另外一个函数

例如://它就是回调函数
$("#btn_1").click(function() {
  alert("Btn 1 Clicked");
});

$("#btn_1").click();
此方法接收了一个函数作为变量

------------
以下方法功能相同
function btnClick(){
	alert("你点击了");
}
var btn = document.getElementById("btn");
btn.addEventListener('click',btnClick);



-----------------------------------------------------------
2.作为函数方法调用函数:(call,apply)

call();

function myFunction(a, b) {
    return a * b;
}
myObject = myFunction.call(myObject, 10, 2);     // 返回 20

-----------------------------
apply()

function myFunction(a, b) {
    return a * b;
}
myArray = [10, 2];
myObject = myFunction.apply(myObject, myArray);  // 返回 20