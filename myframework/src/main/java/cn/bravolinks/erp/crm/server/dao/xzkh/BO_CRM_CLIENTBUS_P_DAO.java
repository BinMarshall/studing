package cn.bravolinks.erp.crm.server.dao.xzkh;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_P;

@Mapper
public interface BO_CRM_CLIENTBUS_P_DAO {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(BO_CRM_CLIENTBUS_P record);

    Integer insertSelective(BO_CRM_CLIENTBUS_P record);

    BO_CRM_CLIENTBUS_P selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(BO_CRM_CLIENTBUS_P record);

    Integer updateByPrimaryKey(BO_CRM_CLIENTBUS_P record);
    
    /**
     *  根据指定的bindid获取数据库记录,BO_CRM_CLIENTBUS_P
     *
     * @param bindid
     */
    BO_CRM_CLIENTBUS_P selectBybindid(String bindid);
    
    /**
     * 更新指定的字段
     */
    
    Integer UpdateById(BO_CRM_CLIENTBUS_P record);
    
    /**
     * 更新指定的字段
     */
    
    Integer UpdateByIdJT(BO_CRM_CLIENTBUS_P record);
    
    
    /**
     * 更新服务部门名称
     */
    
    Integer updateBoCRMBM(BO_CRM_CLIENTBUS_P record);
    
    /**
     * 更新服务ren
     */
    
    Integer updateBoCRMR(BO_CRM_CLIENTBUS_P record);
    
    /**
     *  单位编号
     */
    
    List<BO_CRM_CLIENTBUS_P> selectByKhbh (Integer cd);
    
    Integer UpdateByBindid (BO_CRM_CLIENTBUS_P record);

    String fetchTableFieldUUID(Map<String, String> map);
}