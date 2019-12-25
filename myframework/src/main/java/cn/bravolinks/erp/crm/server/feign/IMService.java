package cn.bravolinks.erp.crm.server.feign;

import cn.bravolinks.erp.crm.server.feign.impl.IMImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 审批流服务 - im 接口
 * @createDate 2018-1-3
 * @updateDate 2018-1-3
 * @author yanqin
 * @version 1.0
 */
@FeignClient(value = "${wfservice}",path = "/im",fallback = IMImpl.class)
public interface IMService {

	/**
	 * 发送内、外网邮件, 正常使用该服务方法发送外网邮件应正确配置邮件服务器（参见AWS 实施手册），
	 * 若 mailTo 包含了发往外部的互联网邮件，
	 * 作为 mailFrom 发送者应在个人配置中设置了自己的外网邮件地址。
	 * @param mailFrom 发送人，合法的 AWS 账户
	 * @param mailTo 接收人邮件地址，合法的 AWS 账户或外部邮件，多个空格隔开
	 * @param subject 邮件标题
	 * @param content 正文内容
	 * @return int 如果发送成功,返回一个大于 0 的 mailTask .ID,出错返回_SENDMAIL_ERROR(-9)
	 */
	@RequestMapping(value = "/sendMail",method = RequestMethod.GET)
	String sendMail(@RequestParam("mailFrom") String mailFrom, @RequestParam("mailTo") String mailTo,
					@RequestParam("subject") String subject, @RequestParam("content") String content);
}
