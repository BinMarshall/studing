package cn.bravolinks.erp.crm.server.service;

import java.util.List;
import java.util.Map;

import cn.bravolinks.erp.crm.server.model.BO_CRM_APPRIGHT;
import cn.bravolinks.erp.crm.server.model.BO_CRM_APPRIGHT_S;
import cn.bravolinks.erp.crm.server.model.BO_USER_EXT;
import cn.bravolinks.erp.crm.server.model.Dept;
import cn.bravolinks.erp.crm.server.model.User;

public interface BO_CRM_APPRIGHTService {
	
	public boolean execute(Integer bindid);
	
	public void updateGSXX(Integer bindid);
	
	public boolean checkCountOfFwbm(String khbh);

	public BO_CRM_APPRIGHT getApprightByBindId(String bindid);

	public String getDbStatusOfTask(String bindid, String taskid);

	public String getYbStatusOfTask(String bindid, String taskid);

	public List<Dept> getRootDept();

	public BO_USER_EXT getUserExtByUserid(String userid);

	public List<BO_CRM_APPRIGHT_S> getApprightSubByBindId(String bindid);

	public List<User> getAllOrgUser();

	List<Dept> getDeptsByID(Integer id);

	List<User> getUsersByID(Integer id);

	public String getTaskTitleByTaskid(Integer taskid);

	public String getUrLOfTodoTask(String dl, String lx, String ywmc);

	public Map<String, String> getTaskMessageByTaskid(String task_id);

	public void updateByCurrent(BO_CRM_APPRIGHT p);

	public List<Map<String, Object>> fetchTracingDatas(Map<String, Object> condition) throws Exception;
}
