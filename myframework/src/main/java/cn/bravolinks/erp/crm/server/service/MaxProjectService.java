package cn.bravolinks.erp.crm.server.service;

import cn.bravolinks.erp.crm.server.model.BoCrmProject;
import cn.bravolinks.erp.crm.server.model.MaxProjectFilter;
import cn.bravolinks.erp.crm.server.model.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 大项目-service
 *
 * @author yanqin
 * @create 2018-03-05
 **/
public interface MaxProjectService {

	/**
	 * 更新填报人
	 * @param userid
	 * @return
	 */
	Result updateCreuser(String newCreuser,String userid,MaxProjectFilter filter);

	/**
	 * 查询用户信息
	 * @param userid
	 * @return
	 */
	Map<String,Object> selectUserInfo(String userid);

	/**
	 * 是不是一个填报人
	 * @param filter
	 * @return
	 */
	List<String> selectCreuser(@RequestBody MaxProjectFilter filter, String userid);

	/**
	 * 判断是不是有改变填报人的权限
	 * @param userid
	 * @return
	 */
	Integer isChangeCreuserAuth(String userid);

	/**
	 * 判断是不是有添加的权限
	 * @param userid
	 * @return
	 */
	Integer isInsertAuth(String userid);

	/**
	 * 更新
	 * @param project
	 * @return
	 */
	Result upd(BoCrmProject project) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;

	/**
	 * 查询
	 * @param id
	 * @return
	 */
	Result selectByPrimaryKey(Integer id,String userid);

	/**
	 * 查询大项目list
	 * @param filter
	 * @return
	 */
	Result selectList(MaxProjectFilter filter,String userid);

	/**
	 * 保存
	 * @param project
	 * @return
	 */
	Result save(BoCrmProject project);

	/**
	 * 查询用户
	 * @param deptId
	 * @param like
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	Result selectUser(Integer deptId,String like,Integer pageSize,Integer pageNumber);

	/**
	 * 查询公司根据userid
	 * @return
	 */
	Result selectZzbm(String userid,Integer id);

	/**
	 * 新建客户
	 * @return
	 */
	Result createKh(@RequestBody Map<String,Object> data);

	/**
	 * 查询行业小类
	 * @param hylx 行业大类
	 * @return
	 */
	Result selectHyxl(Integer hylx);

	/**
	 * 查询城市
	 * @return
	 */
	Result selectCity(String like,Integer pageSize,Integer pageNumber);

	/**
	 * 查询客户
	 * @param like
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	Result selectKh(String like, Integer pageSize, Integer pageNumber);

	/**
	 * 查询维护数据
	 * @return
	 */
	Result selectWeihuData();
}
