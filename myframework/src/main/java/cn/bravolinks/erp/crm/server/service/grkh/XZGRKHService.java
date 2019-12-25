package cn.bravolinks.erp.crm.server.service.grkh;

import java.util.List;
import java.util.Map;

public interface XZGRKHService {

	Map<String, Object> getGrkh(String bindid);

	int updateKhlx(String bindid,String IndKHBH);

	int insertKhda(Map<String, Object> grkhMap);

	int insertLog(Map<String, Object> userMap, int bindid, String khmc, String khbh, String whlxdm);

	List<Map<String, Object>> getGzll(String bindid);

	List<Map<String, Object>> getXglxr(String bindid);

	List<Map<String, Object>> getGrph(String bindid);

	List<Map<String, Object>> getLssj(String bindid);

	int insertGzll(Map<String, Object> gzllMap);

	int insertXglxr(Map<String, Object> xglxrMap);

	int insertGrph(Map<String, Object> grphMap);

	int insertLssj(Map<String, Object> lssjMap);

	List<Map<String, Object>> zhuiZong(String bindid);

	String getTaskTitle(String taskid);

	Map<String, Object> getUserByUserid(String createUser);

	Map<String, Object> getCurrentStepTaskByTaskid(int parseInt);

	String getFileUUID(String fieldName, String tableName);

	String getId(String bindid);

	Map<String, Object> getMC(Map<String, Object> resultMap);

	void insertArchives(String bindid,String bind,String createUser);
	
	Map<String,Object> initParam(Map<String,Object> map,String bindid,String userid);

	Map<String, Object> getXq(Map<String, Object> resultMap,String bindid);

	Map<String, Object> getYwsj(Map<String, Object> resultMap, String bindid, String taskid, String userid);

	String getSuffixNumberByPrefixValue(String prefix, int prefixLength);
	
}
