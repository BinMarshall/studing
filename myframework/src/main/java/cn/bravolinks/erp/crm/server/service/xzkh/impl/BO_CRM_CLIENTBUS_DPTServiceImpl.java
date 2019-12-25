package cn.bravolinks.erp.crm.server.service.xzkh.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bravolinks.erp.crm.server.dao.xzkh.BO_CRM_CLIENTBUS_DPT_DAO;
import cn.bravolinks.erp.crm.server.dao.xzkh.BO_CRM_CLIENT_DPT_DAO;
import cn.bravolinks.erp.crm.server.dao.xzkh.BO_CRM_CLIENT_SERDPT_DAO;
import cn.bravolinks.erp.crm.server.dao.xzkh.BO_CRM_INDCLIENT_P_DAO;
import cn.bravolinks.erp.crm.server.model.tssx.BO_CRM_CLIENTBUS_LM;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_DPT;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENT_DPT;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENT_SERDPT;
import cn.bravolinks.erp.crm.server.service.xzkh.BO_CRM_CLIENTBUS_DPTService;
@Service
public class BO_CRM_CLIENTBUS_DPTServiceImpl implements BO_CRM_CLIENTBUS_DPTService {

	@Autowired
	private BO_CRM_CLIENTBUS_DPT_DAO boCrmClintbusDptDAO;
	
	@Autowired
	private BO_CRM_CLIENT_DPT_DAO BO_CRM_CLIENT_DPT_DAO;
	
	@Autowired
	private BO_CRM_CLIENT_SERDPT_DAO BO_CRM_CLIENT_SERDPT_DAO;
	
	@Autowired
	private BO_CRM_INDCLIENT_P_DAO BO_CRM_INDCLIENT_P_DAO;
	
	@Override
	public List<BO_CRM_CLIENTBUS_DPT> selectByBMbindid(String bindid) {
		// TODO Auto-generated method stub
		return boCrmClintbusDptDAO.selectByBMbindid(bindid);
	}

	

	@Override
	public void UpdateByBindid(BO_CRM_CLIENTBUS_DPT bo_crm) {
		boCrmClintbusDptDAO.UpdateByBindid(bo_crm);
		
	}



	@Override
	public void insertClientDPT(BO_CRM_CLIENT_DPT record) {
		BO_CRM_CLIENT_DPT_DAO.insert(record);
		
	}



	@Override
	public void insertSerdpt(BO_CRM_CLIENT_SERDPT record) {
		BO_CRM_CLIENT_SERDPT_DAO.insert(record);
		
	}



	@Override
	public void insertIndcientLM(BO_CRM_CLIENTBUS_LM record) {
		BO_CRM_INDCLIENT_P_DAO.insert(record);
		
	}



	@Override
	public String selectBmbh(String id) {
		// TODO Auto-generated method stub
		return boCrmClintbusDptDAO.selectBmbh(id);
	}

}
