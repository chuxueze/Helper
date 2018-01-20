主要用于字段自增长：
-- Create sequence 
create sequence YYCGDBM
minvalue 1
maxvalue 9999999
start with 1000100
increment by 1
cache 20;

--获取序列号，每次查询都会自增长一次
select yycgdbm.nextval from dual;