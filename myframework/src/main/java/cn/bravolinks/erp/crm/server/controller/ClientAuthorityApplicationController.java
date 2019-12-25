//package cn.bravolinks.erp.crm.server.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import cn.bravolinks.erp.crm.server.service.BO_CRM_APPRIGHTService;
//
//@RestController
//@RequestMapping("/clientAuthorityApplication")
//public class ClientAuthorityApplicationController extends BaseController {
//
//	private final static Logger logger = LoggerFactory.getLogger(ClientAuthorityApplicationController.class);
//	
//	@Autowired
//	private BO_CRM_APPRIGHTService applicationClientAuthorityService;
//	
//	@RequestMapping("chaxun")
//	public String sysOut(Integer bindid){
//		logger.info("输出参数:"+bindid);
//		String jsonAPPRIGHT = ""; //applicationClientAuthorityService.reId(bindid);
//		System.out.println("逻辑处理");
//		return jsonAPPRIGHT;
//	}
//}
