package cn.bravolinks.erp.crm.server.controller;

import cn.bravolinks.erp.crm.server.feign.CustomService;
import cn.bravolinks.erp.crm.server.feign.GetShenpirenService;
import cn.bravolinks.erp.crm.server.feign.RestWorkFlowService;
import cn.bravolinks.erp.crm.server.model.CustomerContractInfo;
import cn.bravolinks.erp.crm.server.service.CustomerContractInfoService;
import cn.bravolinks.erp.crm.server.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customerContractInfo")
public class CustomerContractInfoController extends BaseController{
	
	private final static Logger logger = LoggerFactory.getLogger(CustomerContractInfoController.class);

	@Autowired
	private CustomerContractInfoService customerContractInfoService;
	@Autowired
	private RestWorkFlowService restWorkFlowService;
	@Autowired
	private CustomService customService;
	@Autowired
	private GetShenpirenService getShenpirenService;

	/**
	 * 查询条数，根据bindid
	 *
	 * @param bindid
	 * @return
	 */
	@RequestMapping("getCountByBindid")
	public Integer getCountByBindid(Integer bindid) {
		return customerContractInfoService.getCountByBindid(bindid);
	}

	/**
	 * 关闭流程
	 * @param loginname
	 * @param bindid
	 * @return
	 */
	@RequestMapping("zuofei")
	public String zuofei(String loginname,Integer bindid){
		customerContractInfoService.deleteDataByBindid(bindid);
		return restWorkFlowService.closeWorkFlowAndTask(loginname,bindid);
	}

	/**
	 * 查询该客户有没有其他在途的客户档案扩展合同信息流程
	 *
	 * @param khmc
	 * @return
	 */
	@RequestMapping("getCountWorkflowBykhmc")
	public Integer getCountWorkflowBykhmc(String khmc) {
		return customerContractInfoService.getCountWorkflowBykhmc(khmc);
	}

	/**
	 * 查询重复的条数
	 *
	 * @param khmc
	 * @param bindid
	 * @return
	 */
	@RequestMapping("getChongfuCount")
	public Integer getChongfuCount(String khmc, Integer bindid) {
		return customerContractInfoService.getChongfuCount(khmc,bindid);
	}

	/**
	 * 删除，根据id
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("delCustomerContractInfoById")
	public boolean delCustomerContractInfoById(Integer id) {
		Integer re= customerContractInfoService.delCustomerContractInfoById(id);
		return re>0?true:false;
	}

	/**
	 * 获取下拉列表数据
	 * @return
	 */
	@RequestMapping("getOptions")
	public Map<String,Object> getOptions(){
		return customerContractInfoService.getOptions();
	}

	/**
	 * 更新客户合同信息表
	 *
	 * @param c
	 * @return
	 */
	@RequestMapping(value = "updHt",method = RequestMethod.POST)
	public boolean updateByPrimaryKey(@RequestBody CustomerContractInfo c) {
		c.setUpdateDate(new Date());
		Integer i = customerContractInfoService.updateByPrimaryKey(c);
		return i>0?true:false;
	}

	/**
	 * 查询客户合同信息表
	 * @param khmc
	 * @return
	 */
	@RequestMapping("getCustomerContractInfo")
	public Map<String,Object> getCustomerContractInfo(String khmc,Integer min,Integer max,Integer bindid) {
		List<CustomerContractInfo> data = customerContractInfoService.getCustomerContractInfo(khmc,min,max,bindid);
		Integer count = customerContractInfoService.getCustomerContractInfoCount(khmc,bindid);
		Map<String,Object> map = new HashMap<>();
		map.put("total",count);
		map.put("rows",data);
		return map;
	}

	/**
	 * 添加合同
	 * @return
	 */
	@RequestMapping(value = "addHt",method = RequestMethod.POST)
	public boolean addHt(@RequestBody String str){
		logger.info("=========="+str);
		CustomerContractInfo c = JsonUtils.jsonToObject(str,CustomerContractInfo.class);
		logger.info("=========="+c.getKhbh());
		c.setIsend(0);
		Date date = new Date();
		c.setCreateDate(date);
		c.setUpdateDate(date);
		c.setIsDelete(1);
		Integer re = customerContractInfoService.insertCustomerContractInfo(c);
		return re>0?true:false;
	}

	/**
	 * 查询供应商
	 *
	 * @param min
	 * @param max
	 * @return
	 */
	@RequestMapping("getGYS")
	public Map<String, Object> getGYS(Integer min, Integer max,String serach, String qygs) {
		//data
		List<Map<String, Object>> data = customerContractInfoService.getGYS(min,max,serach,qygs);
		//count
		Integer count = customerContractInfoService.getGYSCOUNT(serach,qygs);
		Map<String,Object> map = new HashMap<>();
		map.put("total",count);
		map.put("rows",data);
		return map;
	}

	/**
	 * 查询客户的合同信息
	 *
	 * @param khmc
	 * @return
	 */
	@RequestMapping("getHTXX")
	public Map<String,Object> getHTXX(String khmc,Integer min,Integer max) {
		//data
		List<Map<String,Object>> data = customerContractInfoService.getHTXX(khmc,min,max);
		//count
		Integer count = customerContractInfoService.getHTXXCOUNT(khmc);
		Map<String,Object> map = new HashMap<>();
		map.put("total",count);
		map.put("rows",data);
		return map;
	}

	/**
	 * 更新客户信息
	 *
	 * @return
	 */
	@RequestMapping("updateKHINFO")
	public Map<String,Object> updateKHINFO(Integer bindid,String khmc) {
		return customerContractInfoService.updateKHINFO(bindid,khmc);
	}

	/**
	 * 根据bindid查询客户信息
	 *
	 * @param bindid
	 * @return
	 */
	@RequestMapping("getKhxxByBindid")
	public Map<String, Object> getKhxxByBindid(Integer bindid) {
		return customerContractInfoService.getKhxxByBindid(bindid);
	}

	/**
	 * 根据客户名称查询，客户信息
	 *
	 * @param khmc
	 * @return
	 */
	@RequestMapping("getKHXXByKHMC")
	public Map<String, Object> getKHXXByKHMC(String khmc) {
		return customerContractInfoService.getKHXXByKHMC(khmc);
	}
	
	/**
	 * 创建 客户档案合同信息申请流程
	 * @return
	 */
	@RequestMapping("createWorkFlow")
	public Map<String,Object> createWorkFlow(String userid,String khmc){
		return customerContractInfoService.createWorkFlow(userid,khmc);
	}

	/**
	 * 查询客户
	 *
	 * @param min
	 * @param max
	 * @return
	 */
	@RequestMapping("selectKH")
	public Map<String, Object> getKhInfo(String userid, Integer min,  Integer max,String serach) {
		List<Map<String, Object>> data = customerContractInfoService.getKhInfo(userid,min,max,serach);
		Integer count = customerContractInfoService.getKhCount(userid,serach);
		Map<String,Object> remap = new HashMap<>();
		remap.put("total",count);
		remap.put("rows",data);
		return remap;
	}

	/**
	 * 办理
	 * @return
	 */
	@RequestMapping("handleTask")
	public Map<String,Object> handleTask(Integer bindid,Integer taskid,String userid,String isagree,String opinion,Integer stepno,String khbh){
		String username = (String) customService.getWFCreateUserByBindid(bindid).get("USERNAME");
		String khmc = customerContractInfoService.getKHMC(bindid);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String d = sdf.format(date);
		String tasktitle = username+"_"+d+"_"+khmc+"_合同扩展信息变更";
		if(stepno==1){
			if(customService.getIsBJGS(userid)){
				//是北京公司
				Integer nextstepno = 3;
				return restWorkFlowService.TodoUrgePowerTask(bindid,userid,taskid,stepno,nextstepno,tasktitle,"",isagree,opinion);
			}
		}else if(stepno==4){
			String spr = getShenpirenService.getDKHZJ(khbh);
			if(spr==null || spr.equals("")){
				String re = restWorkFlowService.closeWorkFlowAndTask(userid,bindid);
				Map<String,Object> map  = new HashMap<>();
				if(re.equals("true")){
					map.put("isok",true);
					map.put("message","流程办理结束");
					customerContractInfoService.updateIsend(bindid);
				}else{
					map.put("isok",false);
					map.put("message","关闭流程失败");
				}
			}
		}else if(stepno==5){
//			获取第六节点的大客户总监
			String spr = getShenpirenService.getDKHZJ(khbh);
			return restWorkFlowService.ExcuteTask(bindid,userid,taskid,tasktitle,spr,isagree,opinion);
		}
		Map<String,Object> map = restWorkFlowService.ExcuteTask(bindid,userid,taskid,tasktitle,"",isagree,opinion);
		String message = (String) map.get("message");
		if(message.equals("流程办理结束")){
			customerContractInfoService.updateIsend(bindid);
		}
		return map;
	}

	/**
	 * 打回
	 * @return
	 */
	@RequestMapping("RepulseTask2")
	public Map<String,Object> repulseTask(Integer bindid,Integer taskid,String userid,String isagree,String opinion,Integer stepno){
		String username = customerContractInfoService.getUsername(userid);
		String khmc = customerContractInfoService.getKHMC(bindid);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String d = sdf.format(date);
		String tasktitle = username+"_"+d+"_"+khmc+"_合同扩展信息变更";

		return restWorkFlowService.RepulseTask3(bindid,userid,taskid,tasktitle,1,isagree,opinion);
	}

}
