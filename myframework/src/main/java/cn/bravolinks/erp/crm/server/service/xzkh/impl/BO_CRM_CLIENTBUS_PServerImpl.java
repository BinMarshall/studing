package cn.bravolinks.erp.crm.server.service.xzkh.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bravolinks.erp.crm.server.dao.xzkh.BO_CRM_CLIENTBUS_P_DAO;
import cn.bravolinks.erp.crm.server.dao.xzkh.BO_CRM_INDCLIENT_P_DAO;
import cn.bravolinks.erp.crm.server.dao.xzkh.WF_TA_DAO;
import cn.bravolinks.erp.crm.server.dao.xzkh.WF_TA_LOG_DAO;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_P;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENT_P;
import cn.bravolinks.erp.crm.server.model.xzkh.WF_TASK;
import cn.bravolinks.erp.crm.server.service.xzkh.BO_CRM_CLIENTBUS_PServer;
import cn.bravolinks.erp.crm.server.util.PropertiesUtils;

@Service
public class BO_CRM_CLIENTBUS_PServerImpl implements BO_CRM_CLIENTBUS_PServer {

	@Autowired
	private BO_CRM_CLIENTBUS_P_DAO bo_crm_clientbus_dao;
	
	@Autowired
	private BO_CRM_INDCLIENT_P_DAO bo_crm_INDCLIENT_P_DAO;
	
	@Autowired
	private WF_TA_DAO wfTaskDao;
	@Autowired
	private WF_TA_LOG_DAO wfTaskLogDao;

	@Override
	public BO_CRM_CLIENTBUS_P selectBybindid(String bindid) {
		// TODO Auto-generated method stub
		return bo_crm_clientbus_dao.selectBybindid(bindid);
	}

	@Override
	public void UpdateById(BO_CRM_CLIENTBUS_P bo_crm) {
		bo_crm_clientbus_dao.UpdateById(bo_crm);
		
	}

	@Override
	public void UpdateByIdJT(BO_CRM_CLIENTBUS_P bo_crm) {
		
		bo_crm_clientbus_dao.UpdateByIdJT(bo_crm);		
	}

	@Override
	public List<BO_CRM_CLIENTBUS_P> selectByKhbh(Integer cd) {
		// TODO Auto-generated method stub
		return bo_crm_clientbus_dao.selectByKhbh(cd);
	}

	@Override
	public void UpdateByBindid(BO_CRM_CLIENTBUS_P bo_crm) {
		bo_crm_clientbus_dao.UpdateByBindid(bo_crm);
		
	}

	@Override
	public String getDbStatusOfTask(String bindid, String taskid) {
		Map<String, String> m = new HashMap<>();
		m.put("bindid", bindid);
		m.put("taskid", taskid);
		String status = wfTaskDao.getStatusByTaskIdAndBindid(m);
		status = PropertiesUtils.defaultValue(status);
		return status;
	}
	
	@Override
	public String getYbStatusOfTask(String bindid, String taskid) {
		Map<String, String> m = new HashMap<>();
		m.put("bindid", bindid);
		m.put("taskid", taskid);
		String status = wfTaskLogDao.getStatusByTaskIdAndBindid(m);
		status = PropertiesUtils.defaultValue(status);
		return status;
	}
	
	@Override
	public String getTaskTitleByTaskid(int taskid) {
		String taskTitle = wfTaskDao.getTaskTitleByTaskid(taskid);
		if(null == taskTitle){
			taskTitle = "";
		}
		return taskTitle;
	}
	@Override
	public Map<String, String> getTaskMessageByTaskid(String task_id) {
		WF_TASK wt = wfTaskDao.getTaskMessageByTaskid(task_id);
		String fromTaskid = String.valueOf(wt.getFromPoint());
		String fromUserid = wt.getOwner().toString();
		Map<String, String> m = new HashMap<>();
		m.put("fromTaskid", fromTaskid);
		m.put("fromUserid", fromUserid);
		return m;
	}

	@Override
	public void updateBoCRMBM(BO_CRM_CLIENTBUS_P bo_crm) {
		bo_crm_clientbus_dao.updateBoCRMBM(bo_crm);
		
	}

	@Override
	public void updateBoCRMR(BO_CRM_CLIENTBUS_P bo_crm) {
		bo_crm_clientbus_dao.updateBoCRMR(bo_crm);
		
	}

	@Override
	public int insertSelective(BO_CRM_INDCLIENT_P record) {
		// TODO Auto-generated method stub
		return bo_crm_INDCLIENT_P_DAO.insertSelective(record);
	}

}
