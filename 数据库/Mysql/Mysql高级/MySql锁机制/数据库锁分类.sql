1.按操作类型分：
读锁
写锁
2.按细粒度分：
表锁
行锁 
页锁

-------------------
读锁：
    共享锁，针对同一份数据，多个读操作可以同时进行而不会互相影响。

写锁：
    排它锁，当前写操作没有完成前，它会阻断其他写锁和读锁。