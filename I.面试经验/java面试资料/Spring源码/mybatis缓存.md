- [1.简介](#1)
- [2.Spring整合Mybatis，缓存失效原因](#2)


---
# <span id="1">1.简介</span>
- mybatis 默认会开启一级缓存，如果一个查询语句执行两次以上，则只会打印出一次 sql 语句。
其他的都从缓存中取。

---
# <span id="2">2.Spring整合Mybatis，缓存失效原因</span>

