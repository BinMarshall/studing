//package cn.bravolinks.erp.crm.server.util;
//
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by yanqin on 2017/2/18.
// */
//public class WorkFlowUtils {
//
//	private final static Logger logger = LoggerFactory.getLogger(WorkFlowUtils.class);
//
//    private static ObjectMapper mapper = new ObjectMapper();
//
//    /**
//     * 发送内、外网邮件, 正常使用该服务方法发送外网邮件应正确配置邮件服务器（参见AWS 实施手册），
//     * 若 mailTo 包含了发往外部的互联网邮件，
//     * 作为 mailFrom 发送者应在个人配置中设置了自己的外网邮件地址。
//     * @param mailFrom 发送人，合法的 AWS 账户
//     * @param mailTo 接收人邮件地址，合法的 AWS 账户或外部邮件，多个空格隔开
//     * @param subject 邮件标题
//     * @param content 正文内容
//     * @return int 如果发送成功,返回一个大于 0 的 mailTask .ID,出错返回_SENDMAIL_ERROR(-9)
//     */
//    public static String sendMail(String mailFrom,String mailTo,String subject,String content){
//        String url = PropertiesUtils.getPropertyByCommon("WorkFlowServiceURL") + "im/sendMail";
//        try {
//            String data = "mailFrom="+mailFrom+"&mailTo="+mailTo+"&subject="+
//                    URLEncoder.encode(subject, "UTF-8")+"&content="+
//                    URLEncoder.encode(content,"UTF-8");
//            return HttpUtils.httpPost(url,data);
//        } catch (UnsupportedEncodingException e) {
//            logger.error("",e);
//            return null;
//        }
//    }
//
//    /**
//     * 从流程主表获取流程的id（非bindid，非流程实例id）
//     * @return
//     */
//    public static Map<String,Integer> getSysflowidByFlowuuid(String uuid,Integer stepNo){
//        String url = PropertiesUtils.getPropertyByCommon("WorkFlowUrl") + "getSysflowidByFlowuuid/"+uuid+"/"+stepNo;
//        String re = HttpUtils.httpGet(url);
//        logger.info("getSysflowidByFlowuuid = " + re);
//        try {
//            return mapper.readValue(re,Map.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 获取账号，根据角色id和公司代码
//     * @param roleid
//     * @param gsdm
//     * @return
//     */
//    public static String getUseridByCompanyDm(Integer roleid,String gsdm){
//        String url = PropertiesUtils.getPropertyByCommon("WorkFlowUrl") + "getUseridByCompanyDm/"+roleid+"/"+gsdm;
//        return HttpUtils.httpGet(url);
//    }
//
//    /**
//     * 获取下个节点号
//     * @return
//     */
//    public static Integer getNextStepNo(String loginname,Integer bindid,Integer taskid){
//        String url = PropertiesUtils.getPropertyByCommon("WorkFlowUrl")+"GetNextStepNo/";
//        String data = "LoginName="+ loginname
//                +"&processId="+bindid
//                +"&taskid="+taskid;
//        return Integer.valueOf(HttpUtils.httpPost(url, data));
//    }
//
//    /**
//     * 打回接口，通用型
//     * 通过调用aws api，获取审批人
//     * @param bindid
//     * @param taskid
//     * @param loginname
//     * @param tasktitle
//     * @param isagree
//     * @param opinion
//     * @return
//     */
//    public static Map<String,Object> repulseTask(Integer bindid,Integer taskid,String loginname,String tasktitle,String isagree,String opinion){
//        String url = PropertiesUtils.getPropertyByCommon("WorkFlowUrl")+"RepulseTask/";
//        String data = null;
//        try {
//            data = "ProcessID="+bindid
//                    +"&TaskID="+taskid
//                    +"&LoginName="+loginname
//                    +"&TaskTitle="+ URLEncoder.encode(tasktitle,"UTF-8")
//                    +"&Isagree="+URLEncoder.encode(isagree,"UTF-8")
//                    +"&Opinion="+URLEncoder.encode(opinion,"UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String re = HttpUtils.httpPost(url,data);
//        logger.info("repulseTask = " + re);
//        try {
//            return mapper.readValue(re,Map.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 打回任务,通过task_log表来找上个节点审批人
//     */
//    public static Map<String, Object> repulseTask2(Integer bindid,Integer taskid,String loginname,String tasktitle,String isagree,String opinion){
////        (@FormParam("ProcessID") Integer ProcessID, @FormParam("LoginName") String LoginName,
////                @FormParam("TaskID") Integer TaskID, @FormParam("TaskTitle") String TaskTitle
////                ,@FormParam("Isagree")String Isagree
////                ,@FormParam("Opinion")String Opinion
//
//        String url = PropertiesUtils.getPropertyByCommon("WorkFlowUrl")+"RepulseTask2/";
//        String data = null;
//        try {
//            data = "ProcessID="+bindid
//                    +"&TaskID="+taskid
//                    +"&LoginName="+loginname
//                    +"&TaskTitle="+ URLEncoder.encode(tasktitle,"UTF-8")
//                    +"&Isagree="+URLEncoder.encode(isagree,"UTF-8")
//                    +"&Opinion="+URLEncoder.encode(opinion,"UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String re = HttpUtils.httpPost(url,data);
//        logger.info("repulseTask2 = " + re);
//        try {
//            return mapper.readValue(re,Map.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 打回任务,通过task_log表来找指定节点审批人
//     */
//    public static Map<String, Object> repulseTask3(Integer bindid,Integer taskid,String loginname,String tasktitle,Integer nextStepno,String isagree,String opinion){
////        @FormParam("ProcessID") Integer ProcessID, @FormParam("LoginName") String LoginName,
////        @FormParam("TaskID") Integer TaskID, @FormParam("TaskTitle") String TaskTitle,
////        @FormParam("nextStepno")Integer nextStepno
////        ,@FormParam("Isagree")String Isagree
////        ,@FormParam("Opinion")String Opinion
//
//        String url = PropertiesUtils.getPropertyByCommon("WorkFlowUrl")+"RepulseTask3/";
//        String data = null;
//        try {
//            data = "ProcessID="+bindid
//                    +"&TaskID="+taskid
//                    +"&LoginName="+loginname
//                    +"&TaskTitle="+ URLEncoder.encode(tasktitle,"UTF-8")
//                    +"&nextStepno="+nextStepno
//                    +"&Isagree="+URLEncoder.encode(isagree,"UTF-8")
//                    +"&Opinion="+URLEncoder.encode(opinion,"UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String re = HttpUtils.httpPost(url,data);
//        logger.info("repulseTask3 = " + re);
//        try {
//            return mapper.readValue(re,Map.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    /**
//     * 获取留言
//     */
//    public static List<Map<String,Object>> getOpinion(Integer bindid){
////        [{"parentId":2266742,"opinion":null,"createDate":"2015-11-30 14:34:27","createUser":"杨成彦","isagree":"提交",
////                "auditdesc":"出票申请","taskId":2055352,"deptname":"能源与汽车客户部","positionname":"客户经理"}]
//        String url = PropertiesUtils.getPropertyByCommon("WorkFlowUrl")+"getMessageOpinion/"+bindid;
//        String re = HttpUtils.httpGet(url);
//        logger.info("getOpinion = " + re);
//        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Map.class);
//        try {
//            return mapper.readValue(re,javaType);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 获取任务所处节点序号
//     */
//    public static Integer getStepByTaskid(Integer taskid){
//        String url = PropertiesUtils.getPropertyByCommon("WorkFlowUrl")+"getStpeno/"+taskid;
//        String re = HttpUtils.httpGet(url);
//        logger.info("getStepByTaskid = " + re);
//        if(re.equals("null")){
//            return null;
//        }else{
//            return Integer.valueOf(re);
//        }
//    }
//
//    /**
//     * 判断登录人，是不是审批人
//     */
//    public static boolean isTaskShenpiren(Integer taskid,String userid){
//        String url = PropertiesUtils.getPropertyByCommon("WorkFlowUrl")+"isTaskShenpiren/";
//        String data =  "taskid="+taskid+"&userid="+userid;
//        String re = HttpUtils.httpPost(url,data);
//        logger.info("isTaskShenpiren = " + re);
//        if(re.equals("true")){
//            return true;
//        }else{
//            return false;
//        }
//    }
//
//    /**
//     * 启动流程
//     */
//    public static Map<String,Object> startWorkFlow(String workflowuuid,String loginname,String workflowtitle,String tasktitle,String shenpiren){
//        String url = PropertiesUtils.getPropertyByCommon("WorkFlowUrl")+"StartWorkFlow/";
//        String data = null;
//        try
//        {
//            data = "WorkFlowUUID="+workflowuuid
//                    +"&LoginName="+loginname+"&WrokFlowTitle="
//                    + URLEncoder.encode(workflowtitle,"UTF-8")
//                    +"&TaskTitle="+URLEncoder.encode(tasktitle,"UTF-8")
//                    +"&ParticipantUser="+shenpiren
//                    +"&hm=0";
//        }
//        catch(UnsupportedEncodingException e)
//        {
//            e.printStackTrace();
//        }
//        String re = HttpUtils.httpPost(url, data);
//        logger.info("startWorkFlow = " + re);
//        Map<String,Object> map = null;
//        try
//        {
//            map = mapper.readValue(re, Map.class);
//        }
//        catch(IOException e)
//        {
//            e.printStackTrace();
//        }
//        Integer processId = (Integer) map.get("processId");
//        Integer taskid = (Integer)((List<Map<String,Object>>)map.get("tasks")).get(0).get("taskID");
//        logger.info(processId+"");
//        logger.info(taskid+"");
//        return map;
//    }
//
//    /**
//     * 办理任务
//     * @return
//     */
//    public static Map<String, Object> handleTask(Integer bindid,Integer taskid,String loginname,String tasktitle,String shenpiren,String isagree,String opinion){
//        //        isok":true,"message":"success","processId":2976804,"tasks":" +
////                "[{"taskID":2785274,"taskTitle":null,"nextStepNO":-1,"stepNo":3,"userid":"guojh","username":"郭俊华"}]}
//        String url = PropertiesUtils.getPropertyByCommon("WorkFlowUrl") + "ExcuteTask/";
//        String data = null;
//        try {
//            data = "ProcessID=" + bindid + "&LoginName="+ loginname + "&TaskID=" + taskid + "&TaskTitle="
//                    + URLEncoder.encode(tasktitle, "UTF-8") + "&ShenPiRen="+shenpiren+"&Isagree="
//                    + URLEncoder.encode(isagree, "UTF-8") + "&Opinion=" + URLEncoder.encode(opinion, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String re = HttpUtils.httpPost(url, data);
//        logger.info("handleTask = " + re);
//        try {
//            return mapper.readValue(re,Map.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 办理任务,相同办理者不忽略执行
//     * @return
//     */
//    public static Map<String, Object> handleTask(Integer bindid,Integer taskid,String loginname,String tasktitle,String shenpiren,String isagree,String opinion,boolean isJump){
//        String url = PropertiesUtils.getPropertyByCommon("WorkFlowServiceURL") + "customWorkflow/handleTask";
//        String data = null;
//        try {
//            data = "processId=" + bindid + "&loginName="+ loginname + "&taskID=" + taskid + "&taskTitle="
//                    + URLEncoder.encode(tasktitle, "UTF-8") + "&shenPiRen="+shenpiren+"&isagree="
//                    + URLEncoder.encode(isagree, "UTF-8") + "&opinion=" + URLEncoder.encode(opinion, "UTF-8")+"&isJump="+isJump;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String re = HttpUtils.httpPost(url, data);
//        logger.info("handleTask = " + re);
//        try {
//            return mapper.readValue(re,Map.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 关闭流程和任务
//     * @param loginname
//     * @param bindid
//     * @return
//     */
//    public static String closeWorkFlowAndTask(String loginname,Integer bindid){
//        String url = PropertiesUtils.getPropertyByCommon("WorkFlowUrl") + "closeWorkFlowAndTask/"+loginname+"/"+bindid;
//        // 成功就返回true
//        return HttpUtils.httpGet(url);
//    }
//
//    /**
//     * 特事特办
//     * @return
//     */
//    public static Map<String, Object> todoUrgePowerTask(Integer ProcessID, String LoginName, Integer TaskID, Integer StepNo, Integer NextStepNo,
//                                        String TaskTitle , String Shenpiren, String Isagree , String Opinion){
//        String url = PropertiesUtils.getPropertyByCommon("WorkFlowUrl") + "TodoUrgePowerTask/";
//        String data = null;
//        try {
//            data = "ProcessID=" + ProcessID + "&LoginName="+ LoginName + "&TaskID=" + TaskID
//                    + "&StepNo="+StepNo+"&NextStepNo="+NextStepNo
//                    + "&TaskTitle="
//                    + URLEncoder.encode(TaskTitle, "UTF-8") + "&Shenpiren="+Shenpiren+"&Isagree="
//                    + URLEncoder.encode(Isagree, "UTF-8") + "&Opinion=" + URLEncoder.encode(Opinion, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String re = HttpUtils.httpPost(url, data);
//        logger.info("todoUrgePowerTask = " + re);
//        try {
//            return mapper.readValue(re,Map.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 获取流程创建人，根据bindid
//     * @param bindid
//     * @return
//     */
//    public static Map<String,Object> getWFCreateUserByBindid(Integer bindid){
//        String url = PropertiesUtils.getPropertyByCommon("WorkFlowServiceURL") + "custom/getWFCreateUserByBindid?bindid="+bindid;
//        // 成功就返回true
//        return JsonUtils.jsonToObject(HttpUtils.httpGet(url),Map.class);
//    }
//    /**
//     * 创建加签
//     * @return
//     */
//    public static Map<String, Object> AppendProcessTask(Integer bindid,Integer taskid,String loginname,
//    		String tasktitle,String shenpiren,String isagree,String opinion){
//        String url = PropertiesUtils.getPropertyByCommon("WorkFlowUrl") + "AppendProcessTask/";
//
////    	String url = "http://192.168.105.152:8080/WorkFlowWebService/Rest/WorkFlow/AppendProcessTask/";
//        String data = null;
//        try {
//            data = "ProcessID=" + bindid + "&LoginName="+ loginname + "&TaskID=" + taskid + "&TaskTitle="
//                    + URLEncoder.encode(tasktitle, "UTF-8") + "&Participant="+shenpiren+"&Isagree="
//                    + URLEncoder.encode(isagree, "UTF-8") + "&Opinion=" + URLEncoder.encode(opinion, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String re = HttpUtils.httpPost(url, data);
//        System.out.println("handleTask = " + re);
//        try {
//            return mapper.readValue(re,Map.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//    /**
//     * 办理加签
//     * @return
//     */
//    public static Map<String, Object> ExceutAppendProcessTask(Integer bindid,Integer taskid,String loginname,
//    		String isagree,String opinion){
//        String url = PropertiesUtils.getPropertyByCommon("WorkFlowUrl") + "ExceutAppendProcessTask/";
//        String data = null;
//        try {
//            data = "ProcessID=" + bindid + "&LoginName="+ loginname + "&TaskID=" + taskid + "&TaskTitle="
//                    + "&Isagree=" + URLEncoder.encode(isagree, "UTF-8") + "&Opinion=" + URLEncoder.encode(opinion, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String re = HttpUtils.httpPost(url, data);
//        System.out.println("handleTask = " + re);
//        /*try {
//            return mapper.readValue(re,Map.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }*/
//        return null;
//    }
//    /**
//     * 办理加签  最后一个加签完成字段发送给   发送加签的人
//     * @return
//     */
//    public static Map<String, Object> handleAppendProcessTask(Integer bindid, String loginname,
//                                                              Integer taskid, String isagree, String opinion,
//                                                              String title,String description,String url){
//    	String wfurl = PropertiesUtils.getPropertyByCommon("WorkFlowServiceURL") + "customWorkflow/handleAppendProcessTask";
//
////    	String wfurl = "http\\://172.16.250.133\\:8080/WorkFlowWebService/customWorkflow/handleAppendProcessTask";
////    	String wfurl = "http://192.168.105.152:8080/WorkFlowWebService/customWorkflow/handleAppendProcessTask";
//        String data = null;
//        try {
//            data = "processId=" + bindid + "&loginName="+ loginname + "&taskId=" + taskid
//                    + "&isagree=" + URLEncoder.encode(isagree, "UTF-8") + "&opinion=" + URLEncoder.encode(opinion, "UTF-8")
//            +"&title="+URLEncoder.encode(title,"UTF-8")+"&description="+URLEncoder.encode(description,"UTF-8")+"&url="+URLEncoder.encode(url,"UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String re = HttpUtils.httpPost(wfurl, data);
//        System.out.println("handleAppendProcessTask = " + re);
//        return JsonUtils.jsonToObject(re,Map.class);
//    }
//
//    /**
//     * 删除流程
//     * @param bindid
//     * @return
//     */
//    public static boolean removeProcessInstance(int bindid){
//		String url = PropertiesUtils.getPropertyByCommon("WorkFlowServiceURL") +"workflow/removeProcessInstance/"+bindid;
//	    String re = HttpUtils.httpGet(url);
//        return Boolean.parseBoolean(re);
//    }
//    /**
//     * 查询任务是否办理
//     * 1 待办   2已办   0任务不存在
//     * @param taskid
//     * @return
//     */
//    public static Integer getTaskIsHandle(Integer taskid){
//    	String baseUrl = PropertiesUtils.getPropertyByCommon("WorkFlowServiceURL") ;
//        String url = baseUrl + "custom/getTaskIsHandle?taskid="+taskid;
//        String re = HttpUtils.httpGet(url);
//        return Integer.valueOf(re);
//    }
//    /**
//     * 查询任务 从待办已办表
//     * @param id
//     * @return
//     */
//    public static Map<String,Object> getTaskById(Integer id){
//    	//BaseWorkFlowWebServiceURL=http\://172.16.250.133\:8080/BRAVOLINKS_WFSERVICE/
//        String url = PropertiesUtils.getPropertyByCommon("WorkFlowServiceURL") + "data/getTaskById?id="+id;
//        String re = HttpUtils.httpGet(url);
//        System.out.println("查询任务 从待办已办表::::::"+re);
//
//        return re==null||"".equals(re)?null:JsonUtils.jsonToObject(re,Map.class);
//    }
//}
