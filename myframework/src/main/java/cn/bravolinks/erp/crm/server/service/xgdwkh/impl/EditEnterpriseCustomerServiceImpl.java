package cn.bravolinks.erp.crm.server.service.xgdwkh.impl;

import cn.bravolinks.erp.crm.server.dao.COMMON_BO_CRM_APPRIGHT_Dao;
import cn.bravolinks.erp.crm.server.dao.xzkh.*;
import cn.bravolinks.erp.crm.server.feign.*;
import cn.bravolinks.erp.crm.server.model.xzkh.*;
import cn.bravolinks.erp.crm.server.service.BO_CRM_APPRIGHTService;
import cn.bravolinks.erp.crm.server.service.xgdwkh.EditEnterpriseCustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHAL
 * 创建时间：2018/9/10 16:35
 */
@Service
public class EditEnterpriseCustomerServiceImpl implements EditEnterpriseCustomerService {
    @Resource
    RestWorkFlowService rwfservice;
    @Resource
    MainSiteServiceFeign mainSiteServiceFeign;
    @Resource
    BO_CRM_CLIENTBUS_P_DAO clientbuspdao;
    @Resource
    BO_CRM_CLIENT_P_DAO clientpdao;
    @Resource
    BO_CRM_CLIENT_REG_DAO clientregdao;
    @Resource
    BO_CRM_CLIENTBUS_REG_DAO clientbusregdao;
    @Resource
    BO_CRM_CLIENT_EVENTS_DAO clienteventsdao;
    @Resource
    BO_CRM_CLIENTBUS_EVE_DAO clientbusevedao;
    @Resource
    BoCrmClientLogDao clientlogdao;
    @Resource
    BoCrmClientbusOregDao clientbusoregdao;
    @Resource
    BoCrmClientbusOeveDao clientbusoevedao;
    @Resource
    COMMON_BO_CRM_APPRIGHT_Dao commonDao;
    @Resource
    BOService boservice;
    @Resource
    DataService dataservice;
    @Resource
    BO_CRM_APPRIGHTService bO_CRM_APPRIGHTService;
    @Resource
    TppService tppService;
    @Resource
    CustomService customService;
    @Resource
    CustomWorkFlowService customWorkFlowService;

    @Override
    @Transactional
    public Map<String, Object> handleWorkFlow(Map<String, Object> params) throws Exception {
        Integer bindid = (Integer)params.get("bindid");
        Integer taskid = (Integer)params.get("taskid");
        String loginName = (String)params.get("curruser");
        String taskTitle = this.orgTaskTitle();
        String shenpiren = "";
        String isagree = "同意";
        String opinion = (String)params.get("opinion");
        // 获取当前节点信息
        Integer currStepNo = this.rwfservice.getStpeno(taskid);

        BO_CRM_CLIENTBUS_P clientbuspModel = this.clientbuspdao.selectBybindid(bindid.toString());
        Integer jtwlkh = clientbuspModel.getJtwlkh();
        String khbh = clientbuspModel.getKhbh();

//        if(currStepNo == 4 || currStepNo == 6){
//            this.handleBizLogic(bindid, currStepNo, clientbuspModel);
//        }

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("bindid", bindid);
        map.add("loginname",loginName);
        map.add("taskid", taskid);
        map.add("taskTitle", taskTitle);
        map.add("isagree", isagree);
        map.add("opinion", opinion);
        map.add("nextStepno", null);

        if(jtwlkh == 0 && currStepNo == 4 || currStepNo == 6){
            map.add("isCloseProcess", 1);
            this.handleBizLogic(bindid, currStepNo, clientbuspModel);
            this.sendMsgToCreater(bindid, taskid);
        }else{
            List<Map<String, Object>> users = this.fetchNextHandleUsers(bindid, currStepNo + 1, khbh);
            List<String> auditors = null;
            /** lambda表达式
             * users.stream() - 将list转成流
             * .map(user -> (String)user.get("USERID")) - 将流中的每项Map的转成USERID的值
             * .reduce((String result, String one) -> result + " " + one) - result是累加变量将，将USERID的值累加到result上，并以空格隔开
             * .get() - 获取结果中的字符串
             * */
//            String[] auditors = (String[])params.get("auditors");
            if(users != null && users.size() > 0){
                shenpiren = users.stream().map(user -> (String)user.get("USERID")).reduce((String result, String one) -> result + " " + one).get();
//                shenpiren = Arrays.stream(auditors).reduce((String result, String one) -> result + " " + one).get();
                auditors = users.stream().map(user -> (String)user.get("USERID")).collect(Collectors.toList());
            }
            if("".equals(shenpiren)){
                throw new Exception("下一节点审批人为空，不能办理");
            }
            map.add("shenpiren", shenpiren);

            this.sendMsgToNexter(bindid, taskid, auditors);
        }
        Map<String, Object> res = this.rwfservice.handleTask(map);

        // 判断是否发送消息推送

        return res;
    }

    @Override
    public Map<String, Object> receiveHandle(Map<String, Object> params) throws Exception {
        Integer bindid = (Integer)params.get("bindid");
        Integer taskid = (Integer)params.get("taskid");
        return this.customWorkFlowService.receiveHandle(bindid, taskid);
    }


    private boolean handleBizLogic(Integer bindid, Integer currStepNo, BO_CRM_CLIENTBUS_P clientbuspModel) throws Exception {
        if(clientbuspModel == null){
            return true;
        }
        String createUser = clientbuspModel.getCreateuser();
        // 档案id
        String daid = clientbuspModel.getDaid();
        // 集团网络客户
        Integer jtwlkh = clientbuspModel.getJtwlkh();
        if(currStepNo == 4 && jtwlkh == 1){
            return true;
        }

        // 更新单位客户档案主表(BO_CRM_CLIENT_P)的最后更新时间
        Integer viewBindid = Integer.parseInt(daid);
        this.clientpdao.updateByPrimaryKeySelective(this.orgclientp(clientbuspModel));

        // 先根据bindid删除BO_CRM_CLIENT_REG表数据，然后再将BO_CRM_CLIENTBUS_REG中的数据插入到BO_CRM_CLIENT_REG中
        this.clientregdao.deleteByBindid(viewBindid);
        List<BO_CRM_CLIENTBUS_REG> clientbusregs = this.clientbusregdao.selectListByBindid(bindid);
        BO_CRM_CLIENTBUS_REG busreg = null;
        for(int i=0; i<clientbusregs.size(); i++){
            busreg = clientbusregs.get(i);
            busreg.setBindid(viewBindid);
            this.clientregdao.insertSelective(busreg);
        }

        // 先删除单位客户档案-历史事件(BO_CRM_CLIENT_EVENTS)，然后再将单位客户信息-历史事件(BO_CRM_CLIENTBUS_EVE)中的数据插入到BO_CRM_CLIENT_EVENTS中
        this.clienteventsdao.deleteByBindid(viewBindid);
        List<BO_CRM_CLIENTBUS_EVE> clientbuseves = this.clientbusevedao.selectListByBindid(bindid);
        BO_CRM_CLIENTBUS_EVE buseve = null;
        for(int i=0; i<clientbuseves.size(); i++){
            buseve = clientbuseves.get(i);
            buseve.setBindid(viewBindid);
            this.clienteventsdao.insertSelectiveFromBus(buseve);
        }

        // 生成维护记录
        Map<String, Object> infomap = this.clientlogdao.selectInfoByUser(createUser);
        infomap.put("KHMC", clientbuspModel.getKhmc());
        infomap.put("KHBH", clientbuspModel.getKhbh());
        infomap.put("WHLXDM", clientbuspModel.getWhlxdm());
        infomap.put("BINDID", viewBindid);
        infomap.put("ISEND", clientbuspModel.getIsend());
        infomap.put("WORKFLOWID", clientbuspModel.getWorkflowid());
        infomap.put("WORKFLOWSTEPID", clientbuspModel.getWorkflowstepid());
        this.clientlogdao.insertSelective(orgClientLog(infomap));

        return true;
    }

    @Override
    public String sendMsgToCreater(Integer bindid, Integer taskid) throws Exception {
        String result = "";
        Map<String, Object> taskinfo = this.dataservice.getTaskById(taskid);
        BO_CRM_CLIENTBUS_P clientbusp = this.clientbuspdao.selectBybindid(bindid.toString());
        String createUser = clientbusp.getCreateuser();

        //给手机端发送加签
        String receiverType = "single";
        String title = "修改单位客户待办";
        String description = (String)taskinfo.get("TITLE");
        String msgtype = "link";
        String dl = "单位客户";
        String lx = "移动端地址";
        String ywmc = "XGDWKH";
        String url = bO_CRM_APPRIGHTService.getUrLOfTodoTask(dl,lx,ywmc);//ERP中配置的基础数据 采购单团
        url = url+"bindid="+bindid+"&userid="+createUser+"&taskid="+taskid;
        result = tppService.sendLinkMessage("admin", createUser, receiverType, title, description, url, msgtype);
        return result;
    }

    @Override
    public List<String> sendMsgToNexter(Integer bindid, Integer taskid, List<String> users) throws Exception {
        int check = this.commonDao.checkSelectedSendMsg(taskid);
        if(check==1){
            String title = "修改单位客户";
            String description ="";
            description = (String)this.dataservice.getTaskById(taskid).get("TITLE");
            String msgtype = "link";
            String dl = "单位客户";
            String lx = "移动端地址";
            String ywmc = "XGDWKH";
            String url = bO_CRM_APPRIGHTService.getUrLOfTodoTask(dl,lx,ywmc);
            String receiverType = "single";
            List<String> list = new ArrayList<>();
            for (String user : users) {
                url = url+"bindid="+bindid+"&userid="+user+"&taskid="+taskid;
                String result = tppService.sendLinkMessage("admin", user, receiverType, title, description, url, msgtype);
                list.add(result);
            }
            return list;
        }
        return null;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = false, propagation = Propagation.REQUIRED)
    public Map<String, Object> repulseWorkFlow(Map<String, Object> condition) throws Exception {
        String currUser = (String)condition.get("curruser");
        int bindid = (Integer)condition.get("bindid");
        int taskid = (Integer)condition.get("taskid");
        String tasktitle =  this.orgTaskTitle();
        String spyj = (String)condition.get("opinion");
        Map<String, Object> result = this.rwfservice.RepulseTask3(bindid, currUser, taskid, tasktitle, 1, "不同意", spyj);
        return result;
    }

    @Override
    public List<Map<String, Object>> fetchNextHandleUsers(Integer bindid, Integer currStepNo, String khbh) throws Exception {
        Map<String, Object> params = new HashMap<>();
        Integer roleid = fetchRoleId(currStepNo);
        params.put("bindid", bindid);
        params.put("roleid", roleid);
        params.put("customerNo", khbh);
        List<Map<String, Object>> users = null;
        if(roleid == 188599){
            users = (List<Map<String, Object>>) mainSiteServiceFeign.selectServiceRun("customRuleAccountDirector",params).get("data");
        }else{
            users = (List<Map<String, Object>>) mainSiteServiceFeign.selectServiceRun("findRoleForInitiatorDepartmentByCreator",params).get("data");
        }
        return users;
    }

    @Override
    public byte[] downloadFile(Map<String, Object> params) throws Exception {
        Integer tabletype = (Integer)params.get("TABLETYPE");
        String tablename = "";
        String fieldname = "";
        if(tabletype == 1){
            tablename = "BO_CRM_CLIENTBUS_P";
            fieldname = "FJ";
        }else if(tabletype == 2){
            tablename = "BO_CRM_CLIENTBUS_REG";
            fieldname = "FJ";
        }else if(tabletype == 3){
            tablename = "BO_CRM_INDCLIENTBUS_P";
            fieldname = "TX";
        }else if(tabletype == 4){
            tablename = "BO_CRM_INDCLIENTBUS_P";
            fieldname = "MP";
        }
        String fieldUUID = this.fetchTableFieldUUID(tablename, fieldname);
        Integer pkid = (Integer)params.get("PKID");
        String fileName = (String)params.get("FILENAME");
        byte[] bytes = this.boservice.downloadFileByField(pkid.intValue(), fieldUUID, URLEncoder.encode(fileName, "UTF-8"));
        return bytes;
    }

    @Override
    public BO_CRM_CLIENTBUS_P fetchFiles(Integer bindid) throws Exception {
        return this.clientbuspdao.selectBybindid(bindid.toString());
    }

    @Override
    public Map<String, Object> todo(Map<String, Object> params) throws Exception {
        Map<String, Object> result = new HashMap<>();
        Integer bindid = (Integer)params.get("bindid");
        Integer taskid = (Integer)params.get("taskid");
        // 查询单位客户信息主表(BO_CRM_CLIENTBUS_P)
        BO_CRM_CLIENTBUS_P clientbuspmodel = this.clientbuspdao.selectBybindid(bindid.toString());
        result.put("bocrmclientbusp", clientbuspmodel);
        String khbh = clientbuspmodel.getKhbh();

        // 查询单位客户信息-公司合规(BO_CRM_CLIENTBUS_REG)
        List<BO_CRM_CLIENTBUS_REG> clientbusregs = this.clientbusregdao.selectListByBindid(bindid);
        result.put("bocrmclientbusregs", clientbusregs);

        // 查询单位客户信息-历史事件(BO_CRM_CLIENTBUS_EVE)
        List<BO_CRM_CLIENTBUS_EVE> clientbuseves = this.clientbusevedao.selectListByBindid(bindid);
        result.put("bocrmclientbuseves", clientbuseves);

        // 查询原单位客户信息-公司合规(BO_CRM_CLIENTBUS_OREG)
        List<BoCrmClientbusOreg> clientbusoregs = this.clientbusoregdao.selectByBindid(bindid);
        result.put("bocrmclientbusoregs", clientbusoregs);

        // 查询原单位客户信息-历史事件(BO_CRM_CLIENTBUS_OEVE)
        List<BoCrmClientbusOeve> clientbusOeves = this.clientbusoevedao.selectByBindid(bindid);
        result.put("bocrmclientbusoeves", clientbusOeves);

        // 查看该任务是待办还是已办
        int status = this.customService.getTaskIsHandle(taskid);
        result.put("status", status);
        // 查看该任务节点号
        int stepNo = this.rwfservice.getStpeno(taskid);
        result.put("nodeno", stepNo);
        // 获取下一个节点的审批人
        List<Map<String, Object>> auditors = this.fetchNextHandleUsers(bindid, stepNo + 1, khbh);
        result.put("auditors", auditors);
        // 获取流程的创建人
        Map<String, Object> createUser = this.customService.getWFCreateUserByBindid(bindid);
        result.put("createuser", createUser);
        //当前节点是否配置为抢签
        Integer sfqq = customService.getTaskMethod(taskid.intValue());
        //当前流程代办任务数量
        Integer taskCount = customService.getDaibanTaskCount(bindid.intValue());
        boolean isReceiveHandle = false;
        if(sfqq==2&&taskCount>1){
            isReceiveHandle =  true;
        }
        result.put("needreceivehandle", isReceiveHandle);

        return result;
    }

    private String fetchTableFieldUUID(String tableName, String fieldName) throws Exception {
        Map<String, String> condition = new HashMap<>();
        condition.put("tableName", tableName);
        condition.put("fieldName", fieldName);
        return this.clientbuspdao.fetchTableFieldUUID(condition);
    }

    private Integer fetchRoleId(Integer currStepNo){
        Integer roleid = 0;
        switch(currStepNo){
            case 2 : roleid = 2992667; break;  // 第2节点：子公司客户管理专员
            case 3 : roleid = 188602; break;   // 第3节点：集团客户管理专员
            case 4 : roleid = 188597; break;   // 第4节点：部门经理/总监
            case 5 : roleid = 228689; break;   // 第5节点：分管领导
            case 6 : roleid = 188599; break;   // 第6节点：大客户总监
        }
        return roleid;
    }

    private BoCrmClientLog orgClientLog(Map<String, Object> infomap){
        BoCrmClientLog log = new BoCrmClientLog();
        log.setBindid((Integer)infomap.get("BINDID"));
        log.setBmid((BigDecimal)infomap.get("BMID"));
        log.setBmmc((String)infomap.get("BMMC"));
        log.setCreatedate(new Date());
        log.setCreateuser((String)infomap.get("USERID"));
        log.setCzrxm((String)infomap.get("USERID"));
        log.setCzrzh((String)infomap.get("USERNAME"));
        log.setGsdm((String)infomap.get("GSDM"));
        log.setGsid((BigDecimal)infomap.get("GSID"));
        log.setGsmc((String)infomap.get("GSMC"));
        log.setIsend((Integer)infomap.get("ISEND"));
        log.setKhbh((String)infomap.get("KHBH"));
        log.setKhmc((String)infomap.get("KHMC"));
        log.setOrgno("1");
        log.setUpdatedate(new Date());
        log.setUpdateuser((String)infomap.get("USERID"));
        log.setWhlxdm((String)infomap.get("WHLXDM"));
        log.setWhsj(new Date());
        log.setWorkflowid((Integer)infomap.get("WORKFLOWID"));
        log.setWorkflowstepid((Integer)infomap.get("WORKFLOWSTEPID"));
        return log;
    }

    private BO_CRM_CLIENT_P orgclientp(BO_CRM_CLIENTBUS_P clientbusp){
        Integer bindid = Integer.parseInt(clientbusp.getDaid());
        BO_CRM_CLIENT_P clientp = this.clientpdao.selectById(bindid);
        clientp.setZhgxsj(new Date());
        clientp.setKhmc(clientbusp.getKhmc());
        clientp.setKhjc(clientbusp.getKhjc());
        clientp.setKhzt(clientbusp.getKhzt());
        clientp.setDwxz(clientbusp.getDwxzmc());
        clientp.setDwxzdm(clientbusp.getDwxzdm());
        clientp.setHydm(clientbusp.getHydm());
        clientp.setHyxldm(clientbusp.getHyxldm());
        clientp.setClrq(clientbusp.getClrq());
        clientp.setSfss(clientbusp.getSfss());
        clientp.setHydwdm(clientbusp.getHydwdm());
        clientp.setHydw("");
        clientp.setZczb(clientbusp.getZczb());
        clientp.setDwgmdm(clientbusp.getDwgmdm());
        clientp.setDwgm("");
        clientp.setGfwz(clientbusp.getGfwz());
        clientp.setScfe(clientbusp.getScfe());
        clientp.setJyfw(clientbusp.getJyfw());
        clientp.setZjdh(clientbusp.getZjdh());
        clientp.setTzcdz(clientbusp.getTzcdz());
        clientp.setGj(clientbusp.getGj());
        clientp.setSf(clientbusp.getSf());
        clientp.setCs(clientbusp.getCs());
        clientp.setCsdm(clientbusp.getCsdm());
        clientp.setCz(clientbusp.getCz());
        clientp.setBgdz(clientbusp.getBgdz());
        clientp.setZyly(clientbusp.getZyly());
        clientp.setSfysjdw(clientbusp.getSfysjdw());
        clientp.setNsrzgdm(clientbusp.getNsrzgdm());
        clientp.setNsrzg("");
        clientp.setNsrsbh(clientbusp.getNsrsbh());
        clientp.setBz(clientbusp.getBz());
        return clientp;
    }

    private String orgTaskTitle(){
        return "修改单位客户信息";
    }
}
