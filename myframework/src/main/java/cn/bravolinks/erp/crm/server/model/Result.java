package cn.bravolinks.erp.crm.server.model;

/**
 * 封装返回数据javabean
 *
 * @author yanqin
 * @create 2018-03-05
 **/
public class Result {

	private boolean isok;
	private String msg;
	private Object data;

	public Result() {
	}

	public Result(boolean isok, String msg) {
		this.isok = isok;
		this.msg = msg;
	}

	public Result(boolean isok, String msg, Object data) {
		this.isok = isok;
		this.msg = msg;
		this.data = data;
	}

	public boolean isIsok() {
		return isok;
	}

	public void setIsok(boolean isok) {
		this.isok = isok;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
