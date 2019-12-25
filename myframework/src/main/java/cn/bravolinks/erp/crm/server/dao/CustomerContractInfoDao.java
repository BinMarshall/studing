package cn.bravolinks.erp.crm.server.dao;


import cn.bravolinks.erp.crm.server.model.CustomerContractInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerContractInfoDao {

    /**
     *逻辑删除数据，根据bindid
     * @param bindid
     * @return
     */
    Integer deleteCustomerContractByBindid(Integer bindid);

    /**
     * 跟新流程是否结束标识
     * @param bindid
     * @return
     */
    Integer updateIsend(Integer bindid);

    /**
     * 查询重复的条数
     * @param khmc
     * @param bindid
     * @return
     */
    Integer getChongfuCount(@Param("khmc")String khmc,@Param("bindid")Integer bindid);

    /**
     * 删除，根据id
     * @param id
     * @return
     */
    Integer delCustomerContractInfoById(Integer id);

    /**
     * 更新客户合同信息表
     * @param c
     * @return
     */
    Integer updateByPrimaryKey(CustomerContractInfo c);

    /**
     * 查询客户合同信息表条数
     * @param khmc
     * @return
     */
    Integer getCustomerContractInfoCount(@Param("khmc") String khmc,@Param("bindid") Integer bindid);

    /**
     * 查询客户合同信息表
     * @param khmc
     * @return
     */
    List<CustomerContractInfo> getCustomerContractInfo(@Param("khmc") String khmc,@Param("min") Integer min,@Param("max") Integer max,@Param("bindid") Integer bindid);

    /**
     * 添加记录到临时表
     * @param c
     * @return
     */
    Integer insertCustomerContractInfo(CustomerContractInfo c);
    
}