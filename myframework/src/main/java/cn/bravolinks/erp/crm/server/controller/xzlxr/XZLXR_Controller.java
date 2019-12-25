package cn.bravolinks.erp.crm.server.controller.xzlxr;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.bravolinks.erp.crm.server.feign.CustomService;
import cn.bravolinks.erp.crm.server.feign.RestWorkFlowService;
import cn.bravolinks.erp.crm.server.feign.WorkFlowService;
import cn.bravolinks.erp.crm.server.service.xzlxr.XZLXR_Service;
import cn.bravolinks.erp.crm.server.service.zpjl.ZpjlService;


/**
 * 新增单位客户联系人
 * @author ck
 *
 */
@Controller
@RequestMapping("/xzlxr")
public class XZLXR_Controller {

	private static final Logger logger = LoggerFactory.getLogger(XZLXR_Controller.class);
	
	@Autowired
	private XZLXR_Service xzlxrService;
	
	@Autowired
	private ZpjlService zpjlService;
	
	@Autowired
	private RestWorkFlowService restWorkFlowService;
	
	@Autowired
	private CustomService customService;
	
	@Autowired
	private WorkFlowService workFlowService;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	
	/**
	 * 获取客户和联系人信息
	 * @param bindid  流程id
	 * @param taskid  任务id
	 * @param userid  操作人用户
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getLxr")
	public String getAll(String bindid,String userid,String taskid){
		try {
			Map<String,Object> resultMap = new HashMap<>();
			//客户信息
			Map<String,Object> khxxMap = xzlxrService.getKhxx(bindid);
			if(khxxMap==null || khxxMap.size()==0){
				String result = "";
				return objectMapper.writeValueAsString(result);
			}
			//获取行业小类名称
			String hyxl = (String) khxxMap.get("HYXLXNZD");
			if(hyxl!=null&&!"".equals(hyxl)){
				String hyxlmc = xzlxrService.getHyxlmc(hyxl);
				khxxMap.put("HYXLMC", hyxlmc);
			}
			resultMap.put("khxx", khxxMap);
			//联系人信息
			List<Map<String,Object>> lxrList = xzlxrService.getLxr(bindid);
			if(lxrList.size() > 0){
				resultMap.put("lxrxx",lxrList);
			}
			//判断当前用户是否审批人
			String shenpiren = restWorkFlowService.isTaskShenpiren(Integer.parseInt(taskid),userid);
			resultMap.put("shenpiren", shenpiren);
			//获取当前流程节点数
			String stepNo = xzlxrService.getStepNo(bindid);
			resultMap.put("stepNo", stepNo);
			//查询是待办还是已办
			Integer isHandle = customService.getTaskIsHandle(Integer.parseInt(taskid));
			if(isHandle == 1){
				resultMap.put("isHandle", 0);
			} else if(isHandle == 2) {
				resultMap.put("isHandle", 1);
			}
			
			String json = objectMapper.writeValueAsString(resultMap);
			return json;
		} catch (Exception e) {
			logger.error("",e);
			return "访问数据有误，请联系系统管理员";
		}
	}
	
	/**
	 * 获取联系人详细信息
	 * @param bindid
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getLxrxx")
	public String getLxrxx(String bindid,String id){
		try {	
			Map<String,Object> lxrxx = xzlxrService.getLxrxx(id);
			
			Map<String,Object> map = new HashMap<>();
			map.put("bindid", bindid);
			map.put("id", id);
			//联系人人工作履历
			List<Map<String,Object>> gzll = xzlxrService.getGzll(map);
			if(gzll.size() > 0){
				lxrxx.put("gzll", gzll);
			}
			//联系人相关联系人
			List<Map<String,Object>> xglxr = xzlxrService.getXglxr(map);
			if(xglxr.size() > 0){
				lxrxx.put("xglxr", xglxr);
			}
			//联系人个人偏好
			List<Map<String,Object>> grph = xzlxrService.getGrph(map);
			if(grph.size() > 0){
				lxrxx.put("grph", grph);
			}
			//联系人历史事件
			List<Map<String,Object>> lssj = xzlxrService.getLssj(map);
			if(lssj.size() > 0){
				lxrxx.put("lssj", lssj);
			}
		
			String json = objectMapper.writeValueAsString(lxrxx);
			return json;
		} catch (Exception e) {
			logger.error("",e);
			return "访问数据有误，请联系系统管理员";
		}
	}
	
	/**
	 * 办理或者打回
	 * @param bindid  流程id
	 * @param taskid  任务id
	 * @param banli   是否同意办理
	 * @param userid  操作人用户
	 * @param opinion  备注
	 * @return
	 * @throws JsonProcessingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/banli")
	public String banli(String bindid,String taskid,String isagree,String userid,String opinion) throws JsonProcessingException{
		Map<String,Object> resultMap = new HashMap<>();
		Map<String,Object> khxxMap = xzlxrService.getKhxx(bindid);
		String TaskTitle = xzlxrService.getTaskTitle(taskid);
		Integer tasktype = customService.getTaskIsHandle(Integer.parseInt(taskid));
		if(tasktype != 1) {
			 resultMap.put("result", "任务已办理或不存在,请重新打开任务");
        	 resultMap.put("type", "success");
        	 return objectMapper.writeValueAsString(resultMap);
		}
		try {
			if(isagree.equals("同意")){
				//将子表联系人存入档案中
				List<Map<String,Object>> lxrxxList = xzlxrService.getLxrxxList(bindid);
				if(lxrxxList.size() > 0){
					for(Map<String,Object> lxr : lxrxxList){
						String BMBH = (String) lxr.get("BMBH");
						String LXRBH = getSuffixNumberByPrefixValue("BO_CRM_INDCLIENT_P", "LXRBH", BMBH, 3);
						//修改联系人编号
						Map<String,Object> lxrbhMap = new HashMap<>();
						lxrbhMap.put("LXRBH", LXRBH);
						lxrbhMap.put("BINDID", bindid);
						xzlxrService.updateLxrbh(lxrbhMap);
						lxr.put("LXRBH", LXRBH);
						//启动档案记录的视图
						Map<String, Object> resultInstance = workFlowService.createBOInstance("c85d25ada603d25f66c6c29d24ef8d18", userid, "单位联系人信息");
						String msg = (String) resultInstance.get("msg");
						if(!msg.equals("success")){
							String json = "创建档案记录视图失败";
							resultMap.put("result", json);
							resultMap.put("type", "error");
							return objectMapper.writeValueAsString(resultMap);
						}
						String bind = resultInstance.get("data").toString();
						lxr = initParam(lxr,bind,userid);
						//向档案表中插入新的数据信息
						xzlxrService.insertLxrda(lxr);
						//获取信息然后插入到档案表中
						BigDecimal lxrId = (BigDecimal) lxr.get("ID");
						Map<String,Object> map = new HashMap<>();
						map.put("bindid", bindid);
						map.put("id", lxrId);
						//联系人工作履历
						List<Map<String,Object>> gzll = xzlxrService.getGzll(map);
						if(gzll.size()>0){
							for(Map<String,Object> gzllMap : gzll){
								gzllMap = initParam(gzllMap,bind,userid);
								xzlxrService.insertGzll(gzllMap);
							}
						}
						//联系人相关联系人
						List<Map<String,Object>> xglxr = xzlxrService.getXglxr(map);
						if(xglxr.size()>0){
							for(Map<String,Object> xglxrMap : xglxr){
								xglxrMap = initParam(xglxrMap,bind,userid);
								xzlxrService.insertXglxr(xglxrMap);
							}
						}
						//联系人个人偏好
						List<Map<String,Object>> grph = xzlxrService.getGrph(map);
						if(grph.size()>0){
							for(Map<String,Object> grphMap : grph){
								grphMap = initParam(grphMap,bind,userid);
								xzlxrService.insertGrph(grphMap);
							}
						}
						//联系人历史事件
						List<Map<String,Object>> lssj = xzlxrService.getLssj(map);
						if(lssj.size()>0){
							for(Map<String,Object> lssjMap : lssj){
								lssjMap = initParam(lssjMap,bind,userid);
								xzlxrService.insertLssj(lssjMap);
							}
						}
					}
				}
				
				//获取当前节点信息
				Map<String,Object> currentStep = zpjlService.getCurrentStepTaskByTaskid(Integer.parseInt(taskid));
				//获取当前用户信息
				Map<String,Object> userMap = zpjlService.getUserByUserid(userid);
				userMap.putAll(currentStep);
				//日志记录信息
				String whlxdm = (String)khxxMap.get("WHLXDM")==null?"":(String)khxxMap.get("WHLXDM");   //维护类型 
				String khmc = (String)khxxMap.get("KHMC")==null?"":(String)khxxMap.get("KHMC");      //客户名称
				String khbh = (String)khxxMap.get("KHBH")==null?"":(String)khxxMap.get("KHBH");      //客户编号
				
				//插入日志信息
				xzlxrService.insertLog(userMap,bindid, khmc, khbh, whlxdm);
				
				//更新客户时间
				xzlxrService.upCustomerTime(khbh);
				
				Map<String, Object> handleTaskResult = new HashMap<>();

				//获取下个节点号
				Integer nextStepNo = Integer.valueOf(restWorkFlowService.GetNextStepNo(userid,Integer.parseInt(bindid),
						Integer.parseInt(taskid)));
				
				handleTaskResult = restWorkFlowService.ExcuteTask(Integer.parseInt(bindid),userid, Integer.parseInt(taskid),
						TaskTitle,"","同意",opinion);

				// 判断是否是最后节点
				if (nextStepNo == -1) {
					resultMap.put("result", "任务流转已完毕");
					resultMap.put("type", "success");
				} else {
					// 办理至下一节点，返回消息
					String message = (String) handleTaskResult.get("message");
					resultMap.put("result", message);
					resultMap.put("type", "success");
				}
			
				return objectMapper.writeValueAsString(resultMap);
			} else {
				Map<String, Object> repulseTaskResult = restWorkFlowService.RepulseTask(Integer.parseInt(bindid),userid,
						Integer.parseInt(taskid), TaskTitle,"不同意",opinion);
				String message = (String) repulseTaskResult.get("message");
				resultMap.put("result", message);
				resultMap.put("type", "success");
				return objectMapper.writeValueAsString(resultMap);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
 			String json = "当前节点办理时出现问题，请联系管理员";
			resultMap.put("result", json);
			resultMap.put("type", "error");
			return objectMapper.writeValueAsString(resultMap);
		}
	}
	
	/**
	 * 追踪返回链接
	 * @param bindid  流程id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/zhuizong")
	public String zhuiZong(String bindid){
		try {
			List<Map<String,Object>> list = xzlxrService.zhuiZong(bindid);
			return objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			logger.error(e.getMessage());
			return "追踪时出现异常！";
		}
	}
	
	/**
	 * 初始化新增的系统字段
	 * @param map
	 * @param bindid
	 * @param userid
	 * @return
	 */
	public Map<String,Object> initParam(Map<String,Object> map,String bindid,String userid){
		map.put("BINDID", bindid);
		map.put("ORGNO", 1);
		map.put("CREATEUSER", userid);
		map.put("UPDATEUSER", userid);
		map.put("WFID", 0);
		map.put("WFSID", 0);
		map.put("ISEND", "0");
		return map;
	}
	
	/**
	 * 自动生成序列号或版本号
	 * @param tablename 主表的名称
	 * @param prefixfiledname 主表的序号字段
	 * @param prefix  序号前缀字段值
	 * @param prefixLength 序号长度
	 * @return 序列号或版本号
	 */
	public String getSuffixNumberByPrefixValue(String tablename,String prefixfiledname,String prefix,int prefixLength){
		if(tablename==null||prefixfiledname==null||tablename.trim().equals("")||prefixfiledname.trim().equals("")){
			return "-1";
		}
		int allPrefixLength=prefixLength+prefix.length();
		Map<String,Object> map = new HashMap<>();
		map.put("LXRBH",prefixfiledname);
		map.put("BMBH", prefix);
		map.put("LENGTH", allPrefixLength);
		List<String> lxrbhList = xzlxrService.getLxrbh(map);
		int suffixValue=1;//序号值
		String returnSuffixValue="-1";
		try{
			ArrayList<Integer> al=new ArrayList<Integer>();
			for(String lxrbh : lxrbhList){
				String oldSuffValueStr=lxrbh;
				int oldSuffValue=Integer.parseInt(oldSuffValueStr.replaceAll(prefix, "0"));
				if(oldSuffValue>0){
		            boolean flag=true;
					for(int i=0;i<al.size();i++){
		            	if(al.get(i)==oldSuffValue){
		            		flag=false;
		            		break;
		            	}
		            }
					if(flag){
					    al.add(oldSuffValue);
					}
				}
			}
			
			if(al.size()==0){
				suffixValue=1;
			}else{		
				/*
				 * 冒泡排序
				 */
				
				Object [] objects=al.toArray();
				
				for(int i=0;i<objects.length-1;i++){
					for(int j=0;j<objects.length-1-i;j++){
						if(Integer.parseInt(objects[j].toString())<Integer.parseInt(objects[j+1].toString())){
							suffixValue=Integer.parseInt(objects[j+1].toString());
							objects[j]=objects[j+1];
							objects[j+1]=new Integer(suffixValue);
						}
					}
				}
				
				/*
				 * 获取序号
				 */
				int k=0;
				for(;k<objects.length;k++){
					if(k+1!=Integer.parseInt(objects[k].toString())){
						break;
					}
				}
				suffixValue=suffixValue+1;
			}
			String str="";
			for(int i=0;i<prefixLength-(suffixValue+"").length();i++){
				str=str+"0";
			}
			returnSuffixValue=prefix+str+suffixValue+"";
		}catch(Exception ex){
			ex.printStackTrace();
			return "-1";
		}
		return returnSuffixValue;
	}
}
