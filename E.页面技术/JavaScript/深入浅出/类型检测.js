类型检测，判断变量的类型。

判断基本类型：（typeof）
typeof 100;  结果：'number'
typeof true;	结果：'boolean'
typeof function;	结果：'function'
typeof (undefined);	结果：'undefined'
typeof new Object();	结果：'object'
typeof [1,2];	结果：'object'
typeof NaN;		结果：'number'
typeof null;	结果：'object'


判断对象类型：(instanceof)
obj instanceof Object
[1,2] instanceof Array ===true;
new Object instanceof Array ===false;
在不同的iframe和window间检测时失效。


Object.prototype.toString
Object.prototype.toString.apply([]);	结果：'[object Array]'
Object.prototype.toString.apply(function(){});	结果：'[object Function]'


constructor

duck type