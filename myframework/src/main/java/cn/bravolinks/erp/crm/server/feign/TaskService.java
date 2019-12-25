package cn.bravolinks.erp.crm.server.feign;

import cn.bravolinks.erp.crm.server.feign.impl.TaskImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 审批流服务 - task 接口
 * @createDate 2018-1-3
 * @updateDate 2018-1-3
 * @author yanqin
 * @version 1.0
 */
@FeignClient(value = "${wfservice}",path = "/task",fallback = TaskImpl.class)
public interface TaskService {

	/**
	 * 查询发送人
	 *
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/selectOwnerByTarget",method = RequestMethod.GET)
	List<Map<String,Object>> selectOwnerByTarget(@RequestParam("type") String type, @RequestParam("userid") String userid);
}
