package cn.bravolinks.erp.crm.server.dao.xzkh;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_EVE;

import java.util.List;

@Mapper
public interface BO_CRM_CLIENTBUS_EVE_DAO {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(BO_CRM_CLIENTBUS_EVE record);

    Integer insertSelective(BO_CRM_CLIENTBUS_EVE record);

    BO_CRM_CLIENTBUS_EVE selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(BO_CRM_CLIENTBUS_EVE record);

    Integer updateByPrimaryKey(BO_CRM_CLIENTBUS_EVE record);
    
    BO_CRM_CLIENTBUS_EVE selectByBindid(Integer bindid);

    List<BO_CRM_CLIENTBUS_EVE> selectListByBindid(Integer bindid);
}