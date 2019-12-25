package cn.bravolinks.erp.crm.server.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author yanqin
 */
public class BoCrmProject implements Serializable {
    private static final long serialVersionUID = -8497994261147849507L;
    /**
     *  主键,所属表字段为BO_CRM_PROJECT.ID
     */
    private Integer id;

    /**
     *  项目来源,所属表字段为BO_CRM_PROJECT.PROSOURCE
     */
    private Integer prosource;

    /**
     *  创建人,所属表字段为BO_CRM_PROJECT.CREUSER
     */
    private String creuser;

    /**
     *  更新人,所属表字段为BO_CRM_PROJECT.UPDUSER
     */
    private String upduser;

    /**
     *  创建时间,所属表字段为BO_CRM_PROJECT.CRETIME
     */
    private Date cretime;

    /**
     *  更新时间,所属表字段为BO_CRM_PROJECT.UPDTIME
     */
    private Date updtime;

    /**
     *  客户ID,所属表字段为BO_CRM_PROJECT.CUSNAME
     */
    private String cusname;

    /**
     *  项目名称,所属表字段为BO_CRM_PROJECT.PRONAME
     */
    private String proname;

    /**
     *  服务类型,所属表字段为BO_CRM_PROJECT.SERVICE_TYPE
     */
    private Integer serviceType;

    /**
     *  服务内容,所属表字段为BO_CRM_PROJECT.SERVICE_CONTENT
     */
    private String serviceContent;

    /**
     *  预计出发日期-年,所属表字段为BO_CRM_PROJECT.START_DATE_YEAR
     */
    private Integer startDateYear;

    /**
     *  预计出发日期-月,所属表字段为BO_CRM_PROJECT.START_DATE_MONTH
     */
    private Integer startDateMonth;

    /**
     *  预计出发日期-日,所属表字段为BO_CRM_PROJECT.START_DATE_DAY
     */
    private Integer startDateDay;

    /**
     *  预计结束日期-年,所属表字段为BO_CRM_PROJECT.END_DATE_YEAR
     */
    private Integer endDateYear;

    /**
     *  预计结束日期-月,所属表字段为BO_CRM_PROJECT.END_DATE_MONTH
     */
    private Integer endDateMonth;

    /**
     *  预计结束日期-日,所属表字段为BO_CRM_PROJECT.END_DATE_DAY
     */
    private Integer endDateDay;

    /**
     *  项目区域类型,所属表字段为BO_CRM_PROJECT.REGION_TYPE
     */
    private Integer regionType;

    /**
     *  项目举办地,所属表字段为BO_CRM_PROJECT.CITY
     */
    private String city;

    /**
     *  预估人数,所属表字段为BO_CRM_PROJECT.YGRS
     */
    private Integer ygrs;

    /**
     *  项目金额,所属表字段为BO_CRM_PROJECT.MONEY
     */
    private Double money;

    /**
     *  项目难点,所属表字段为BO_CRM_PROJECT.PROBLEM
     */
    private String problem;

    /**
     *  期望协助部门,所属表字段为BO_CRM_PROJECT.EXPECT_ASSIST_DEPT
     */
    private String expectAssistDept;

    /**
     *  最新项目状态,所属表字段为BO_CRM_PROJECT.NEW_STATUS
     */
    private Integer newStatus;

    /**
     *  影像资料收集渠道,所属表字段为BO_CRM_PROJECT.DATA_SOURCE
     */
    private Integer dataSource;

    /**
     *  项目状态说明,所属表字段为BO_CRM_PROJECT.STATUS_DESC
     */
    private String statusDesc;

    /**
     *  主责部门,所属表字段为BO_CRM_PROJECT.ZZBM
     */
    private Integer zzbm;

    /**
     *  所属公司,所属表字段为BO_CRM_PROJECT.SSGS
     */
    private Integer ssgs;

    /**
     *  项目负责人,所属表字段为BO_CRM_PROJECT.XMFZR
     */
    private Integer xmfzr;

    /**
     *  项目主管领导,所属表字段为BO_CRM_PROJECT.XMZGLD
     */
    private Integer xmzgld;

    /**
     *  协同部门,所属表字段为BO_CRM_PROJECT.XTBM
     */
    private String xtbm;

    /**
     *  备注,所属表字段为BO_CRM_PROJECT.NOTE
     */
    private String note;
    /**
     * 行业代码
     */
    private String hydm;
    /**
     * 行业小类代码
     */
    private String hyxldm;

    public String getHydm() {
        return hydm;
    }

    public void setHydm(String hydm) {
        this.hydm = hydm;
    }

    public String getHyxldm() {
        return hyxldm;
    }

    public void setHyxldm(String hyxldm) {
        this.hyxldm = hyxldm;
    }

    private List<BoCrmProjectMember> projectMembers;

    public List<BoCrmProjectMember> getProjectMembers() {
        return projectMembers;
    }

    public void setProjectMembers(List<BoCrmProjectMember> projectMembers) {
        this.projectMembers = projectMembers;
    }

    /**
     * 获取 主键 字段:BO_CRM_PROJECT.ID
     *
     * @return BO_CRM_PROJECT.ID, 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置 主键 字段:BO_CRM_PROJECT.ID
     *
     * @param id BO_CRM_PROJECT.ID, 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取 项目来源 字段:BO_CRM_PROJECT.PROSOURCE
     *
     * @return BO_CRM_PROJECT.PROSOURCE, 项目来源
     */
    public Integer getProsource() {
        return prosource;
    }

    /**
     * 设置 项目来源 字段:BO_CRM_PROJECT.PROSOURCE
     *
     * @param prosource BO_CRM_PROJECT.PROSOURCE, 项目来源
     */
    public void setProsource(Integer prosource) {
        this.prosource = prosource;
    }

    /**
     * 获取 创建人 字段:BO_CRM_PROJECT.CREUSER
     *
     * @return BO_CRM_PROJECT.CREUSER, 创建人
     */
    public String getCreuser() {
        return creuser;
    }

    /**
     * 设置 创建人 字段:BO_CRM_PROJECT.CREUSER
     *
     * @param creuser BO_CRM_PROJECT.CREUSER, 创建人
     */
    public void setCreuser(String creuser) {
        this.creuser = creuser;
    }

    /**
     * 获取 更新人 字段:BO_CRM_PROJECT.UPDUSER
     *
     * @return BO_CRM_PROJECT.UPDUSER, 更新人
     */
    public String getUpduser() {
        return upduser;
    }

    /**
     * 设置 更新人 字段:BO_CRM_PROJECT.UPDUSER
     *
     * @param upduser BO_CRM_PROJECT.UPDUSER, 更新人
     */
    public void setUpduser(String upduser) {
        this.upduser = upduser;
    }

    /**
     * 获取 创建时间 字段:BO_CRM_PROJECT.CRETIME
     *
     * @return BO_CRM_PROJECT.CRETIME, 创建时间
     */
    public Date getCretime() {
        return cretime;
    }

    /**
     * 设置 创建时间 字段:BO_CRM_PROJECT.CRETIME
     *
     * @param cretime BO_CRM_PROJECT.CRETIME, 创建时间
     */
    public void setCretime(Date cretime) {
        this.cretime = cretime;
    }

    /**
     * 获取 更新时间 字段:BO_CRM_PROJECT.UPDTIME
     *
     * @return BO_CRM_PROJECT.UPDTIME, 更新时间
     */
    public Date getUpdtime() {
        return updtime;
    }

    /**
     * 设置 更新时间 字段:BO_CRM_PROJECT.UPDTIME
     *
     * @param updtime BO_CRM_PROJECT.UPDTIME, 更新时间
     */
    public void setUpdtime(Date updtime) {
        this.updtime = updtime;
    }

    /**
     * 获取 客户ID 字段:BO_CRM_PROJECT.CUSNAME
     *
     * @return BO_CRM_PROJECT.CUSNAME, 客户ID
     */
    public String getCusname() {
        return cusname;
    }

    /**
     * 设置 客户ID 字段:BO_CRM_PROJECT.CUSNAME
     *
     * @param cusname BO_CRM_PROJECT.CUSNAME, 客户ID
     */
    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    /**
     * 获取 项目名称 字段:BO_CRM_PROJECT.PRONAME
     *
     * @return BO_CRM_PROJECT.PRONAME, 项目名称
     */
    public String getProname() {
        return proname;
    }

    /**
     * 设置 项目名称 字段:BO_CRM_PROJECT.PRONAME
     *
     * @param proname BO_CRM_PROJECT.PRONAME, 项目名称
     */
    public void setProname(String proname) {
        this.proname = proname;
    }

    /**
     * 获取 服务类型 字段:BO_CRM_PROJECT.SERVICE_TYPE
     *
     * @return BO_CRM_PROJECT.SERVICE_TYPE, 服务类型
     */
    public Integer getServiceType() {
        return serviceType;
    }

    /**
     * 设置 服务类型 字段:BO_CRM_PROJECT.SERVICE_TYPE
     *
     * @param serviceType BO_CRM_PROJECT.SERVICE_TYPE, 服务类型
     */
    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * 获取 服务内容 字段:BO_CRM_PROJECT.SERVICE_CONTENT
     *
     * @return BO_CRM_PROJECT.SERVICE_CONTENT, 服务内容
     */
    public String getServiceContent() {
        return serviceContent;
    }

    /**
     * 设置 服务内容 字段:BO_CRM_PROJECT.SERVICE_CONTENT
     *
     * @param serviceContent BO_CRM_PROJECT.SERVICE_CONTENT, 服务内容
     */
    public void setServiceContent(String serviceContent) {
        this.serviceContent = serviceContent;
    }

    /**
     * 获取 预计出发日期-年 字段:BO_CRM_PROJECT.START_DATE_YEAR
     *
     * @return BO_CRM_PROJECT.START_DATE_YEAR, 预计出发日期-年
     */
    public Integer getStartDateYear() {
        return startDateYear;
    }

    /**
     * 设置 预计出发日期-年 字段:BO_CRM_PROJECT.START_DATE_YEAR
     *
     * @param startDateYear BO_CRM_PROJECT.START_DATE_YEAR, 预计出发日期-年
     */
    public void setStartDateYear(Integer startDateYear) {
        this.startDateYear = startDateYear;
    }

    /**
     * 获取 预计出发日期-月 字段:BO_CRM_PROJECT.START_DATE_MONTH
     *
     * @return BO_CRM_PROJECT.START_DATE_MONTH, 预计出发日期-月
     */
    public Integer getStartDateMonth() {
        return startDateMonth;
    }

    /**
     * 设置 预计出发日期-月 字段:BO_CRM_PROJECT.START_DATE_MONTH
     *
     * @param startDateMonth BO_CRM_PROJECT.START_DATE_MONTH, 预计出发日期-月
     */
    public void setStartDateMonth(Integer startDateMonth) {
        this.startDateMonth = startDateMonth;
    }

    /**
     * 获取 预计出发日期-日 字段:BO_CRM_PROJECT.START_DATE_DAY
     *
     * @return BO_CRM_PROJECT.START_DATE_DAY, 预计出发日期-日
     */
    public Integer getStartDateDay() {
        return startDateDay;
    }

    /**
     * 设置 预计出发日期-日 字段:BO_CRM_PROJECT.START_DATE_DAY
     *
     * @param startDateDay BO_CRM_PROJECT.START_DATE_DAY, 预计出发日期-日
     */
    public void setStartDateDay(Integer startDateDay) {
        this.startDateDay = startDateDay;
    }

    /**
     * 获取 预计结束日期-年 字段:BO_CRM_PROJECT.END_DATE_YEAR
     *
     * @return BO_CRM_PROJECT.END_DATE_YEAR, 预计结束日期-年
     */
    public Integer getEndDateYear() {
        return endDateYear;
    }

    /**
     * 设置 预计结束日期-年 字段:BO_CRM_PROJECT.END_DATE_YEAR
     *
     * @param endDateYear BO_CRM_PROJECT.END_DATE_YEAR, 预计结束日期-年
     */
    public void setEndDateYear(Integer endDateYear) {
        this.endDateYear = endDateYear;
    }

    /**
     * 获取 预计结束日期-月 字段:BO_CRM_PROJECT.END_DATE_MONTH
     *
     * @return BO_CRM_PROJECT.END_DATE_MONTH, 预计结束日期-月
     */
    public Integer getEndDateMonth() {
        return endDateMonth;
    }

    /**
     * 设置 预计结束日期-月 字段:BO_CRM_PROJECT.END_DATE_MONTH
     *
     * @param endDateMonth BO_CRM_PROJECT.END_DATE_MONTH, 预计结束日期-月
     */
    public void setEndDateMonth(Integer endDateMonth) {
        this.endDateMonth = endDateMonth;
    }

    /**
     * 获取 预计结束日期-日 字段:BO_CRM_PROJECT.END_DATE_DAY
     *
     * @return BO_CRM_PROJECT.END_DATE_DAY, 预计结束日期-日
     */
    public Integer getEndDateDay() {
        return endDateDay;
    }

    /**
     * 设置 预计结束日期-日 字段:BO_CRM_PROJECT.END_DATE_DAY
     *
     * @param endDateDay BO_CRM_PROJECT.END_DATE_DAY, 预计结束日期-日
     */
    public void setEndDateDay(Integer endDateDay) {
        this.endDateDay = endDateDay;
    }

    /**
     * 获取 项目区域类型 字段:BO_CRM_PROJECT.REGION_TYPE
     *
     * @return BO_CRM_PROJECT.REGION_TYPE, 项目区域类型
     */
    public Integer getRegionType() {
        return regionType;
    }

    /**
     * 设置 项目区域类型 字段:BO_CRM_PROJECT.REGION_TYPE
     *
     * @param regionType BO_CRM_PROJECT.REGION_TYPE, 项目区域类型
     */
    public void setRegionType(Integer regionType) {
        this.regionType = regionType;
    }

    /**
     * 获取 项目举办地 字段:BO_CRM_PROJECT.CITY
     *
     * @return BO_CRM_PROJECT.CITY, 项目举办地
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置 项目举办地 字段:BO_CRM_PROJECT.CITY
     *
     * @param city BO_CRM_PROJECT.CITY, 项目举办地
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取 预估人数 字段:BO_CRM_PROJECT.YGRS
     *
     * @return BO_CRM_PROJECT.YGRS, 预估人数
     */
    public Integer getYgrs() {
        return ygrs;
    }

    /**
     * 设置 预估人数 字段:BO_CRM_PROJECT.YGRS
     *
     * @param ygrs BO_CRM_PROJECT.YGRS, 预估人数
     */
    public void setYgrs(Integer ygrs) {
        this.ygrs = ygrs;
    }

    /**
     * 获取 项目金额 字段:BO_CRM_PROJECT.MONEY
     *
     * @return BO_CRM_PROJECT.MONEY, 项目金额
     */
    public Double getMoney() {
        return money;
    }

    /**
     * 设置 项目金额 字段:BO_CRM_PROJECT.MONEY
     *
     * @param money BO_CRM_PROJECT.MONEY, 项目金额
     */
    public void setMoney(Double money) {
        this.money = money;
    }

    /**
     * 获取 项目难点 字段:BO_CRM_PROJECT.PROBLEM
     *
     * @return BO_CRM_PROJECT.PROBLEM, 项目难点
     */
    public String getProblem() {
        return problem;
    }

    /**
     * 设置 项目难点 字段:BO_CRM_PROJECT.PROBLEM
     *
     * @param problem BO_CRM_PROJECT.PROBLEM, 项目难点
     */
    public void setProblem(String problem) {
        this.problem = problem;
    }

    /**
     * 获取 期望协助部门 字段:BO_CRM_PROJECT.EXPECT_ASSIST_DEPT
     *
     * @return BO_CRM_PROJECT.EXPECT_ASSIST_DEPT, 期望协助部门
     */
    public String getExpectAssistDept() {
        return expectAssistDept;
    }

    /**
     * 设置 期望协助部门 字段:BO_CRM_PROJECT.EXPECT_ASSIST_DEPT
     *
     * @param expectAssistDept BO_CRM_PROJECT.EXPECT_ASSIST_DEPT, 期望协助部门
     */
    public void setExpectAssistDept(String expectAssistDept) {
        this.expectAssistDept = expectAssistDept;
    }

    /**
     * 获取 最新项目状态 字段:BO_CRM_PROJECT.NEW_STATUS
     *
     * @return BO_CRM_PROJECT.NEW_STATUS, 最新项目状态
     */
    public Integer getNewStatus() {
        return newStatus;
    }

    /**
     * 设置 最新项目状态 字段:BO_CRM_PROJECT.NEW_STATUS
     *
     * @param newStatus BO_CRM_PROJECT.NEW_STATUS, 最新项目状态
     */
    public void setNewStatus(Integer newStatus) {
        this.newStatus = newStatus;
    }

    /**
     * 获取 影像资料收集渠道 字段:BO_CRM_PROJECT.DATA_SOURCE
     *
     * @return BO_CRM_PROJECT.DATA_SOURCE, 影像资料收集渠道
     */
    public Integer getDataSource() {
        return dataSource;
    }

    /**
     * 设置 影像资料收集渠道 字段:BO_CRM_PROJECT.DATA_SOURCE
     *
     * @param dataSource BO_CRM_PROJECT.DATA_SOURCE, 影像资料收集渠道
     */
    public void setDataSource(Integer dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取 项目状态说明 字段:BO_CRM_PROJECT.STATUS_DESC
     *
     * @return BO_CRM_PROJECT.STATUS_DESC, 项目状态说明
     */
    public String getStatusDesc() {
        return statusDesc;
    }

    /**
     * 设置 项目状态说明 字段:BO_CRM_PROJECT.STATUS_DESC
     *
     * @param statusDesc BO_CRM_PROJECT.STATUS_DESC, 项目状态说明
     */
    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    /**
     * 获取 主责部门 字段:BO_CRM_PROJECT.ZZBM
     *
     * @return BO_CRM_PROJECT.ZZBM, 主责部门
     */
    public Integer getZzbm() {
        return zzbm;
    }

    /**
     * 设置 主责部门 字段:BO_CRM_PROJECT.ZZBM
     *
     * @param zzbm BO_CRM_PROJECT.ZZBM, 主责部门
     */
    public void setZzbm(Integer zzbm) {
        this.zzbm = zzbm;
    }

    /**
     * 获取 所属公司 字段:BO_CRM_PROJECT.SSGS
     *
     * @return BO_CRM_PROJECT.SSGS, 所属公司
     */
    public Integer getSsgs() {
        return ssgs;
    }

    /**
     * 设置 所属公司 字段:BO_CRM_PROJECT.SSGS
     *
     * @param ssgs BO_CRM_PROJECT.SSGS, 所属公司
     */
    public void setSsgs(Integer ssgs) {
        this.ssgs = ssgs;
    }

    /**
     * 获取 项目负责人 字段:BO_CRM_PROJECT.XMFZR
     *
     * @return BO_CRM_PROJECT.XMFZR, 项目负责人
     */
    public Integer getXmfzr() {
        return xmfzr;
    }

    /**
     * 设置 项目负责人 字段:BO_CRM_PROJECT.XMFZR
     *
     * @param xmfzr BO_CRM_PROJECT.XMFZR, 项目负责人
     */
    public void setXmfzr(Integer xmfzr) {
        this.xmfzr = xmfzr;
    }

    /**
     * 获取 项目主管领导 字段:BO_CRM_PROJECT.XMZGLD
     *
     * @return BO_CRM_PROJECT.XMZGLD, 项目主管领导
     */
    public Integer getXmzgld() {
        return xmzgld;
    }

    /**
     * 设置 项目主管领导 字段:BO_CRM_PROJECT.XMZGLD
     *
     * @param xmzgld BO_CRM_PROJECT.XMZGLD, 项目主管领导
     */
    public void setXmzgld(Integer xmzgld) {
        this.xmzgld = xmzgld;
    }

    /**
     * 获取 协同部门 字段:BO_CRM_PROJECT.XTBM
     *
     * @return BO_CRM_PROJECT.XTBM, 协同部门
     */
    public String getXtbm() {
        return xtbm;
    }

    /**
     * 设置 协同部门 字段:BO_CRM_PROJECT.XTBM
     *
     * @param xtbm BO_CRM_PROJECT.XTBM, 协同部门
     */
    public void setXtbm(String xtbm) {
        this.xtbm = xtbm;
    }

    /**
     * 获取 备注 字段:BO_CRM_PROJECT.NOTE
     *
     * @return BO_CRM_PROJECT.NOTE, 备注
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置 备注 字段:BO_CRM_PROJECT.NOTE
     *
     * @param note BO_CRM_PROJECT.NOTE, 备注
     */
    public void setNote(String note) {
        this.note = note;
    }
}