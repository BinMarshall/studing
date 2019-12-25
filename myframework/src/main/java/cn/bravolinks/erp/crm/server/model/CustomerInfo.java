package cn.bravolinks.erp.crm.server.model;

import java.util.Date;

public class CustomerInfo {
    /**
     *  ID
     */
    private Integer id;

    /**
     *  客户名称
     */
    private String khmc;

    /**
     * 客户编号
     */
    private String khbh;

    /**
     * 客户简称
     */
    private String khjc;

    /**
     *  单位性质
     */
    private String dwxz;

    /**
     * 行业大类
     */
    private String hydl;

    /**
     *  行业小类
     */
    private String hyxl;

    /**
     *  客户状态
     */
    private String khzt;

    /**
     *  主管机构
     */
    private String zgjg;

    /**
     *  总计电话
     */
    private String zjdh;

    /**
     *  官方网站
     */
    private String gfwz;

    /**
     *  办公地址
     */
    private String bgdz;

    /**
     *  bindid
     */
    private Integer bindid;

    /**
     *  0未结束 1结束
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
     *  1未删除 0删除
     */
    private Integer isDelete;


    @Override
    public String toString() {
        return "CustomerInfo{" +
                "id=" + id +
                ", khmc='" + khmc + '\'' +
                ", khbh='" + khbh + '\'' +
                ", khjc='" + khjc + '\'' +
                ", dwxz='" + dwxz + '\'' +
                ", hydl='" + hydl + '\'' +
                ", hyxl='" + hyxl + '\'' +
                ", khzt='" + khzt + '\'' +
                ", zgjg='" + zgjg + '\'' +
                ", zjdh='" + zjdh + '\'' +
                ", gfwz='" + gfwz + '\'' +
                ", bgdz='" + bgdz + '\'' +
                ", bindid=" + bindid +
                ", isend=" + isend +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", isDelete=" + isDelete +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKhmc() {
        return khmc;
    }

    public void setKhmc(String khmc) {
        this.khmc = khmc;
    }

    public String getKhbh() {
        return khbh;
    }

    public void setKhbh(String khbh) {
        this.khbh = khbh;
    }

    public String getKhjc() {
        return khjc;
    }

    public void setKhjc(String khjc) {
        this.khjc = khjc;
    }

    public String getDwxz() {
        return dwxz;
    }

    public void setDwxz(String dwxz) {
        this.dwxz = dwxz;
    }

    public String getHydl() {
        return hydl;
    }

    public void setHydl(String hydl) {
        this.hydl = hydl;
    }

    public String getHyxl() {
        return hyxl;
    }

    public void setHyxl(String hyxl) {
        this.hyxl = hyxl;
    }

    public String getKhzt() {
        return khzt;
    }

    public void setKhzt(String khzt) {
        this.khzt = khzt;
    }

    public String getZgjg() {
        return zgjg;
    }

    public void setZgjg(String zgjg) {
        this.zgjg = zgjg;
    }

    public String getZjdh() {
        return zjdh;
    }

    public void setZjdh(String zjdh) {
        this.zjdh = zjdh;
    }

    public String getGfwz() {
        return gfwz;
    }

    public void setGfwz(String gfwz) {
        this.gfwz = gfwz;
    }

    public String getBgdz() {
        return bgdz;
    }

    public void setBgdz(String bgdz) {
        this.bgdz = bgdz;
    }

    public Integer getBindid() {
        return bindid;
    }

    public void setBindid(Integer bindid) {
        this.bindid = bindid;
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
}