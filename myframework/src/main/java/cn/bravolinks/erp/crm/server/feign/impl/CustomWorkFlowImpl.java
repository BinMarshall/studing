package cn.bravolinks.erp.crm.server.feign.impl;

import cn.bravolinks.erp.crm.server.feign.CustomWorkFlowService;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 审批流服务 - custom workflow Impl
 * @createDate 2018-1-3
 * @updateDate 2018-1-3
 * @author yanqin
 * @version 1.0
 */
@Component
public class CustomWorkFlowImpl implements CustomWorkFlowService {
	@Override
	public Map<String, Object> receiveHandle(Integer bindid, Integer taskid) {
		return null;
	}

	@Override
	public Map<String, Object> ExcuteTask(Integer processId, String loginName, Integer taskID, String taskTitle, String shenPiRen, String isagree, String opinion, Boolean isJump) {
		return null;
	}

	@Override
	public Map<String, Object> handleAppendProcessTask(Integer processId, String loginName, Integer taskId, String isagree, String opinion, String title, String description, String url) {
		return null;
	}
}
