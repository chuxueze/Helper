1:查看锁表
2:查看表状态

--------------------------------------------------------



1:查看锁表
查看没有锁住的表:
show open tables;
+----------+-------------------+--------+-------------+
| Database | Table             | In_use | Name_locked |
+----------+-------------------+--------+-------------+
| guns     | sys_role          |      1 |           0 |
| guns     | sys_login_log     |      0 |           0 |
| guns     | sys_dict          |      0 |           0 |
| guns     | sys_expense       |      0 |           0 |
| mysql    | proc              |      0 |           0 |
| guns     | sys_relation      |      0 |           0 |
| guns     | sys_dept          |      0 |           0 |
| guns     | user              |      0 |           0 |
| guns     | sys_menu          |      0 |           0 |
| guns     | sys_operation_log |      0 |           0 |
| guns     | sys_notice        |      0 |           0 |
| guns     | sys_user          |      1 |           0 |
+----------+-------------------+--------+-------------+
In_use 为 0，则没有锁
In_use 为 1，则加了锁




2:查看表状态
show status like 'table%';
+-----------------------+-------+
| Variable_name         | Value |
+-----------------------+-------+
| Table_locks_immediate | 22    |
| Table_locks_waited    | 0     |
+-----------------------+-------+
Table_locks_immediate:
产生表级锁定的次数，表示可以立即获取锁的次数，每立即获取锁值加1
Table_locks_waited:
出现表级锁定争用而发生等待的次数（不能立即获取锁的次数，每等待一次锁值加1）此值高则说明存在着较严重的表级锁争用情况。