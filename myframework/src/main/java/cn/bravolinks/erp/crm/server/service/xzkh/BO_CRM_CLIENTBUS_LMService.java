package cn.bravolinks.erp.crm.server.service.xzkh;

import java.util.List;

import cn.bravolinks.erp.crm.server.model.tssx.BO_CRM_CLIENTBUS_LM;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENTBUS_EVE;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENTBUS_HOB;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENTBUS_TR;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENT_LM;



public interface BO_CRM_CLIENTBUS_LMService {

	//查询联系人信息
	public List<BO_CRM_CLIENTBUS_LM> selectByid(String bindidid);
	
	
	int insertSelective(BO_CRM_INDCLIENT_LM record);
	
	 /**
     *  根据指定bindid获取数据库记录,BO_CRM_INDCLIENTBUS_TR
     *
     * @param id
     */
    
    List<BO_CRM_INDCLIENTBUS_TR> selectByTR (String bindid,String id);
    
    
    /**
     *  根据指定bindid获取数据库记录,BO_CRM_INDCLIENTBUS_EVE
     *
     * @param id
     */
    
    List<BO_CRM_INDCLIENTBUS_HOB> selectByHOB (String bindid,String id);
    
    

    /**
       *  根据指定bindid获取数据库记录,BO_CRM_INDCLIENTBUS_EVE
       *
       * @param id
       */
      
      List<BO_CRM_INDCLIENTBUS_EVE> selectByEVE (String bindid,String id);
      
      
      

	  	int insertSelectiveHOB(BO_CRM_INDCLIENTBUS_HOB record);
	
		int insertSelectiveTR(BO_CRM_INDCLIENTBUS_TR record);
	
		int insertSelectiveEVE(BO_CRM_INDCLIENTBUS_EVE record);

      
}
