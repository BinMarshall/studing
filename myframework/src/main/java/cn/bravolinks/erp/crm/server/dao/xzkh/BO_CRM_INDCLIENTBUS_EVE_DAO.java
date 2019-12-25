package cn.bravolinks.erp.crm.server.dao.xzkh;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENTBUS_EVE;
@Mapper
public interface BO_CRM_INDCLIENTBUS_EVE_DAO {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(BO_CRM_INDCLIENTBUS_EVE record);

    Integer insertSelective(BO_CRM_INDCLIENTBUS_EVE record);

    BO_CRM_INDCLIENTBUS_EVE selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(BO_CRM_INDCLIENTBUS_EVE record);

    Integer updateByPrimaryKey(BO_CRM_INDCLIENTBUS_EVE record);
    
    
    /**
     *  根据指定bindid获取数据库记录,BO_CRM_INDCLIENTBUS_EVE
     *
     * @param id
     */
    
    List<BO_CRM_INDCLIENTBUS_EVE> selectByid (String bindid,String id);

    List<BO_CRM_INDCLIENTBUS_EVE> selectListByBindid(Integer bindid);
}