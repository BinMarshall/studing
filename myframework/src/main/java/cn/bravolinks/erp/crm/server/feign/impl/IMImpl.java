package cn.bravolinks.erp.crm.server.feign.impl;

import cn.bravolinks.erp.crm.server.feign.IMService;
import org.springframework.stereotype.Component;

/**
 * 审批流服务 - IM Impl
 * @createDate 2018-1-3
 * @updateDate 2018-1-3
 * @author yanqin
 * @version 1.0
 */
@Component
public class IMImpl implements IMService {
	@Override
	public String sendMail(String mailFrom, String mailTo, String subject, String content) {
		return null;
	}
}
