package cn.bravolinks.erp.crm.server.model;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by yanqin on 2017/3/27.
 */
public class User{
    private String userid;//成员UserID。对应管理端的帐号
    private String name;//成员名称
    private Integer[] department;//成员所属部门id列表
    private String position;//职位信息
    private String mobile;//手机号码
    private String gender;//性别。0表示未定义，1表示男性，2表示女性
    private String email;//邮箱
    private String weixinid;//微信号
    private String avatar;//头像url。注：如果要获取小图将url最后的"/0"改成"/64"即可
    private Integer status;//关注状态: 1=已关注，2=已冻结，4=未关注
    private Map<String,Object> extattr;//扩展属性  例如："extattr": {"attrs":[{"name":"爱好","value":"旅游"},{"name":"卡号","value":"1234567234"}]}
    private String subName;
    private String[] dept;
    private String departmentname;//部门名称
    private String bmqc;//部门全路径   北京公司/信息技术部
    public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public String getBmqc() {
		return bmqc;
	}

	public void setBmqc(String bmqc) {
		this.bmqc = bmqc;
	}

	@Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", department=" + Arrays.toString(department) +
                ", position='" + position + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", weixinid='" + weixinid + '\'' +
                ", avatar='" + avatar + '\'' +
                ", status=" + status +
                ", extattr=" + extattr +
                ", subName='" + subName + '\'' +
                ", dept=" + Arrays.toString(dept) +
                '}';
    }

    public String[] getDept() {
        return dept;
    }

    public void setDept(String[] dept) {
        this.dept = dept;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer[] getDepartment() {
        return department;
    }

    public void setDepartment(Integer[] department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeixinid() {
        return weixinid;
    }

    public void setWeixinid(String weixinid) {
        this.weixinid = weixinid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Map<String, Object> getExtattr() {
        return extattr;
    }

    public void setExtattr(Map<String, Object> extattr) {
        this.extattr = extattr;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }
}
