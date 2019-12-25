package cn.bravolinks.erp.crm.server.dao;

import cn.bravolinks.erp.crm.server.model.MaxProjectFilter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * crm大项目其他数据库操作
 *
 * @author yanqin
 * @create 2018-03-05
 **/
@Mapper
public interface BoCrmProjectOtherDao {

	/**
	 * 更新填报人
	 * @return
	 */
	Integer updateCreuser(MaxProjectFilter filter);

	/**
	 * 查询用户信息
	 * @param userid
	 * @return
	 */
	Map<String,Object> selectUserInfo(String userid);

	/**
	 * 查询根据过滤条件过滤后，所有项目填报人
	 * @param filter
	 * @return
	 */
	List<String> selectCreuserByFilter(MaxProjectFilter filter);

	/**
	 * 查询权限
	 * @param userid
	 * @return
	 */
	List<Map<String,Object>> selectUserAuth(String userid);

	/**
	 * 查询用户名
	 * @param id
	 * @return
	 */
	String selectUsernameById(Integer id);

	/**
	 * 查询用户id
	 * @param id
	 * @return
	 */
	Integer selectId(String id);

	/**
	 * 是不是对接人
	 * @param userid
	 * @return
	 */
	Integer isDjr(String userid);

	/**
	 * 查询权限
	 * @param userid
	 * @return
	 */
	List<Map<String,Object>> selectAuth(String userid);

	/**
	 * 查询部门信息
	 * @param id
	 * @return
	 */
	Map<String,Object> selectDeptById(Integer id);

	/**
	 * 查询
	 * @param id
	 * @return
	 */
	Map<String,Object> selectByPrimaryKey(@Param("id") Integer id,
										  @Param("userid") String userid,
										  @Param("currDate")Date date);

	/**
	 * 查询客户信息
	 * @param id
	 * @return
	 */
	Map<String,Object> selectKhInfo(Integer id);

	/**
	 * 查询大项目list
	 * @param filter
	 * @return
	 */
	List<Map<String,Object>> selectList(MaxProjectFilter filter);

	/**
	 * 查询count
	 * @param filter
	 * @return
	 */
	Integer selectCount(MaxProjectFilter filter);

	/**
	 * 查询用户数量
	 * @return
	 */
	Integer selectUserCount(@Param("deptId") Integer deptId,@Param("like") String like,
							@Param("id")Integer id);

	/**
	 * 查询用户
	 * @return
	 */
	List<Map<String,Object>> selectUser(@Param("deptId") Integer deptId,@Param("like") String like,
										@Param("min") Integer min, @Param("max") Integer max,
										@Param("id")Integer id);

	/**
	 * 查询公司
	 * @return
	 */
	List<Map<String,Object>> selectCompany();

	/**
	 * 查询部门根据公司
	 * @param companyId
	 * @param deptId
	 * @return
	 */
	List<Map<String,Object>> selectDeptByCompany(@Param("companyId")Integer companyId,@Param("deptId")Integer deptId);

	/**
	 * 查询公司根据userid
	 * @param userid
	 * @return
	 */
	Map<String,Object> selectCompanyByUserid(String userid);

	/**
	 * 查询客户根据名称
	 * @param name
	 * @return
	 */
	Integer selectKhByName(String name);

	/**
	 * 创建潜在客户
	 * @param map
	 * @return
	 */
	Integer insertKh(Map<String,Object> map);

	/**
	 * 查询行业小类
	 * @param hylx 行业大类
	 * @return
	 */
	List<Map<String,Object>> selectHyxl(Integer hylx);

	/**
	 * 查询城市数量
	 * @return
	 */
	Integer selectCityCount(@Param("like") String like,@Param("id")Integer id);

	/**
	 * 查询城市
	 * @param min
	 * @param max
	 * @return
	 */
	List<Map<String,Object>> selectCity(@Param("like") String like,
										@Param("min") Integer min,
										@Param("max") Integer max,
										@Param("id") Integer id
	);

	/**
	 * 查询客户
	 * @param like
	 * @param min
	 * @param max
	 * @return
	 */
	List<Map<String,Object>> selectKh(@Param("like") String like, @Param("min") Integer min,
									  @Param("max") Integer max);

	/**
	 * 查询客户count
	 * @param like
	 * @return
	 */
	Integer selectKhCount(@Param("like") String like);

	/**
	 * 查询维护数据
	 * @return
	 */
	Map<String,Object> selectWeihuData();

	/**
	 * 查询期望部门
	 * @return
	 */
	List<Map<String,Object>> selectQwbm();
}
