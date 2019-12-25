package cn.bravolinks.erp.crm.server.dao.xzlxr;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XZLXR_DAO {

	Map<String, Object> getKhxx(String bindid);

	List<Map<String, Object>> getLxrxxList(String bindid);

	List<Map<String, Object>> getGzll(Map<String, Object> map);

	List<Map<String, Object>> getXglxr(Map<String, Object> map);

	List<Map<String, Object>> getGrph(Map<String, Object> map);

	List<Map<String, Object>> getLssj(Map<String, Object> map);

	List<Map<String, Object>> getLxr(String bindid);

	Map<String, Object> getLxrxx(String id);

	Integer insertLog(Map<String, Object> userMap);

	Integer upCustomerTime(String khbh);

	String getTaskTitle(String taskid);

	int insertGzll(Map<String, Object> gzllMap);

	List<String> getLxrbh(Map<String, Object> map);

	void updateLxrbh(Map<String, Object> lxrbhMap);

	int insertXglxr(Map<String, Object> xglxrMap);

	int insertLxrda(Map<String, Object> lxr);

	int insertGrph(Map<String, Object> grphMap);

	int insertLssj(Map<String, Object> lssjMap);

	List<Map<String, Object>> zhuiZong(String bindid);

	String getHyxlmc(String hyxl);

	String getStepNo(String bindid);


}
