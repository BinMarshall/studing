package cn.bravolinks.erp.crm.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CustomerContractInfo {
    /**
     *  id
     */
    private Integer id;

    /**
     *  合同编号
     */
    private String htbh;

    /**
     *  合同名称
     */
    private String htmc;

    /**
     *  签约公司
     */
    private String qygs;

    /**
     *  合同起始日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date htqsrq;

    /**
     *  合同终止日期
     */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date htzzrq;

    /**
     *  客户编号
     */
    private String khbh;

    /**
     *  客户名称
     */
    private String khmc;

    /**
     *  业务属性
     */
    private Integer ywsx;

    /**
     *  人数大于
     */
    private Integer rsdy;

    /**
     *  人数小于
     */
    private Integer rsxy;

    /**
     *  目的地类型
     */
    private Integer mddlx;

    /**
     *  项目金额大于
     */
    private Double xmjedy;

    /**
     *  项目金额小于
     */
    private Double xmjexy;

    /**
     *  指定供应商
     */
    private String zdgys;

    /**
     *  服务费
     */
    private Double fwf;

    /**
     *  币种
     */
    private String fwfbz;

    /**
     *  服务费率
     */
    private Integer fwfl;

    /**
     *  流程是否结束，0未结束 1结束
     */
    private Integer isend;

    /**
     *  创建时间
     */
    private Date createDate;

    /**
     *  更新时间
     */
    private Date updateDate;

    /**
     *  是否删除，0删除  1未删除
     */
    private Integer isDelete;

    /**
     *  流程bindid
     */
    private Integer bindid;

    /**
     *  合同流程bindid
     */
    private Integer htBindid;

	/**
	 * 临时字段，根据 合同版本号 关联取出的合同binddid
	 */
	private Integer tempHTBindid;
	/**
	 * 指定供应商编号
	 */
	private String zdgysbh;
	/**
	 * mll
	 */
//	private String mll;
	/**
	 * 是否上保险
	 */
	private String sfsbx;

	@Override
	public String toString() {
		return "CustomerContractInfo{" +
				"id=" + id +
				", htbh='" + htbh + '\'' +
				", htmc='" + htmc + '\'' +
				", qygs='" + qygs + '\'' +
				", htqsrq=" + htqsrq +
				", htzzrq=" + htzzrq +
				", khbh='" + khbh + '\'' +
				", khmc='" + khmc + '\'' +
				", ywsx=" + ywsx +
				", rsdy=" + rsdy +
				", rsxy=" + rsxy +
				", mddlx=" + mddlx +
				", xmjedy=" + xmjedy +
				", xmjexy=" + xmjexy +
				", zdgys='" + zdgys + '\'' +
				", fwf=" + fwf +
				", fwfbz='" + fwfbz + '\'' +
				", fwfl=" + fwfl +
				", isend=" + isend +
				", createDate=" + createDate +
				", updateDate=" + updateDate +
				", isDelete=" + isDelete +
				", bindid=" + bindid +
				", htBindid=" + htBindid +
				", tempHTBindid=" + tempHTBindid +
				", sfsbx=" + sfsbx +
				", zdgysbh='" + zdgysbh + '\'' +
				'}';
	}

	public Integer getTempHTBindid() {
		return tempHTBindid;
	}

	public void setTempHTBindid(Integer tempHTBindid) {
		this.tempHTBindid = tempHTBindid;
	}

	public String getZdgysbh() {
		return zdgysbh;
	}

	public void setZdgysbh(String zdgysbh) {
		this.zdgysbh = zdgysbh;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHtbh() {
		return htbh;
	}

	public void setHtbh(String htbh) {
		this.htbh = htbh;
	}

	public String getHtmc() {
		return htmc;
	}

	public void setHtmc(String htmc) {
		this.htmc = htmc;
	}

	public String getQygs() {
		return qygs;
	}

	public void setQygs(String qygs) {
		this.qygs = qygs;
	}

	public Date getHtqsrq() {
		return htqsrq;
	}

	public void setHtqsrq(Date htqsrq) {
		this.htqsrq = htqsrq;
	}

	public Date getHtzzrq() {
		return htzzrq;
	}

	public void setHtzzrq(Date htzzrq) {
		this.htzzrq = htzzrq;
	}

	public String getKhbh() {
		return khbh;
	}

	public void setKhbh(String khbh) {
		this.khbh = khbh;
	}

	public String getKhmc() {
		return khmc;
	}

	public void setKhmc(String khmc) {
		this.khmc = khmc;
	}

	public Integer getYwsx() {
		return ywsx;
	}

	public void setYwsx(Integer ywsx) {
		this.ywsx = ywsx;
	}

	public Integer getRsdy() {
		return rsdy;
	}

	public void setRsdy(Integer rsdy) {
		this.rsdy = rsdy;
	}

	public Integer getRsxy() {
		return rsxy;
	}

	public void setRsxy(Integer rsxy) {
		this.rsxy = rsxy;
	}

	public Integer getMddlx() {
		return mddlx;
	}

	public void setMddlx(Integer mddlx) {
		this.mddlx = mddlx;
	}

	public Double getXmjedy() {
		return xmjedy;
	}

	public void setXmjedy(Double xmjedy) {
		this.xmjedy = xmjedy;
	}

	public Double getXmjexy() {
		return xmjexy;
	}

	public void setXmjexy(Double xmjexy) {
		this.xmjexy = xmjexy;
	}

	public String getZdgys() {
		return zdgys;
	}

	public void setZdgys(String zdgys) {
		this.zdgys = zdgys;
	}

	public Double getFwf() {
		return fwf;
	}

	public void setFwf(Double fwf) {
		this.fwf = fwf;
	}

	public String getFwfbz() {
		return fwfbz;
	}

	public void setFwfbz(String fwfbz) {
		this.fwfbz = fwfbz;
	}

	public Integer getFwfl() {
		return fwfl;
	}

	public void setFwfl(Integer fwfl) {
		this.fwfl = fwfl;
	}

	public Integer getIsend() {
		return isend;
	}

	public void setIsend(Integer isend) {
		this.isend = isend;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getBindid() {
		return bindid;
	}

	public void setBindid(Integer bindid) {
		this.bindid = bindid;
	}

	public Integer getHtBindid() {
		return htBindid;
	}

	public void setHtBindid(Integer htBindid) {
		this.htBindid = htBindid;
	}

	public String getSfsbx() {
		return sfsbx;
	}

	public void setSfsbx(String sfsbx) {
		this.sfsbx = sfsbx;
	}

	

}