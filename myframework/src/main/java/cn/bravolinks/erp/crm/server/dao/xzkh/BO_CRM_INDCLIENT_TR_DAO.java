package cn.bravolinks.erp.crm.server.dao.xzkh;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENTBUS_TR;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENT_TR;

@Mapper
public interface BO_CRM_INDCLIENT_TR_DAO {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(BO_CRM_INDCLIENT_TR record);

    Integer insertSelective(BO_CRM_INDCLIENTBUS_TR record);

    BO_CRM_INDCLIENT_TR selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(BO_CRM_INDCLIENT_TR record);

    Integer updateByPrimaryKey(BO_CRM_INDCLIENT_TR record);
    
    BO_CRM_INDCLIENT_TR selectByBid(Integer bindid);

    Integer deleteByBindid(Integer bindid);
}