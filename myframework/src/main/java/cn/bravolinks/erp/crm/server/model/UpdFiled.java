package cn.bravolinks.erp.crm.server.model;

/**
 * 大项目更新字段
 *
 * @author yanqin
 * @create 2018-03-28
 **/
public class UpdFiled {
	/**
	 * 字段
	 */
	private String filed;
	/**
	 * 字段名称
	 */
	private String filedName;
	/**
	 * 是否需要前端显示
	 */
	private Integer isClientShow = 0;
	/**
	 * 是否需要保存代码
	 */
	private Integer isSaveCode = 0;

	public UpdFiled(String filed, String filedName) {
		this.filed = filed;
		this.filedName = filedName;
	}

	public UpdFiled(String filed, String filedName, Integer isClientShow, Integer isSaveCode) {
		this.filed = filed;
		this.filedName = filedName;
		this.isClientShow = isClientShow;
		this.isSaveCode = isSaveCode;
	}

	public String getFiled() {
		return filed;
	}

	public void setFiled(String filed) {
		this.filed = filed;
	}

	public String getFiledName() {
		return filedName;
	}

	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}

	public Integer getIsClientShow() {
		return isClientShow;
	}

	public void setIsClientShow(Integer isClientShow) {
		this.isClientShow = isClientShow;
	}

	public Integer getIsSaveCode() {
		return isSaveCode;
	}

	public void setIsSaveCode(Integer isSaveCode) {
		this.isSaveCode = isSaveCode;
	}
}
