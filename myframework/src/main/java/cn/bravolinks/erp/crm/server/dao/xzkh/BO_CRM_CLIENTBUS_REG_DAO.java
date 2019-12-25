package cn.bravolinks.erp.crm.server.dao.xzkh;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_REG;

import java.util.List;

@Mapper
public interface BO_CRM_CLIENTBUS_REG_DAO {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(BO_CRM_CLIENTBUS_REG record);

    Integer insertSelective(BO_CRM_CLIENTBUS_REG record);

    BO_CRM_CLIENTBUS_REG selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(BO_CRM_CLIENTBUS_REG record);

    Integer updateByPrimaryKey(BO_CRM_CLIENTBUS_REG record);

    BO_CRM_CLIENTBUS_REG selectByBindid(Integer id);

    List<BO_CRM_CLIENTBUS_REG> selectListByBindid(Integer bindid);
}