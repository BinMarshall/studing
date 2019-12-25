package cn.bravolinks.erp.crm.server.dao.xzkh;

import cn.bravolinks.erp.crm.server.model.xzkh.BoCrmIndclientbusLm;
import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENT_LM;
@Mapper
public interface BO_CRM_INDCLIENT_LM_DAO {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(BO_CRM_INDCLIENT_LM record);

    Integer insertSelective(BO_CRM_INDCLIENT_LM record);

    Integer insertSelectiveFromBusLm(BoCrmIndclientbusLm buslm);

    BO_CRM_INDCLIENT_LM selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(BO_CRM_INDCLIENT_LM record);

    Integer updateByPrimaryKey(BO_CRM_INDCLIENT_LM record);
    
    BO_CRM_INDCLIENT_LM selectByBid(Integer bindid);

    Integer deleteByBindid(Integer bindid);
}