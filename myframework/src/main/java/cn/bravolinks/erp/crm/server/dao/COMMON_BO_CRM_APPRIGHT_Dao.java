package cn.bravolinks.erp.crm.server.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.BO_CRM_APPRIGHT_S;
import cn.bravolinks.erp.crm.server.model.Dept;
import cn.bravolinks.erp.crm.server.model.User;

@Mapper
public interface COMMON_BO_CRM_APPRIGHT_Dao {
	
	String getDKHZJZH(String khbh);
	
	Integer isSingleCompany(String khbh);
	
	String getKHSSGS(String khbh);
	
	Map<String,String> getGSXX(String bmid);
	
	Integer up_APPRIGHT_S(String gsdm,String gsmc,Integer bindid,String bmid);
	
	Integer countFwbmSql(String khbh);
	
	Integer countDkhzjSql(String khbh);
	
	Integer distinctdkhzjcount(String khbh);
	
	List<Dept> getRootDept();

	List<Dept> getDeptsByID(Integer id);

	List<User> getUsersByID(Integer id);

	List<User> getAllOrgUser();

	String getUrLOfTodoTask(Map<String, String> m);

	List<Map<String, Object>> fetchTracingDatas(Map<String, Object> condition);

	Integer checkSelectedSendMsg(Integer taskid);
}
