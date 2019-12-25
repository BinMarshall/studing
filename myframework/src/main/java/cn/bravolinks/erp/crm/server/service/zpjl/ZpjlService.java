package cn.bravolinks.erp.crm.server.service.zpjl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ZpjlService {

	List<Map<String,Object>> getBMIDForSerDpt(Integer bindid);

	Map<String, Object> getGSXXByBmid(String bmid);

	Integer updSerdpt(Map<String, Object> ht1);

	Map<String,Object> getKHBMBHById(Integer boId);

	List<Map<String, Object>> getAllSerdptByBindid(Integer bindid);

	Integer getSerdptCount(String khbmbh, String bmid, String khjlzh, String khjlzh1, Integer bindid);

	HashMap<String, Object> getBoCrmClientbusPByBindid(Integer bindid);

	List<Map<String, Object>> getoCrmClientbusSerdptbyBindid(Integer bindid);

	Map<String, Object> extSql(String bmid);

	Map<String, Object> ykhjlzhSql(String khbmbh, Integer daid);

	Integer updClientSerdpt(String khjlzh, String khjlxm, String bmid, String bmmc, String gsdm, String gsmc, String gsid,
			String khbmbh, Integer daid);

	Map<String, Object> getCurrentStepTaskByTaskid(Integer taskid);

	Integer insertClientSerdpt(Map<String, Object> ht);

	Integer removeClientSerdpt(Integer parseInt);

	Map<String, Object> getUserByUserid(String userid);

	Integer insertLog(Map<String, Object> userMap, Integer bindid, String khmc, String khbh, String whlxdm);

	Map<String, Object> getDkhzjxx(String khbh);

	String getdkhzjEmial(String dkhzjzh);

	Map<String, Object> getdkhxx(String khbh);

	Integer updDkhzh(String dKHZJXM, String dKHZJZH, String sSGSDM, String sSGSMC, String khbh);

	List<Map<String, Object>> getAllCust(String userid,String khjlmc);

	Map<String,Object> getBindid(String khbh);

	String getUserName(String userid);

	List<Map<String, Object>> CustomerDesignateDate(Integer bindid);

	List<Map<String, Object>> CustomerDesignateDate2(Integer bindid, String userid);

	Integer insertCrmClientbusP(List<Map<String, Object>> vc, Integer processId, String userid, Map<String, Object> currentStep);

	Integer insertCrmClientbusSerdpt(List<Map<String, Object>> vc1, Integer processId, String userid, Map<String, Object> currentStep);

	Integer updClientbusPYKHJLID(String ykhjlid, Integer crmClientbusPID);

	Integer delzpjl(Integer id);

	Map<String, Object> getSerDptById(Integer id);

	List<Map<String, Object>> getkhbmList(String khbh);

	List<Map<String, Object>> getKhjlAndBM(String khbh, String userid,String val);

	List<Map<String, Object>> getZpjlList(Integer bindid);

	String pdztlc(String khbh,String userid);

	String updDate(Integer bindid, Integer id, String khbmbh, String khbmmc, String khjlxm, String khjlzh, String bmid,
			String bmmc,String userid);

	String addDate(Map<String, Object> currentStep, Integer bindid, String khbmbh, String khbmmc, String khjlxm,
			String khjlzh, String bmid, String bmmc);

	String getDbStatusOfTask(String bindid, String taskid);

	String getYbStatusOfTask(String bindid, String taskid);

	Integer delData(Integer bindid);

	Map<String, String> getMailXX();

	Map<String, Object> getkhxxForClientSerdpt(String string);


}
