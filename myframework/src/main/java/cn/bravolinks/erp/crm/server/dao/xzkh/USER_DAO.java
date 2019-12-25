package cn.bravolinks.erp.crm.server.dao.xzkh;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.xzkh.Dept;
import cn.bravolinks.erp.crm.server.model.xzkh.User;


@Mapper
public interface USER_DAO {
	/*
	 * 查询ERP组织架构部门根部门
	 */
	List<Dept> getRootDept();

	/*
	 *  根据部门id查询子部门
	 */
	List<Dept> getDeptsByID(Integer id);

	/*
	 * 根据部门id查询部门下的人员
	 */
	List<User> getUsersByID(Integer id);

	/**
	 * 查询ERP所有人员信息
	 */
	List<User> getAllOrgUser();
	
	/**
	 * 查询指定人员名称
	 * @param userid
	 * @return
	 */
	
	User getUserExtByUserid(String userid);
	
	/*
	 * 查询指定公司下的部门
	 */
	List<Dept> getRootDeptById();

	/**
	 * 查找公司名称和公司代码
	 */
	
	Dept getGS(Integer id);
	
	/**
	 * 查找指定的user
	 */
	
	List<Dept> getUser(Integer bmid) ;  
	
	/**
	 * 指定userid  查询gsdm 
	 */
	
	Dept getUserGSDM(String userid) ;  
	/**
	 * -- 根据gsdm 查询GSMC
	 */
	
	Dept getGsdmGSMC(String gsdm) ;  
	/**
	 * --根据客户编号查询bmid
	 */
	
	Dept getKhBMID(String gsdm) ;  
	
	/**
	 * -- 根据GSID 查询name-
	 */
	
	Dept getGsdmGSID(String gsdm) ;  
	

	
	Dept getBMidGS(String gsdm) ;  
	
	
	
	Dept getBMidGSID(String gsdm) ;  
	
	/**
	 * 查询公司
	 * @return
	 */
	List<Dept> getGSName();
	
	/**
	 * 查询bm
	 * @return
	 */
	List<Dept> getDeptByGSId(String gsid);
	
	
	/**
	 * 根据部门id查询部门的人
	 * @return
	 */
	List<Dept> getPeopleByBMId(String gsid);
	
	String getGsid(String gsdm);
	
	
	
}