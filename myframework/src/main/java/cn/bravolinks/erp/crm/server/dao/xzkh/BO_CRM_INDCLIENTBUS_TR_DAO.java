package cn.bravolinks.erp.crm.server.dao.xzkh;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENTBUS_TR;
@Mapper
public interface BO_CRM_INDCLIENTBUS_TR_DAO {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(BO_CRM_INDCLIENTBUS_TR record);

    Integer insertSelective(BO_CRM_INDCLIENTBUS_TR record);

    BO_CRM_INDCLIENTBUS_TR selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(BO_CRM_INDCLIENTBUS_TR record);

    Integer updateByPrimaryKey(BO_CRM_INDCLIENTBUS_TR record);
    
    /**
     *  根据指定bindid获取数据库记录,BO_CRM_INDCLIENTBUS_TR
     *
     * @param id
     */
    
    List<BO_CRM_INDCLIENTBUS_TR> selectByid (String bindid ,String id);

    List<BO_CRM_INDCLIENTBUS_TR> selectListByBindid(Integer bindid);
}