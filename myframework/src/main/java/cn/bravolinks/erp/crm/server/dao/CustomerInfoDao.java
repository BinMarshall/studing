package cn.bravolinks.erp.crm.server.dao;

import cn.bravolinks.erp.crm.server.model.CustomerInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CustomerInfoDao {

    /**
     * 查询条数，根据bindid
     * @param bindid
     * @return
     */
    Integer getCountByBindid(Integer bindid);

    /**
     *逻辑删除数据，根据bindid
     * @param bindid
     * @return
     */
    Integer delCustomerInfoByBindid(Integer bindid);

    /**
     *查询该客户有没有其他在途的客户档案扩展合同信息流程
     * @param khmc
     * @return
     */
    Integer getCountWorkflowBykhmc(String khmc);

    /**
     *查询客户名称
     * @param bindid
     * @return
     */
    String getKHMC(Integer bindid);

    /**
     *查询用户名
     * @param userid
     * @return
     */
    String getUsername(String userid);

    /**
     * 根据bindid查询客户信息
     * @param bindid
     * @return
     */
    Integer updateIsend(Integer bindid);

    /**
     * 根据bindid查询客户信息
     * @param bindid
     * @return
     */
    Map<String,Object> getKhxxByBindid(Integer bindid);

    /**
     * 查询币种
     * @return
     */
    List<Map<String,Object>> getBIZHONG();

    /**
     *查询供应商条数
     * @return
     */
    Integer getGYSCOUNT(@Param("serach") String serach,@Param("qygs") String qygs);

    /**
     * 查询供应商
     * @param min
     * @param max
     * @return
     */
    List<Map<String ,Object>> getGYS(@Param("min") Integer min,@Param("max") Integer max,@Param("serach") String serach,@Param("qygs") String qygs);

    /**
     * 查询目的地类型
     * @return
     */
    List<Map<String,Object>> getMDDLX();

    /**
     * 查询业务属性
     * @return
     */
    List<Map<String,Object>> getYWSX();

    /**
     *查询客户的合同信息条数
     * @param khmc
     * @return
     */
    Integer getHTXXCOUNT(@Param("khmc") String khmc);


    /**
     * 查询客户的合同信息
     * @param khmc
     * @return
     */
    List<Map<String,Object>> getHTXX(@Param("khmc") String khmc,@Param("min") Integer min,@Param("max") Integer max);

    /**
     * 更新客户信息
     * @param customerInfo
     * @return
     */
    Integer updateKHINFO(CustomerInfo customerInfo);

    /**
     * 查询客户总数
     * @return
     */
    Integer getKhCount(@Param("userid") String userid,@Param("serach") String serach);

    /**
     * 查询客户
     * @param min
     * @param max
     * @return
     */
    List<Map<String, Object>> getKhInfo(@Param("userid")String userid,@Param("min") Integer min,
                                        @Param("max") Integer max,@Param("serach")String serach);

    /**
     * 根据客户名称查询，客户信息
     * @param khmc
     * @return
     */
    Map<String,Object> getKHXXByKHMC(String khmc);

    /**
     * insert
     * @param customerInfo
     * @return
     */
    Integer insert(CustomerInfo customerInfo);
}