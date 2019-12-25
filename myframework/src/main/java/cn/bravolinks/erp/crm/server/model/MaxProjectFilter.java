package cn.bravolinks.erp.crm.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 大项目过滤bean
 *
 * @author yanqin
 * @create 2018-03-23
 **/
public class MaxProjectFilter {
	/**
	 * 每页数量
	 */
	private Integer rows;
	/**
	 * 第几页
	 */
	private Integer page;
	/**
	 * 从多少条开始
	 */
	private Integer min;
	/**
	 * 从多少条结束
	 */
	private Integer max;
	/**
	 * 客户名称
	 */
	private String cusname;
	/**
	 * 行业类型
	 */
	private Integer hylx;
	/**
	 * 行业小类
	 */
	private Integer hyxl;
	/**
	 * 事业部
	 */
	private String division;
	/**
	 * 主责部门
	 */
	private Integer zzbm;
	/**
	 * 主管领导
	 */
	private Integer zgld;
	/**
	 * 服务类型
	 */
	private Integer serviceType;
	/**
	 * 区域类型
	 */
	private Integer regionType;
	/**
	 * 举办地
	 */
	private String city;
	/**
	 * 项目人数
	 */
	private Integer xmrs;
	/**
	 * 项目中标状态
	 */
	private Integer[] proStatus;
	/**
	 * 开始时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date startBefore;
	/**
	 * 开始时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date startAfter;
	/**
	 * 结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date endBefore;
	/**
	 * 结束时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date endAfter;
	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date updateBefore;
	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date updateAfter;
	/**
	 * 查询人
	 */
	private String userid;
	/**
	 * 当前时间
	 */
	private Date currDate;
	/**
	 * 项目名称
	 */
	private String proname;
	/**
	 * 填报人
	 */
	private String creuser;
	/**
	 * 服务内容
	 */
	private Integer[] serviceContent;
	/**
	 * 新的填报人
	 */
	private String newCreuser;

	public String getNewCreuser() {
		return newCreuser;
	}

	public void setNewCreuser(String newCreuser) {
		this.newCreuser = newCreuser;
	}

	public Integer[] getServiceContent() {
		return serviceContent;
	}

	public void setServiceContent(Integer[] serviceContent) {
		this.serviceContent = serviceContent;
	}

	public String getCreuser() {
		return creuser;
	}

	public void setCreuser(String creuser) {
		this.creuser = creuser;
	}

	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	public Date getCurrDate() {
		return currDate;
	}

	public void setCurrDate(Date currDate) {
		this.currDate = currDate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}


	public Integer getHylx() {
		return hylx;
	}

	public void setHylx(Integer hylx) {
		this.hylx = hylx;
	}

	public Integer getHyxl() {
		return hyxl;
	}

	public void setHyxl(Integer hyxl) {
		this.hyxl = hyxl;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public Integer getZzbm() {
		return zzbm;
	}

	public void setZzbm(Integer zzbm) {
		this.zzbm = zzbm;
	}

	public Integer getZgld() {
		return zgld;
	}

	public void setZgld(Integer zgld) {
		this.zgld = zgld;
	}

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}

	public Integer getRegionType() {
		return regionType;
	}

	public void setRegionType(Integer regionType) {
		this.regionType = regionType;
	}

	public String getCusname() {
		return cusname;
	}

	public void setCusname(String cusname) {
		this.cusname = cusname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getXmrs() {
		return xmrs;
	}

	public void setXmrs(Integer xmrs) {
		this.xmrs = xmrs;
	}

	public Integer[] getProStatus() {
		return proStatus;
	}

	public void setProStatus(Integer[] proStatus) {
		this.proStatus = proStatus;
	}

	public Date getStartBefore() {
		return startBefore;
	}

	public void setStartBefore(Date startBefore) {
		this.startBefore = startBefore;
	}

	public Date getStartAfter() {
		return startAfter;
	}

	public void setStartAfter(Date startAfter) {
		this.startAfter = startAfter;
	}

	public Date getEndBefore() {
		return endBefore;
	}

	public void setEndBefore(Date endBefore) {
		this.endBefore = endBefore;
	}

	public Date getEndAfter() {
		return endAfter;
	}

	public void setEndAfter(Date endAfter) {
		this.endAfter = endAfter;
	}

	public Date getUpdateBefore() {
		return updateBefore;
	}

	public void setUpdateBefore(Date updateBefore) {
		this.updateBefore = updateBefore;
	}

	public Date getUpdateAfter() {
		return updateAfter;
	}

	public void setUpdateAfter(Date updateAfter) {
		this.updateAfter = updateAfter;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}
}
