package cn.bravolinks.erp.crm.server.feign.impl;

import cn.bravolinks.erp.crm.server.feign.TaskService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 审批流服务 - Task Impl
 * @createDate 2018-1-3
 * @updateDate 2018-1-3
 * @author yanqin
 * @version 1.0
 */
@Component
public class TaskImpl implements TaskService {
	@Override
	public List<Map<String, Object>> selectOwnerByTarget(String type, String userid) {
		return null;
	}
}
