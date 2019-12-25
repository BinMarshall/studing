package cn.bravolinks.erp.crm.server.dao.tssx;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.tssx.BO_PRM_PTT_CSC_SPECON_S;
@Mapper
public interface BO_PRM_PTT_CSC_SPECON_S_DAO {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(BO_PRM_PTT_CSC_SPECON_S record);

    Integer insertSelective(BO_PRM_PTT_CSC_SPECON_S record);

    BO_PRM_PTT_CSC_SPECON_S selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(BO_PRM_PTT_CSC_SPECON_S record);

    Integer updateByPrimaryKey(BO_PRM_PTT_CSC_SPECON_S record);
}