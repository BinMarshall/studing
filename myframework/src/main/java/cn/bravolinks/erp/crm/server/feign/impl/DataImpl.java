package cn.bravolinks.erp.crm.server.feign.impl;

import cn.bravolinks.erp.crm.server.feign.DataService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 审批流服务 - data Impl
 * @createDate 2018-1-3
 * @updateDate 2018-1-3
 * @author yanqin
 * @version 1.0
 */
@Component
public class DataImpl implements DataService {
	@Override
	public List<Map<String, Object>> selectWFTaskLogByObject(Map<String, Object> wfTaskLog) {
		return null;
	}

	@Override
	public List<Map<String, Object>> selectWFTaskByObject(Map<String, Object> wfTask) {
		return null;
	}

	@Override
	public Map<String, Object> queryUserCommonInfo(String userid) {
		return null;
	}

	@Override
	public String selectUsernameByUserid(String userid) {
		return null;
	}

	@Override
	public List<Map<String, Object>> getWFTaskLogByBindid(Integer bindid) {
		return null;
	}

	@Override
	public List<Map<String, Object>> getWFTaskByBindid(Integer bindid) {
		return null;
	}

	@Override
	public Map<String, Object> getWFTaskLogById(Integer id) {
		return null;
	}

	@Override
	public Map<String, Object> getWFTaskById(Integer id) {
		return null;
	}

	@Override
	public Map<String, Object> getTaskById(Integer id) {
		return null;
	}
}
