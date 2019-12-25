package cn.bravolinks.erp.crm.server.dao.xzkh;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENT_P;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface BO_CRM_CLIENT_P_DAO {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(BO_CRM_CLIENT_P record);

    Integer insertSelective(BO_CRM_CLIENT_P record);

    BO_CRM_CLIENT_P selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(BO_CRM_CLIENT_P record);

    Integer updateByPrimaryKey(BO_CRM_CLIENT_P record);
    
    BO_CRM_CLIENT_P selectById(Integer id);
    
    /**
     * gengxin指定服务部门
     * @param record
     * @return
     */
    Integer updateBoCRMBM (BO_CRM_CLIENT_P record);

    Integer updateByBindid(Map<String, Object> condition);
}