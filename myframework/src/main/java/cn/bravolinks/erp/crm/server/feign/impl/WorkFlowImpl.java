package cn.bravolinks.erp.crm.server.feign.impl;

import cn.bravolinks.erp.crm.server.feign.WorkFlowService;

import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * 审批流服务 - workflow Impl
 * @createDate 2018-1-3
 * @updateDate 2018-1-3
 * @author yanqin
 * @version 1.0
 */
@Component
public class WorkFlowImpl implements WorkFlowService {
	@Override
	public boolean removeProcessInstance(int processInstanceId) {
		return false;
	}

	@Override
	public boolean removeProcessInstances(int[] processInstanceIds) {
		return false;
	}

	@Override
	public String getVariables(String userId, int processInstanceId, int taskInstanceId) {
		return null;
	}

	@Override 
	public Map<String, Object> createBOInstance(String uuid, String userid, String title) {
		return null;
	}
}
