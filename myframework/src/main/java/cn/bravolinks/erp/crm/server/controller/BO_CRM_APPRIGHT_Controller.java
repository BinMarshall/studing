package cn.bravolinks.erp.crm.server.controller;

import cn.bravolinks.erp.crm.server.feign.CustomWorkFlowService;
import cn.bravolinks.erp.crm.server.feign.DataService;
import cn.bravolinks.erp.crm.server.feign.RestWorkFlowService;
import cn.bravolinks.erp.crm.server.feign.TppService;
import cn.bravolinks.erp.crm.server.model.BO_CRM_APPRIGHT;
import cn.bravolinks.erp.crm.server.model.BO_CRM_APPRIGHT_S;
import cn.bravolinks.erp.crm.server.model.BO_USER_EXT;
import cn.bravolinks.erp.crm.server.model.Dept;
import cn.bravolinks.erp.crm.server.model.User;
import cn.bravolinks.erp.crm.server.service.BO_CRM_APPRIGHTService;
import cn.bravolinks.erp.crm.server.util.SysUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 客户权限申请服务端接口
 * @author lipengliang
 * 2017-6-19
 */
@Controller
@RequestMapping("/")
public class BO_CRM_APPRIGHT_Controller {
	private static final Logger logger = LoggerFactory.getLogger(BO_CRM_APPRIGHT_Controller.class);
	@Autowired
	private BO_CRM_APPRIGHTService bO_CRM_APPRIGHTService;
	@Autowired
	private TppService tppService;
	@Autowired
	private RestWorkFlowService restWorkFlowService;
	@Autowired
	private DataService dataService;
	@Autowired
	private CustomWorkFlowService customWorkFlowService;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	
	/***
	 * 客户权限申请主表信息
	 * @param bindid
	 * @param userid
	 * @return 返回json格式的字符串
	 * @author lipengliang
	 */
	@RequestMapping(value = "/getApprightByBindId",method=RequestMethod.GET)
	@ResponseBody
	public String getAll(String bindid,String userid) {
        try {
        	Map<String, String> map = new HashMap<String, String>();
        	map.put("bindid", bindid);
        	map.put("userid", userid);
        	//获取对象
        	BO_CRM_APPRIGHT bO_CRM_APPRIGHT = bO_CRM_APPRIGHTService.getApprightByBindId(bindid);
    		//返回json格式的字符串
            ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(bO_CRM_APPRIGHT);
			return json;
		} catch (Exception e) {
			logger.error("",e);
			return "访问数据有误，请联系系统管理员";
		}
	}
	
	/**
     * 获取部门列表数据
     *
     * @return
     */
    @RequestMapping(value = "/getRootDept", method = RequestMethod.GET)
    @ResponseBody
    public String deptListData() {
    	ObjectMapper mapper = new ObjectMapper();
        //获取部门信息返回list
    	List<Dept> childDepts = bO_CRM_APPRIGHTService.getRootDept();
        /*获取所有部门成员信息*/
        List<Object> os = new ArrayList<>();
        os.addAll(childDepts);
        try {
			return mapper.writeValueAsString(os);
		} catch (JsonProcessingException e) {
			logger.error("",e);
		}
        return "";
    }
    
    /**
     * 获取子表数据
     *
     * @return
     */
    @RequestMapping(value = "/getApprightSubByBindId", method = RequestMethod.GET)
    @ResponseBody
    public String getApprightSubByBindId(String bindid) {
    	ObjectMapper mapper = new ObjectMapper();
    	List<BO_CRM_APPRIGHT_S> subs = bO_CRM_APPRIGHTService.getApprightSubByBindId(bindid);
        /*获取子表数据*/
        List<Object> os = new ArrayList<>();
        os.addAll(subs);
        try {
			return mapper.writeValueAsString(os);
		} catch (JsonProcessingException e) {
			logger.error("",e);
		}
        return "";
    }
	
	/**
     * 获取任务状态，包括已办和待办
     *
     * @return
     */
    @RequestMapping(value = "/getStatusOfTask", method = RequestMethod.GET)
    @ResponseBody
    public String getStatusOfTask(String bindid,String taskid) {
    	//待办任务状态
    	String dbStatus = bO_CRM_APPRIGHTService.getDbStatusOfTask(bindid,taskid);
    	//已办任务状态
    	String ybStatus = bO_CRM_APPRIGHTService.getYbStatusOfTask(bindid,taskid);
    	
    	Map m = new HashMap<>();
    	m.put("dbStatus", dbStatus);
    	m.put("ybStatus", ybStatus);
    	String res = "";
        try {
        	res =  objectMapper.writeValueAsString(m);
		} catch (JsonProcessingException e) {
			logger.error("",e);
		}
        return res;
    }
    /**根据userid查询用户扩展信息
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/getUserExtByUserid",method=RequestMethod.GET)
	@ResponseBody
	public String getUserExtByUserid(String userid) {
		ObjectMapper objectMapper = new ObjectMapper();
		BO_USER_EXT ext = bO_CRM_APPRIGHTService.getUserExtByUserid(userid);
		String res;
		try {
			res = objectMapper.writeValueAsString(ext);
			return res;
		} catch (Exception e) {
			logger.error("",e);
			return "访问数据有误，请联系系统管理员";
		}
	}
	/**
     * 获取ERP所有人员信息
     *
     * @return
     */
    @RequestMapping(value = "/getAllOrgUser", method = RequestMethod.GET)
    @ResponseBody
    public String getAllOrgUser() {
    	ObjectMapper mapper = new ObjectMapper();
    	//获取部门下的人员
    	List<User> users = bO_CRM_APPRIGHTService.getAllOrgUser();
    	List<Object> os = new ArrayList<>();
    	if(null!=users && users.size()>0){
    		os.addAll(users);
    	}
    	String res = "";
        try {
        	res =  mapper.writeValueAsString(os);
		} catch (JsonProcessingException e) {
			logger.error("",e);
		}
        return res;
//        return JsonUtils.objectToJson(os);
    }
	
	/**
     * 获取部门列表数据
     *
     * @return
     */
    @RequestMapping(value = "/getUserOrDeptByID", method = RequestMethod.GET)
    @ResponseBody
    public String getUserOrDeptByID(Integer id) {
    	ObjectMapper mapper = new ObjectMapper();
        //获取子部门信息返回list
    	List<Dept> childDepts = bO_CRM_APPRIGHTService.getDeptsByID(id);
    	//获取部门下的人员
    	List<User> users = bO_CRM_APPRIGHTService.getUsersByID(id);
    	
    	List<Object> os = new ArrayList<>();
    	if(null!=childDepts && childDepts.size()>0){
    		os.addAll(childDepts);
    	}
    	if(null!=users && users.size()>0){
    		os.addAll(users);
    	}
    	String res = "";
        try {
        	res =  mapper.writeValueAsString(os);
		} catch (JsonProcessingException e) {
			logger.error("",e);
		}
        return res;
    }
    /**发送加签
     * @param request
     * @param stepno
     * @param bindid
     * @param task_id
     * @param userid
     * @param opinion
     * @param zdr
     * @param htbbh
     * @param gysmc
     * @param htje
     * @param taskTitle
     * @param shenpiren
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/sendJiaqian", method = RequestMethod.GET)
    @ResponseBody//此注解不能省略 否则ajax无法接受返回值
    public String sendJiaqian(HttpServletRequest request,String stepno,String bindid,String task_id,String userid,String opinion,String zdr,String htbbh,String gysmc,String htje,String taskTitle,String shenpiren) throws JsonProcessingException { ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> resultMap = new HashMap<String, String>();
        String json ="";
        try {
//        	Long mms = new Date().getTime();
        	//发送加签
        	String isagree = "1";
        	taskTitle = "（加签）"+bO_CRM_APPRIGHTService.getTaskTitleByTaskid(Integer.parseInt(task_id));
        	Map<String, Object> res = restWorkFlowService.AppendProcessTask(Integer.valueOf(bindid),userid,shenpiren,Integer.valueOf(task_id),taskTitle,isagree,opinion);

	    	List<Map<String,Object>> aaa=(List<Map<String,Object>>)res.get("tasks");
	        int count=0;
			for (Map<String, Object> o : aaa) {
				String shenpiUser = o.get("userid").toString();
				int taskid = Integer.valueOf(o.get("taskID").toString());
//				taskTitle = boHtContractGroupService.getTaskTitleByTaskid(taskid);
				shenpiren=shenpiren.trim();
				//给手机端发送加签
				String receiverType = "single";
				String title = "客户权限申请审批待办（加签）";
				String description = taskTitle;
				String msgtype = "link";
				String dl = "单位客户";
            	String lx = "移动端地址";
            	String ywmc = "KHQXSQ";
				String url = bO_CRM_APPRIGHTService.getUrLOfTodoTask(dl,lx,ywmc);//ERP中配置的基础数据 采购单团
				url = url+"bindid="+bindid+"&userid="+shenpiUser+"&taskid="+taskid;
				tppService.sendLinkMessage("admin", shenpiUser,receiverType,title,description,url,msgtype);
			}
        	
        	resultMap.put("result", "加签成功");
        	resultMap.put("type", "success");
            return objectMapper.writeValueAsString(resultMap);
        } catch (Exception e) {
            logger.error("",e);
            String json1 = "当前节点办理时出现问题，请联系管理员";
            resultMap.put("result", json1);
        	resultMap.put("type", "error");
            return objectMapper.writeValueAsString(resultMap);
        }
    }
    
    /***
	  * 点击链接时校验是当前用户是否是审批人
	  * @param taskid 任务id
	  * @param userid 用户id
	  * @return 返回json格式的字符串
	  */
	@RequestMapping(value = "/checkTaskidIsOK",method=RequestMethod.GET)
	@ResponseBody
	public String checkTaskidIsOK(Integer taskid,String userid) {
		try{	
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap = dataService.getTaskById(taskid);

			if(resultMap!=null){
				String spr=resultMap.get("target")==null?"":resultMap.get("target").toString()==null?"":resultMap.get("target").toString();
				if(null!=userid && userid.equals(spr)){
					resultMap.put("type", "success");
					resultMap.put("result", "当前审批人与登录人一致，可以办理");
				}else{
					resultMap.put("type", "error");
					resultMap.put("result", "当前登录人与审批人不一致");
				}
			}else{
				Map<String, Object> resultMap1 = new HashMap<String, Object>();
				resultMap1.put("type", "error");
				resultMap1.put("result", "当前任务已经被其他人抢签办理或者当前任务已被删除！");
				String json = objectMapper.writeValueAsString(resultMap1);
				return json;
			}
			String json = objectMapper.writeValueAsString(resultMap);
			return json;
		} catch (Exception e) {
			logger.error("",e);
    		return SysUtils.errorResult("当前登录人与审批人不一致");
		}
	}
    
    
    /**
     * @param request
     * @param stepno
     * @param zdr
     * @param bindid
     * @param task_id
     * @param userid
     * @param isagree
     * @param opinion
     * @return
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/cgdtbl", method = RequestMethod.GET)
    @ResponseBody
    public String zcsqsp(HttpServletRequest request, String stepno, String zdr,String bindid,String task_id,String userid,String isagree,String opinion) throws JsonProcessingException {
    	ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> resultMap = new HashMap<String, String>();
        String json ="";
        try {
        	//判断任务是否存在
        	String dbstatus = bO_CRM_APPRIGHTService.getDbStatusOfTask(bindid, task_id);
        	String ybstatus = bO_CRM_APPRIGHTService.getYbStatusOfTask(bindid, task_id);
        	if("".equals(dbstatus) && "".equals(ybstatus)){//任务已经不存在了
        		resultMap.put("result", "当前任务已经被其他人抢签办理或者当前任务已被删除！");
    			resultMap.put("type", "notexist");
    			return objectMapper.writeValueAsString(resultMap);
        	}
        	BO_CRM_APPRIGHT  p = bO_CRM_APPRIGHTService.getApprightByBindId(bindid);//根据bindid获取数据
        	String description = bO_CRM_APPRIGHTService.getTaskTitleByTaskid(Integer.parseInt(task_id));
        	//1 大客户总监   2	服务部门   3	客户共享  5	客户经理
        	String sqlxdm = p.getSqlxdm();
        	if("同意".equals(isagree)){
        		description = description.substring(description.indexOf(")", 1)+1);
        		if("5".equals(stepno) && "3".equals(sqlxdm)){//当地市场部经理审批
        			//更新单据状态i
        			p.setDjztdm("3");//完成
        			//TODO 发送邮件
        		}
        		if("7".equals(stepno)){//
        			//更新单据状态
        			p.setDjztdm("3");//完成
        			//TODO 发送邮件
        		}
        		if("8".equals(stepno)){//
        			//更新单据状态
        			p.setDjztdm("3");//完成
        		}
        		if("10".equals(stepno)){//
        			//更新单据状态
        			p.setDjztdm("3");//完成
        			//TODO 发送邮件
        		}
        		if("11".equals(stepno)){//
        			//更新单据状态
        			p.setDjztdm("3");//完成
        			//TODO 发送邮件
        		}
        		
        		bO_CRM_APPRIGHTService.updateByCurrent(p);
        		
            	//获取下个节点号
            	Integer nextStepNo = Integer.valueOf(restWorkFlowService.GetNextStepNo(userid,Integer.parseInt(bindid),Integer.parseInt(task_id)));
            	Map<String,Object> handleTaskResult = restWorkFlowService.ExcuteTask(Integer.parseInt(bindid),userid,Integer.parseInt(task_id),description,"",isagree,opinion+"(移动审批)");

            	//判断是否是最后节点
            	if (nextStepNo==-1) {
            		resultMap.put("result", "任务流转已完毕");
            		resultMap.put("type", "success");
            	} else {
            		boolean isok = (boolean)handleTaskResult.get("isok");
            		//办理至下一节点，返回消息
            		String message = (String)handleTaskResult.get("message");
            		if(isok){//办理成功
            			resultMap.put("result", message);
            			resultMap.put("type", "success");
            		}else{
            			resultMap.put("result", message);
            			resultMap.put("type", "error");
            		}
            	}
            }
        	if("不同意".equals(isagree)){//不同意打回第一节点  起草者
        		//**==============审批流程打回到指定节点=====ssy===============start================*//*
        		description = description.substring(description.indexOf(")", 1)+1);
                Map<String,Object> repulseTaskResult = restWorkFlowService.RepulseTask3(Integer.parseInt(bindid),userid,Integer.parseInt(task_id),description,1,isagree,opinion+"(移动审批)");
                String message = (String)repulseTaskResult.get("message");
                boolean isok = (boolean)repulseTaskResult.get("isok");
              //获取下一节点的审批人
                List<Map<String,Object>> aaa=(List<Map<String,Object>>)repulseTaskResult.get("tasks");
                	for(Map<String,Object> o : aaa){
                		String shenpiren = o.get("userid").toString();
                		String[] nextUsers= shenpiren.split(" ");
                		for(String shenpiUserId:nextUsers){
                			if(isok){
                				resultMap.put("result", message);
                				resultMap.put("type", "success");
                			}else{
                				resultMap.put("result", message);
                				resultMap.put("type", "error");
                			}
                		}
                	}
				}
        	if("完成".equals(isagree)){//加签审批完成 调用加签完成
//        		Map<String, Object> re = WorkFlowUtils.ExceutAppendProcessTask(Integer.parseInt(bindid), Integer.parseInt(task_id), userid, "1", "加签审批留言："+opinion);
        		String title = "客户权限申请审批待办";
        		String dl = "单位客户";
            	String lx = "移动端地址";
            	String ywmc = "KHQXSQ";
        		description = description.split("（加签）")[1];
        		//当前的加签任务   查询发送加签任务的任务信息
        		Map<String ,String> map = bO_CRM_APPRIGHTService.getTaskMessageByTaskid(task_id);
        		String fromTaskid = map.get("fromTaskid");
        		String fromUserid = map.get("fromUserid");
        		
        		String url = bO_CRM_APPRIGHTService.getUrLOfTodoTask(dl,lx,ywmc);//ERP中配置的基础数据 采购单团
        		//userid 合同taskid是发送加签人的 userid合同taskid
            	url = url+"bindid="+bindid+"&userid="+fromUserid+"&taskid="+fromTaskid;
            	
        		Map<String, Object> re = customWorkFlowService.handleAppendProcessTask(Integer.parseInt(bindid),"admin",Integer.parseInt(task_id),isagree,opinion+"(移动审批)",title,description,url);
        		resultMap.put("result", "任务已成功办理");
             	resultMap.put("type", "success");
        	}
            return objectMapper.writeValueAsString(resultMap);
        } catch (Exception e) {
            logger.error("",e);
            String json1 = "当前节点办理时出现问题，请联系管理员";
            resultMap.put("result", json1);
        	resultMap.put("type", "error");
            return objectMapper.writeValueAsString(resultMap);
        }
    }
}
