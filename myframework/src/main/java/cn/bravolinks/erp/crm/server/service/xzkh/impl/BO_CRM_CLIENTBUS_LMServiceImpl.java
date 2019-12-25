package cn.bravolinks.erp.crm.server.service.xzkh.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bravolinks.erp.crm.server.dao.xzkh.BO_CRM_CLIENTBUS_LM_DAO;
import cn.bravolinks.erp.crm.server.dao.xzkh.BO_CRM_INDCLIENTBUS_EVE_DAO;
import cn.bravolinks.erp.crm.server.dao.xzkh.BO_CRM_INDCLIENTBUS_HOB_DAO;
import cn.bravolinks.erp.crm.server.dao.xzkh.BO_CRM_INDCLIENTBUS_TR_DAO;
import cn.bravolinks.erp.crm.server.dao.xzkh.BO_CRM_INDCLIENT_EVE_DAO;
import cn.bravolinks.erp.crm.server.dao.xzkh.BO_CRM_INDCLIENT_HOB_DAO;
import cn.bravolinks.erp.crm.server.dao.xzkh.BO_CRM_INDCLIENT_LM_DAO;
import cn.bravolinks.erp.crm.server.dao.xzkh.BO_CRM_INDCLIENT_TR_DAO;
import cn.bravolinks.erp.crm.server.model.tssx.BO_CRM_CLIENTBUS_LM;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENTBUS_EVE;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENTBUS_HOB;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENTBUS_TR;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENT_LM;
import cn.bravolinks.erp.crm.server.service.xzkh.BO_CRM_CLIENTBUS_LMService;

@Service
public class BO_CRM_CLIENTBUS_LMServiceImpl implements BO_CRM_CLIENTBUS_LMService {
	
	@Autowired
	private BO_CRM_CLIENTBUS_LM_DAO BoCrmClientbusLmDao;
	
	@Autowired
	private BO_CRM_INDCLIENT_LM_DAO bo_CRM_INDCLIENT_LM_DAO;
	
	@Autowired
	private BO_CRM_INDCLIENTBUS_EVE_DAO bO_CRM_INDCLIENTBUS_EVE_DAO;
	
	
	@Autowired
	private BO_CRM_INDCLIENTBUS_HOB_DAO bO_CRM_INDCLIENTBUS_HOB_DAO;
	
	@Autowired
	private BO_CRM_INDCLIENTBUS_TR_DAO bO_CRM_INDCLIENTBUS_TR_DAO;
	
	
	@Autowired
	private BO_CRM_INDCLIENT_HOB_DAO bO_CRM_INDCLIENT_HOB_DAO;
	
	
	@Autowired
	private BO_CRM_INDCLIENT_EVE_DAO bO_CRM_INDCLIENT_EVE_DAO;
	
	@Autowired
	private BO_CRM_INDCLIENT_TR_DAO bO_CRM_INDCLIENT_TR_DAO;
	
	

	@Override
	public List<BO_CRM_CLIENTBUS_LM> selectByid(String bindidid) {
		// TODO Auto-generated method stub
		return BoCrmClientbusLmDao.selectByid(bindidid);
	}

	@Override
	public int insertSelective(BO_CRM_INDCLIENT_LM record) {
		// TODO Auto-generated method stub
		return bo_CRM_INDCLIENT_LM_DAO.insertSelective(record);
	}

	@Override
	public List<BO_CRM_INDCLIENTBUS_TR> selectByTR(String bindid,String id) {
		// TODO Auto-generated method stub
		return bO_CRM_INDCLIENTBUS_TR_DAO.selectByid(bindid,id);
	}

	@Override
	public List<BO_CRM_INDCLIENTBUS_HOB> selectByHOB(String bindid,String id) {
		// TODO Auto-generated method stub
		return bO_CRM_INDCLIENTBUS_HOB_DAO.selectByid(bindid,id);
	}

	@Override
	public List<BO_CRM_INDCLIENTBUS_EVE> selectByEVE(String bindid,String id) {
		// TODO Auto-generated method stub
		return bO_CRM_INDCLIENTBUS_EVE_DAO.selectByid(bindid,id);
	}

	@Override
	public int insertSelectiveHOB(BO_CRM_INDCLIENTBUS_HOB record) {
		// TODO Auto-generated method stub
		return bO_CRM_INDCLIENT_HOB_DAO.insertSelective(record);
	}

	@Override
	public int insertSelectiveTR(BO_CRM_INDCLIENTBUS_TR record) {
		// TODO Auto-generated method stub
		return bO_CRM_INDCLIENT_TR_DAO.insertSelective(record);
	}

	@Override
	public int insertSelectiveEVE(BO_CRM_INDCLIENTBUS_EVE record) {
		// TODO Auto-generated method stub
		return bO_CRM_INDCLIENT_EVE_DAO.insertSelective(record);
	}

	

	

}
