package cn.bravolinks.erp.crm.server.feign.impl;

import cn.bravolinks.erp.crm.server.feign.BOService;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 审批流服务 - BO Impl
 * @createDate 2018-1-3
 * @updateDate 2018-1-3
 * @author yanqin
 * @version 1.0
 */
@Component
public class BOImpl implements BOService {
	@Override
	public byte[] downloadFileByField(int boId, String fieldUUID, String fileName) {
		return new byte[0];
	}

	@Override
	public String upFileByFiled(int boId, byte[] bytes, String fieldUUID, String fileName) {
		return null;
	}

	@Override
	public String removeFileByName(int boId, String fieldUUID, String fileName) {
		return null;
	}

	@Override
	public Map<String, Object> getBOData(String boTableName, int processInstanceId) {
		return null;
	}
}
