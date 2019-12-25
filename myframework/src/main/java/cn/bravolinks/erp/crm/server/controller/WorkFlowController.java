package cn.bravolinks.erp.crm.server.controller;

import cn.bravolinks.erp.crm.server.feign.IMService;
import cn.bravolinks.erp.crm.server.feign.PageService;
import cn.bravolinks.erp.crm.server.feign.RestWorkFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by yanqin on 2017/5/25.
 */
@RestController
@RequestMapping("/WorkFlow")
public class WorkFlowController extends BaseController{
    @Autowired
    private RestWorkFlowService restWorkFlowService;
    @Autowired
    private IMService imService;
    @Autowired
    private PageService pageService;

    /**
     * 获取节点号
     * @param taskid
     * @return
     */
    @RequestMapping("/getStepno")
    public Integer getStpeno(Integer taskid){
        return restWorkFlowService.getStpeno(taskid);
    }

    /**
     * 判断是不是该节点审批人
     * @return
     */
    @RequestMapping("/isTaskShenpiren")
    public Boolean isTaskShenpiren(Integer taskid,String userid){
        return Boolean.valueOf(restWorkFlowService.isTaskShenpiren(taskid,userid));
    }

    /**
     * 获取留言
     * @param bindid
     * @return
     */
    @RequestMapping("getOpinion")
    public List<Map<String,Object>> getOpinion(Integer bindid){
        return restWorkFlowService.getMessageOpinion(bindid);
    }

    /**
     * 发送邮件
     * @param from
     * @param to
     * @param title
     * @param content
     * @return
     */
    @RequestMapping("/sendMail")
    public String sendMail(String from,String to,String title,String content){
        return imService.sendMail(from,to,title,content);
    }
    /**
	 * 查询流程实例所有任务
	 * @param bindid
	 * @return
	 */
	@RequestMapping(value = "selectWfAllTask",method = RequestMethod.GET)
	List<Map<String,Object>> selectWfAllTask(@RequestParam("bindid") Integer bindid){
		return pageService.selectWfAllTask(bindid);
	};
}
