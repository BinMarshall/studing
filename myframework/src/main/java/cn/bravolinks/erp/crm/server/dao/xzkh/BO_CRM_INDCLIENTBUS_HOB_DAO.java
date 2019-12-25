package cn.bravolinks.erp.crm.server.dao.xzkh;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENTBUS_HOB;
@Mapper
public interface BO_CRM_INDCLIENTBUS_HOB_DAO {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(BO_CRM_INDCLIENTBUS_HOB record);

    Integer insertSelective(BO_CRM_INDCLIENTBUS_HOB record);

    BO_CRM_INDCLIENTBUS_HOB selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(BO_CRM_INDCLIENTBUS_HOB record);

    Integer updateByPrimaryKey(BO_CRM_INDCLIENTBUS_HOB record);
    
    /**
     *  根据指定bindid获取数据库记录,BO_CRM_INDCLIENTBUS_EVE
     *
     * @param id
     */
    
    List<BO_CRM_INDCLIENTBUS_HOB> selectByid (String bindid,String id);

    List<BO_CRM_INDCLIENTBUS_HOB> selectByBindid(Integer bindid);
}