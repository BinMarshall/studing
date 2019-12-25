package cn.bravolinks.erp.crm.server.service.tssx.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bravolinks.erp.crm.server.dao.tssx.BO_PRM_PTT_CSC_SPECON_DAO;
import cn.bravolinks.erp.crm.server.model.tssx.BO_PRM_PTT_CSC_SPECON;
import cn.bravolinks.erp.crm.server.service.tssx.BO_PRM_PTT_CSC_SPECONService;

@Service
public class BO_PRM_PTT_CSC_SPECONServiceImpl implements BO_PRM_PTT_CSC_SPECONService {

	@Autowired
	private BO_PRM_PTT_CSC_SPECON_DAO boPrmPttCscSpecoNDao;
	
	@Override
	public List<BO_PRM_PTT_CSC_SPECON> getSpecByBindid(String bindidid) {
		// TODO Auto-generated method stub
		return boPrmPttCscSpecoNDao.getSpecByBindid(bindidid);
				
	}

}
