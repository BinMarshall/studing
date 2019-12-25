package cn.bravolinks.erp.crm.server.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 大项目跟进记录表-bean
 * @author yanqin
 */
public class BoCrmUpdrecord implements Serializable{
    private static final long serialVersionUID = -5616791625953275978L;
    /**
     *  主键,所属表字段为BO_CRM_UPDRECORD.ID
     */
    private Integer id;

    /**
     *  更新时间,所属表字段为BO_CRM_UPDRECORD.UPDTIME
     */
    private Date updtime;

    /**
     *  操作人,所属表字段为BO_CRM_UPDRECORD.USER
     */
    private String user;

    /**
     *  更新类型  1 更新
     */
    private Integer updtype;

    /**
     *  更新字段,所属表字段为BO_CRM_UPDRECORD.UPDFIELD
     */
    private String updfield;

    /**
     *  更新前内容,所属表字段为BO_CRM_UPDRECORD.UPDBEFORE
     */
    private String updbefore;

    /**
     *  更新后内容,所属表字段为BO_CRM_UPDRECORD.UPDAFTER
     */
    private String updafter;

    /**
     *  项目表主键,所属表字段为BO_CRM_UPDRECORD.PROID
     */
    private Integer proid;

    /**
     * 更新前代码
     */
    private String updbeforeCode;
    /**
     * 更新后代码
     */
    private String updafterCode;

    public String getUpdbeforeCode() {
        return updbeforeCode;
    }

    public void setUpdbeforeCode(String updbeforeCode) {
        this.updbeforeCode = updbeforeCode;
    }

    public String getUpdafterCode() {
        return updafterCode;
    }

    public void setUpdafterCode(String updafterCode) {
        this.updafterCode = updafterCode;
    }

    /**
     * 获取 主键 字段:BO_CRM_UPDRECORD.ID
     *
     * @return BO_CRM_UPDRECORD.ID, 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 主键 字段:BO_CRM_UPDRECORD.ID
     *
     * @param id BO_CRM_UPDRECORD.ID, 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 更新时间 字段:BO_CRM_UPDRECORD.UPDTIME
     *
     * @return BO_CRM_UPDRECORD.UPDTIME, 更新时间
     */
    public Date getUpdtime() {
        return updtime;
    }

    /**
     * 设置 更新时间 字段:BO_CRM_UPDRECORD.UPDTIME
     *
     * @param updtime BO_CRM_UPDRECORD.UPDTIME, 更新时间
     */
    public void setUpdtime(Date updtime) {
        this.updtime = updtime;
    }

    /**
     * 获取 操作人 字段:BO_CRM_UPDRECORD.USER
     *
     * @return BO_CRM_UPDRECORD.USER, 操作人
     */
    public String getUser() {
        return user;
    }

    /**
     * 设置 操作人 字段:BO_CRM_UPDRECORD.USER
     *
     * @param user BO_CRM_UPDRECORD.USER, 操作人
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * 获取 更新类型 字段:BO_CRM_UPDRECORD.UPDTYPE
     *
     * @return BO_CRM_UPDRECORD.UPDTYPE, 更新类型
     */
    public Integer getUpdtype() {
        return updtype;
    }

    /**
     * 设置 更新类型 字段:BO_CRM_UPDRECORD.UPDTYPE
     *
     * @param updtype BO_CRM_UPDRECORD.UPDTYPE, 更新类型
     */
    public void setUpdtype(Integer updtype) {
        this.updtype = updtype;
    }

    /**
     * 获取 更新字段 字段:BO_CRM_UPDRECORD.UPDFIELD
     *
     * @return BO_CRM_UPDRECORD.UPDFIELD, 更新字段
     */
    public String getUpdfield() {
        return updfield;
    }

    /**
     * 设置 更新字段 字段:BO_CRM_UPDRECORD.UPDFIELD
     *
     * @param updfield BO_CRM_UPDRECORD.UPDFIELD, 更新字段
     */
    public void setUpdfield(String updfield) {
        this.updfield = updfield;
    }

    /**
     * 获取 更新前内容 字段:BO_CRM_UPDRECORD.UPDBEFORE
     *
     * @return BO_CRM_UPDRECORD.UPDBEFORE, 更新前内容
     */
    public String getUpdbefore() {
        return updbefore;
    }

    /**
     * 设置 更新前内容 字段:BO_CRM_UPDRECORD.UPDBEFORE
     *
     * @param updbefore BO_CRM_UPDRECORD.UPDBEFORE, 更新前内容
     */
    public void setUpdbefore(String updbefore) {
        this.updbefore = updbefore;
    }

    /**
     * 获取 更新后内容 字段:BO_CRM_UPDRECORD.UPDAFTER
     *
     * @return BO_CRM_UPDRECORD.UPDAFTER, 更新后内容
     */
    public String getUpdafter() {
        return updafter;
    }

    /**
     * 设置 更新后内容 字段:BO_CRM_UPDRECORD.UPDAFTER
     *
     * @param updafter BO_CRM_UPDRECORD.UPDAFTER, 更新后内容
     */
    public void setUpdafter(String updafter) {
        this.updafter = updafter;
    }

    /**
     * 获取 项目表主键 字段:BO_CRM_UPDRECORD.PROID
     *
     * @return BO_CRM_UPDRECORD.PROID, 项目表主键
     */
    public Integer getProid() {
        return proid;
    }

    /**
     * 设置 项目表主键 字段:BO_CRM_UPDRECORD.PROID
     *
     * @param proid BO_CRM_UPDRECORD.PROID, 项目表主键
     */
    public void setProid(Integer proid) {
        this.proid = proid;
    }
}