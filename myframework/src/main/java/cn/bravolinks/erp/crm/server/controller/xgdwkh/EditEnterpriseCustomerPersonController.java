package cn.bravolinks.erp.crm.server.controller.xgdwkh;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_P;
import cn.bravolinks.erp.crm.server.service.BO_CRM_APPRIGHTService;
import cn.bravolinks.erp.crm.server.service.xgdwkh.EditEnterpriseCustomerPersonService;
import cn.bravolinks.erp.crm.server.service.xgdwkh.EditEnterpriseCustomerService;
import cn.bravolinks.erp.crm.server.util.OutputDatas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：修改单位客户联系人
 * 作者姓名：Hao Bin - MARSHAL
 * 创建时间：2018/9/10 15:56
 */
@RestController
@RequestMapping("/editecpserver")
public class EditEnterpriseCustomerPersonController {
    private Logger logger = LoggerFactory.getLogger(EditEnterpriseCustomerPersonController.class);

    @Resource
    EditEnterpriseCustomerPersonService eecpservice;
    @Resource
    BO_CRM_APPRIGHTService commonService;

    @RequestMapping("/todo")
    public String todo(Integer bindid, Integer taskid){
        OutputDatas output = null;
        try{
            Map<String, Object> params = new HashMap<>();
            params.put("bindid", bindid);
            params.put("taskid", taskid);
            Map<String, Object> result = this.eecpservice.todo(params);
            if(result == null){
                throw new Exception("办理失败");
            }else{
                output = new OutputDatas(OutputDatas.RESULT_SUCCESS);
                output.setDatas(result);
            }
        }catch(Exception e){
            output = new OutputDatas(OutputDatas.RESULT_FAILURE);
            output.setErrinfo(e.getMessage() == null ? e.toString() : e.getMessage());
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }

        return output.toString();
    }

    @RequestMapping("/handle")
    public String handle(Integer bindid, Integer taskid, String userid, String opinion){
        OutputDatas output = null;
        try{
            Map<String, Object> params = new HashMap<>();
            params.put("bindid", bindid);
            params.put("taskid", taskid);
            params.put("curruser", userid);
            params.put("opinion", opinion);
            Map<String, Object> result = this.eecpservice.handleWorkFlow(params);
            if(result == null){
                throw new Exception("办理失败");
            }else{
                if((boolean)result.get("isok")){
                    output = new OutputDatas(OutputDatas.RESULT_SUCCESS);
                    output.setDatas(result);
                }else{
                    throw new Exception((String)result.get("message"));
                }
            }
        }catch(Exception e){
            output = new OutputDatas(OutputDatas.RESULT_FAILURE);
            output.setErrinfo(e.getMessage() == null ? e.toString() : e.getMessage());
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }

        return output.toString();
    }

    @RequestMapping("/repulse")
    public String repulse(Integer bindid, Integer taskid, String userid, String opinion){
        OutputDatas output = null;
        try{
            Map<String, Object> params = new HashMap<>();
            params.put("bindid", bindid);
            params.put("taskid", taskid);
            params.put("curruser", userid);
            params.put("opinion", opinion);
            Map<String, Object> result = this.eecpservice.repulseWorkFlow(params);
            if(result == null){
                throw new Exception("打回失败");
            }else{
                if((boolean)result.get("isok")){
                    output = new OutputDatas(OutputDatas.RESULT_SUCCESS);
                    output.setDatas(result);
                }else{
                    throw new Exception((String)result.get("message"));
                }
            }
        }catch(Exception e){
            output = new OutputDatas(OutputDatas.RESULT_FAILURE);
            output.setErrinfo(e.getMessage() == null ? e.toString() : e.getMessage());
            logger.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return output.toString();
    }
}
