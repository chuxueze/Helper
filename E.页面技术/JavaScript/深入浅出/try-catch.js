如果内部的异常没有处理，抛到外面了，那么会先执行里面的 finally 语句。
try{
	try{
		throw new Error('opps');
	}finally{
		console.log('finally');
	}
}catch(ex){
	console.error('outer',ex.message);
}
执行顺序：
里面的 try 块抛出异常后，先执行finally，再被外面的
 catch 语句块接住，所以结果应该是：
 'finally'
 'outer'  'opps'
----------------------------------
try{
	try{
		throw new Error('opps');
	}catch(){
		console.error('inner',ex.message);
	}finally{
		console.log('finally');
	}
}catch(ex){
	console.error('outer',ex.message);
}
执行顺序：
里面的 try 块抛出异常后，里面的 catch 块捕获到异常并处理异常，
最后执行 finally 语句，所以结果应该是：
 'inner' 'opps'
 'finally'  
 因为里面的 catch 块已经将异常处理，所以异常不会抛出到外层。
 ----------------------------------
 try{
	try{
		throw new Error('opps');
	}catch(){
		console.error('inner',ex.message);
		throw ex;
	}finally{
		console.log('finally');
	}
}catch(ex){
	console.error('outer',ex.message);
}
由于里面的 catch 块处理异常之后，自己又抛出了异常，所以外层的 
catch 块可以捕获到异常，所以结果应该是：
'inner' 'opps'
'finally'
'outer' 'opps'