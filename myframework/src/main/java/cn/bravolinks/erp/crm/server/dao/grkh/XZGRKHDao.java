package cn.bravolinks.erp.crm.server.dao.grkh;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface XZGRKHDao {

	Map<String, Object> getGrkh(String bindid);

	int updateKhlx(Map<String,Object> map);

	int insertKhda(Map<String, Object> grkhMap);

	int insertLog(Map<String, Object> userMap);

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

	Map<String, Object> getCurrentStepTaskByTaskid(int taskid);

	String getFileUUID(Map<String, Object> map);

	String getId(String bindid);

	String getGjmc(String gjdm);

	String getZzmmmc(String zzmmdm);

	String getZjlxmc(String zjlxdm);

	String getJsmc(String jsdm);

	String getHyzkmc(String hyzkdm);

	String getZjxymc(String zjxydm);

	String getMzmc(String mzdm);

	String getKhdjmc(String khdj);

	String getSjlymc(String sjlydm);

	String getGxmc(String gxdm);

	String getXqahflmc(String xqahfldm);

	List<String> getLxrbh(int prefixLength);

}
