package cn.bravolinks.erp.crm.server.dao;

import cn.bravolinks.erp.crm.server.model.BoCrmProjectMember;
import org.apache.ibatis.annotations.Mapper;

/**
 * 大项目 - 项目成员 dao
 */
@Mapper
public interface BoCrmProjectMemberDao {

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	Integer deleteByPrimaryKey(Integer id);

	/**
	 * 插入
	 * @param projectMember
	 * @return
	 */
	Integer insert(BoCrmProjectMember projectMember);

	/**
	 * 更新
	 * @param projectMember
	 * @return
	 */
	Integer updateByPrimaryKey(BoCrmProjectMember projectMember);
}