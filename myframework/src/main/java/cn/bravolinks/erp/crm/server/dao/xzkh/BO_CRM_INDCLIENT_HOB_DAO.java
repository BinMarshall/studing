package cn.bravolinks.erp.crm.server.dao.xzkh;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENTBUS_HOB;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENT_HOB;

@Mapper
public interface BO_CRM_INDCLIENT_HOB_DAO {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(BO_CRM_INDCLIENT_HOB record);

    Integer insertSelective(BO_CRM_INDCLIENTBUS_HOB record);

    BO_CRM_INDCLIENT_HOB selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(BO_CRM_INDCLIENT_HOB record);

    Integer updateByPrimaryKey(BO_CRM_INDCLIENT_HOB record);
    
    BO_CRM_INDCLIENT_HOB selectByBid(Integer bindid);

    Integer deleteByBindid(Integer bindid);
}