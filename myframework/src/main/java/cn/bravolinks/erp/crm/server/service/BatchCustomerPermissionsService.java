package cn.bravolinks.erp.crm.server.service;

import cn.bravolinks.erp.crm.server.model.BoCrmProject;
import cn.bravolinks.erp.crm.server.model.MaxProjectFilter;
import cn.bravolinks.erp.crm.server.model.Result;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 批量处理客户权限-service
 *
 * @author yangbo
 * @create 2018-08-09
 **/
public interface BatchCustomerPermissionsService {

	/**
	 * 查询用户信息
	 * @return
	 */
	Result selectClientInfo(String cusname,String sfType,int page,int rows);

	/**
	 * 更新大客户总监
	 * @return
	 */
	Result updateDKHZJ(String ids,String ndkhzj,String NDKHZJID,String KHBH);

	/**
	 * 删除客户经理
	 * @return
	 */
	Result updateKHJL(String ids);

	/**
	 * 查询用户根据userid
	 * @return
	 */
	Result selectYhm(String userid,Integer id);

	/**
	 * 查询维护数据
	 * @return
	 */
	Result selectWeihuData();

	/**
	 * 查询身份
	 * @return
	 */
	Result selectSf();

	/**
	 * 模糊查询用户
	 * @return
	 */
	Result checkLikeUsername(String username);

	/**
	 * 查询用户信息
	 * @return
	 */
	Result getUsers();
}
