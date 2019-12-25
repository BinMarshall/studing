package cn.bravolinks.erp.crm.server.service.grkh;

import java.util.Map;

public interface XGGRKHService {

	Map<String,Object> getCustomerId(int oldbindid);

	int updateLxr(Map<String, Object> grkhMap, String lxrbindid);

	int insertLog(Map<String, Object> userMap,String lxrbindid);

	void insertArchives(String bindid, int oldbindid, String createUser);

	Map<String, Object> getGrkh(String bindid);

	Map<String, Object> getMC(Map<String, Object> resultMap);

	int updateView(Map<String, Object> grkhMap, int oldid);

	int insertLog(Map<String, Object> userMap, int bindid, String khmc, String khbh, String whlxdm,String userid);
}
