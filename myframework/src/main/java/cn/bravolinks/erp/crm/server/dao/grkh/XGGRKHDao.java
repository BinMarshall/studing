package cn.bravolinks.erp.crm.server.dao.grkh;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XGGRKHDao {

	Map<String,Object> getCustomerId(int oldbindid);

	int updateLxr(Map<String, Object> params);

	Map<String, Object> getLogData(int lxrbindid);

	void deleteGrllArchives(int bindid);

	void deleteXglxrArchives(int bindid);

	void deleteXqahArchives(int bindid);

	void deleteLssjArchives(int bindid);

	Map<String, Object> getGrkh(String bindid);

	int updateView(Map<String, Object> grkhMap);

	int insertLog(Map<String, Object> userMap);

}
