package cn.bravolinks.erp.crm.server.feign.impl;

import cn.bravolinks.erp.crm.server.feign.PageService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 审批流服务 - Page Impl
 * @createDate 2018-1-3
 * @updateDate 2018-1-3
 * @author yanqin
 * @version 1.0
 */
@Component
public class PageImpl implements PageService {
	@Override
	public List<Map<String, Object>> selectGYSYibanTask(Integer pageNumber, Integer pageSize, String userid, Map<String,Object> filter) {
		return null;
	}

	@Override
	public List<Map<String, Object>> selectGYSDaibanTask(Integer pageNumber, Integer pageSize, String userid, Map<String,Object> filter) {
		return null;
	}

	@Override
	public Map<String, Object> selectTaskDetails(Integer taskid) {
		return null;
	}

	@Override
	public Map<String, Object> selectWeiyue(String target, Integer pageNumber, Integer pageSize, Integer isMoblie, String like) {
		return null;
	}

	@Override
	public Map<String, Object> db(Integer pageNumber, Integer pageSize, String userid, String wf_type, Integer isMoblie, String like) {
		return null;
	}

	@Override
	public Map<String, Object> yb(Integer pageNumber, Integer pageSize, String userid, String wf_type, Integer isMoblie, String like) {
		return null;
	}

	@Override
	public Map<String, Object> fc(Integer pageNumber, Integer pageSize, String userid, String wf_type) {
		return null;
	}

	@Override
	public List<Map<String, Object>> selectWfAllTask(Integer bindid) {
		return null;
	}

	@Override
	public Map<String, Object> selectWfInfo(Integer bindid) {
		return null;
	}
}
