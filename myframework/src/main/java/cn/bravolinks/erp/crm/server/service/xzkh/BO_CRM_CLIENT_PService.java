package cn.bravolinks.erp.crm.server.service.xzkh;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENT_P;

public interface BO_CRM_CLIENT_PService {
	
	//向单位客户档案主表插入数据
	
	public void insert(BO_CRM_CLIENT_P record);
	
	public BO_CRM_CLIENT_P selectById(Integer id);
	

}
