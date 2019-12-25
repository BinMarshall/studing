package cn.bravolinks.erp.crm.server.dao.xzkh;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENT_SERDPT;

@Mapper
public interface BO_CRM_CLIENT_SERDPT_DAO {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(BO_CRM_CLIENT_SERDPT record);

    Integer insertSelective(BO_CRM_CLIENT_SERDPT record);

    BO_CRM_CLIENT_SERDPT selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(BO_CRM_CLIENT_SERDPT record);

    Integer updateByPrimaryKey(BO_CRM_CLIENT_SERDPT record);
    
    String selectByBMZH(String khbh);
    
    String selectByBMXM(String khbh);

    
}

