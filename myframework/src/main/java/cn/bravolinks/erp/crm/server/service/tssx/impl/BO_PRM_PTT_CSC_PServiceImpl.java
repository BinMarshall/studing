package cn.bravolinks.erp.crm.server.service.tssx.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bravolinks.erp.crm.server.dao.tssx.BO_PRM_PTT_CSC_P_DAO;
import cn.bravolinks.erp.crm.server.model.tssx.BO_PRM_PTT_CSC_P;
import cn.bravolinks.erp.crm.server.service.tssx.BO_PRM_PTT_CSC_PService;

@Service
public class BO_PRM_PTT_CSC_PServiceImpl implements BO_PRM_PTT_CSC_PService {

	@Autowired
	private BO_PRM_PTT_CSC_P_DAO boPrmPttCscPDao;
	
	
	@Override
	public BO_PRM_PTT_CSC_P getKhfwfa(String bindid) {
		// TODO Auto-generated method stub
		return boPrmPttCscPDao.getKhfwfa(bindid);
	}

}
