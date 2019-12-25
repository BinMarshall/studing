package cn.bravolinks.erp.crm.server.service.xgdwkh.impl;

import cn.bravolinks.erp.crm.server.dao.COMMON_BO_CRM_APPRIGHT_Dao;
import cn.bravolinks.erp.crm.server.dao.xzkh.*;
import cn.bravolinks.erp.crm.server.feign.*;
import cn.bravolinks.erp.crm.server.model.xzkh.*;
import cn.bravolinks.erp.crm.server.service.BO_CRM_APPRIGHTService;
import cn.bravolinks.erp.crm.server.service.xgdwkh.EditEnterpriseCustomerPersonService;
import cn.bravolinks.erp.crm.server.service.xgdwkh.EditEnterpriseCustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHAL
 * 创建时间：2018/9/10 16:35
 */
@Service
public class EditEnterpriseCustomerPersonServiceImpl implements EditEnterpriseCustomerPersonService {
    @Resource
    RestWorkFlowService rwfservice;
    @Resource
    MainSiteServiceFeign mainSiteServiceFeign;
    @Resource
    BoCrmIndclientbusPDao indclientbuspdao;
    @Resource
    BoCrmIndclientbusYeeDao indclientbusyeedao;
    @Resource
    BoCrmIndclientbusYlmDao indclientbusylmdao;
    @Resource
    BoCrmIndclientbusYtrDao indclientbusytrdao;
    @Resource
    BoCrmIndclientbusYhbDao indclientbusyhbdao;
    @Resource
    BO_CRM_INDCLIENTBUS_EVE_DAO indclientbusevedao;
    @Resource
    BO_CRM_INDCLIENTBUS_TR_DAO indclientbustrdao;
    @Resource
    BoCrmIndclientbusLmDao indclientbusLmdao;
    @Resource
    BO_CRM_INDCLIENTBUS_HOB_DAO indclientbushobdao;
    @Resource
    BO_CRM_INDCLIENT_P_DAO indclientpdao;
    @Resource
    COMMON_BO_CRM_APPRIGHT_Dao commonDao;
    @Resource
    BO_CRM_INDCLIENT_TR_DAO indclienttrdao;
    @Resource
    BO_CRM_INDCLIENT_LM_DAO indclientlmdao;
    @Resource
    BO_CRM_INDCLIENT_HOB_DAO indclienthobdao;
    @Resource
    BO_CRM_INDCLIENT_EVE_DAO indclientevedao;
    @Resource
    BoCrmClientLogDao clientlogdao;
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
    @Resource
    EditEnterpriseCustomerService eecservice;

    @Override
    @Transactional
    public Map<String, Object> handleWorkFlow(Map<String, Object> params) throws Exception {
        Integer bindid = (Integer)params.get("bindid");
        Integer taskid = (Integer)params.get("taskid");
        String loginName = (String)params.get("curruser");
        String taskTitle = this.orgTaskTitle();
        String isagree = "同意";
        String opinion = (String)params.get("opinion");

        this.handleBizLogic(bindid, taskid);

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("bindid", bindid);
        map.add("loginname",loginName);
        map.add("taskid", taskid);
        map.add("taskTitle", taskTitle);
        map.add("isagree", isagree);
        map.add("opinion", opinion);
        map.add("nextStepno", null);
        map.add("isCloseProcess", 1);
        this.sendMsgToCreater(bindid, taskid);
        Map<String, Object> res = this.rwfservice.handleTask(map);

        return res;
    }

    private boolean handleBizLogic(Integer bindid, Integer taskid) throws Exception {
        //根据流程实例ID和表名 获取数据信息
        BoCrmIndclientbusP p = this.indclientbuspdao.selectBybindid(bindid);
        //工作履历信息
        List<BO_CRM_INDCLIENTBUS_TR> bustrs = this.indclientbustrdao.selectListByBindid(bindid);
        //相关联系人信息
        List<BoCrmIndclientbusLm> buslms = this.indclientbusLmdao.selectByBindid(bindid);
        //兴趣爱好信息
        List<BO_CRM_INDCLIENTBUS_HOB> bushobs = this.indclientbushobdao.selectByBindid(bindid);
        //历史事件信息
        List<BO_CRM_INDCLIENTBUS_EVE> buseves = this.indclientbusevedao.selectListByBindid(bindid);

        String khbh = (String)p.getKhbh();      //客户名称
        String createUser = (String)p.getCreateuser();
        int daid =Integer.parseInt((String)p.getDaid());
        p.setBindid(daid);

        if(daid != 0){
            BO_CRM_INDCLIENT_P clientp = this.indclientpdao.selectByBindid(daid);
            this.indclientpdao.updateByPrimaryKeySelective(this.fromBusP(p, clientp.getId()));
            //删除档案中对应的子表信息
            this.indclienttrdao.deleteByBindid(bindid);
            this.indclientlmdao.deleteByBindid(bindid);
            this.indclienthobdao.deleteByBindid(bindid);
            this.indclientevedao.deleteByBindid(bindid);
        }
        this.indclienttrdao.deleteByBindid(daid);
        if(bustrs != null && bustrs.size()>0){
            for(BO_CRM_INDCLIENTBUS_TR bustr : bustrs){
                bustr.setBindid(daid);
                this.indclienttrdao.insertSelective(bustr);
            }
        }
        this.indclientlmdao.deleteByBindid(daid);
        if(buslms != null && buslms.size()>0){
            for(BoCrmIndclientbusLm buslm : buslms){
                buslm.setBindid(daid);
                this.indclientlmdao.insertSelectiveFromBusLm(buslm);
            }
        }
        this.indclienthobdao.deleteByBindid(daid);
        if(bushobs != null && bushobs.size()>0){
            for(BO_CRM_INDCLIENTBUS_HOB bushob : bushobs){
                bushob.setBindid(daid);
                this.indclienthobdao.insertSelective(bushob);
            }
        }
        this.indclientevedao.deleteByBindid(daid);
        if(buseves != null && buseves.size()>0){
            for(BO_CRM_INDCLIENTBUS_EVE buseve : buseves){
                buseve.setBindid(daid);
                this.indclientevedao.insertSelective(buseve);
            }
        }

        // 生成维护记录
        Map<String, Object> infomap = this.clientlogdao.selectInfoByUser(createUser);
        infomap.put("KHMC", p.getXm());
        infomap.put("KHBH", p.getLxrbh());
        infomap.put("WHLXDM", p.getWhlxdm());
        infomap.put("BINDID", daid);
        infomap.put("ISEND", p.getIsend());
        infomap.put("WORKFLOWID", p.getWorkflowid());
        infomap.put("WORKFLOWSTEPID", p.getWorkflowstepid());
        /*this.clientlogdao.insertSelective(orgClientLog(infomap));*/

        //单位客户联系人修改后，如果有个人客户联系人来自本单位客户，则关联修改，同时加入修改的操作日志 --lipengliang add 2017-6-8
        //如果当前修改的单位客户也是个人客户，需要更新个人客户信息

        String lxrbh = "";
        String xm = "";
        Integer lxrbindid = 0;
        BO_CRM_INDCLIENT_P lxrclientp = this.indclientpdao.selectByLxrBindid(daid);
        if(lxrclientp != null){
            String xbdm = (String)p.getXbdm();
            String yddh =(String)p.getYddh();
            String email = (String)p.getEmail();
            String jsdm = (String)p.getJsdm();
            lxrclientp.setXbdm(xbdm);
            lxrclientp.setYddh(yddh);
            lxrclientp.setEmail(email);
            lxrclientp.setJsdm(jsdm);
            this.indclientpdao.updateByPrimaryKey(lxrclientp);

            lxrbh = lxrclientp.getLxrbh();
            xm = lxrclientp.getXm();
            lxrbindid = lxrclientp.getBindid();
        }
        SimpleDateFormat faDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化时间
        Hashtable doLog = new Hashtable();

        BigDecimal gsid = (BigDecimal)infomap.get("GSID");
        String gsdm = (String)infomap.get("GSDM");
        String gsmc = (String)infomap.get("GSMC");
        BigDecimal bmid = (BigDecimal)infomap.get("BMID");
        String bmmc = (String)infomap.get("BMMC");
        doLog.put("BMID", bmid);
        doLog.put("BMMC", bmmc);
        doLog.put("USERID", (String)infomap.get("USERID"));
        doLog.put("CREATEUSER",(String)infomap.get("USERNAME"));
        doLog.put("CZRZH",(String)infomap.get("USERID"));
        doLog.put("CZRXM", (String)infomap.get("USERNAME"));
        doLog.put("GSID", gsid);
        doLog.put("GSDM", gsdm);
        doLog.put("GSMC", gsmc);
        doLog.put("ISEND", infomap.get("ISEND"));
        doLog.put("KHBH", infomap.get("KHBH"));
        doLog.put("KHMC", infomap.get("KHMC"));
        doLog.put("UPDATEUSER", (String)infomap.get("USERNAME"));
        doLog.put("WHLXDM", (String)infomap.get("WHLXDM"));
        doLog.put("WHSJ", faDateFormat.format(new Date()));
        doLog.put("BINDID",daid);
        doLog.put("WORKFLOWID", infomap.get("WORKFLOWID"));
        doLog.put("WORKFLOWSTEPID", infomap.get("WORKFLOWSTEPID"));
        this.clientlogdao.insertSelective(orgClientLog(doLog));

        //更新客户时间
        Map<String, Object> con = new HashMap<>();
        con.put("latestdate", new Date());
        con.put("khbh", khbh);
        this.indclientpdao.updateZHGXSJByKHBH(con);

        return true;
    }

    @Override
    public String sendMsgToCreater(Integer bindid, Integer taskid) throws Exception {
        String result = "";
        Map<String, Object> taskinfo = this.dataservice.getTaskById(taskid);
        BoCrmIndclientbusP clientbusp = this.indclientbuspdao.selectBybindid(bindid);
        String createUser = (String)clientbusp.getCreateuser();

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
    public List<String> sendMsgToNexter(Integer bindid, Integer taskid, List<Map<String, Object>> users) throws Exception {
        int check = this.commonDao.checkSelectedSendMsg(taskid);
        if(check==1){
            String title = "修改单位客户";
            String description ="";
            description = (String)this.dataservice.getTaskById(taskid).get("TITLE");
            String msgtype = "link";
            String dl = "单位客户";
            String lx = "移动端地址";
            String ywmc = "XGDWKHLXR";
            String url = bO_CRM_APPRIGHTService.getUrLOfTodoTask(dl,lx,ywmc);
            String receiverType = "single";
            List<String> list = new ArrayList<>();
            for (Map<String, Object> jsr : users) {
                url = url+"bindid="+bindid+"&userid="+jsr.get("USERID")+"&taskid="+taskid;
                String result = tppService.sendLinkMessage("admin", (String)jsr.get("USERID"), receiverType, title, description, url, msgtype);
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
    public Map<String, Object> todo(Map<String, Object> params) throws Exception {
        Map<String, Object> result = new HashMap<>();
        Integer bindid = (Integer)params.get("bindid");
        Integer taskid = (Integer)params.get("taskid");

        // 个人客户信息主表(BO_CRM_INDCLIENTBUS_P)
        BoCrmIndclientbusP indClientbuspmodel = this.indclientbuspdao.selectBybindid(bindid);
        result.put("bocrmindclientbusp", indClientbuspmodel);
        String khbh = (String)indClientbuspmodel.getKhbh();

        // 个人客户信息-历史事件(BO_CRM_INDCLIENTBUS_EVE)
        List<BO_CRM_INDCLIENTBUS_EVE> indclientbuseves = this.indclientbusevedao.selectListByBindid(bindid);
        result.put("bocrmindclientbuseves", indclientbuseves);

        // 个人客户信息-工作履历(BO_CRM_INDCLIENTBUS_TR)
        List<BO_CRM_INDCLIENTBUS_TR> indclientbustrs = this.indclientbustrdao.selectListByBindid(bindid);
        result.put("bocrmindclientbustrs", indclientbustrs);

        // 个人客户信息-相关联系人(BO_CRM_INDCLIENTBUS_LM)
        List<BoCrmIndclientbusLm> indclientbuslms = this.indclientbusLmdao.selectByBindid(bindid);
        result.put("bocrmindclientbuslms", indclientbuslms);

        // 个人客户信息-个人偏好(BO_CRM_INDCLIENTBUS_HOB)
        List<BO_CRM_INDCLIENTBUS_HOB> indclientbushobs = this.indclientbushobdao.selectByBindid(bindid);
        result.put("bocrmindclientbushobs", indclientbushobs);

        // 原个人客户信息-历史事件(BO_CRM_INDCLIENTBUS_YEE)
        List<BoCrmIndclientbusYee> indclientbusyees = this.indclientbusyeedao.selectByBindid(bindid);
        result.put("bocrmindclientbusyees", indclientbusyees);

        // 原个人客户信息-个人偏好(BO_CRM_INDCLIENTBUS_YHB)
        List<BoCrmIndclientbusYhb> indclientbusyhbs = this.indclientbusyhbdao.selectByBindid(bindid);
        result.put("bocrmindclientbusyhbs", indclientbusyhbs);

        // 原个人客户信息-工作履历(BO_CRM_INDCLIENTBUS_YTR)
        List<BoCrmIndclientbusYtr> indclientbusytrs = this.indclientbusytrdao.selectByBindid(bindid);
        result.put("bocrmindclientbusytrs", indclientbusytrs);

        // 原个人客户信息-相关联系人(BO_CRM_INDCLIENTBUS_YLM)
        List<BoCrmIndclientbusYlm> indclientbusylms = this.indclientbusylmdao.selectByBindid(bindid);
        result.put("bocrmindclientbusylms", indclientbusylms);

        // 查看该任务是待办还是已办
        int status = this.customService.getTaskIsHandle(taskid);
        result.put("status", status);
        // 查看该任务节点号
        int stepNo = this.rwfservice.getStpeno(taskid);
        result.put("nodeno", stepNo);
        // 获取下一个节点的审批人
        List<Map<String, Object>> auditors = this.eecservice.fetchNextHandleUsers(bindid, stepNo + 1, khbh);
        result.put("auditors", auditors);
        // 获取流程的创建人
        Map<String, Object> createUser = this.customService.getWFCreateUserByBindid(bindid);
        result.put("createuser", createUser);

        return result;
    }

    private Integer fetchRoleId(Integer currStepNo){
        Integer roleid = 0;
        switch(currStepNo){
            case 2 : roleid = 188597; break;   // 第4节点：部门经理/总监
        }
        return roleid;
    }

    private String orgTaskTitle(){
        return "修改单位客户联系人信息";
    }

    private BO_CRM_INDCLIENT_P fromBusP(BoCrmIndclientbusP busp, Integer id){
        BO_CRM_INDCLIENT_P top = new BO_CRM_INDCLIENT_P();
        top.setId(id);
        top.setOrgno((String)busp.getOrgno());
        top.setBindid(busp.getBindid());
        top.setCreatedate(busp.getCreatedate());
        top.setCreateuser((String)busp.getCreateuser());
        top.setUpdatedate(busp.getUpdatedate());
        top.setUpdateuser((String)busp.getUpdateuser());
        top.setWorkflowid(busp.getWorkflowid());
        top.setWorkflowstepid(busp.getWorkflowstepid());
        top.setIsend(busp.getIsend());
        top.setLx((String)busp.getLx());
        top.setKhbh((String)busp.getKhbh());
        top.setBmxzdm((String)busp.getBmxzdm());
        top.setXm((String)busp.getXm());
        top.setYw((String)busp.getYw());
        top.setGjdm((String)busp.getGjdm());
        top.setMzdm((String)busp.getMzdm());
        top.setXbdm((String)busp.getXbdm());
        top.setCsrq(busp.getCsrq());
        top.setHyzkdm((String)busp.getHyzkdm());
        top.setZjxydm((String)busp.getZjxydm());
        top.setZzmmdm((String)busp.getZzmmdm());
        top.setQtshjs((String)busp.getQtshjs());
        top.setIfdam((String)busp.getIfdam());
        top.setCdggjdq((String)busp.getCdggjdq());
        top.setRyztdm((String)busp.getRyztdm());
        top.setJsdm((String)busp.getJsdm());
        top.setZwmc((String)busp.getZwmc());
        top.setXrzdwbh((String)busp.getXrzdwbh());
        top.setXrzdw((String)busp.getXrzdw());
        top.setDrzw((String)busp.getDrzw());
        top.setDwdh((String)busp.getDwdh());
        top.setYddh((String)busp.getYddh());
        top.setEmail((String)busp.getEmail());
        top.setJcfg((String)busp.getJcfg());
        top.setSjgj((String)busp.getSjgj());
        top.setZjlxdm((String)busp.getZjlxdm());
        top.setZjh((String)busp.getZjh());
        top.setSjlydm((String)busp.getSjlydm());
        top.setBmndylxrdm((String)busp.getBmndylxrdm());
        top.setBz((String)busp.getBz());
        top.setFj((String)busp.getFj());
        top.setTx((String)busp.getTx());
        top.setMp((String)busp.getMp());
        top.setLyxqys(busp.getLyxqys() == null ? null : busp.getLyxqys().doubleValue());
        top.setKhjlzh((String)busp.getKhjlzh());
        top.setLxrbh((String)busp.getLxrbh());
        top.setBmbh((String)busp.getBmbh());
        top.setKhjlssbm((String)busp.getKhjlssbm());
        top.setLxrbindid((String)busp.getLxrbindid());
        top.setKhdj((String)busp.getKhdj());
        return top;
    }

    private BoCrmClientLog orgClientLog(Map<String, Object> infomap) throws Exception{
        BoCrmClientLog log = new BoCrmClientLog();
        log.setBindid((Integer)infomap.get("BINDID"));
        log.setBmid(infomap.get("BMID") == null ? 0 : ((BigDecimal)infomap.get("BMID")).intValue());
        log.setBmmc((String)infomap.get("BMMC"));
        log.setCreatedate(new Date());
        log.setCreateuser((String)infomap.get("USERID"));
        log.setCzrxm((String)infomap.get("CZRXM"));
        log.setCzrzh((String)infomap.get("USERID"));
        log.setGsdm((String)infomap.get("GSDM"));
        log.setGsid(infomap.get("GSID") == null ? 0 : ((BigDecimal)infomap.get("GSID")).intValue());
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
}
