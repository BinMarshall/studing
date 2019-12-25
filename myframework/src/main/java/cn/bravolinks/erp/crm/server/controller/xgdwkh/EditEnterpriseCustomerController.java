package cn.bravolinks.erp.crm.server.controller.xgdwkh;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_P;
import cn.bravolinks.erp.crm.server.service.BO_CRM_APPRIGHTService;
import cn.bravolinks.erp.crm.server.service.xgdwkh.EditEnterpriseCustomerService;
import cn.bravolinks.erp.crm.server.util.OutputDatas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：修改单位客户
 * 作者姓名：Hao Bin - MARSHAL
 * 创建时间：2018/9/10 15:56
 */
@RestController
@RequestMapping("/editecserver")
public class EditEnterpriseCustomerController {
    private Logger logger = LoggerFactory.getLogger(EditEnterpriseCustomerController.class);

    @Resource
    EditEnterpriseCustomerService eecservice;
    @Resource
    BO_CRM_APPRIGHTService commonService;

    @RequestMapping("/todo")
    public String todo(Integer bindid, Integer taskid){
        OutputDatas output = null;
        try{
            Map<String, Object> params = new HashMap<>();
            params.put("bindid", bindid);
            params.put("taskid", taskid);
            Map<String, Object> result = this.eecservice.todo(params);
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
            Map<String, Object> result = this.eecservice.handleWorkFlow(params);
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

    /** 接收办理 */
    @RequestMapping("/receiveHandle")
    public String receiveHandle(Integer bindid, Integer taskid){
        OutputDatas output = null;
        try{
            Map<String, Object> params = new HashMap<>();
            params.put("bindid", bindid);
            params.put("taskid", taskid);
            Map<String, Object> result = this.eecservice.receiveHandle(params);
            if(result == null){
                throw new Exception("接收办理失败");
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
            Map<String, Object> result = this.eecservice.repulseWorkFlow(params);
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

    @RequestMapping("/downloadFile")
    public ResponseEntity<byte[]> download(Integer pkid, String fileName, Integer tabletype){
        OutputDatas output = null;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("FILENAME", fileName);
            params.put("PKID", pkid);
            params.put("TABLETYPE", tabletype);
            byte[] byteArray = this.eecservice.downloadFile(params);

            //添加响应头
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName,"UTF-8"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //bodys响应体，headers响应头，status响应状态码
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(byteArray, headers, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            output = new OutputDatas(OutputDatas.RESULT_FAILURE, e.getMessage());
            logger.error("调用/filehandling/downloadUsualFiles接口发生错误：" + e.getMessage(),e);
            e.printStackTrace();
        }
        return null;
    }

    /*@RequestMapping("/fetchNextHandleUsers")
    public String fetchNextHandleUsers(Integer bindid, Integer nodeNo){
        OutputDatas output = null;
        try{
            List<Map<String, Object>> result = this.eecservice.fetchNextHandleUsers(bindid, nodeNo);
            if(result == null){
                throw new Exception("获取下一节点审批人失败");
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
    }*/

    @RequestMapping("/tracing")
    public String tracing(Integer bindid){
        OutputDatas output = null;
        try{
            Map<String, Object> condition = new HashMap<>();
            condition.put("bindid", bindid);
            List<Map<String, Object>> result = this.commonService.fetchTracingDatas(condition);
            if(result == null){
                throw new Exception("获取下一节点审批人失败");
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
}
