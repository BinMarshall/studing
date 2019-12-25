package cn.bravolinks.erp.crm.server.service.xzkh;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_P;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENT_P;
/**
 * 单位客户信息主表
 * @author 49781
 *
 */
public interface BO_CRM_CLIENTBUS_PServer {
	
	/**
	 * 根据指定的bindid获取数据库记录,BO_CRM_CLIENTBUS_P
	 * @param bindid
	 * @return
	 */
	BO_CRM_CLIENTBUS_P selectBybindid(String bindid);
	
	List<BO_CRM_CLIENTBUS_P> selectByKhbh(Integer cd);

	
	
	//  修改数据
	@Transactional
	public void UpdateById(BO_CRM_CLIENTBUS_P bo_crm);

	
	//  修改数据
	@Transactional
	public void UpdateByIdJT(BO_CRM_CLIENTBUS_P bo_crm);
	
//  修改数据
	@Transactional
	public void UpdateByBindid(BO_CRM_CLIENTBUS_P bo_crm);
		
//  修改数据
	@Transactional
	public void updateBoCRMBM(BO_CRM_CLIENTBUS_P bo_crm);
	
//  修改数据
	@Transactional
	public void updateBoCRMR(BO_CRM_CLIENTBUS_P bo_crm);
	
	/**
	 * 查询待办
	 * @param bindid
	 * @param taskid
	 * @return
	 */
	public String getDbStatusOfTask(String bindid, String taskid);

	/**
	 * 查询已办
	 * @param bindid
	 * @param taskid
	 * @return
	 */
	public String getYbStatusOfTask(String bindid, String taskid);
	
	

	public String getTaskTitleByTaskid(int taskid);

	public Map<String, String> getTaskMessageByTaskid(String task_id);
	
    int insertSelective(BO_CRM_INDCLIENT_P record);


	
	
}
