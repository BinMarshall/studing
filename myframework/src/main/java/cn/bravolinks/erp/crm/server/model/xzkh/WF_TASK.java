package cn.bravolinks.erp.crm.server.model.xzkh;

import java.util.Date;

public class WF_TASK {
    private Integer id;

    private Integer bindId;

    private Object owner;

    private Object target;

    private Integer status;

    private Object title;

    private Date begintime;

    private Date endtime;

    private Integer expiretime;

    private Object workflowtype;

    private Integer priority;

    private Integer fromPoint;

    private Object wfStyle;

    private Integer readTask;

    private Integer ownerDptId;

    private Long wfid;

    private Long wfsid;

    private Date readtime;

    private Long orgid;

    private Long dptid;

    private Long roleid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBindId() {
        return bindId;
    }

    public void setBindId(Integer bindId) {
        this.bindId = bindId;
    }

    public Object getOwner() {
        return owner;
    }

    public void setOwner(Object owner) {
        this.owner = owner;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object getTitle() {
        return title;
    }

    public void setTitle(Object title) {
        this.title = title;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getExpiretime() {
        return expiretime;
    }

    public void setExpiretime(Integer expiretime) {
        this.expiretime = expiretime;
    }

    public Object getWorkflowtype() {
        return workflowtype;
    }

    public void setWorkflowtype(Object workflowtype) {
        this.workflowtype = workflowtype;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getFromPoint() {
        return fromPoint;
    }

    public void setFromPoint(Integer fromPoint) {
        this.fromPoint = fromPoint;
    }

    public Object getWfStyle() {
        return wfStyle;
    }

    public void setWfStyle(Object wfStyle) {
        this.wfStyle = wfStyle;
    }

    public Integer getReadTask() {
        return readTask;
    }

    public void setReadTask(Integer readTask) {
        this.readTask = readTask;
    }

    public Integer getOwnerDptId() {
        return ownerDptId;
    }

    public void setOwnerDptId(Integer ownerDptId) {
        this.ownerDptId = ownerDptId;
    }

    public Long getWfid() {
        return wfid;
    }

    public void setWfid(Long wfid) {
        this.wfid = wfid;
    }

    public Long getWfsid() {
        return wfsid;
    }

    public void setWfsid(Long wfsid) {
        this.wfsid = wfsid;
    }

    public Date getReadtime() {
        return readtime;
    }

    public void setReadtime(Date readtime) {
        this.readtime = readtime;
    }

    public Long getOrgid() {
        return orgid;
    }

    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    public Long getDptid() {
        return dptid;
    }

    public void setDptid(Long dptid) {
        this.dptid = dptid;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }
}