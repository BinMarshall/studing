package cn.bravolinks.erp.crm.server.dao.xzkh;

import cn.bravolinks.erp.crm.server.model.xzkh.BoCrmIndclientbusLm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoCrmIndclientbusLmDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BO_CRM_INDCLIENTBUS_LM
     *
     * @mbggenerated Fri Sep 21 14:49:32 CST 2018
     */
    int deleteByPrimaryKey(Short id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BO_CRM_INDCLIENTBUS_LM
     *
     * @mbggenerated Fri Sep 21 14:49:32 CST 2018
     */
    int insert(BoCrmIndclientbusLm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BO_CRM_INDCLIENTBUS_LM
     *
     * @mbggenerated Fri Sep 21 14:49:32 CST 2018
     */
    int insertSelective(BoCrmIndclientbusLm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BO_CRM_INDCLIENTBUS_LM
     *
     * @mbggenerated Fri Sep 21 14:49:32 CST 2018
     */
    BoCrmIndclientbusLm selectByPrimaryKey(Short id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BO_CRM_INDCLIENTBUS_LM
     *
     * @mbggenerated Fri Sep 21 14:49:32 CST 2018
     */
    int updateByPrimaryKeySelective(BoCrmIndclientbusLm record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BO_CRM_INDCLIENTBUS_LM
     *
     * @mbggenerated Fri Sep 21 14:49:32 CST 2018
     */
    int updateByPrimaryKey(BoCrmIndclientbusLm record);

    List<BoCrmIndclientbusLm> selectByBindid(Integer bindid);
}