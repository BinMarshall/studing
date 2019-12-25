package cn.bravolinks.erp.crm.server.feign;

import cn.bravolinks.erp.crm.server.feign.impl.WorkFlowImpl;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 审批流服务 - workflow 接口
 * @createDate 2018-1-3
 * @updateDate 2018-1-3
 * @author yanqin
 * @version 1.0
 */
@FeignClient(value = "${wfservice}",path = "/workflow",fallback = WorkFlowImpl.class)
public interface WorkFlowService {

	/**
	 * 删除流程实例
	 * @param processInstanceId
	 * @return
	 */
	@RequestMapping(value = "removeProcessInstance/{processInstanceId}",method = RequestMethod.GET)
	boolean removeProcessInstance(@PathVariable("processInstanceId") int processInstanceId);

	/**
	 * 删除多个流程实例
	 * @param processInstanceIds
	 * @return
	 */
	@RequestMapping(value = "removeProcessInstances",method = RequestMethod.GET)
	boolean removeProcessInstances(@RequestParam("processInstanceIds") int[] processInstanceIds);

	/**
	 * 获取一个流程实例的全部流程变量及值。
	 * @param userId 一个合法的 AWS 账户
	 * @param processInstanceId 流程实例 ID
	 * @param taskInstanceId 任务实例 ID（可选）
	 * @return Hashtable Key 为变量名，Value 为变量值。若该变量未初始化，返回值为’’，出错抛出 AWSSDKException 异常。
	 */
	@RequestMapping(value = "getVariables",method = RequestMethod.GET)
	String getVariables(@RequestParam("userId") String userId, @RequestParam("processInstanceId") int processInstanceId,
						@RequestParam("taskInstanceId") int taskInstanceId);
	
	/**
	 * 创建一个仅存储的数据维护实例，并触发流程启动事件。
	 * @return
	 */
	@RequestMapping(value = "/createBOInstance",method = RequestMethod.GET) 
	Map<String,Object> createBOInstance(@RequestParam("uuid") String uuid, @RequestParam("userid") String userid, @RequestParam("title") String title);

}
