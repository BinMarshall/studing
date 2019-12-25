package cn.bravolinks.erp.crm.server.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.WF_TASK;

@Mapper
public interface WF_TASK_DAO {
    /**
     *  根据taskid合bindid 查询任务状态
     *
     * @param taskid
     */
	String getStatusByTaskIdAndBindid(Map m);
	
	List<WF_TASK> getWF_TASK(String bindid);
	
	List<WF_TASK> getWF_TASKByBindidAndTarget(Map m);

	String getYbyjByTaskidAndBindid(Map<String, String> params);

	String getTaskTitleByTaskid(Integer taskid);

	WF_TASK getTaskMessageByTaskid(String task_id);
	
}