package cn.bravolinks.erp.crm.server.feign;


import cn.bravolinks.erp.crm.server.feign.impl.PageImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 审批流服务 - im 接口
 * @createDate 2018-1-3
 * @updateDate 2018-1-3
 * @author yanqin
 * @version 1.0
 */
@FeignClient(value = "${wfservice}",path = "/page",fallback = PageImpl.class)
public interface PageService {

	/**
	 * 分页查询供应商已办任务
	 *
	 * @return
	 */
	@RequestMapping(value = "/selectGYSYibanTask",method = RequestMethod.POST)
	List<Map<String, Object>> selectGYSYibanTask(@RequestParam("pageNumber") Integer pageNumber, @RequestParam("pageSize") Integer pageSize,
												 @RequestParam("userid") String userid, @RequestBody Map<String,Object> filter);

	/**
	 * 分页查询供应商待办
	 *
	 * @return
	 */
	@RequestMapping(value = "/selectGYSDaibanTask",method = RequestMethod.POST)
	List<Map<String, Object>> selectGYSDaibanTask(@RequestParam("pageNumber") Integer pageNumber, @RequestParam("pageSize") Integer pageSize,
												  @RequestParam("userid") String userid, @RequestBody Map<String,Object> filter);

	/**
	 * 查询任务详情
	 * @param taskid
	 * @return
	 */
	@RequestMapping(value = "/selectTaskDetails",method = RequestMethod.GET)
	Map<String,Object> selectTaskDetails(@RequestParam("taskid") Integer taskid);

	/**
	 * 查询未阅通知
	 * @param target
	 * @return
	 */
	@RequestMapping(value = "selectWeiyue",method = RequestMethod.GET)
	Map<String,Object> selectWeiyue(@RequestParam("target") String target, @RequestParam("pageNumber") Integer pageNumber,
									@RequestParam("pageSize") Integer pageSize,
									@RequestParam("isMoblie") Integer isMoblie, @RequestParam("like") String like);

	/**
	 * 待办列表
	 * @param pageNumber
	 * @param userid
	 * @param wf_type
	 * @return
	 */
	@RequestMapping(value = "workflowlist_db",method = RequestMethod.GET)
	Map<String,Object> db(@RequestParam("pageNumber") Integer pageNumber, @RequestParam("pageSize") Integer pageSize,
						  @RequestParam("userid") String userid, @RequestParam("wf_type") String wf_type,
						  @RequestParam("isMoblie") Integer isMoblie, @RequestParam("like") String like);

	/**
	 * 已办任务列表
	 * @param pageNumber
	 * @param pageSize
	 * @param userid
	 * @param wf_type
	 * @return
	 */
	@RequestMapping(value = "workflowlist_yb",method = RequestMethod.GET)
	Map<String,Object> yb(@RequestParam("pageNumber") Integer pageNumber, @RequestParam("pageSize") Integer pageSize,
						  @RequestParam("userid") String userid, @RequestParam("wf_type") String wf_type,
						  @RequestParam("isMoblie") Integer isMoblie, @RequestParam("like") String like);

	/**
	 * 发出列表
	 * @param pageNumber
	 * @param pageSize
	 * @param userid
	 * @param wf_type
	 * @return
	 */
	@RequestMapping(value = "workflowlist_fc",method = RequestMethod.GET)
	Map<String,Object> fc(@RequestParam("pageNumber") Integer pageNumber, @RequestParam("pageSize") Integer pageSize,
						  @RequestParam("userid") String userid, @RequestParam("wf_type") String wf_type);

	/**
	 * 查询流程实例所有任务
	 * @param bindid
	 * @return
	 */
	@RequestMapping(value = "selectWfAllTask",method = RequestMethod.GET)
	List<Map<String,Object>> selectWfAllTask(@RequestParam("bindid") Integer bindid);

	/**
	 * 查询流程实例信息
	 * @param bindid
	 * @return
	 */
	@RequestMapping(value = "selectWfInfo",method = RequestMethod.GET)
	Map<String,Object> selectWfInfo(@RequestParam("bindid") Integer bindid);
}
