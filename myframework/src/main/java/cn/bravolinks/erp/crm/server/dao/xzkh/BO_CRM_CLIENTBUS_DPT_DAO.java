package cn.bravolinks.erp.crm.server.dao.xzkh;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_DPT;
@Mapper
public interface BO_CRM_CLIENTBUS_DPT_DAO {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(BO_CRM_CLIENTBUS_DPT record);

    Integer insertSelective(BO_CRM_CLIENTBUS_DPT record);

    BO_CRM_CLIENTBUS_DPT selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(BO_CRM_CLIENTBUS_DPT record);

    Integer updateByPrimaryKey(BO_CRM_CLIENTBUS_DPT record);
    /**
     *  根据指定的bindid获取数据库记录,BO_CRM_CLIENTBUS_DPT
     *
     * @param bindid
     */
    List<BO_CRM_CLIENTBUS_DPT> selectByBMbindid(String bindid);
    /**
     * 根据bindid 更新指定内容
     * @param record
     * @return
     */
    Integer UpdateByBindid(BO_CRM_CLIENTBUS_DPT record);
    
    BO_CRM_CLIENTBUS_DPT selectByBindid(Integer bindid);
    
    
    String selectBmbh(String id);
}