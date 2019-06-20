# 目录
- 1.使用场景:
- 2.代码实例:

---
# 1.使用场景:
- 系统需要使用一个通用数据接入接口，通过查找配置表数据，对不同类型的数据进行处理。各处理类对自己的类型数据进行处理。最终返回相同类型的数据结果
- 入参相同，回参相同。处理规则不同。



---
# 2.代码实例:
## 过程
- 定义一个通用接口，各接接口类各自实现该接口。通过注入一个List<Service>结合type方法，判断需要调用的是哪个实现类。

- 接口类  
``` java
/**
 * @Description 数据抽取统一通用接口，使用策略模式
 * @Author zhanghaochun
 * @Date 2019年6月17日下午4:15:13
 */
public interface SourceDataService {
	/**
	 * @Description 数据源类型,调用时可根据此方法值 来判断调用哪个实现类
	 * @Author zhanghaochun
	 * @Date 2019年6月17日下午4:10:05
	 * @return
	 */
	String getType();
	
	/**
	 * @Description 数据读取Service类
	 * @Author zhanghaochun
	 * @Date 2019年6月17日下午4:10:13
	 * @param dataExtractConfigVo
	 * @return
	 */
	public List<Map<String,Object>> getDataByConfig(DataExtractConfigVo dataExtractConfigVo);
}
```

- 各实现类
``` java
@Service
public class EfileServiceImpl implements SourceDataService {

	@Override
	public String getType() {
		return ExtractDataTypeEnum.EF.getCode();
	}

	@Override
	public List<Map<String, Object>> getDataByConfig(DataExtractConfigVo dataExtractConfigVo) {

		return null;
	}

}

public class OdsServiceImpl implements SourceDataService {
	@Override
	public String getType() {
		return ExtractDataTypeEnum.ODS.getCode();
	}

	@Override
	public List<Map<String, Object>> getDataByConfig(DataExtractConfigVo dataExtractConfigVo) {

		return null;
	}
}
//...更多实现类
```
- 调用类
``` java
/**
 * @Description 数据ETL主处理入口类
 * @Author zhanghaochun
 * @Date 2019年6月18日下午4:29:17
 */
@Service
public class DataEtlServiceImpl implements DataEtlService {

	@Autowired
	private DataExtractConfigService dataExtractConfigService;

	@Autowired
	private List<SourceDataService> sourceDataServiceList;

	/**
	 * @Description 数据通用处理，包括数据的ETL操作
	 * @Author zhanghaochun
	 * @Date 2019年6月17日上午9:15:17
	 */
	@Override
	public void handleData() {
		// 1.查询数据抽取配置表
		List<DataExtractConfigVo> dataExtractConfigVoList = dataExtractConfigService.findAll();

		// 2.根据数据抽取配置表，进行对数据的ETL数据操作
		dataExtractConfigVoList.forEach(dataExtractConfigVo -> etlData(dataExtractConfigVo));

	}

	/**
	 * @Description 开始对数据执行etl操作
	 * @Author zhanghaochun
	 * @Date 2019年6月17日下午2:56:23
	 * @param dataExtractConfigVo
	 * @return
	 */
	private void etlData(DataExtractConfigVo dataExtractConfigVo) {
		String type = dataExtractConfigVo.getType();
		// 结合策略模式进行各类数据源服务调用进行数据处理
		sourceDataServiceList.forEach(sourceDataService -> {
			// 如果类型为实现类的类型，则调用对应的实现类去实现
			if (type.equals(sourceDataService.getType())) {
				// 1.Extract抽取数据操作
				List<Map<String, Object>> dataList = sourceDataService.getDataByConfig(dataExtractConfigVo);

				// 2.Transf转换数据操作 TODO调用数据转换服务进行数据转换
				// tranfService(dataExtractConfigVo,dataList);

			}
		});

	}

}
```