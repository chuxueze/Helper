    
    如果定义的变量准备在将来用于保存对象，那么最好将该变量初始化为 null 而不是其他值。这样
一来，只要直接检查 null 值就可以知道相应的变量是否已经保存了一个对象的引用。
    这样做不仅可以体现 null 作为空对象指针的惯例，而且也有助于进一步区分 null 和 undefined。

var obj = null;
//只需判断obj是否为null，而不需要担心obj为undefined
if(obj == null){
	//dosomething...
}