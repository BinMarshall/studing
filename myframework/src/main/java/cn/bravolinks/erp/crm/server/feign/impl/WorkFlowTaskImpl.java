package cn.bravolinks.erp.crm.server.feign.impl;

import cn.bravolinks.erp.crm.server.feign.WorkFlowTaskService;
import org.springframework.stereotype.Component;

/**
 * 审批流服务 - workflow task Impl
 * @createDate 2018-1-3
 * @updateDate 2018-1-3
 * @author yanqin
 * @version 1.0
 */
@Component
public class WorkFlowTaskImpl implements WorkFlowTaskService {
	@Override
	public String createCCProcessTaskInstance(String ownerId, int processInstanceId, int processTaskInstanceId, String participant, String title) {
		return null;
	}

	@Override
	public String removeProcessTaskInstance(int processTaskInstanceId) {
		return null;
	}

	@Override
	public Integer suspendProcessTaskInstance(int processTaskInstanceId) {
		return null;
	}

	@Override
	public Integer resumeProcessTaskInstance(int processTaskInstanceId) {
		return null;
	}

	@Override
	public String closeProcessTaskInstance(String userId, int processInstanceId, int processTaskInstanceId) {
		return null;
	}

	@Override
	public String createProcessTaskInstance(String ownerId, int processInstanceId, int activityNo, String participantId, String title) {
		return null;
	}
}
