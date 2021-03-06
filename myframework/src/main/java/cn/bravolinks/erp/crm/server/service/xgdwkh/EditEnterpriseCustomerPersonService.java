package cn.bravolinks.erp.crm.server.service.xgdwkh;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_P;

import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 * 作者姓名：Hao Bin - MARSHAL
 * 创建时间：2018/9/10 16:35
 */
public interface EditEnterpriseCustomerPersonService {
    /** 流程节点办理 */
    public Map<String, Object> handleWorkFlow(Map<String, Object> params) throws Exception;
    /** 最后节点办理完成后，给流程起草者发通知 */
    public String sendMsgToCreater(Integer bindid, Integer taskid) throws Exception;
    /** 如果下一个节点勾选了“发送短信”，则给下一节点办理人发通知 */
    public List<String> sendMsgToNexter(Integer bindid, Integer taskid, List<Map<String, Object>> users) throws Exception;
    /** 流程打回到第1节点 */
    public Map<String, Object> repulseWorkFlow(Map<String, Object> params) throws Exception;
    /** 获取待办/已办数据 */
    public Map<String, Object> todo(Map<String, Object> params) throws Exception;
}
