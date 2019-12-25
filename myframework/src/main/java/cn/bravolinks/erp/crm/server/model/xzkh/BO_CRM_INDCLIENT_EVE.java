package cn.bravolinks.erp.crm.server.model.xzkh;

import java.util.Date;

public class BO_CRM_INDCLIENT_EVE {
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

    private String lssj;

    private Date fsrq;

    private String fsdd;

    private Integer parentsubid;

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

    public String getLssj() {
        return lssj;
    }

    public void setLssj(String lssj) {
        this.lssj = lssj;
    }

    public Date getFsrq() {
        return fsrq;
    }

    public void setFsrq(Date fsrq) {
        this.fsrq = fsrq;
    }

    public String getFsdd() {
        return fsdd;
    }

    public void setFsdd(String fsdd) {
        this.fsdd = fsdd;
    }

    public Integer getParentsubid() {
        return parentsubid;
    }

    public void setParentsubid(Integer parentsubid) {
        this.parentsubid = parentsubid;
    }
}