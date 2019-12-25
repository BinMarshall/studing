package cn.bravolinks.erp.crm.server.feign;

import cn.bravolinks.erp.crm.server.feign.impl.GetShenpirenImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 审批流服务 - getShenpiren 接口
 * @createDate 2018-1-3
 * @updateDate 2018-1-3
 * @author yanqin
 * @version 1.0
 */
@FeignClient(value = "${wfservice}",path = "/getShenpiren",fallback = GetShenpirenImpl.class)
public interface GetShenpirenService {

	/**
	 * 查大客户总监
	 * @param khbh
	 * @return
	 */
	@RequestMapping(value = "getDKHZJ",method = RequestMethod.GET)
	String getDKHZJ(@RequestParam("khbh") String khbh);

	/**
	 * 根据部门id和角色id查询账户
	 * @return
	 */
	@RequestMapping(value = "/getUseridDaoByDeptIDAndRoleid",method = RequestMethod.GET)
	String getUseridDaoByDeptIDAndRoleid(@RequestParam("roleid") Integer roleid, @RequestParam("deptId") Integer deptId);
}
