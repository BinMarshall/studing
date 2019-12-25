package cn.bravolinks.erp.crm.server.dao.xzkh;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.xzkh.WF_TASK_LOG;



@Mapper
public interface WF_TA_LOG_DAO {
    /**
     *  根据taskid合bindid 查询任务状态
     *
     * @param taskid
     */
	String getStatusByTaskIdAndBindid(Map m);
	
	List<WF_TASK_LOG> getWF_TASK_LOG(String bindid);
	
	List<WF_TASK_LOG> getWF_TASK_LOGByBindidAndTarget(Map m);
}