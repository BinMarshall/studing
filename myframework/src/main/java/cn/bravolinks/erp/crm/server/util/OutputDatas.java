package cn.bravolinks.erp.crm.server.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class OutputDatas {
	static public String RESULT_SUCCESS = "success";
	static public String RESULT_FAILURE = "failure";

	/** 返回结果：成功 OR 失败 */
	private String result = "";
	/** 错误信息说明 */
	private String errinfo = "";
	/** 其他信息说明 */
	private String othinfo = "";
	/** 返回的数据结果 */
	private Object datas = null;
	/** 其他返回的数据  */
	private Map<String, Object> othdatas = null;

	public OutputDatas(){

	}

	public OutputDatas(String result){
		this.result = result;
	}

	public OutputDatas(String result, String errinfo){
		this.result = result;
		this.errinfo = errinfo;
	}

	public OutputDatas(String result, Object datas){
		this.result = result;
		this.datas = datas;
	}
	
	public String toString(){
		JSONObject object = new JSONObject();
		object.put("result", this.result);
		object.put("errinfo", this.errinfo);
		object.put("othinfo", this.othinfo);
		object.put("datas", this.datas);
		object.put("othdatas", this.othdatas);
		return object.toJSONString();
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Object getDatas() {
		return datas;
	}

	public void setDatas(Object datas) {
		this.datas = datas;
	}
	
	public String getErrinfo() {
		return errinfo;
	}

	public void setErrinfo(String errinfo) {
		this.errinfo = errinfo;
	}

	public String getOthinfo() {
		return othinfo;
	}

	public void setOthinfo(String othinfo) {
		this.othinfo = othinfo;
	}

	public Map<String, Object> getOthdatas() {
		return othdatas;
	}

	public void setOthdatas(Map<String, Object> othdatas) {
		this.othdatas = othdatas;
	}
}
