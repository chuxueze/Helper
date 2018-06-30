--建表时复制数据（可以不用存在表）
--复制全部数据
create table userjd_new 
as
select * from userjd
--复制指定数据字段
create table userjd_new1 
as
select id,mc,dz from userjd;

--添加数据时复制（表应该先存在）
--复制全部数据
insert into userjd_new 
select * from userjd
--复制部分字段数据
insert into userjd_new(id,username) 
select (id,username) from userjd
--字段可以不对应，但字段类型需要保持一致
