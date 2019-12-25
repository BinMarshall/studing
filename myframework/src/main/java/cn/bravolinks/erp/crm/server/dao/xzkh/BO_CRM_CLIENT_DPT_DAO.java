package cn.bravolinks.erp.crm.server.dao.xzkh;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENT_DPT;
@Mapper
public interface BO_CRM_CLIENT_DPT_DAO {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(BO_CRM_CLIENT_DPT record);

    Integer insertSelective(BO_CRM_CLIENT_DPT record);

    BO_CRM_CLIENT_DPT selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(BO_CRM_CLIENT_DPT record);

    Integer updateByPrimaryKey(BO_CRM_CLIENT_DPT record);
    
  
    
}