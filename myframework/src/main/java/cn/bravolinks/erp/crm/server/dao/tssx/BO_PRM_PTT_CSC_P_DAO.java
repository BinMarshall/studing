package cn.bravolinks.erp.crm.server.dao.tssx;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.tssx.BO_PRM_PTT_CSC_P;



@Mapper
public interface BO_PRM_PTT_CSC_P_DAO {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(BO_PRM_PTT_CSC_P record);

    Integer insertSelective(BO_PRM_PTT_CSC_P record);

    BO_PRM_PTT_CSC_P selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(BO_PRM_PTT_CSC_P record);

    Integer updateByPrimaryKey(BO_PRM_PTT_CSC_P record);
    
    /**
     * 
     * 根据bindid 查询出对应的客户服务方案主表信息
     */
    
    BO_PRM_PTT_CSC_P getKhfwfa(String bindid);
    
}
