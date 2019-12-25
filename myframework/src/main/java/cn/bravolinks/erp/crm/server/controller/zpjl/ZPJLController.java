package cn.bravolinks.erp.crm.server.controller.zpjl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.bravolinks.erp.crm.server.feign.CustomService;
import cn.bravolinks.erp.crm.server.feign.RestWorkFlowService;
import cn.bravolinks.erp.crm.server.feign.WorkFlowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.bravolinks.erp.crm.server.service.zpjl.ZpjlService;
import cn.bravolinks.erp.crm.server.util.Mail;
import cn.bravolinks.erp.crm.server.util.PropertiesUtils;

/**
 * 指派单位客户经理
 * @author zsh
 * 2017-8-9
 */
@Controller
@RequestMapping("/zpjl")
public class ZPJLController {
	private final static Logger logger = LoggerFactory.getLogger(ZPJLController.class);
	@Autowired
	private ZpjlService zpjlService;
	@Autowired
	private CustomService customService;
	@Autowired
	private RestWorkFlowService restWorkFlowService;
	@Autowired
	private WorkFlowService workFlowService;

	private static String putMessage = "";
	//办理或作废
	@RequestMapping(value = "/banliordahui")
	@ResponseBody
	public Map<String, String> banliordahui(Integer bindid, Integer taskid,String userid,String opinion,String isagree) throws Exception {
		String shenpiren = "";
		Map<String,Object> handleTaskResult = new HashMap<String,Object>();
		Map<String, String> resultMap = new HashMap<String, String>();
		Boolean flag = true;
		Integer tasktype = customService.getTaskIsHandle(taskid);
		if(tasktype != 1) {
			 resultMap.put("result", "任务已办理或不存在,请重新打开任务");
        	 resultMap.put("type", "success");
        	 return resultMap;
		}
		if (isagree.equals("办理")) {
			try {
	            //执行保存前事件
				flag = CustomerDesignateSaveAfterRtclass(bindid);
				if(flag) {
					//指派单位客户经理保存前校验
					flag = CustomerDesignateBeforeAfterRtclass(bindid);
					if(flag) {
						//获取下个节点号
			            Integer nextStepNo = Integer.valueOf(restWorkFlowService.GetNextStepNo(userid,bindid,taskid));
						handleTaskResult = restWorkFlowService.ExcuteTask(bindid,userid,taskid,"指派单位客户经理流程",shenpiren,"同意",opinion);
						if (nextStepNo==-1) {
			             	String mes = (String)handleTaskResult.get("message");
			             	if(mes.equals("流程办理结束")){
			             		mes = "任务流转已完毕";
			             	}
			             	putMessage = mes;
			             	flag = CustomerDesignateCustomerDirectorBizRtclass(bindid,taskid,userid,isagree);
			             	if(!flag) {
			             		 resultMap.put("result", "任务流转已完毕,"+putMessage);
				            	 resultMap.put("type", "success");
			             	}else{
			             		 resultMap.put("result", putMessage);
				            	 resultMap.put("type", "success");
			             	}
			               
			            	return resultMap;
			            }else {
			              //办理至下一节点，返回消息
	                        String message = (String)handleTaskResult.get("message");
	                        if("没有找到下个节点审批人！".equals(message)) {
	                        	resultMap.put("type", "error");
	                        }else{
	                        	resultMap.put("type", "success");
	                        }
	                        resultMap.put("result", message);
			            }
					}else {
						resultMap.put("result", putMessage);
						resultMap.put("type", "error");
		            }
				}else{
					resultMap.put("result", putMessage);
					resultMap.put("type", "error");
				}
				
			} catch (Exception e) {
				logger.error("",e);
				resultMap.put("result", "当前节点办理时出现问题，请联系管理员");
				resultMap.put("type", "error");
			}
		}else if (isagree.equals("作废")) {//作废
			flag = workFlowService.removeProcessInstance(bindid);
			if(flag) {
				//清数据
				zpjlService.delData(bindid);
				resultMap.put("result", "作废成功");
	            resultMap.put("type", "success");
			}else{
				resultMap.put("result", "作废失败，请联系管理员");
	            resultMap.put("type", "error");
			}
			
		}else{
			resultMap.put("result", "操作失败，请联系管理员");
            resultMap.put("type", "error");
		}
    	return resultMap;
	}
	/*
	 * 客户列表
	 */
	@RequestMapping(value = "/getAllCust")
	@ResponseBody
	public String getAllCust(String userid,String khjlmc) {
		try {
			List<Map<String,Object>> record = zpjlService.getAllCust(userid,khjlmc);
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(record);
			return json;
		} catch (JsonProcessingException e) {
			logger.error("",e);
			return "访问数据有误，请联系系统管理员";
		}
	}
	
	@RequestMapping(value = "/getBoCrmClientbusPByBindid")
	@ResponseBody
	public String getBoCrmClientbusPByBindid(Integer bindid) {
		try {
			Map<String,Object> record = zpjlService.getBoCrmClientbusPByBindid(bindid);
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(record);
			return json;
		} catch (JsonProcessingException e) {
			logger.error("",e);
			return "访问数据有误，请联系系统管理员";
		}
	}
	/*
	 * 指派经理列表
	 */
	@RequestMapping(value = "/getZpjlList")
	@ResponseBody
	public String getZpjlList(Integer bindid) {
		try {
			List<Map<String,Object>> record = zpjlService.getZpjlList(bindid);
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(record);
			return json;
		} catch (JsonProcessingException e) {
			logger.error("",e);
			return "访问数据有误，请联系系统管理员";
		}
	}
	/*
	 * 判断是否有在途流程
	 */
	@RequestMapping(value = "/pdztlc")
	@ResponseBody
	public String pdztlc(String khbh,String userid) {
		try {
			String flag = zpjlService.pdztlc(khbh,userid);
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(flag);
			return json;
		} catch (JsonProcessingException e) {
			logger.error("",e);
			return "访问数据有误，请联系系统管理员";
		}
	}
	/*
	 * 新增
	 */
	@RequestMapping(value = "/addDate")
	@ResponseBody
	public String addDate(Integer bindid,String userid,String khbmbh,String khbmmc,
						  String khjlxm,String khjlzh,String bmid,
						  String bmmc,Integer taskid) {
		try {
			Integer tasktype = customService.getTaskIsHandle(taskid);
			if(tasktype != 1) {
				return "false";
			}
			//获取当前节点信息
			Map<String,Object> currentStep = zpjlService.getCurrentStepTaskByTaskid(taskid);
			/*//获取当前用户信息
			Map<String,Object> userMap = zpjlService.getUserByUserid(userid);*/
			currentStep.put("userid", userid);
			String message = zpjlService.addDate(currentStep,bindid,khbmbh,khbmmc,khjlxm,khjlzh,bmid,bmmc);
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(message);
			return json;
		} catch (Exception e) {
			logger.error("",e);
			return "访问数据有误，请联系系统管理员";
		}
	}
	/*
	 * 更新
	 */
	@RequestMapping(value = "/updDate")
	@ResponseBody
	public String updDate(Integer bindid,String userid,Integer id,String khbmbh,String khbmmc,String khjlxm,String khjlzh,String bmid,String bmmc,String taskid) {
		try {
			Integer tasktype = customService.getTaskIsHandle(Integer.valueOf(taskid));
			if(tasktype != 1) {
				return "false";
			}
			String message = zpjlService.updDate(bindid,id,khbmbh,khbmmc,khjlxm,khjlzh,bmid,bmmc,userid);
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(message);
			return json;
		} catch (JsonProcessingException e) {
			logger.error("",e);
			return "访问数据有误，请联系系统管理员";
		}
	}
	/*
	 * 通过userid 获取username 
	 */
	@RequestMapping(value = "/getUserName")
	@ResponseBody
	public String getUserName(String userid) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			String userName = zpjlService.getUserName(userid);
			String json = objectMapper.writeValueAsString(userName);
			return json;
		} catch (JsonProcessingException e) {
			logger.error("",e);
			return "访问数据有误，请联系系统管理员";
		}
	}
	/*
	 * 通过khbh获取bindid
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/getBindid")
	@ResponseBody
	public String getBindid(String khbh,String userid) {
		try {
			Map<String,Object> resMap = zpjlService.getBindid(khbh);
			//创建流程
			String uuid = PropertiesUtils.getPropertyByCommon("CYTSMICE_ZPJL_WorkFlowUUID");
			String name = String.valueOf(resMap.get("KHMC"));
			String title = "单位客户经理指派";
			//通过userid 获取username 
			String userName = zpjlService.getUserName(userid);
			String taskTitle = userName+
					new SimpleDateFormat("yyyy-MM-dd").format(new Date())+
					"指派"+name+"的客户经理";
			String operation = "单位客户经理指派";
			//返回bindid 和taskid
			Map<String,Object> workFlowMap = restWorkFlowService.StartWorkFlow(uuid,userid,taskTitle,taskTitle,userid);
			Integer processId = (Integer) workFlowMap.get("processId");
			Integer taskid = (Integer)((List<Map<String,Object>>)workFlowMap.get("tasks")).get(0).get("taskID");
			if ("单位客户经理指派".equals(operation)) {
				//获取当前流程信息
				Map<String,Object> currentStep = zpjlService.getCurrentStepTaskByTaskid(taskid);
				Integer bindid = Integer.parseInt(String.valueOf(resMap.get("BINDID")));
				// 获取客户信息主表数据
				List<Map<String,Object>> vc = zpjlService.CustomerDesignateDate(bindid);
				// 获取部门表数据(Vector)
				List<Map<String,Object>> vc1 = zpjlService.CustomerDesignateDate2(bindid,userid);
				try {
					String ykhjlid = "";
					for (Integer i = 0; i < vc1.size(); i++) {
						ykhjlid+=((Map<String,Object>)vc1.get(i)).get("DAID")+" ";
					}
					//插入单位客户信息主表(BO_CRM_CLIENTBUS_P) 并返回主键
					Integer CrmClientbusPID = zpjlService.insertCrmClientbusP(vc, processId, userid,currentStep);
					//插入数据到BO_CRM_CLIENTBUS_SERDPT
					Integer insertCrmClientbusSerdpt = zpjlService.insertCrmClientbusSerdpt(vc1, processId, userid,currentStep);
					Integer YKHJLID = zpjlService.updClientbusPYKHJLID(ykhjlid,CrmClientbusPID);
				} catch (Exception e) {
					logger.error("",e);
				}

			}
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(workFlowMap);
			return json;
		} catch (JsonProcessingException e) {
			logger.error("",e);
			return "访问数据有误，请联系系统管理员";
		}
	}
	/*
	 * 删除指派经理列表
	 */
	@RequestMapping(value = "/delzpjl")
	@ResponseBody
	public String delzpjl(Integer bindid,Integer id,Integer taskid) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Integer tasktype = customService.getTaskIsHandle(taskid);
			if(tasktype != 1) {
				String json = objectMapper.writeValueAsString("任务已办理或不存在,请重新打开任务");
				return json;
			}else{
				
			}			
			//删除之前校验
			boolean flag = CustomerDesignateCantonalLeaderBeforRemove(id,bindid);
			if(flag) {
				Integer count = zpjlService.delzpjl(id);
				if(count <=0) {
					putMessage = "删除失败,请刷新后重试";
				}
				putMessage = "删除成功";
			}
			
			String json = objectMapper.writeValueAsString(putMessage);
			return json;
		} catch (JsonProcessingException e) {
			logger.error("",e);
			return "访问数据有误，请联系系统管理员";
		}
	}
	/*
	 * 单位客户信息-服务部门 查询byID
	 */
	@RequestMapping(value = "/getSerDptById")
	@ResponseBody
	public String getSerDptById(Integer id) throws Exception {
			Map<String,Object> map = zpjlService.getSerDptById(id);
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(map);
			return json;
	}
	/*
	 * 单位客户信息-服务部门 查询byID
	 */
	@RequestMapping(value = "/getkhbmList")
	@ResponseBody
	public String getkhbmList(String khbh) throws Exception {
		List<Map<String,Object>> list = zpjlService.getkhbmList(khbh);
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(list);
		return json;
	}
	/*
	 * 单位客户信息-服务部门 查询byID
	 */
	@RequestMapping(value = "/getKhjlAndBM")
	@ResponseBody
	public String getKhjlAndBM(String khbh,String userid,String val) throws Exception {
		List<Map<String,Object>> list = zpjlService.getKhjlAndBM(khbh,userid,val);
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writeValueAsString(list);
		return json;
	}
	
	
	
	
	//更新公司信息
	private boolean CustomerDesignateSaveAfterRtclass(Integer bindid) {
		String tablename = "BO_CRM_CLIENTBUS_P";// 主表表名
		if(!"BO_CRM_CLIENTBUS_P".equals(tablename)){
			return true;
		}
		try {
			//从单位客户信息-服务部门 查询部门ID
			List<Map<String,Object>> vc = zpjlService.getBMIDForSerDpt(bindid);
			for (Integer i = 0; i < vc.size(); i++) {
				Map<String,Object> ht = (Map<String,Object>) vc.get(i);
				String bmid = ht.get("BMID") == null ? "" :String.valueOf(ht.get("BMID")); // 获取客户经理部门id
				Map<String,Object> ht1 = new HashMap<String,Object>();
				// 通过部门id获取所在公司的信息(ID、代码、名称)
				ht1 = zpjlService.getGSXXByBmid(bmid);
				ht1.put("bindid", bindid);
				ht1.put("bmid", bmid);
				Integer res = zpjlService.updSerdpt(ht1);
				if (res <= 0) {
					putMessage = "客户经理公司信息更新失败！";
				}
			}
			return true;
		} catch (Exception e) {
			logger.error("",e);
		}
		return false;
	}
	/*子表删除前事件
	 * boId 删除数据的ID
	 */
	private boolean CustomerDesignateCantonalLeaderBeforRemove(Integer boId,Integer bindid) {
		boolean flag = false;
		try {
			Map<String,Object> mapRes = zpjlService.getKHBMBHById(boId);
		    String sckhbmbh = String.valueOf(mapRes.get("KHBMBH"));
		    Integer scdaid = 0;
		    if(mapRes.get("DAID") != "" && mapRes.get("DAID")!=null) {
		    	scdaid = Integer.valueOf(String.valueOf(mapRes.get("DAID")));
		    }
			List<Map<String,Object>> vc = zpjlService.getAllSerdptByBindid(bindid);
			String delbmbh = "";
			Integer count = 0;
			List<String> list = new ArrayList<>();
			for (Integer i = 0; i < vc.size(); i++) {
				Map<String,Object> ht = (Map<String,Object>)vc.get(i);
				Integer id = Integer.parseInt(ht.get("ID")==null?"0":String.valueOf(ht.get("ID")));
				String khbmbh = String.valueOf(ht.get("KHBMBH")==null?"":String.valueOf(ht.get("KHBMBH")));
				if(id.equals(boId)) {
					delbmbh = khbmbh;
				}
				list.add(khbmbh);
			}
			if(list != null && list.size()>0) {
				for(int i=0;i<list.size();i++) {
					if(delbmbh.equals(list.get(i))) {
						count++;
					}
				}
			}
			if(count > 1) {
				flag = true;
				return flag;
			}
		} catch (Exception e) {
 			logger.error("",e);
		}
		putMessage = "每个客户部门必须存在一条客户经理数据！如需重新指派客户经理可编辑原数据进行指派";
		return flag;
	}
	//指派单位客户经理保存前校验
	private boolean CustomerDesignateBeforeAfterRtclass(Integer bindid) {
		List<Map<String,Object>> vc = zpjlService.getAllSerdptByBindid(bindid);
		if(vc != null){
			for (Integer i = 0; i < vc.size(); i++) {
				Map<String,Object> ht = (Map<String,Object>)vc.get(i);
				String khbmbh = String.valueOf(ht.get("KHBMBH")==null?"":String.valueOf(ht.get("KHBMBH")));//客户部门编号
				String khjlzh = String.valueOf(ht.get("KHJLZH")==null?"":String.valueOf(ht.get("KHJLZH")));//客户经理账号
				String khjlxm = String.valueOf(ht.get("KHJLXM")==null?"":String.valueOf(ht.get("KHJLXM")));//客户经理姓名
				String bmid = String.valueOf(ht.get("BMID")==null?"":String.valueOf(ht.get("BMID")));//服务部门ID
				if(khjlzh.contains("<")){
					khjlzh = khjlzh.substring(0, khjlzh.indexOf("<"));
				}
				String khjlzh1 = khjlzh+"<"+khjlxm+">";
				//lipengliang update  start 2017-1-11 由于存储表中有两种情况，客户经理账号‘shaof’和‘shaof<邵非>’所以多加了判断
				Integer count = zpjlService.getSerdptCount(khbmbh,bmid,khjlzh,khjlzh1,bindid);
				//lipengliang update  end 2017-1-11 由于存储表中有两种情况，客户经理账号‘shaof’和‘shaof<邵非>’所以多加了判断
				if(count >= 2){
					putMessage = "指派的客户经理: ‘"+khjlxm+"’ 重复了，请核对后办理!";
					return false;
				}
			}
		}	
		return true;
	}
	//新增日志记录，发送邮件给大客户总监  将数据更新到档案中  并且增加一条维护日志
	private boolean CustomerDesignateCustomerDirectorBizRtclass(Integer bindid,Integer taskid,String userid,String isagree ) {
		boolean flag = true;
		//单位客户信息主表(BO_CRM_CLIENTBUS_P)
		HashMap<String, Object> ht1 = zpjlService.getBoCrmClientbusPByBindid(bindid);
		// 日志记录信息
		String whlxdm = "107";   //维护类型 
		String khmc = ht1.get("KHMC")==null?"":String.valueOf(ht1.get("KHMC"));      //客户名称
		String khbh = ht1.get("KHBH")==null?"":String.valueOf(ht1.get("KHBH"));      //客户编号
		String updatedate = ht1.get("UPDATEDATE")==null?"":String.valueOf(ht1.get("UPDATEDATE"));
		String ybindid = ht1.get("DAID")==null?"":String.valueOf(ht1.get("DAID"));   //原BINDID
		String ykhjlid = ht1.get("YKHJLID")==null?"":String.valueOf(ht1.get("YKHJLID"));      //原客户经理ID
		//获取当前节点信息
		Map<String,Object> currentStep = zpjlService.getCurrentStepTaskByTaskid(taskid);
		//获取当前用户信息
		Map<String,Object> userMap = zpjlService.getUserByUserid(userid);
		try {
			if( isagree.equals("办理")){
			//if(WorkflowTaskInstanceAPI.getInstance().checkCurrentAuditMenu(bindid, taskid, "指派")){
				List<Map<String,Object>> vc = zpjlService.getoCrmClientbusSerdptbyBindid(bindid);
				StringBuilder sb = new StringBuilder();//存储变更客户经理信息
				sb.append("");
				Integer res = 0;
				for (Integer i = 0; i < vc.size(); i++) {
					Map<String,Object> ht = (Map<String,Object>)vc.get(i);
					String khbmbh = ht.get("KHBMBH")==null?"":String.valueOf(ht.get("KHBMBH"));
					String khbmmc =ht.get("KHBMMC")==null?"":String.valueOf(ht.get("KHBMMC"));
					String bmid = ht.get("BMID")==null?"":String.valueOf(ht.get("BMID"));
					String bmmc = ht.get("BMMC")==null?"":String.valueOf(ht.get("BMMC"));
					String khjlzh = ht.get("KHJLZH")==null?"":String.valueOf(ht.get("KHJLZH"));
					String khjlxm = ht.get("KHJLXM")==null?"":String.valueOf(ht.get("KHJLXM"));
					String gsid = ht.get("GSID")==null?"":String.valueOf(ht.get("GSID"));
					String gsdm = ht.get("GSDM")==null?"":String.valueOf(ht.get("GSDM"));
					String gsmc = ht.get("GSMC")==null?"":String.valueOf(ht.get("GSMC"));
					String daidStr = ht.get("DAID")==null?"":String.valueOf(ht.get("DAID"));
					Integer daid = 0;
					if(!StringUtils.isEmpty(daidStr)) {
						daid = Integer.parseInt(daidStr);
					}
					//lipengliang add 2017-1-6 start
					Map<String,Object> map = zpjlService.extSql(bmid);
					if(map != null) {
						gsdm =String.valueOf(map.get("GSDM"));
						gsmc =String.valueOf(map.get("GSJC"));
						gsid =String.valueOf(map.get("GSID"));
						//lipengliang add 2017-1-6 end
						if(daid == 0){     //新增客户经理的数据
//						sb.append("\r\n"+"--（客户部门名称为："+khbmmc+"，服务部门为："+bmmc+"，新增了客户经理< "+khjlxm+">）；");
							sb.append("<DIV><SPAN style='FONT-SIZE: 12px'>"+"--客户部门名称为："+khbmmc+"，服务部门为："+bmmc+"，新增了客户经理< "+khjlxm+" >；"+"</SPAN></DIV><br />");
							ht.put("KHBH",khbh);
							ht.put("GSID", gsid);
							ht.put("GSDM", gsdm);
							ht.put("GSMC", gsmc);
							//查询当前流程信息
							ht.putAll(currentStep);
							ht.putAll(userMap);
							ht.put("BINDID", ybindid);
							//添加一条数据到BO_CRM_CLIENT_SERDPT
							res = zpjlService.insertClientSerdpt(ht);
							if(res <= 0){
								flag = false;
							}
							//更新单位客户档案-服务部门表的大客户总监账号
							Map<String,Object> dkhxxMap = zpjlService.getdkhxx(khbh);
							String DKHZJXM = String.valueOf(dkhxxMap.get("DKHZJXM"));//大客户总监姓名
							String DKHZJZH = String.valueOf(dkhxxMap.get("DKHZJZH"));//大客户总监账号
							String SSGSDM = String.valueOf(dkhxxMap.get("SSGSDM"));//大客户总监所属公司代码
							String SSGSMC = String.valueOf(dkhxxMap.get("SSGSMC"));//大客户总监所属公司名称
							Integer result = zpjlService.updDkhzh(DKHZJXM,DKHZJZH,SSGSDM,SSGSMC,khbh);
							if(result <= 0){
								flag = false;
								putMessage = "大客户总监账号更新失败!";
							}
							
						}else {			//修改客户经理的数据
							/**
							 * lipengliang ADD start 2017-1-6
							 */
							Map<String,Object> reMap = zpjlService.ykhjlzhSql(khbmbh,daid);
							if(reMap != null){
								String okhjlxm = String.valueOf(reMap.get("KHJLXM"));
								String okhjlzh = String.valueOf(reMap.get("KHJLZH"));
								String okhbmmc = String.valueOf(reMap.get("KHBMMC"));
								if(!khjlzh.equals(okhjlzh)){//客户经理有变化有变化
									sb.append("<DIV><SPAN style='FONT-SIZE: 12px'>"+"--客户部门名称为："+okhbmmc+"，服务部门为："+bmmc+"，的客户经理由< "+okhjlxm+" >变更为< "+khjlxm+" >；"+"</SPAN></DIV><br />");
								}
							}
							/**
							 * lipengliang ADD end 2017-1-6
							 */
							res = zpjlService.updClientSerdpt(khjlzh,khjlxm,bmid,bmmc,gsdm,gsmc,gsid,khbmbh,daid);
							if(res <= 0){
								flag = false;
							}
							ykhjlid = ykhjlid.replace(String.valueOf(daid), "");   //将主表原客户经理ID中不需要删除的ID替换掉
						}
					}
				}
				//删除客户经理的数据 
				String []scid = ykhjlid.split(" ");
				for (Integer i = 0; i < scid.length; i++) {
					if(!"".equals(scid[i]) && scid[i] != null){
						//通过ykhjlid 删除BO_CRM_CLIENT_SERDPT
						//删除之前先拼接邮件信息
						Map<String,Object> map = zpjlService.getkhxxForClientSerdpt(scid[i]);
						if(map != null){
							sb.append("<DIV><SPAN style='FONT-SIZE: 12px'>"+"--客户部门名称为："+String.valueOf(map.get("KHBMMC"))+"，服务部门为："+String.valueOf(map.get("BMMC"))+"，客户经理< "+String.valueOf(map.get("KHJLXM"))+" >被删除；"+"</SPAN></DIV><br />");
						}
						res = zpjlService.removeClientSerdpt(Integer.parseInt(scid[i]));
						/*if(Integer.parseInt(scid[i]) > 0){
							//通过ykhjlid 删除BO_CRM_CLIENT_SERDPT
							res = zpjlService.removeClientSerdpt(Integer.parseInt(scid[i]));
						}*/
					}
				}
				//数据操作失败
				if(flag == false){
					putMessage = "客户经理信息更新失败！";
				}
				//  启动档案记录的视图,在单位维护日志中插入一条新的维护日志
				userMap.putAll(currentStep);
				Integer logCount = zpjlService.insertLog(userMap,bindid, khmc, khbh, whlxdm);
				if(logCount <=0){
					flag = false;
					putMessage = "新增日志信息出错!";
				}
				/**
				 * lipengliang ADD start 2017-1-6
				 * 发送邮件和通知给大客户总监
				 */
				//获取大客户总监的账号和邮箱
				Map<String,Object> mapdkhzj = zpjlService.getDkhzjxx(khbh);
				if(mapdkhzj == null) {
					flag = false;
					putMessage = "新增日志信息和发送邮件出错!";
				}
				String dkhzjzh = String.valueOf(mapdkhzj.get("DKHZJZH"));
				String dkhzjxm = String.valueOf(mapdkhzj.get("DKHZJXM"));
				//没出现异常,并且客户配置了大客户总监，发送邮件和通知给大客户总监，并更新单位客户档案-服务部门表的大客户总监账号
				if(dkhzjzh !=null && !"".equals(dkhzjzh)&&flag == true && logCount>0){
					if(dkhzjzh.contains("<")){
						dkhzjzh = dkhzjzh.substring(0, dkhzjzh.indexOf("<"));
					}
					//获取大客户总监邮箱email
					String email = zpjlService.getdkhzjEmial(dkhzjzh);
					//邮件标题
					String flowTitle = "【中青博联】平台任务提醒";
					//办理人
					String blr = String.valueOf(userMap.get("USERNAME"));
					String blrid = userid;
					//发送邮件内容：
					String message = "<DIV><SPAN style='FONT-SIZE: 12px'>"+"部门总监< "+blr+" >对单位客户< "+khmc+" >的客户经理做了如下变更："+"</SPAN></DIV><br />";
					message = message + sb.toString();
					String title = 
						"<DIV><SPAN style='FONT-SIZE: 16px'>"+dkhzjxm+",您好:</SPAN></DIV><br />"
						+"<DIV>"
						+"	<SPAN  style='FONT-SIZE:12PX;'>&nbsp;&nbsp;您管理的单位客户< "+khmc+" >的客户经理信息发生如下变化：</SPAN>"
						+"</DIV><br />"
						+"<DIV><SPAN style='FONT-SIZE: 12px'>"+message+"</SPAN></DIV><br />"
						+"<DIV><SPAN style='FONT-SIZE: 12px'>请知晓，此邮件不接受回复。</SPAN></DIV><br /><br />"
						+"<DIV style='FONT-SIZE: 16px'><DIV>中青博联整合营销顾问股份有限公司</DIV><DIV>"+updatedate+"(北京时间)</DIV></DIV>";
					Mail mail=new Mail("smtp.cytsonline.com");
					Map<String,String> mailMap = zpjlService.getMailXX();
					//Mail.send("smtp.cytsonline.com", "lipengliang@bravolinks.cn", "sunshuoyang@bravolinks.cn", "主题", "内容：ilovey", "lipengliang", "Qwer@1234");
					Mail.send("smtp.cytsonline.com", mailMap.get("BZ"), email, flowTitle, title, mailMap.get("YWMC"), mailMap.get("SYFW"));
				}
				/**
				 * lipengliang ADD end 2017-1-6
				 */
			}
		} catch (Exception e) {
			logger.error("",e);
			flag = false;
		}
		return flag;
	}
	 //获取部门列表数据
	@RequestMapping(value = "/getStatusOfTask")
	@ResponseBody
	public String getStatusOfTask(String bindid, String taskid) {
		ObjectMapper objectMapper = new ObjectMapper();
		//待办任务状态
    	String dbStatus = zpjlService.getDbStatusOfTask(bindid,taskid);
    	//已办任务状态
    	String ybStatus = zpjlService.getYbStatusOfTask(bindid,taskid);
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("dbStatus", dbStatus);
		m.put("ybStatus", ybStatus);
		String res = "";
		try {
			res = objectMapper.writeValueAsString(m);
		} catch (JsonProcessingException e) {
			logger.error("",e);
		}
		return res;
	}
	
}
