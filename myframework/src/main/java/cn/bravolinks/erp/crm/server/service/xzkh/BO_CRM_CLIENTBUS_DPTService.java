package cn.bravolinks.erp.crm.server.service.xzkh;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.bravolinks.erp.crm.server.model.tssx.BO_CRM_CLIENTBUS_LM;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_DPT;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENT_DPT;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENT_SERDPT;

public interface BO_CRM_CLIENTBUS_DPTService {
	
	
	/**
	 * 根据指定的bindid获取数据库记录,BO_CRM_CLIENTBUS_P
	 * @param bindid
	 * @return
	 */
	
	List<BO_CRM_CLIENTBUS_DPT> selectByBMbindid(String bindid);
	
	
	//  修改数据
	@Transactional
	public void UpdateByBindid(BO_CRM_CLIENTBUS_DPT bo_crm);
	

	/**
	 * 插入 BO_CRM_CLIENT_DPT
	 * @param record
	 */
	public void insertClientDPT(BO_CRM_CLIENT_DPT record);
	
	
	/**
	 * 插入 BO_CRM_CLIENT_SERDPT
	 * @param record
	 */
	public void insertSerdpt(BO_CRM_CLIENT_SERDPT record);
	
	/**
	 * 插入 BO_CRM_CLIENTBUS_LM
	 * @param record
	 */
	public void insertIndcientLM(BO_CRM_CLIENTBUS_LM record);
	
	
	 String selectBmbh(String id);

}
