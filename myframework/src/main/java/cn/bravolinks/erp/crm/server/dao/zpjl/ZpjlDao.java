package cn.bravolinks.erp.crm.server.dao.zpjl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ZpjlDao {

	List<Map<String, Object>> getBMIDForSerDpt(Integer bindid);

	Map<String, Object> getGSXXByBmid(String bmid);

	Integer updSerdpt(Map<String, Object> ht1);

	Map<String,Object> getKHBMBHById(Integer boId);

	List<Map<String, Object>> getAllSerdptByBindid(Integer bindid);

	Integer getSerdptCount(Map<String, Object> map);

	HashMap<String, Object> getBoCrmClientbusPByBindid(Integer bindid);

	List<Map<String, Object>> getoCrmClientbusSerdptbyBindid(Integer bindid);

	Map<String, Object> extSql(String bmid);

	Map<String, Object> ykhjlzhSql(Map<String, Object> map);

	Integer updClientSerdpt(Map<String, Object> map);

	Map<String, Object> getCurrentStepTaskByTaskid(Integer taskid);

	Integer insertClientSerdpt(Map<String, Object> ht);

	Integer removeClientSerdpt(Integer id);

	Map<String, Object> getUserByUserid(String userid);

	Integer insertLog(Map<String, Object> userMap);

	Map<String, Object> getDkhzjxx(String khbh);

	String getdkhzjEmial(String dkhzjzh);

	Map<String, Object> getdkhxx(String khbh);

	Integer updDkhzh(Map<String, Object> map);

	List<Map<String, Object>> getAllCust(Map<String, Object> map);

	Map<String,Object> getBindid(String khbh);

	String getUserName(String userid);

	List<Map<String, Object>> CustomerDesignateDate(Integer bindid);

	List<Map<String, Object>> CustomerDesignateDate2(Map<String, Object> map);

	Integer insertCrmClientbusP(Map<String, Object> map);

	Integer insertCrmClientbusSerdpt(Map<String, Object> map);

	Integer updClientbusPYKHJLID(Map<String, Object> map);

	Integer delzpjl(Integer id);

	Map<String, Object> getSerDptById(Integer id);

	List<Map<String, Object>> getkhbmList(String khbh);

	List<Map<String, Object>> getKhjlAndBM(Map<String, Object> map);

	List<Map<String, Object>> getZpjlList(Integer bindid);

	int pdztlc(Map<String, Object> map);

	Integer addDate(Map<String, Object> map);

	Integer updDate(Map<String, Object> map);

	String getDbStatusOfTask(Map<String, String> m);

	String getYbStatusOfTask(Map<String, String> m);

	void delbo_crm_clientbus_p(Integer bindid);

	void delbo_crm_clientbus_serdpt(Integer bindid);

	List<Map<String, Object>> getbmid1(String userid);

	List<Map<String, Object>> getbmid2(String userid);

	Map<String, String> getMailXX();

	Map<String, Object> getkhxxForClientSerdpt(String clientSerdptId);

	
}
