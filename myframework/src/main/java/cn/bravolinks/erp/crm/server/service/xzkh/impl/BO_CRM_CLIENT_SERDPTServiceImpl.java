package cn.bravolinks.erp.crm.server.service.xzkh.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bravolinks.erp.crm.server.dao.xzkh.BO_CRM_CLIENT_SERDPT_DAO;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENT_SERDPT;
import cn.bravolinks.erp.crm.server.service.xzkh.BO_CRM_CLIENT_SERDPTService;
@Service
public class BO_CRM_CLIENT_SERDPTServiceImpl implements BO_CRM_CLIENT_SERDPTService {

	@Autowired   
	private BO_CRM_CLIENT_SERDPT_DAO bo_Crm_Client_Serdpt_Dao;
	
	@Override
	public String selectByBMZH(String khbh) {
		// TODO Auto-generated method stub
		return bo_Crm_Client_Serdpt_Dao.selectByBMZH(khbh);
	}

	@Override
	public String selectByBMXM(String khbh) {
		// TODO Auto-generated method stub
		return bo_Crm_Client_Serdpt_Dao.selectByBMXM(khbh);
	}

}
