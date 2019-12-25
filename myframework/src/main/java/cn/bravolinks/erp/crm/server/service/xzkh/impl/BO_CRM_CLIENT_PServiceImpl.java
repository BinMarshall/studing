package cn.bravolinks.erp.crm.server.service.xzkh.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bravolinks.erp.crm.server.dao.xzkh.BO_CRM_CLIENT_P_DAO;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENT_P;
import cn.bravolinks.erp.crm.server.service.xzkh.BO_CRM_CLIENT_PService;

@Service
public class BO_CRM_CLIENT_PServiceImpl implements BO_CRM_CLIENT_PService {

	@Autowired
	private BO_CRM_CLIENT_P_DAO bo_Crm_Client_P_Dao;
	
	@Override
	public void insert(BO_CRM_CLIENT_P record) {
		
		bo_Crm_Client_P_Dao.insert(record);

	}

	@Override
	public BO_CRM_CLIENT_P selectById(Integer id) {
		// TODO Auto-generated method stub
		return bo_Crm_Client_P_Dao.selectById(id);
	}



}
