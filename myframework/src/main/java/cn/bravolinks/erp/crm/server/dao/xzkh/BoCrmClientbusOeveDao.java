package cn.bravolinks.erp.crm.server.dao.xzkh;

import cn.bravolinks.erp.crm.server.model.xzkh.BoCrmClientbusOeve;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BoCrmClientbusOeveDao {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BO_CRM_CLIENTBUS_OEVE
     *
     * @mbggenerated Wed Sep 12 12:56:31 CST 2018
     */
    int deleteByPrimaryKey(Short id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BO_CRM_CLIENTBUS_OEVE
     *
     * @mbggenerated Wed Sep 12 12:56:31 CST 2018
     */
    int insert(BoCrmClientbusOeve record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BO_CRM_CLIENTBUS_OEVE
     *
     * @mbggenerated Wed Sep 12 12:56:31 CST 2018
     */
    int insertSelective(BoCrmClientbusOeve record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BO_CRM_CLIENTBUS_OEVE
     *
     * @mbggenerated Wed Sep 12 12:56:31 CST 2018
     */
    BoCrmClientbusOeve selectByPrimaryKey(Short id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BO_CRM_CLIENTBUS_OEVE
     *
     * @mbggenerated Wed Sep 12 12:56:31 CST 2018
     */
    int updateByPrimaryKeySelective(BoCrmClientbusOeve record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BO_CRM_CLIENTBUS_OEVE
     *
     * @mbggenerated Wed Sep 12 12:56:31 CST 2018
     */
    int updateByPrimaryKey(BoCrmClientbusOeve record);

    List<BoCrmClientbusOeve> selectByBindid(Integer bindid);
}