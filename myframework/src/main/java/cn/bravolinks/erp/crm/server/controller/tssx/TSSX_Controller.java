package cn.bravolinks.erp.crm.server.controller.tssx;

import cn.bravolinks.erp.crm.server.model.tssx.BO_PRM_PTT_CSC_P;
import cn.bravolinks.erp.crm.server.model.tssx.BO_PRM_PTT_CSC_SPECON;
import cn.bravolinks.erp.crm.server.service.tssx.BO_PRM_PTT_CSC_PService;
import cn.bravolinks.erp.crm.server.service.tssx.BO_PRM_PTT_CSC_SPECONService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 特殊事项服务端接口
 * @author JDP
 * 2017-6-19
 */
@Controller
@RequestMapping("/tssx")
public class TSSX_Controller {
	private static final Logger logger = LoggerFactory.getLogger(TSSX_Controller.class);
	@Autowired
	private BO_PRM_PTT_CSC_SPECONService boPrmPttCscSpeconService;
	@Autowired
	private BO_PRM_PTT_CSC_PService boPrmPttCscService;
	

	/***
	 * 查询特殊事项各种信息
	 * @param bindid
	 * @param userid
	 * @return 返回json格式的字符串
	 * @author JDP
	 */
	@RequestMapping(value = "/getTssxByBindId",method=RequestMethod.GET)
	@ResponseBody
	public String getAll(String bindid,String userid) {
        try {
        	Map<String, String> map = new HashMap<String, String>();
        	map.put("bindid", bindid);
        	map.put("userid", userid);
        	//获取对象
        	List<BO_PRM_PTT_CSC_SPECON> boPrmPttCscSpecon = boPrmPttCscSpeconService.getSpecByBindid(bindid);
        	BO_PRM_PTT_CSC_P boPrmPttCscP = boPrmPttCscService.getKhfwfa(bindid);
        	Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("boPrmPttCscSpecon ", boPrmPttCscSpecon);
			map1.put("boPrmPttCscP", boPrmPttCscP);
    		//返回json格式的字符串
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(map1);
		
			return json;
		} catch (Exception e) {
			logger.error("",e);
			return "访问数据有误，请联系系统管理员";
		}
	}
	
  
	
    
}
