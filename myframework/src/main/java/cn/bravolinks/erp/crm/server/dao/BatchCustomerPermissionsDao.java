package cn.bravolinks.erp.crm.server.dao;

import cn.bravolinks.erp.crm.server.model.MaxProjectFilter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 批量处理客户权限
 *
 * @author yangbo
 * @create 2018-08-09
 **/
@Mapper
public interface BatchCustomerPermissionsDao {

	/**
	 * 查询客户信息
	 * @return
	 */
	List<Map<String,Object>> selectList(@Param("cusname") String cusname,@Param("sfType") String sfType,
										@Param("min") int min,@Param("max") int max);

	/**
	 * 更新大客户总监
	 * @return
	 */
	Integer updateDKHZJ(@Param("idarr") List<String> idarr,@Param("ndkhzj") String ndkhzj,
						@Param("ndkhzjId") String ndkhzjId,@Param("khbh") List<String> khbh);

	/**
	 * 删除客户经理
	 * @return
	 */
	Integer updateKHJL(@Param("idarr") List<String> idarr);


	/**
	 * 查询用户账号
	 * @return
	 */
	String selectUserId(@Param("ndkhzj") String ndkhzj);

	/**
	 * 查询客户信息根据userid
	 * @return
	 */
	List<Map<String,Object>> selectYhm();

	/**
	 * 查询维护数据
	 * @return
	 */
	Map<String,Object> selectWeihuData();

	/**
	 * 查询身份
	 * @return
	 */
	Map<String,Object> selectSf();

	/**
	 * 查询总数
	 * @return
	 */
	int selectCounts(@Param("cusname") String cusname,@Param("sfType") String sfType);

	/**
	 * 模糊查询用户
	 * @return
	 */
	List<Map<String,Object>> checkLikeUsername(@Param("username") String username);

	/**
	 * 查询用户信息
	 * @return
	 */
	List<Map<String,Object>> getUsers();
}
