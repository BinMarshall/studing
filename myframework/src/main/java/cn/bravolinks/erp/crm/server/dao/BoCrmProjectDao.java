package cn.bravolinks.erp.crm.server.dao;


import cn.bravolinks.erp.crm.server.model.BoCrmProject;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author yanqin
 * 大项目主表-dao
 */
@Mapper
public interface BoCrmProjectDao {

	/**
	 * 查询
	 * @param id
	 * @return
	 */
	BoCrmProject selectById(Integer id);

	/**
	 * 插入
	 * @return
	 */
	Integer insert(BoCrmProject project);

	/**
	 * 更新
	 * @param project
	 * @return
	 */
	Integer updateByPrimaryKey(BoCrmProject project);

}