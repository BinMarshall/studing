package cn.bravolinks.erp.crm.server.feign.impl;

import cn.bravolinks.erp.crm.server.feign.RestWorkFlowService;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

/**
 * 审批流服务 - rest workflow Impl
 * @createDate 2018-1-3
 * @updateDate 2018-1-3
 * @author yanqin
 * @version 1.0
 */
@Component
public class RestWorkFlowImpl implements RestWorkFlowService {
	@Override
	public Integer getStpeno(Integer taskid) {
		return null;
	}

	@Override
	public String getUseridByCompanyDm(String roleid, String gsdm) {
		return null;
	}

	@Override
	public Map<String, Object> getSysflowidByFlowuuid(String uuid, Integer stepno) {
		return null;
	}

	@Override
	public String closeWorkFlowAndTask(String userid, Integer processId) {
		return null;
	}

	@Override
	public String GetNextStepNo(String LoginName, Integer processId, Integer taskid) {
		return null;
	}

	@Override
	public String isTaskShenpiren(Integer taskid, String userid) {
		return null;
	}

	@Override
	public Map<String, Object> getTaskSpr(Integer taskid) {
		return null;
	}

	@Override
	public List<Map<String, Object>> getMessageOpinion(Integer workflowid) {
		return null;
	}

	@Override
	public List<Map<String, Object>> getMessageOpinionAndFilename(Integer workflowid) {
		return null;
	}

	@Override
	public String getUUID(String tablename, String fieldName) {
		return null;
	}

	@Override
	public Integer saveMessageOpinion(Integer workflowid, Integer taskid, String isagree, String opinion) {
		return null;
	}

	@Override
	public Map<String, Object> StartWorkFlow(String WorkFlowUUID, String LoginName, String WrokFlowTitle, String TaskTitle, String ParticipantUser) {
		return null;
	}

	@Override
	public Map<String, Object> StartWorkFlow2(String WorkFlowUUID, String LoginName, String WrokFlowTitle, String TaskTitle, String ParticipantUser) {
		return null;
	}

	@Override
	public Map<String, Object> ExcuteTask(Integer ProcessID, String LoginName, Integer TaskID, String TaskTitle, String ShenPiRen, String Isagree, String Opinion) {
		return null;
	}

	@Override
	public Map<String, Object> handleTask(MultiValueMap<String, Object> map) {
		return null;
	}

	@Override
	public Map<String, Object> RepulseTask(Integer ProcessID, String LoginName, Integer TaskID, String TaskTitle, String Isagree, String Opinion) {
		return null;
	}

	@Override
	public Map<String, Object> RepulseTask2(Integer ProcessID, String LoginName, Integer TaskID, String TaskTitle, String Isagree, String Opinion) {
		return null;
	}

	@Override
	public Map<String, Object> RepulseTask3(Integer ProcessID, String LoginName, Integer TaskID, String TaskTitle, Integer nextStepno, String Isagree, String Opinion) {
		return null;
	}

	@Override
	public Map<String, Object> AppendProcessTask(Integer ProcessID, String LoginName, String Participant, Integer TaskID, String TaskTitle, String Isagree, String Opinion) {
		return null;
	}

	@Override
	public Map<String, Object> ExceutAppendProcessTask(Integer ProcessID, String LoginName, Integer TaskID, String Isagree, String Opinion) {
		return null;
	}

	@Override
	public Map<String, Object> UndoTask(Integer TaskID, String Isagree, String Opinion) {
		return null;
	}

	@Override
	public Map<String, Object> TodoUrgePowerTask(Integer ProcessID, String LoginName, Integer TaskID, Integer StepNo, Integer NextStepNo, String TaskTitle, String Shenpiren, String Isagree, String Opinion) {
		return null;
	}

	
}
