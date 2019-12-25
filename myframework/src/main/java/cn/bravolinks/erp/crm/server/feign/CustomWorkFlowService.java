package cn.bravolinks.erp.crm.server.feign;

import cn.bravolinks.erp.crm.server.feign.impl.CustomWorkFlowImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 审批流服务 - custom workflow 接口
 * @createDate 2018-1-3
 * @updateDate 2018-1-3
 * @author yanqin
 * @version 1.0
 */
@FeignClient(value = "${wfservice}",path = "/customWorkflow",fallback = CustomWorkFlowImpl.class)
public interface CustomWorkFlowService {

	/**
	 * 模拟 接收办理功能：根据传来的待办id，将其他待办删除
	 * @param bindid
	 * @param taskid
	 * @return
	 */
	@RequestMapping(value = "/receiveHandle",method = RequestMethod.GET)
	Map<String,Object> receiveHandle(@RequestParam("bindid") Integer bindid, @RequestParam("taskid") Integer taskid);

	/**
	 * 办理任务
	 * 如果参数中有审批人，那么就用传送来的审批人。
	 * 如果参数中没有审批人，那么就调用接口，通过平台上配置的路由规则去获取审批人
	 * 推荐使用 @{@link RestWorkFlowService} 的ExcuteTask
	 * @param processId
	 * @param loginName
	 * @param taskID
	 * @param taskTitle
	 * @param shenPiRen
	 * @param isagree
	 * @param opinion
	 * @param isJump  是否跳转 true 跳  false 不跳
	 * @return
	 */
	@RequestMapping(value = "/handleTask",method = RequestMethod.POST)
	@Deprecated
	Map<String,Object> ExcuteTask(@RequestParam("processId") Integer processId, @RequestParam("loginName") String loginName,
								  @RequestParam("taskID") Integer taskID, @RequestParam("taskTitle") String taskTitle,
								  @RequestParam("shenPiRen") String shenPiRen, @RequestParam("isagree") String isagree,
								  @RequestParam("opinion") String opinion,
								  @RequestParam("isJump") Boolean isJump);

	/**
	 * 办理加签,会发送消息的
	 * @param processId
	 * @param loginName
	 * @param taskId
	 * @param isagree
	 * @param opinion
	 * @param title
	 * @param description
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/handleAppendProcessTask",method = RequestMethod.POST)
	Map<String,Object> handleAppendProcessTask(@RequestParam("processId") Integer processId, @RequestParam("loginName") String loginName,
											   @RequestParam("taskId") Integer taskId, @RequestParam("isagree") String isagree,
											   @RequestParam("opinion") String opinion,
											   @RequestParam("title") String title,
											   @RequestParam("description") String description, @RequestParam("url") String url);
}
