package cn.bravolinks.erp.crm.server.service.xzkh.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bravolinks.erp.crm.server.dao.xzkh.BO_CRM_CLIENTBUS_REG_DAO;
import cn.bravolinks.erp.crm.server.dao.xzkh.BO_CRM_INDCLIENT_EVE_DAO;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_REG;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENT_EVE;
import cn.bravolinks.erp.crm.server.service.xzkh.BO_CRM_CLIENTBUS_REGService;

import java.util.List;

@Service
public class BO_CRM_CLIENTBUS_REGServiceImpl implements BO_CRM_CLIENTBUS_REGService {

	@Autowired
	private BO_CRM_CLIENTBUS_REG_DAO BO_CRM_CLIENTBUS_REG_DAO;
	
	@Autowired
	private BO_CRM_INDCLIENT_EVE_DAO BO_CRM_INDCLIENT_EVE_DAO;
	
	
	@Override
	public BO_CRM_CLIENTBUS_REG selectByBindid(Integer id) {
		// TODO Auto-generated method stub
		return BO_CRM_CLIENTBUS_REG_DAO.selectByBindid(id);
	}

	@Override
	public List<BO_CRM_CLIENTBUS_REG> selectListByBindid(Integer bindid) {
		return BO_CRM_CLIENTBUS_REG_DAO.selectListByBindid(bindid);
	}

	@Override
	public BO_CRM_INDCLIENT_EVE selectByEve(Integer id) {
		// TODO Auto-generated method stub
		return BO_CRM_INDCLIENT_EVE_DAO.selectByBindid(id);
	}

}
