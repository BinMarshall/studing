package cn.bravolinks.erp.crm.server.service.xzkh;

import org.springframework.transaction.annotation.Transactional;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_REG;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENT_EVE;

public interface BO_CRM_CLIENT_REGService {

//  修改数据
	@Transactional
	public void UpdateByBindid(BO_CRM_CLIENTBUS_REG bo_crm);
	
//  修改数据
	@Transactional
	public void UpdateBy(BO_CRM_INDCLIENT_EVE bo_crm);
}
