package cn.bravolinks.erp.crm.server.feign;

import cn.bravolinks.erp.crm.server.feign.impl.CustomImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 审批流服务 - custom 接口
 * @createDate 2018-1-3
 * @updateDate 2018-3-7
 * @author yanqin
 * @version 1.1
 */
@FeignClient(value = "${wfservice}",path = "/custom",fallback = CustomImpl.class)
public interface CustomService {

	/**
	 * 查询当前流程待办数量
	 * @param bindid
	 * @return
	 */
	@RequestMapping(value = "/getDaibanTaskCount",method = RequestMethod.GET)
	Integer getDaibanTaskCount(@RequestParam("bindid") Integer bindid);

	/**
	 * 获取任务流转方式
	 * @param taskid
	 * @return 0=参数错误或者其他错误,1=不是抢签（可能是串签或者并签，在使用审批流服务中，不考虑串签功能），2=抢签
	 */
	@RequestMapping(value = "/getTaskMethod",method = RequestMethod.GET)
	int getTaskMethod(@RequestParam("taskid") Integer taskid);

	/**
	 * 待办更新为已读
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateAleadyRead",method = RequestMethod.GET)
	int updateAleadyRead(@RequestParam("id") Integer id);

	/**
	 * 已办更新为已读
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/updateYBAleadyRead",method = RequestMethod.GET)
	int updateYBAleadyRead(@RequestParam("id") Integer id);

	/**
	 * 获取uuid根据bindid
	 * @param bindid
	 * @return
	 */
	@RequestMapping(value = "getUUIDByBindid",method = RequestMethod.GET)
	String getUUIDByBindid(@RequestParam("bindid") Integer bindid);

	/**
	 * 获取流程创建人，根据bindid
	 * @param bindid
	 * @return
	 */
	@RequestMapping(value = "getWFCreateUserByBindid",method = RequestMethod.GET)
	Map<String,Object> getWFCreateUserByBindid(@RequestParam("bindid") Integer bindid);

	/**
	 * 查询流程信息，orgno 流程id 流程节点id
	 * @param bindid
	 * @param stepno
	 * @return
	 */
	@RequestMapping(value = "getBindidINFO",method = RequestMethod.GET)
	Map<String,Object> getBindidINFO(@RequestParam("bindid") Integer bindid, @RequestParam("stepno") Integer stepno);

	/**
	 * 是不是北京公司的人
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "getIsBJGS",method = RequestMethod.GET)
	boolean getIsBJGS(@RequestParam("userid") String userid);

	/**
	 * 查询taskid，根据bindid和userid
	 * @param bindid
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "getTaskidByBindidAndUserid",method = RequestMethod.GET)
	Integer getTaskidByBindidAndUserid(@RequestParam("bindid") Integer bindid, @RequestParam("userid") String userid);

	/**
	 * 查询是否存在待办，根据审批人和bindid
	 * @return  1存在，0不存在
	 */
	@RequestMapping(value = "getIsExistDaibanTask",method = RequestMethod.GET)
	Integer getIsExistDaibanTask(@RequestParam("bindid") Integer bindid, @RequestParam("userid") String userid);

	/**
	 * 查询是否存在已办，根据审批人和bindid
	 * @return  1存在，0不存在
	 */
	@RequestMapping(value = "getIsExistYibanTask",method = RequestMethod.GET)
	Integer getIsExistYibanTask(@RequestParam("bindid") Integer bindid, @RequestParam("userid") String userid);

	/**
	 * 查询任务是否办理
	 * 1 待办   2已办   0任务不存在
	 * @param taskid
	 * @return
	 */
	@RequestMapping(value = "/getTaskIsHandle",method = RequestMethod.GET)
	Integer getTaskIsHandle(@RequestParam("taskid") Integer taskid);

	/**
	 * 查询加签任务状态，是否是被加签任务加签的
	 * @return 0是普通的加签   1是被加签任务加签的任务
	 */
	@RequestMapping(value = "/selectAppendTaskStatus",method = RequestMethod.GET)
	Map<String,Object> selectAppendTaskStatus(@RequestParam("taskid") Integer taskid);
}
