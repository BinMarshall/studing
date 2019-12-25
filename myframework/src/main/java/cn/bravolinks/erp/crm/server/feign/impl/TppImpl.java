package cn.bravolinks.erp.crm.server.feign.impl;

import cn.bravolinks.erp.crm.server.feign.TppService;
import org.springframework.stereotype.Component;

/**
 * @author yanqin
 * @create 2018-04-27
 **/
@Component
public class TppImpl implements TppService {
	/**
	 * 发送link消息
	 *
	 * @param senderUserid
	 * @param receiverUserid
	 * @param receiverType
	 * @param title
	 * @param description
	 * @param url
	 * @param msgType
	 * @return
	 */
	@Override
	public String sendLinkMessage(String senderUserid, String receiverUserid, String receiverType, String title, String description, String url, String msgType) {
		return null;
	}
}
