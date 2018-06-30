阻止程序的正常运行，称为异常。
1.Throwable:(万恶之源，异常父类)
1.1:Eroor
	很少出现，一旦出现，程序挂掉
	1.1.1:VirtualMachineError(虚拟机错误)
	1.1.2:ThreadDeath(线程死锁)
1.2:Exception
	编码，环境，用户操作输入出现问题
	1.2.1:RuntimeException(非检查时异常，运行时异常，可以由JVM自动捕获)
		1.2.1.1:NullPointerException(空指针异常)
		1.2.1.2:ArrayIndexOutOfBoundsException(数组下标越界异常)
		1.2.1.3:ClassCastException(类型转换异常)
		1.2.1.4:ArithmeticException(算术异常)
	1.2.2:检查异常(多种多样,需要自己处理异常)
		1.2.2.1:IOExceptoin(文件异常)
		1.2.2.2:SQLExceptoin(SQL 异常)


throws Exception :将异常抛出到上一层 

实际应用中的经验与总结：
1.处理运行时异常时，采用逻辑去合理规避同时辅助 try-catch 处理
2.在多重 catch 块后面，可以加一个 catch(Exception) 来处理可能会被遗忘的异常
3.对于不确定的代码，也可以加上 try-catch ，处理潜在的异常
4.尽量去处理异常，切忌只是简单的调用 printStackTrace() 去打印输出
5.具体如何处理异常，要根据不同的业务需求和异常类型去决定
6.尽量添加 finally 语句块去释放占用的资源