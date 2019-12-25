package cn.bravolinks.erp.crm.server.service;


import cn.bravolinks.erp.crm.server.model.CustomerContractInfo;

import java.util.List;
import java.util.Map;

public interface CustomerContractInfoService {

	/**
	 * 查询条数，根据bindid
	 * @param bindid
	 * @return
	 */
	Integer getCountByBindid(Integer bindid);

	/**
	 * 删除数据，根据bindid
	 * @param bindid
	 * @return
	 */
	Integer deleteDataByBindid(Integer bindid);

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
	Integer getChongfuCount(String khmc, Integer bindid);

	/**
	 * 删除，根据id
	 * @param id
	 * @return
	 */
	Integer delCustomerContractInfoById(Integer id);

	/**
	 * 获取下拉列表数据
	 * @return
	 */
	Map<String,Object> getOptions();

	/**
	 * 根据bindid查询客户信息
	 * @param bindid
	 * @return
	 */
	Map<String,Object> getKhxxByBindid(Integer bindid);

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
	Integer getCustomerContractInfoCount(String khmc,Integer bindid);

	/**
	 * 查询
	 * @param khmc
	 * @return
	 */
	List<CustomerContractInfo> getCustomerContractInfo(String khmc,Integer min,Integer max,Integer bindid);

	/**
	 * 添加记录到临时表
	 * @param c
	 * @return
	 */
	Integer insertCustomerContractInfo(CustomerContractInfo c);

	/**
	 *查询供应商条数
	 * @return
	 */
	Integer getGYSCOUNT(String serach, String qygs);

	/**
	 * 查询供应商
	 * @param min
	 * @param max
	 * @return
	 */
	List<Map<String ,Object>> getGYS( Integer min,Integer max,String serach, String qygs);

	/**
	 *查询客户的合同信息条数
	 * @param khmc
	 * @return
	 */
	Integer getHTXXCOUNT(String khmc);

	/**
	 * 查询客户的合同信息
	 * @param khmc
	 * @return
	 */
	List<Map<String,Object>> getHTXX(String khmc,Integer min,Integer max);

	/**
	 * 更新客户信息
	 * @return
	 */
	Map<String,Object> updateKHINFO(Integer bindid,String khmc);

	/**
	 * 根据客户名称查询，客户信息
	 * @param khmc
	 * @return
	 */
	Map<String,Object> getKHXXByKHMC(String khmc);

	/**
	 *创建流程
	 * @param userid
	 * @param htbh
	 * @return
	 */
	Map<String,Object> createWorkFlow(String userid,String htbh);

	/**
	 * 查询客户
	 * @param min
	 * @param max
	 * @return
	 */
	List<Map<String,Object>> getKhInfo(String userid,Integer min, Integer max,String serach);

	/**
	 * 查询客户总数
	 * @return
	 */
	Integer getKhCount(String userid,String serach);

}
