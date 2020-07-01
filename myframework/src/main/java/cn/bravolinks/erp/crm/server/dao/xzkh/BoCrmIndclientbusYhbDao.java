package cn.bravolinks.erp.crm.server.dao.xzkh;

import cn.bravolinks.erp.crm.server.model.xzkh.BoCrmIndclientbusYhb;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoCrmIndclientbusYhbDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BO_CRM_INDCLIENTBUS_YHB
     *
     * @mbggenerated Fri Sep 21 14:49:32 CST 2018
     */
    int deleteByPrimaryKey(Short id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BO_CRM_INDCLIENTBUS_YHB
     *
     * @mbggenerated Fri Sep 21 14:49:32 CST 2018
     */
    int insert(BoCrmIndclientbusYhb record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BO_CRM_INDCLIENTBUS_YHB
     *
     * @mbggenerated Fri Sep 21 14:49:32 CST 2018
     */
    int insertSelective(BoCrmIndclientbusYhb record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BO_CRM_INDCLIENTBUS_YHB
     *
     * @mbggenerated Fri Sep 21 14:49:32 CST 2018
     */
    BoCrmIndclientbusYhb selectByPrimaryKey(Short id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BO_CRM_INDCLIENTBUS_YHB
     *
     * @mbggenerated Fri Sep 21 14:49:32 CST 2018
     */
    int updateByPrimaryKeySelective(BoCrmIndclientbusYhb record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BO_CRM_INDCLIENTBUS_YHB
     *
     * @mbggenerated Fri Sep 21 14:49:32 CST 2018
     */
    int updateByPrimaryKey(BoCrmIndclientbusYhb record);

    List<BoCrmIndclientbusYhb> selectByBindid(Integer bindid);
}