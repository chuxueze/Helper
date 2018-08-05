如果做数据校验或者逻辑判断非法操作，可以运用异常的技巧。
这样可以简化代码，对代码分层更有帮助。

如:
判断参数是否为空时，可以进行抛出数据异常
throw new IllegalArgumentException("数据不能为空");
然后在 Controller 层捕获相应的异常，做相应的操作。友好提示用户。

	@RequestMapping("change")
	@ResponseBody
	public HttpEntity change(@RequestBody JSONObject jsonObject) {
		String resultMsg = null;
		SaleOrderBase saleOrderBase = JSONObject.toJavaObject(jsonObject.getJSONObject("saleOrderBase"),
				SaleOrderBase.class);
		SaleOrderChangeLog saleOrderChangeLog = JSONObject.toJavaObject(jsonObject.getJSONObject("saleOrderChangeLog"),
				SaleOrderChangeLog.class);
		
		try {
			checkDataVersion(saleOrderBase);
			changeCheckData(saleOrderBase,saleOrderChangeLog);
			
			User user = userUtils.getUser();
			saleOrderBaseService.changeValid(saleOrderBase,user,saleOrderChangeLog);

			resultMsg = "变更成功";
			return new HttpEntity(HttpStatus.OK, true, resultMsg, null);
		} 
		catch (IllegalArgumentException e) {
			return new HttpEntity(HttpStatus.OK,false,e.getMessage(),null);
		} 
		catch (Exception e) {
			resultMsg = "变更失败";
			return new HttpEntity(HttpStatus.OK, false, resultMsg, e);
		}
	}	


异常的抛出可以放在 service ,这样就能把大部分业务逻辑放在 service 层做处理。
controller 只负责数据的传输与结果返回。