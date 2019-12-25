package cn.bravolinks.erp.crm.server.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 大项目 - 项目成员
 * @author yanqin
 */
public class BoCrmProjectMember {
    /**
     * id
     */
    private Integer id;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 创建用户
     */
    private String createUser;
    /**
     * 项目成员
     */
    private Integer projectUser;
    /**
     * 成员角色
     */
    private Integer userRole;
    /**
     * 大项目id
     */
    private Integer projectId;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getProjectUser() {
        return projectUser;
    }

    public void setProjectUser(Integer projectUser) {
        this.projectUser = projectUser;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

}