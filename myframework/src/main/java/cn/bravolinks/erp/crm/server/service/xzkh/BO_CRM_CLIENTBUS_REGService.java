package cn.bravolinks.erp.crm.server.service.xzkh;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_REG;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENT_EVE;

import java.util.List;

public interface BO_CRM_CLIENTBUS_REGService {

	BO_CRM_CLIENTBUS_REG selectByBindid(Integer id);

	public List<BO_CRM_CLIENTBUS_REG> selectListByBindid(Integer id);

	public BO_CRM_INDCLIENT_EVE selectByEve(Integer id);
}
