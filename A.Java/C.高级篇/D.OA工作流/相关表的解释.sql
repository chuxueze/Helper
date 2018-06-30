1.资源库流程规则表 'RE'表示repository。 这个前缀的表包含了流程定义和流程静态资源（图片，规则，等等）
	act_re_deloyment:部署信息表
	act_re_model:流程设计模型部署表
	act_re_procdef:流程定义数据表


2.运行时数据库表  'RU'表示runtime。 这些运行时的表，包含流程实例，任务，变量，异步任务，等运行中的数据。 Activiti只在流程实例执行过程中保存这些数据，在流程结束时就会删除这些记录。 这样运行时表可以一直很小速度很快
	act_ru_exceution:运行时流程执行实例表
	act_ru_identitylink:运行时流程人员表，主要存储任务节点与参与者的相关信息
	act_ru_task:运行时任务节点表
	act_ru_version:运行时流程变量数据表

3.历史数据库表  'HI'表示history。 这些表包含历史数据，比如历史流程实例，变量，任务等等。
	act_hi_actinst:历史节点表
	act_hi_attachment:历史附件表
	act_hi_comment:历史意见表
	act_hi_adentitylink:历史流程人员表
	act_hi_detail:历史详情表，提供历史变量的查询
	act_hi_procinst:历史流程实例表
	act_hi_taskinst:历史任务实例表
	act_hi_hi_varinst:历史变量表

4.组织机构表  'ID'表示identity。 这些表包含身份信息，比如用户，组等等。
	act_id_group:用户组信息表
	act_id_info:用户扩展信息表
	act_id_membership:用户与用户组对应信息表
	act_id_user:用户信息表

5.通用数据表  通用数据，用于不同场景下，如存放资源文件
	act_ge_bytearray:二进制数据表
	act_ge_property:属性数据表（存储整个流程引擎级别的数据，初始化表结构时，会默认插入三条记录）


这些表主要是用来存放流程数据的，如果是业务数据，需要自己设计保存。
如果要和流程关联，那么业务表就要和流程表挂勾。