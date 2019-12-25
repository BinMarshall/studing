package cn.bravolinks.erp.crm.server.dao.xzkh;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_REG;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENT_REG;
@Mapper
public interface BO_CRM_CLIENT_REG_DAO {
    Integer deleteByPrimaryKey(Integer id);

    Integer deleteByBindid(Integer bindid);

    Integer insert(BO_CRM_CLIENTBUS_REG record);

    Integer insertSelective(BO_CRM_CLIENTBUS_REG record);

    BO_CRM_CLIENT_REG selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(BO_CRM_CLIENT_REG record);

    Integer updateByPrimaryKey(BO_CRM_CLIENT_REG record);
}