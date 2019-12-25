package cn.bravolinks.erp.crm.server.dao.xzkh;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.tssx.BO_CRM_CLIENTBUS_LM;

@Mapper
public interface BO_CRM_CLIENTBUS_LM_DAO {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(BO_CRM_CLIENTBUS_LM record);

    Integer insertSelective(BO_CRM_CLIENTBUS_LM record);

    BO_CRM_CLIENTBUS_LM selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(BO_CRM_CLIENTBUS_LM record);

    Integer updateByPrimaryKey(BO_CRM_CLIENTBUS_LM record);
    
    /**
     *  根据指定bindid获取数据库记录,BO_CRM_CLIENTBUS_LM
     *
     * @param id
     */
    
    List<BO_CRM_CLIENTBUS_LM> selectByid (String bindid);
    
}