package cn.bravolinks.erp.crm.server.feign.impl;

import cn.bravolinks.erp.crm.server.feign.GetShenpirenService;
import org.springframework.stereotype.Component;

/**
 * 审批流服务 - getshenpiren Impl
 * @createDate 2018-1-3
 * @updateDate 2018-1-3
 * @author yanqin
 * @version 1.0
 */
@Component
public class GetShenpirenImpl implements GetShenpirenService {
	@Override
	public String getDKHZJ(String khbh) {
		return null;
	}

	@Override
	public String getUseridDaoByDeptIDAndRoleid(Integer roleid, Integer deptId) {
		return null;
	}
}
