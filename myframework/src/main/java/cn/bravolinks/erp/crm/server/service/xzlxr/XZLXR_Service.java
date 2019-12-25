package cn.bravolinks.erp.crm.server.service.xzlxr;

import java.util.List;
import java.util.Map;

public interface XZLXR_Service {

	Map<String, Object> getKhxx(String bindid);

	List<Map<String, Object>> getLxrxxList(String bindid);
	
	Map<String,Object> getLxrxx(String id);

	List<Map<String, Object>> getGzll(Map<String, Object> map);

	List<Map<String, Object>> getXglxr(Map<String, Object> map);

	List<Map<String, Object>> getGrph(Map<String, Object> map);

	List<Map<String, Object>> getLssj(Map<String, Object> map);

	List<Map<String, Object>> getLxr(String bindid);

	Integer insertLog(Map<String, Object> userMap, String bindid, String khmc, String khbh, String whlxdm);

	Integer upCustomerTime(String khbh);

	String getTaskTitle(String taskid);

	List<String> getLxrbh(Map<String, Object> map);

	void updateLxrbh(Map<String, Object> lxrbhMap);

	int insertGzll(Map<String, Object> gzllMap);
	
	int insertXglxr(Map<String, Object> xglxrMap);

	int insertLxrda(Map<String, Object> lxr);

	int insertGrph(Map<String, Object> grphMap);

	int insertLssj(Map<String, Object> lssjMap);

	List<Map<String, Object>> zhuiZong(String bindid);

	String getHyxlmc(String hyxl);

	String getStepNo(String bindid);


}
