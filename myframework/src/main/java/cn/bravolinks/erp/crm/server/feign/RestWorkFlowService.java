package cn.bravolinks.erp.crm.server.feign;

import cn.bravolinks.erp.crm.server.feign.impl.RestWorkFlowImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 审批流服务 - rest workflow 接口
 * @createDate 2018-1-3
 * @updateDate 2018-1-3
 * @author yanqin
 * @version 1.0
 */
//@FeignClient(value = "${wfservice}",path = "/Rest/WorkFlow",fallback = RestWorkFlowImpl.class)
@FeignClient(value = "${wfservice}",path = "/Rest/WorkFlow")
public interface RestWorkFlowService {

	/**
	 * 根据任务获取节点序号
	 *
	 * @param taskid
	 * @return
	 */
	@RequestMapping(value = "/getStpeno/{taskid}",method = RequestMethod.GET)
	Integer getStpeno(@PathVariable("taskid") Integer taskid);

	/**
	 * 根据公司代码和角色，来获取用户
	 * 目前用于 CYTSMICE_FIN
	 *
	 * @param roleid
	 * @param gsdm
	 * @return
	 */
	@RequestMapping(value = "/getUseridByCompanyDm/{roleid}/{gsdm}",method = RequestMethod.GET)
	String getUseridByCompanyDm(@PathVariable("roleid") String roleid, @PathVariable("gsdm") String gsdm);

	/**
	 * 获取流程主表的id
	 *
	 * @param uuid
	 * @param stepno
	 * @return
	 */
	@RequestMapping(value = "/getSysflowidByFlowuuid/{uuid}/{stepno}",method = RequestMethod.GET)
	Map<String, Object> getSysflowidByFlowuuid(@PathVariable("uuid") String uuid, @PathVariable("stepno") Integer stepno);

	/**
	 * 关闭任务和流程，不会进行留言
	 * 该接口，不会判断用户是否和流程有关系。仅仅只需要是合法账号
	 * @param userid
	 * @param processId
	 * @return
	 */
	@RequestMapping(value = "/closeWorkFlowAndTask/{userid}/{processId}",method = RequestMethod.GET)
	String closeWorkFlowAndTask(@PathVariable("userid") String userid, @PathVariable("processId") Integer processId);

	/**
	 * 获取下个节点序号
	 * 该接口，不会判断用户是否和流程有关系。仅仅只需要是合法账号
	 * @param LoginName
	 * @param processId
	 * @param taskid
	 * @return
	 */
	@RequestMapping(value = "/GetNextStepNo/", method = RequestMethod.POST)
	String GetNextStepNo(@RequestParam("LoginName") String LoginName, @RequestParam("processId") Integer processId, @RequestParam("taskid") Integer taskid);

	/**
	 * 是否是审批人
	 *
	 * @param taskid
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/isTaskShenpiren/", method = RequestMethod.POST)
	String isTaskShenpiren(@RequestParam("taskid") Integer taskid, @RequestParam("userid") String userid);

	/**
	 * 获取任务办理人以及被委托人
	 * @return
	 */
	@RequestMapping(value = "/getTaskSpr",method = RequestMethod.GET)
	Map<String,Object> getTaskSpr(@RequestParam("taskid") Integer taskid);

	/**
	 * 返回留言
	 *
	 * @param workflowid
	 * @return
	 */
	@RequestMapping(value = "/getMessageOpinion/{workflowid}",method = RequestMethod.GET)
	List<Map<String,Object>> getMessageOpinion(@PathVariable("workflowid") Integer workflowid);

	/**
	 * 返回留言
	 *
	 * @param workflowid
	 * @return
	 */
	@RequestMapping(value = "/getMessageOpinionAndFilename/{workflowid}",method = RequestMethod.GET)
	List<Map<String,Object>> getMessageOpinionAndFilename(@PathVariable("workflowid") Integer workflowid);

	@RequestMapping(value = "/getUUID",method = RequestMethod.GET)
	String getUUID(@RequestParam("tablename") String tablename, @RequestParam("fieldName") String fieldName);

	/**
	 * 保存留言
	 *
	 * @param workflowid
	 * @param taskid
	 * @param isagree
	 * @param opinion
	 * @return
	 */
	@RequestMapping(value = "/saveMessageOpinion/", method = RequestMethod.POST)
	Integer saveMessageOpinion(@RequestParam("workflowid") Integer workflowid, @RequestParam("taskid") Integer taskid
			, @RequestParam("isagree") String isagree, @RequestParam("opinion") String opinion);

	/**
	 * 创建审批流，开启任务
	 * 该接口，创建的任务审批人，是需要客户端传递的(多个审批人，空格分割)
	 *
	 * @param WorkFlowUUID
	 * @param LoginName
	 * @param WrokFlowTitle
	 * @param TaskTitle
	 * @param ParticipantUser
	 * @return
	 */
	@RequestMapping(value = "/StartWorkFlow/", method = RequestMethod.POST)
	Map<String,Object> StartWorkFlow(@RequestParam("WorkFlowUUID") String WorkFlowUUID, @RequestParam("LoginName") String LoginName,
									 @RequestParam("WrokFlowTitle") String WrokFlowTitle, @RequestParam("TaskTitle") String TaskTitle,
									 @RequestParam("ParticipantUser") String ParticipantUser);

	/**
	 * 创建审批流，开启任务
	 * 该接口，创建的任务审批人，是根据(审批流项目自己实现的路由方案功能)去根据，流程节点一配置的路由方案和角色去查找
	 *
	 * @param WorkFlowUUID
	 * @param LoginName
	 * @param WrokFlowTitle
	 * @param TaskTitle
	 * @param ParticipantUser
	 * @return
	 */
	@RequestMapping(value = "/StartWorkFlow2/", method = RequestMethod.POST)
	Map<String,Object> StartWorkFlow2(@RequestParam("WorkFlowUUID") String WorkFlowUUID, @RequestParam("LoginName") String LoginName,
									  @RequestParam("WrokFlowTitle") String WrokFlowTitle, @RequestParam("TaskTitle") String TaskTitle,
									  @RequestParam("ParticipantUser") String ParticipantUser);

	/**
	 * 办理任务
	 * 如果参数中有审批人，那么就用传送来的审批人。
	 * 如果参数中没有审批人，那么就调用接口，通过平台上配置的路由规则去获取审批人
	 *
	 * @param ProcessID
	 * @param LoginName
	 * @param TaskID
	 * @param TaskTitle
	 * @param ShenPiRen
	 * @param Isagree
	 * @param Opinion
	 * @return
	 */
	@RequestMapping(value = "/ExcuteTask/", method = RequestMethod.POST)
	Map<String,Object> ExcuteTask(@RequestParam("ProcessID") Integer ProcessID, @RequestParam("LoginName") String LoginName, @RequestParam("TaskID") Integer TaskID,
								  @RequestParam("TaskTitle") String TaskTitle, @RequestParam("ShenPiRen") String ShenPiRen
			, @RequestParam("Isagree") String Isagree, @RequestParam("Opinion") String Opinion);

	/**
	 * 办理任务接口
	 * 允许指定下个节点审批人
	 * 允许指定下个节点
	 * 允许指定关闭当前任务后，是否直接关闭流程
	 * * @param bindid
	 * 	 * @param loginname
	 * 	 * @param taskid
	 * 	 * @param taskTitle
	 * 	 * @param shenpiren
	 * 	 * @param isagree
	 * 	 * @param opinion
	 * 	 * @param nextStepno
	 * 	 * @param isCloseProcess 0 不关闭   1关闭
	 * @return
	 */
	@RequestMapping(value = "/handleTask", method = RequestMethod.POST)
	Map<String,Object> handleTask(MultiValueMap<String,Object> map);

	/**
	 * 打回接口，通用型
	 * 通过调用aws api，获取审批人
	 *
	 * @param ProcessID
	 * @param LoginName
	 * @param TaskID
	 * @param TaskTitle
	 * @param Isagree
	 * @param Opinion
	 * @return
	 */
	@RequestMapping(value = "/RepulseTask/", method = RequestMethod.POST)
	Map<String,Object> RepulseTask(@RequestParam("ProcessID") Integer ProcessID, @RequestParam("LoginName") String LoginName,
								   @RequestParam("TaskID") Integer TaskID, @RequestParam("TaskTitle") String TaskTitle
			, @RequestParam("Isagree") String Isagree
			, @RequestParam("Opinion") String Opinion);

	/**
	 * 打回接口，该接口是定制功能接口，非通用型
	 * 根据task_log表，上个节点最后一个审批人，将打回任务，打回给他
	 *
	 * @param ProcessID
	 * @param LoginName
	 * @param TaskID
	 * @param TaskTitle
	 * @param Isagree
	 * @param Opinion
	 * @return
	 */
	@RequestMapping(value = "/RepulseTask2/", method = RequestMethod.POST)
	Map<String,Object> RepulseTask2(@RequestParam("ProcessID") Integer ProcessID, @RequestParam("LoginName") String LoginName,
									@RequestParam("TaskID") Integer TaskID, @RequestParam("TaskTitle") String TaskTitle
			, @RequestParam("Isagree") String Isagree, @RequestParam("Opinion") String Opinion);

	/**
	 * 打回接口，该接口是定制功能接口，非通用型
	 * 根据task_log表，指定节点查找审批人，将打回任务，打回给他
	 *
	 * @param ProcessID
	 * @param LoginName
	 * @param TaskID
	 * @param TaskTitle
	 * @param Isagree
	 * @param Opinion
	 * @return
	 */
	@RequestMapping(value = "/RepulseTask3/", method = RequestMethod.POST)
	Map<String,Object> RepulseTask3(@RequestParam("ProcessID") Integer ProcessID, @RequestParam("LoginName") String LoginName,
									@RequestParam("TaskID") Integer TaskID, @RequestParam("TaskTitle") String TaskTitle,
									@RequestParam("nextStepno") Integer nextStepno
			, @RequestParam("Isagree") String Isagree, @RequestParam("Opinion") String Opinion);

	/**
	 * 创建加签
	 *
	 * @param ProcessID
	 * @param LoginName
	 * @param Participant
	 * @param TaskID
	 * @param TaskTitle
	 * @param Isagree
	 * @param Opinion
	 * @return
	 */
	@RequestMapping(value = "/AppendProcessTask/", method = RequestMethod.POST)
	Map<String,Object> AppendProcessTask(@RequestParam("ProcessID") Integer ProcessID, @RequestParam("LoginName") String LoginName,
										 @RequestParam("Participant") String Participant, @RequestParam("TaskID") Integer TaskID,
										 @RequestParam("TaskTitle") String TaskTitle
			, @RequestParam("Isagree") String Isagree, @RequestParam("Opinion") String Opinion);

	/**
	 * 办理加签
	 *
	 * @param ProcessID
	 * @param LoginName
	 * @param TaskID
	 * @param Isagree
	 * @param Opinion
	 * @return
	 */
	@RequestMapping(value = "/ExceutAppendProcessTask/", method = RequestMethod.POST)
	Map<String,Object> ExceutAppendProcessTask(@RequestParam("ProcessID") Integer ProcessID, @RequestParam("LoginName") String LoginName,
											   @RequestParam("TaskID") Integer TaskID
			, @RequestParam("Isagree") String Isagree, @RequestParam("Opinion") String Opinion);

	/**
	 * 撤销任务
	 *
	 * @param TaskID
	 * @param Isagree
	 * @param Opinion
	 * @return
	 */
	@RequestMapping(value = "/UndoTask/", method = RequestMethod.POST)
	Map<String,Object> UndoTask(@RequestParam("TaskID") Integer TaskID
			, @RequestParam("Isagree") String Isagree
			, @RequestParam("Opinion") String Opinion);

	/**
	 * 特事特办
	 *
	 * @param ProcessID
	 * @param LoginName
	 * @param TaskID
	 * @param StepNo
	 * @param NextStepNo
	 * @param TaskTitle
	 * @param Isagree
	 * @param Opinion
	 * @return
	 */
	@RequestMapping(value = "/TodoUrgePowerTask/", method = RequestMethod.POST)
	Map<String,Object> TodoUrgePowerTask(@RequestParam("ProcessID") Integer ProcessID, @RequestParam("LoginName") String LoginName,
										 @RequestParam("TaskID") Integer TaskID, @RequestParam("StepNo") Integer StepNo,
										 @RequestParam("NextStepNo") Integer NextStepNo,
										 @RequestParam("TaskTitle") String TaskTitle, @RequestParam("Shenpiren") String Shenpiren
			, @RequestParam("Isagree") String Isagree, @RequestParam("Opinion") String Opinion);
	

}

