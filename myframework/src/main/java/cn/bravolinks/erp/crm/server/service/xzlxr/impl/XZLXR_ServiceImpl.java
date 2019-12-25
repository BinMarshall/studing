package cn.bravolinks.erp.crm.server.service.xzlxr.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bravolinks.erp.crm.server.dao.xzlxr.XZLXR_DAO;
import cn.bravolinks.erp.crm.server.service.xzlxr.XZLXR_Service;

@Service
public class XZLXR_ServiceImpl implements XZLXR_Service {
	
	@Autowired
	private XZLXR_DAO xzlxrDao;

	@Override
	public Map<String, Object> getKhxx(String bindid) {
		return xzlxrDao.getKhxx(bindid);
	}

	@Override
	public List<Map<String, Object>> getLxrxxList(String bindid) {
		return xzlxrDao.getLxrxxList(bindid);
	}

	@Override
	public List<Map<String, Object>> getGzll(Map<String, Object> map) {
		return xzlxrDao.getGzll(map);
	}

	@Override
	public List<Map<String, Object>> getXglxr(Map<String, Object> map) {
		return xzlxrDao.getXglxr(map);
	}

	@Override
	public List<Map<String, Object>> getGrph(Map<String, Object> map) {
		return xzlxrDao.getGrph(map);
	}

	@Override
	public List<Map<String, Object>> getLssj(Map<String, Object> map) {
		return xzlxrDao.getLssj(map);
	}

	@Override
	public List<Map<String, Object>> getLxr(String bindid) {
		return xzlxrDao.getLxr(bindid);
	}

	@Override
	public Map<String, Object> getLxrxx(String id) {
		return xzlxrDao.getLxrxx(id);
	}

	@Override
	public Integer insertLog(Map<String, Object> userMap, String bindid, String khmc, String khbh, String whlxdm) {
		userMap.put("BINDID", bindid);
		userMap.put("KHMC", khmc);
		userMap.put("KHBH", khbh);
		userMap.put("WHLXDM", whlxdm);
		return xzlxrDao.insertLog(userMap);
	}

	@Override
	public Integer upCustomerTime(String khbh) {
		return xzlxrDao.upCustomerTime(khbh);
	}

	@Override
	public String getTaskTitle(String taskid) {
		return xzlxrDao.getTaskTitle(taskid);
	}

	@Override
	public int insertGzll(Map<String, Object> gzllMap) {
		return xzlxrDao.insertGzll(gzllMap);
	}

	@Override
	public List<String> getLxrbh(Map<String, Object> map) {
		return xzlxrDao.getLxrbh(map);
	}

	@Override
	public void updateLxrbh(Map<String, Object> lxrbhMap) {
		xzlxrDao.updateLxrbh(lxrbhMap);
	}

	@Override
	public int insertXglxr(Map<String, Object> xglxrMap) {
		return xzlxrDao.insertXglxr(xglxrMap);
	}

	@Override
	public int insertLxrda(Map<String, Object> lxr) {
		return xzlxrDao.insertLxrda(lxr);
	}

	@Override
	public int insertGrph(Map<String, Object> grphMap) {
		return xzlxrDao.insertGrph(grphMap);
	}

	@Override
	public int insertLssj(Map<String, Object> lssjMap) {
		return xzlxrDao.insertLssj(lssjMap);
	}

	@Override
	public List<Map<String, Object>> zhuiZong(String bindid) {
		return xzlxrDao.zhuiZong(bindid);
	}

	@Override
	public String getHyxlmc(String hyxl) {
		return xzlxrDao.getHyxlmc(hyxl);
	}

	@Override
	public String getStepNo(String bindid) {
		return xzlxrDao.getStepNo(bindid);
	}


}
