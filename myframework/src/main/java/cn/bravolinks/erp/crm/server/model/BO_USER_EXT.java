package cn.bravolinks.erp.crm.server.model;

import java.util.Date;

public class BO_USER_EXT {
	//用户userid 比如 wuq
	private Object userid;
	//用户ID唯一
	private Integer id;
	//角色id
	private Object roleid;
	//用户名
	private Object username;
	//Email
	private Object email;
	//电话
	private Object mobile;
	//用户部门id
	private Object departmentid;
	//用户部门名
	private Object departmentname;
	//部门号
	private Object departmentno;
	//上级部门id
	private Object parentdepartmentid;
	//用户公司代码
	private Object gsdm;
	//用户公司名称
	private Object gsmc;
	//用户所属事业部
	private Object sssyb;
	//用户公司名称代码
	private Object sssybdm;
	//公司简称
	private Object gsjc;
	//用户公司全称
	private Object gsqc;
	//用户公司ID
	private Object gsid;
	public Object getUserid() {
		return userid;
	}
	public void setUserid(Object userid) {
		this.userid = userid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Object getRoleid() {
		return roleid;
	}
	public void setRoleid(Object roleid) {
		this.roleid = roleid;
	}
	public Object getUsername() {
		return username;
	}
	public void setUsername(Object username) {
		this.username = username;
	}
	public Object getEmail() {
		return email;
	}
	public void setEmail(Object email) {
		this.email = email;
	}
	public Object getMobile() {
		return mobile;
	}
	public void setMobile(Object mobile) {
		this.mobile = mobile;
	}
	public Object getDepartmentid() {
		return departmentid;
	}
	public void setDepartmentid(Object departmentid) {
		this.departmentid = departmentid;
	}
	public Object getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(Object departmentname) {
		this.departmentname = departmentname;
	}
	public Object getDepartmentno() {
		return departmentno;
	}
	public void setDepartmentno(Object departmentno) {
		this.departmentno = departmentno;
	}
	public Object getParentdepartmentid() {
		return parentdepartmentid;
	}
	public void setParentdepartmentid(Object parentdepartmentid) {
		this.parentdepartmentid = parentdepartmentid;
	}
	public Object getGsdm() {
		return gsdm;
	}
	public void setGsdm(Object gsdm) {
		this.gsdm = gsdm;
	}
	public Object getGsmc() {
		return gsmc;
	}
	public void setGsmc(Object gsmc) {
		this.gsmc = gsmc;
	}
	public Object getSssyb() {
		return sssyb;
	}
	public void setSssyb(Object sssyb) {
		this.sssyb = sssyb;
	}
	public Object getSssybdm() {
		return sssybdm;
	}
	public void setSssybdm(Object sssybdm) {
		this.sssybdm = sssybdm;
	}
	public Object getGsjc() {
		return gsjc;
	}
	public void setGsjc(Object gsjc) {
		this.gsjc = gsjc;
	}
	public Object getGsqc() {
		return gsqc;
	}
	public void setGsqc(Object gsqc) {
		this.gsqc = gsqc;
	}
	public Object getGsid() {
		return gsid;
	}
	public void setGsid(Object gsid) {
		this.gsid = gsid;
	}
	
}