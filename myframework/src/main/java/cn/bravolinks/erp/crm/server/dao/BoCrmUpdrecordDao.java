package cn.bravolinks.erp.crm.server.dao;

import cn.bravolinks.erp.crm.server.model.BoCrmUpdrecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 大项目跟进记录-dao
 */
@Mapper
public interface BoCrmUpdrecordDao {

	/**
	 * 查询
	 * @return
	 */
	List<Map<String,Object>> select(Integer id);

	/**
	 * 插入
	 * @param updrecord
	 * @return
	 */
	Integer insert(BoCrmUpdrecord updrecord);
}