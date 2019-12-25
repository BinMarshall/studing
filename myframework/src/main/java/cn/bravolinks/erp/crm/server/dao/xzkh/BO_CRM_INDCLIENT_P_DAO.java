package cn.bravolinks.erp.crm.server.dao.xzkh;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.tssx.BO_CRM_CLIENTBUS_LM;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENT_P;

import java.util.Map;

@Mapper
public interface BO_CRM_INDCLIENT_P_DAO {
    Integer deleteByPrimaryKey(Integer id);

    Integer insert(BO_CRM_CLIENTBUS_LM record);

    Integer insertSelective(BO_CRM_INDCLIENT_P record);

    BO_CRM_INDCLIENT_P selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(BO_CRM_INDCLIENT_P record);

    Integer updateByPrimaryKey(BO_CRM_INDCLIENT_P record);

    BO_CRM_INDCLIENT_P selectByBindid(Integer bindid);

    BO_CRM_INDCLIENT_P selectByLxrBindid(Integer bindid);

    Integer updateZHGXSJByKHBH(Map<String, Object> condition);
}