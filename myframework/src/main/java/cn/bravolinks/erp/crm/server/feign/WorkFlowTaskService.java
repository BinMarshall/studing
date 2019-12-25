package cn.bravolinks.erp.crm.server.feign;

import cn.bravolinks.erp.crm.server.feign.impl.WorkFlowImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 审批流服务 - workflowTask 接口
 * @createDate 2018-1-3
 * @updateDate 2018-1-3
 * @author yanqin
 * @version 1.0
 */
@FeignClient(value = "${wfservice}",path = "/workflowTask",fallback = WorkFlowImpl.class)
public interface WorkFlowTaskService {

	/**
	 * 创建 一个只读传阅的 任务实例，数量与 participant 有关
	 *
	 * @param ownerId               传阅操作人账户 ，支持 @ 公式
	 * @param processInstanceId     流程实例 ID
	 * @param processTaskInstanceId 任务实例 ID
	 * @param participant           传阅接收人账户，多个 空格隔开 ，支持 @ 公式
	 * @param title                 标题
	 * @return int[] 传阅 任务实例 ID 数组， 失败抛出 AWSSDKException异常
	 */
	@RequestMapping(value = "/createCCProcessTaskInstance",method = RequestMethod.GET)
	String createCCProcessTaskInstance(@RequestParam("ownerId") String ownerId, @RequestParam("processInstanceId") int processInstanceId,
									   @RequestParam("processTaskInstanceId") int processTaskInstanceId,
									   @RequestParam("participant") String participant, @RequestParam("title") String title);

	/**
	 * removeProcessTaskInstance
	 * 不做流程语法校验，强行从待办列表中删除一个 Task 任务。
	 *
	 * @param processTaskInstanceId 工作流任务实例 ID
	 * @return 无，失败抛出 AWSSDKException 异常,
	 */
	@RequestMapping(value = "/removeProcessTaskInstance",method = RequestMethod.GET)
	String removeProcessTaskInstance(@RequestParam("processTaskInstanceId") int processTaskInstanceId);

	/**
	 * 挂起任务实例，挂起状态 STATUS=4
	 * 只有任务status为1的可以被挂起
	 * @param processTaskInstanceId 工作流任务实例 ID
	 * @return 被挂起的任务数量
	 */
	@RequestMapping(value = "/suspendProcessTaskInstance/{processTaskInstanceId}",method = RequestMethod.GET)
	Integer suspendProcessTaskInstance(@PathVariable("processTaskInstanceId") int processTaskInstanceId);

	/**
	 * 恢复处于挂起状态的任务实例
	 * 只有任务status为4的可以被恢复
	 * @param processTaskInstanceId 工作流任务实例 ID
	 * @return 被恢复的任务数量
	 */
	@RequestMapping(value = "/resumeProcessTaskInstance/{processTaskInstanceId}",method = RequestMethod.GET)
	Integer resumeProcessTaskInstance(@PathVariable("processTaskInstanceId") int processTaskInstanceId);

	/**
	 * 关闭一个正在执行的任务，该操作将依据策略留下审批痕迹并将任务归档到任务日志库，同时会触发相关事件。
	 * 若该节点设计了子流程模型，支持自动启动子流程（包括InMapping 等），
	 * 通常配合 isWaitSplitSubProcess 方法判断当前任务没有异步子流程的汇聚等待规则后再使用该 API。
	 * @param userId 操作者账户
	 * @param processInstanceId 流程实例 ID
	 * @param processTaskInstanceId 任务实例 ID
	 * @return String 成功返回 true，失败抛出 AWSSDKException 异常
	 */
	@RequestMapping(value = "/closeProcessTaskInstance",method = RequestMethod.GET)
	String closeProcessTaskInstance(@RequestParam("userId") String userId, @RequestParam("processInstanceId") int processInstanceId,
									@RequestParam("processTaskInstanceId") int processTaskInstanceId);

	/**
	 * 创建流程任务实例，即给每个参与者产生一个代办任务，实例数量与 participant 有关。
	 * 当参与者为多个时，API 自动读取该节点的并/串策略，并签或串签调度多任务。
	 * @param ownerId 任务拥有者账户(创建者) ，支持@公式
	 * @param processInstanceId 流程实例 ID
	 * @param activityNo 节点序号，从 1 开始
	 * @param participantId 参与者账户，多个空格隔开，支持@公式
	 * @param title 任务标题，如果标题前缀未包含(xxx)，给出节点名
	 * @return String int[] 任务实例 ID 数组，每个参与者对应一个，失败抛出 AWSSDKException 异常
	 */
	@RequestMapping(value = "/createProcessTaskInstance",method = RequestMethod.GET)
	String createProcessTaskInstance(@RequestParam("ownerId") String ownerId, @RequestParam("processInstanceId") int processInstanceId,
									 @RequestParam("activityNo") int activityNo, @RequestParam("participantId") String participantId,
									 @RequestParam("title") String title);
}
