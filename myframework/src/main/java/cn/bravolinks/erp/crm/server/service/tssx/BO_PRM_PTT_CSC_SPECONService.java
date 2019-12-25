package cn.bravolinks.erp.crm.server.service.tssx;

import java.util.List;

import cn.bravolinks.erp.crm.server.model.tssx.BO_PRM_PTT_CSC_SPECON;

/**
 * 特殊事项内容
 * @author 49781
 *
 */
public interface BO_PRM_PTT_CSC_SPECONService {
	
	/*
	 * 查询特殊事项内容
	 */
	public List<BO_PRM_PTT_CSC_SPECON> getSpecByBindid(String bindidid);


}
