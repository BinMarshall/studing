package cn.bravolinks.erp.crm.server.feign;

import cn.bravolinks.erp.crm.server.feign.impl.DataImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 审批流服务 - data 接口
 * @createDate 2018-1-3
 * @updateDate 2018-1-3
 * @author yanqin
 * @version 1.0
 */
@FeignClient(value = "${wfservice}",path = "/data",fallback = DataImpl.class)
public interface DataService {

	/**
	 * 灵活查询
	 *
	 * @param wfTaskLog
	 * @return
	 */
	@RequestMapping(value = "/selectWFTaskLogByObject",method = RequestMethod.POST)
	List<Map<String,Object>> selectWFTaskLogByObject(@RequestBody Map<String, Object> wfTaskLog);

	/**
	 * 灵活查询
	 *
	 * @param wfTask
	 * @return
	 */
	@RequestMapping(value = "/selectWFTaskByObject",method = RequestMethod.POST)
	List<Map<String,Object>> selectWFTaskByObject(@RequestBody Map<String, Object> wfTask);

	/**
	 * 查询用户常用信息
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/queryUserCommonInfo",method = RequestMethod.GET)
	Map<String,Object> queryUserCommonInfo(@RequestParam("userid") String userid);

	/**
	 * 查询用户名
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/selectUsernameByUserid",method = RequestMethod.GET)
	String selectUsernameByUserid(@RequestParam("userid") String userid);

	/**
	 * 查询任务，根据bindid，从已办表
	 * @param bindid
	 * @return
	 */
	@RequestMapping(value = "/getWFTaskLogByBindid",method = RequestMethod.GET)
	List<Map<String,Object>> getWFTaskLogByBindid(@RequestParam("bindid") Integer bindid);

	/**
	 * 查询任务，根据bindid,从待办表
	 * @param bindid
	 * @return
	 */
	@RequestMapping(value = "/getWFTaskByBindid",method = RequestMethod.GET)
	List<Map<String,Object>> getWFTaskByBindid(@RequestParam("bindid") Integer bindid);

	/**
	 * 查询任务，从已办表
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getWFTaskLogById",method = RequestMethod.GET)
	Map<String,Object> getWFTaskLogById(@RequestParam("id") Integer id);

	/**
	 * 查询任务，从代办表
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getWFTaskById",method = RequestMethod.GET)
	Map<String,Object> getWFTaskById(@RequestParam("id") Integer id);

	/**
	 * 查询任务 从待办已办表
	 * @param id - taskid(任务id)
	 * @return
	 */
	@RequestMapping(value = "/getTaskById",method = RequestMethod.GET)
	Map<String,Object> getTaskById(@RequestParam("id") Integer id);
}
