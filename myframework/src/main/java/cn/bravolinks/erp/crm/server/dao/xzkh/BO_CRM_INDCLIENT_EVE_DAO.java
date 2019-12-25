package cn.bravolinks.erp.crm.server.dao.xzkh;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_EVE;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENTBUS_EVE;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENT_EVE;
@Mapper
public interface BO_CRM_INDCLIENT_EVE_DAO {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(BO_CRM_CLIENTBUS_EVE record);

    Integer insertSelective(BO_CRM_INDCLIENTBUS_EVE record);

    BO_CRM_INDCLIENT_EVE selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(BO_CRM_INDCLIENT_EVE record);

    Integer updateByPrimaryKey(BO_CRM_INDCLIENT_EVE record);
    
    BO_CRM_INDCLIENT_EVE selectByBindid(Integer id);

    Integer deleteByBindid(Integer bindid);
}