package cn.bravolinks.erp.crm.server.service.xzkh.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bravolinks.erp.crm.server.dao.xzkh.BO_CRM_CLIENT_EVENTS_DAO;
import cn.bravolinks.erp.crm.server.dao.xzkh.BO_CRM_CLIENT_REG_DAO;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_REG;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENT_EVE;
import cn.bravolinks.erp.crm.server.service.xzkh.BO_CRM_CLIENT_REGService;

@Service
public class BO_CRM_CLIENT_REGServiceImpl implements BO_CRM_CLIENT_REGService {
	@Autowired
	private BO_CRM_CLIENT_REG_DAO BO_CRM_CLIENT_REG_DAO;
	
	@Autowired
	private BO_CRM_CLIENT_EVENTS_DAO BO_CRM_CLIENT_EVENTS_DAO;
	

	@Override
	public void UpdateByBindid(BO_CRM_CLIENTBUS_REG bo_crm) {
		BO_CRM_CLIENT_REG_DAO.insertSelective(bo_crm);
		
	}


	@Override
	public void UpdateBy(BO_CRM_INDCLIENT_EVE bo_crm) {
		BO_CRM_CLIENT_EVENTS_DAO.insertSelective(bo_crm);
		
	}




}
