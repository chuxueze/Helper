第一步,需要在pom.xml文件中引入Quartz,格式如下
<dependency>  
    <groupId>org.quartz-scheduler</groupId>  
    <artifactId>quartz</artifactId>  
    <version>2.2.1</version>  
</dependency> 

---------------
第二步，配置定时器 spring-quartz.xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
	http://www.springframework.org/schema/beans/spring-beans-4.0.xsd">
	<!-- default-autowire="byName" default-lazy-init="false"此两个值可以不配置 -->
	<description>Quartz Job Setting</description>



	<!-- A.配置调度的任务对应bean的id和自定义class -->
	<!-- U8用户数据 -->
	<bean id="Customer" class="crs.tq.wl.u8.user.quartz.Customer" />
	<!-- U8库存 -->
	<bean id="Stock" class="crs.tq.wl.u8.user.quartz.Stock" />
	<!-- U8发货单 -->
	<bean id="Dispatch" class="crs.tq.wl.u8.user.quartz.Dispatch" />
	<!-- U8指令单 -->
	<bean id="Bill" class="crs.tq.wl.u8.bill.quartz.TomBillQuartz" />
	
	
	<!-- B.配置调度任务对应的bean的id和执行的方法,作业不并发调度 -->
	<bean id="CustomerQuartzDetail"
		class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
		<property name="targetObject" ref="Customer" />
		<property name="targetMethod">
			<value>execute</value>
		</property>
		<property name="concurrent" value="false" />
	</bean>
	<bean id="StockQuartzDetail"
		class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
		<property name="targetObject" ref="Stock" />
		<property name="targetMethod">
			<value>execute</value>
		</property>
		<property name="concurrent" value="false" />
	</bean>
	<bean id="DispatchQuartzDetail"
		class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
		<property name="targetObject" ref="Dispatch" />
		<property name="targetMethod">
			<value>execute</value>
		</property>
		<property name="concurrent" value="false" />
	</bean>
	<bean id="BillQuartzDetail"
		class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
		<property name="targetObject" ref="Bill" />
		<property name="targetMethod">
			<value>execute</value>
		</property>
		<property name="concurrent" value="false" />
	</bean>
	
	
	
	<!-- C.配置调度任务执行的触发的时间 -->
	<bean id="CustomerTrigger"
		class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
		<property name="jobDetail" ref="CustomerQuartzDetail" />
		<property name="cronExpression">
			<!-- 每隔2小时 -->
			<value>0 0/3 * * * ?</value>
			
		</property>
	</bean>
	<bean id="StockTrigger"
		class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
		<property name="jobDetail" ref="StockQuartzDetail" />
		<property name="cronExpression">
			<!-- 每隔2分钟 -->
			<!-- <value>0 */2 * * * ?</value> -->
			<!-- 第30分钟执行一次 -->
			<value>0 0/30 * * * ?</value>
		</property>
	</bean>
	<bean id="DispatchTrigger"
		class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
		<property name="jobDetail" ref="DispatchQuartzDetail" />
		<property name="cronExpression">
		   <value>0 0/3 * * * ?</value>
		</property>
	</bean>

    <!-- C.配置调度任务执行的触发的时间 -->
	<bean id="BillTrigger"
		class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
		<property name="jobDetail" ref="BillQuartzDetail" />
		<property name="cronExpression">
			<!-- 每隔5分钟 -->
			<value>0 0/3 * * * ?</value>
		</property>
	</bean>
	
	<!-- ——————————————————————————————————————————————————————————————————————————————————————————————————— -->
	<!-- D.Quartz的调度工厂，调度工厂只能有一个，多个调度任务在list中添加 -->
	<bean class="org.springframework.scheduling.quartz.SchedulerFactoryBean">
		<property name="triggers">
			<list>
			
				<!-- 提取u8发货单数据 -->
				<ref bean="DispatchTrigger" />
				
				<!-- 提取U8库存数据 -->
				<ref bean="StockTrigger" />
				
				<!-- 提取u8客户数据 -->
				<ref bean="CustomerTrigger" />
				
				<!-- 提取指令单 -->
				<ref bean="BillTrigger" />
				
			</list>
		</property>
	</bean>
</beans>


---------------
第三步，编写定时器执行代码
package crs.tq.wl.u8.user.quartz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import crs.tq.wl.u8.common.utils.JdbcUtil;
import crs.tq.wl.u8.user.entity.UeightStock;
import crs.tq.wl.u8.user.service.UeightStockService;

/**
 * 提取U8库存数据
 * 全量删除同志全量保存
 * @author Lenovo
 *
 */
public class Stock {
	private static final Logger LOG = LoggerFactory.getLogger(Stock.class);
	@Autowired 
	private UeightStockService ueightStockService;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public void execute() {
		
		delData();
		if(LOG.isInfoEnabled()) {
			LOG.info("物流系统库存数据删除成功!");
		}
		
		synchroData();
		if(LOG.isInfoEnabled()) {
			LOG.info("物流系统库存数据同步成功!");
		}
		
	}
	
	/**
	 * 删除现有库存数据
	 */
	public void delData() {
		ueightStockService.deleteAll();
	}
	/**
	 * 查找U8数据并将数据同步过来
	 */
	public void synchroData() {
		String sql = "SELECT\r\n" + 
				"	cWhcode,\r\n" + 
				"	cwhname,\r\n" + 
				"	cInvCode,\r\n" + 
				"	cinvname,\r\n" + 
				"	cInvStd,\r\n" + 
				"	cinvm_unit,\r\n" + 
				"	iinvexchrate,\r\n" + 
				"	cinva_unit,\r\n" + 
				"	iquantity,\r\n" + 
				"	inum,\r\n" + 
				"	iCanQuantity,\r\n" + 
				"	iCanNum,\r\n" + 
				"	cBatch,\r\n" + 
				"	cFree1,\r\n" + 
				"	cFree2,\r\n" + 
				"	cFree4,\r\n" + 
				"	cFree5,\r\n" + 
				"	cinvccode,\r\n" + 
				"	cinvcname,\r\n" + 
				"	cbatchproperty10,\r\n" + 
				"	cInvDefine1,\r\n" + 
				"	cInvDefine2,\r\n" + 
				"	cInvDefine3,\r\n" + 
				"	cInvDefine4,\r\n" + 
				"	cInvDefine5,\r\n" + 
				"	cInvDefine6,\r\n" + 
				"	cInvDefine7,\r\n" + 
				"	cInvDefine8,\r\n" + 
				"	cInvDefine13,\r\n" + 
				"	CBATCH_bak,\r\n" + 
				"	dDate,\r\n" + 
				"	cDefine32,\r\n" + 
				"	dDays,\r\n" + 
				"	cMemo,\r\n" + 
				"	cPersonName \r\n" + 
				"FROM\r\n" + 
				"	Fs_vi_KuCunStockAgeDay a \r\n" + 
				"WHERE\r\n" + 
				"	a.cWhcode IN ( 'CPC', 'DGCPC', 'KSCPC', 'YCC', 'DGYCC', 'KSC', 'FTC', 'DGBTC', 'KSBTC' );";
		JdbcUtil jdbcUtil = new JdbcUtil();
		con = jdbcUtil.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				UeightStock ueightStock = new UeightStock();
				
				ueightStock.setCwhcode(rs.getString("cWhcode"));
				ueightStock.setCwhname(rs.getString("cwhname"));
				ueightStock.setCinvcode(rs.getString("cInvCode"));
				ueightStock.setCinvname(rs.getString("cinvname"));
				ueightStock.setCinvstd(rs.getString("cInvStd"));
				ueightStock.setCinvmUnit(rs.getString("cinvm_unit"));
				ueightStock.setIinvexchrate(rs.getString("iinvexchrate"));
				ueightStock.setCinvaUnit(rs.getString("cinva_unit"));
				ueightStock.setIquantity(rs.getString("iquantity"));
				ueightStock.setInum(rs.getString("inum"));
				ueightStock.setIcanquantity(rs.getString("iCanQuantity"));
				ueightStock.setIcannum(rs.getString("iCanNum"));
				ueightStock.setCbatch(rs.getString("cBatch"));
				ueightStock.setCfree1(rs.getString("cFree1"));
				ueightStock.setCfree2(rs.getString("cFree2"));
				ueightStock.setCfree4(rs.getString("cFree4"));
				ueightStock.setCfree5(rs.getString("cFree5"));
				ueightStock.setCinvccode(rs.getString("cinvccode"));
				ueightStock.setCinvcname(rs.getString("cinvcname"));
				ueightStock.setCbatchproperty10(rs.getString("cbatchproperty10"));
				ueightStock.setCinvdefine1(rs.getString("cInvDefine1"));
				ueightStock.setCinvdefine2(rs.getString("cInvDefine2"));
				ueightStock.setCinvdefine3(rs.getString("cInvDefine3"));
				ueightStock.setCinvdefine4(rs.getString("cInvDefine4"));
				ueightStock.setCinvdefine5(rs.getString("cInvDefine5"));
				ueightStock.setCinvdefine6(rs.getString("cInvDefine6"));
				ueightStock.setCinvdefine7(rs.getString("cInvDefine7"));
				ueightStock.setCinvdefine8(rs.getString("cInvDefine8"));
				ueightStock.setCinvdefine11(rs.getString("cInvDefine13"));
				ueightStock.setCbatchBak(rs.getString("CBATCH_bak"));
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String dDate = rs.getString("dDate");
				if(StringUtils.isNotBlank(dDate)) {
					ueightStock.setDdate(sdf.parse(dDate));
				}
				ueightStock.setCdefine32(rs.getString("cDefine32"));
				ueightStock.setDdays(rs.getString("dDays"));
				ueightStock.setCmemo(rs.getString("cMemo"));
				ueightStock.setCpersonname(rs.getString("cPersonName"));
				
				ueightStockService.save(ueightStock);
			}
		}catch(SQLException e) {
			LOG.error("提取U8库存数据sql出错"+sql,e);
		}catch(Exception e) {
			LOG.error("同步U8库存数据出错"+sql,e);
		}finally {
			jdbcUtil.close(rs, pstmt, con);
		}
	}
	
}


---------------
第四步，配置 tomcat
	
<context-param>
	<param-name>contextConfigLocation</param-name>
	<param-value>
		classpath*:/spring-context*.xml
		classpath*:/spring-quartz.xml
	</param-value>
</context-param>