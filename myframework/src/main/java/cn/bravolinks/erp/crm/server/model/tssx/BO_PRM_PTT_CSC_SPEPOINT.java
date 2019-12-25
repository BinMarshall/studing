package cn.bravolinks.erp.crm.server.model.tssx;

import java.util.Date;

public class BO_PRM_PTT_CSC_SPEPOINT {
    private Integer id;

    private String orgno;

    private Integer bindid;

    private Date createdate;

    private String createuser;

    private Date updatedate;

    private String updateuser;

    private Integer workflowid;

    private Integer workflowstepid;

    private Integer isend;

    private String tssxspd;

    private String yysm;

    private String fj;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrgno() {
        return orgno;
    }

    public void setOrgno(String orgno) {
        this.orgno = orgno;
    }

    public Integer getBindid() {
        return bindid;
    }

    public void setBindid(Integer bindid) {
        this.bindid = bindid;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser;
    }

    public Integer getWorkflowid() {
        return workflowid;
    }

    public void setWorkflowid(Integer workflowid) {
        this.workflowid = workflowid;
    }

    public Integer getWorkflowstepid() {
        return workflowstepid;
    }

    public void setWorkflowstepid(Integer workflowstepid) {
        this.workflowstepid = workflowstepid;
    }

    public Integer getIsend() {
        return isend;
    }

    public void setIsend(Integer isend) {
        this.isend = isend;
    }

    public String getTssxspd() {
        return tssxspd;
    }

    public void setTssxspd(String tssxspd) {
        this.tssxspd = tssxspd;
    }

    public String getYysm() {
        return yysm;
    }

    public void setYysm(String yysm) {
        this.yysm = yysm;
    }

    public String getFj() {
        return fj;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }
}