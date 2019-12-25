package cn.bravolinks.erp.crm.server.feign;

import cn.bravolinks.erp.crm.server.feign.impl.TppImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * tpp服务
 *
 * @author yanqin
 * @create 2018-04-27
 **/
@FeignClient(value = "${tppService}",fallback = TppImpl.class)
public interface TppService {

	/**
	 * 发送link消息
	 * @param senderUserid
	 * @param receiverUserid
	 * @param receiverType
	 * @param title
	 * @param description
	 * @param url
	 * @param msgType
	 * @return
	 */
	@RequestMapping(value = "/weixin/webservice/sendMessage",method = RequestMethod.POST)
	String sendLinkMessage(@RequestParam("senderUserid") String senderUserid, @RequestParam("receiverUserid")  String receiverUserid,
						   @RequestParam("receiverType") String receiverType, @RequestParam("title")  String title,
						   @RequestParam("description") String description, @RequestParam("url")  String url,
						   @RequestParam("msgType") String msgType);
}
