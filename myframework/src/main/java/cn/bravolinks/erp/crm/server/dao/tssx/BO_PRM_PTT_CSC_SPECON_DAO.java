package cn.bravolinks.erp.crm.server.dao.tssx;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.tssx.BO_PRM_PTT_CSC_SPECON;



@Mapper
public interface BO_PRM_PTT_CSC_SPECON_DAO {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(BO_PRM_PTT_CSC_SPECON record);

    Integer insertSelective(BO_PRM_PTT_CSC_SPECON record);

    BO_PRM_PTT_CSC_SPECON selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(BO_PRM_PTT_CSC_SPECON record);

    Integer updateByPrimaryKey(BO_PRM_PTT_CSC_SPECON record);
    
    /**
     * 根据bindid 查询所有的特殊事项内容
     * @param bindid
     * @return
     */
    List<BO_PRM_PTT_CSC_SPECON> getSpecByBindid(String bindid);

    
    
}