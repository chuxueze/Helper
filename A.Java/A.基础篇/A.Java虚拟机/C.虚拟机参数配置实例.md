# 目录
- [1.例子](#1)
- [2.配置说明](#2)  
---
# <span id='1'>1.例子</span>
- jdk版本 机器配置 建议jvm参数 备注  
```
jdk1.7 6V8G -server 
-Xms4g -Xmx4g -Xmn2g -Xss768k -XX:PermSize=512m -XX:MaxPermSize=512m 
-XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+CMSClassUnloadingEnabled 
-XX:+DisableExplicitGC -XX:+UseCMSInitiatingOccupancyOnly 
-XX:CMSInitiatingOccupancyFraction=68 -verbose:gc -XX:+PrintGCDetails 
-Xloggc:{CATALINA_BASE}/logs/gc.log -XX:+PrintGCDateStamps 
-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath={CATALINA_BASE}/logs 
```
- 前台jdk1.7 8V8G -server 
```
-Xms4g -Xmx4g -Xmn2g -Xss768k -XX:PermSize=512m -XX:MaxPermSize=512m 
-XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+CMSClassUnloadingEnabled 
-XX:+DisableExplicitGC -XX:+UseCMSInitiatingOccupancyOnly 
-XX:CMSInitiatingOccupancyFraction=68 -verbose:gc -XX:+PrintGCDetails 
-Xloggc:{CATALINA_BASE}/logs/gc.log -XX:+PrintGCDateStamps -XX:+HeapDumpOnOutOfMemoryError 
-XX:HeapDumpPath={CATALINA_BASE}/logs 
```
- 前台jdk1.7 4V8G -server 
```
-Xms4g -Xmx4g -Xmn2g -Xss768k -XX:PermSize=512m -XX:MaxPermSize=512m 
-XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:+CMSClassUnloadingEnabled 
-XX:+DisableExplicitGC -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=68 
-verbose:gc -XX:+PrintGCDetails -Xloggc:{CATALINA_BASE}/logs/gc.log 
-XX:+PrintGCDateStamps -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath={CATALINA_BASE}/logs 
```

- 前台jdk1.7 6V8G -server  
``` 
-Xms4g -Xmx4g -XX:MaxPermSize=512m \-verbose:gc -XX:+PrintGCDetails 
-Xloggc￼{CATALINA_BASE}/logs/gc.log -XX:+PrintGCTimeStamps \ 后台
```

# <span id='2'>2.配置说明</span>
1. 堆设置
- -Xms:初始堆大小
- -Xmx:最大堆大小
- -XX:NewSize=n:设置年轻代大小
- -XX:NewRatio=n:设置年轻代和年老代的比值。  
如:为3，表示年轻代与年老代比值为1：3，年轻代占整个年轻代年老代和的1/4
- -XX:SurvivorRatio=n:年轻代中Eden区与两个Survivor区的比值。  
注意Survivor区有两个。如：3，表示Eden：Survivor=3：2，一个Survivor区占整个年轻代的1/5
- -XX:MaxPermSize=n:设置持久代大小

2. 收集器设置
- -XX:+UseSerialGC:设置串行收集器
- -XX:+UseParallelGC:设置并行收集器
- -XX:+UseParalledlOldGC:设置并行年老代收集器
- -XX:+UseConcMarkSweepGC:设置并发收集器

3. 垃圾回收统计信息 
- -XX:+PrintGC 
- -XX:+PrintGCDetails 
- -XX:+PrintGCTimeStamps 
- -Xloggc:filename

4. 并行收集器设置 
- -XX:ParallelGCThreads=n:设置并行收集器收集时使用的CPU数。并行收集线程数。 
- -XX:MaxGCPauseMillis=n:设置并行收集最大暂停时间 
- -XX:GCTimeRatio=n:设置垃圾回收时间占程序运行时间的百分比。公式为1/(1+n)

5. 并发收集器设置
- -XX:+CMSIncrementalMode:设置为增量模式。适用于单CPU情况。 
- -XX:ParallelGCThreads=n:设置并发收集器年轻代收集方式为并行收集时，使用的CPU数。并行收集线程数。


### 如果想要调试好最准确的参数，可以结合 jMeter 性能测试工具，去修改配置参数，并调试最优解决方案。