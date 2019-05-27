1.定义:
2.数组类型检测:
3.数组方法:







--------------------------------------------------------------------------------------------------------------
1.定义:

var aar = [];

var aar0 = new Array();

var aar1 = new Array(20);



-----------------------------------------------------------

2.数组类型检测:

Array.isArray();

Array.isArray([]);
//ture

-----------------------------------------------------------
3.数组方法:

转换:
toLocaleString()
toString()
valueOf()
join(separator: string)

--------------------
栈方法:
push(items...: any)
pop()

--------------------
队列方法:
shift()
unshift()

--------------------
重排序方法:
reverse()
sort(compareFn: Function)

--------------------
操作方法:
concat(items...: any)
slice(start?: long long, end?: long long, contentType?: DOMString)
splice(start: int, deleteCount: int, items...: any)

--------------------
位置方法:
indexOf(searchElement: any, fromIndex?: int)
lastIndexOf(searchElement: any, fromIndex?: int)

--------------------
迭代方法:
 every()：对数组中的每一项运行给定函数，如果该函数对每一项都返回 true，则返回 tru
 filter()：对数组中的每一项运行给定函数，返回该函数会返回 true 的项组成的数组。
 forEach()：对数组中的每一项运行给定函数。这个方法没有返回值
 map()：对数组中的每一项运行给定函数，返回每次函数调用的结果组成的数组
 some()：对数组中的每一项运行给定函数如果该函数对任一项返回 true则返回 true

--------------------
归并方法:
reduce(callbackfn: Function, initialValue?: any)
reduceRight(callbackfn: Function, initialValue?: any)
