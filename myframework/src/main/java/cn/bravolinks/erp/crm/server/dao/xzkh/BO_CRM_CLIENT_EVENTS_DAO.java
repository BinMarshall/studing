package cn.bravolinks.erp.crm.server.dao.xzkh;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_EVE;
import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENT_EVENTS;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENT_EVE;

@Mapper
public interface BO_CRM_CLIENT_EVENTS_DAO {
    Integer deleteByPrimaryKey(Integer id);

    Integer deleteByBindid(Integer bindid);

    Integer insert(BO_CRM_INDCLIENT_EVE record);

    Integer insertSelective(BO_CRM_INDCLIENT_EVE record);

    Integer insertSelectiveFromBus(BO_CRM_CLIENTBUS_EVE record);

    BO_CRM_CLIENT_EVENTS selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(BO_CRM_CLIENT_EVENTS record);

    Integer updateByPrimaryKey(BO_CRM_CLIENT_EVENTS record);
}