``` java
try{
	//可能抛出异常的方法
}catch(Exception e){
	//处理该异常的代码块
}finally{
	//最终需要执行的代码
}
```

如果 try 里真的抛出异常，则程序终止执行，
程序的控制权将交给 catch 块中的异常处理程序处理。  
catch 中想要怎么处理异常，则根据具体业务需求，
可以是做一个提示，一个警告，错误日志记录。  
catch 中可以捕获多种异常 



当异常被捕获之后，则不会抛到上一层，直接在捕获的那一层被处理。

如果捕获异常之后，再将异常抛出，则由上层处理。如：
``` java
try {
	StringBuilder lSQL = new StringBuilder();
	DBHelper.executeUpdate(lSQL.toString(), businessMap);
}catch (Exception e) {
	throw new HygeiaException("保存费用明细临时表出现SQL异常", e);
}
```
此例子会将异常抛出到上层。

如果只做普通处理，不抛出异常，则程序正常运行。如：
``` java
try{
	feeDetailList = quereyMedicalFeeDetailService.extractMedicalFeeDetail(dto);	
	successCount++;
}catch(Exception e){
	failDetailList = quereyMedicalFeeDetailService.queryMedicalFee(dto,"2");//提取失败的记录
	failDetailLists.addAll(failDetailList);
	failCount++;
}
```