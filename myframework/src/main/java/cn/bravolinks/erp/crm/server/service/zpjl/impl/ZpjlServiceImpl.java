package cn.bravolinks.erp.crm.server.service.zpjl.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.bravolinks.erp.crm.server.dao.zpjl.ZpjlDao;
import cn.bravolinks.erp.crm.server.service.zpjl.ZpjlService;

@Service
public class ZpjlServiceImpl implements ZpjlService {
	@Autowired
	private ZpjlDao ZpjlDao;
	
	@Override
	public List<Map<String,Object>> getBMIDForSerDpt(Integer bindid) {
		return ZpjlDao.getBMIDForSerDpt(bindid);
	}

	@Override
	public Map<String, Object> getGSXXByBmid(String bmid) {
		return ZpjlDao.getGSXXByBmid(bmid);
	}

	@Override
	public Integer updSerdpt(Map<String, Object> ht1) {
		return ZpjlDao.updSerdpt(ht1);
	}

	@Override
	public Map<String,Object> getKHBMBHById(Integer boId) {
		return ZpjlDao.getKHBMBHById(boId);
	}

	@Override
	public List<Map<String, Object>> getAllSerdptByBindid(Integer bindid) {
		return ZpjlDao.getAllSerdptByBindid(bindid);
	}

	@Override
	public Integer getSerdptCount(String khbmbh, String bmid, String khjlzh, String khjlzh1, Integer bindid) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("khbmbh", khbmbh);
		map.put("bmid", bmid);
		map.put("khjlzh", khjlzh);
		map.put("khjlzh1", khjlzh1);
		map.put("bindid", bindid);
		return ZpjlDao.getSerdptCount(map);
	}

	@Override
	public HashMap<String, Object> getBoCrmClientbusPByBindid(Integer bindid) {
		return ZpjlDao.getBoCrmClientbusPByBindid(bindid);
	}

	@Override
	public List<Map<String, Object>> getoCrmClientbusSerdptbyBindid(Integer bindid) {
		return ZpjlDao.getoCrmClientbusSerdptbyBindid(bindid);
	}

	@Override
	public Map<String, Object> extSql(String bmid) {
		return ZpjlDao.extSql(bmid);
	}

	@Override
	public Map<String, Object> ykhjlzhSql(String khbmbh, Integer daid) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("khbmbh", khbmbh);
		map.put("daid", daid);
		return ZpjlDao.ykhjlzhSql(map);
	}

	@Override
	public Integer updClientSerdpt(String khjlzh, String khjlxm, String bmid, String bmmc, String gsdm, String gsmc,
			String gsid, String khbmbh, Integer daid) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("khjlzh", khjlzh);
		map.put("khjlxm", khjlxm);
		map.put("bmid", bmid);
		map.put("bmmc", bmmc);
		map.put("gsdm", gsdm);
		map.put("gsmc", gsmc);
		map.put("gsid", gsid);
		map.put("khbmbh", khbmbh);
		map.put("daid", daid);
		return ZpjlDao.updClientSerdpt(map);
	}

	@Override
	public Map<String, Object> getCurrentStepTaskByTaskid(Integer taskid) {
		return ZpjlDao.getCurrentStepTaskByTaskid(taskid);
	}

	@Override
	public Integer insertClientSerdpt(Map<String, Object> ht) {
		return ZpjlDao.insertClientSerdpt(ht);
	}

	@Override
	public Integer removeClientSerdpt(Integer id) {
		return ZpjlDao.removeClientSerdpt(id);
	}

	@Override
	public Map<String, Object> getUserByUserid(String userid) {
		return ZpjlDao.getUserByUserid(userid);
	}

	@Override
	public Integer insertLog(Map<String, Object> userMap, Integer bindid, String khmc, String khbh, String whlxdm) {
		userMap.put("bindid", bindid);
		userMap.put("khmc", khmc);
		userMap.put("khbh", khbh);
		userMap.put("whlxdm", whlxdm);
		return ZpjlDao.insertLog(userMap);
	}

	@Override
	public Map<String, Object> getDkhzjxx(String khbh) {
		return ZpjlDao.getDkhzjxx(khbh);
	}

	@Override
	public String getdkhzjEmial(String dkhzjzh) {
		return ZpjlDao.getdkhzjEmial(dkhzjzh);
	}

	@Override
	public Map<String, Object> getdkhxx(String khbh) {
		return ZpjlDao.getdkhxx(khbh);
	}

	@Override
	public Integer updDkhzh(String DKHZJXM, String DKHZJZH, String SSGSDM, String SSGSMC, String KHBH) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("DKHZJXM", DKHZJXM);
		map.put("DKHZJZH", DKHZJZH);
		map.put("SSGSDM", SSGSDM);
		map.put("SSGSMC", SSGSMC);
		map.put("KHBH", KHBH);
		return ZpjlDao.updDkhzh(map);
	}

	@Override
	public List<Map<String, Object>> getAllCust(String userid,String khjlmc) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("superadmin", 0);
		if(userid.equals("admin")) {
			map.put("superadmin", 1);
		}
		map.put("userid", userid);
		map.put("khjlmc", khjlmc);
		return ZpjlDao.getAllCust(map);
	}

	@Override
	public Map<String,Object> getBindid(String khbh) {
		return ZpjlDao.getBindid(khbh);
	}

	@Override
	public String getUserName(String userid) {
		return ZpjlDao.getUserName(userid);
	}

	@Override
	public List<Map<String, Object>> CustomerDesignateDate(Integer bindid) {
		return ZpjlDao.CustomerDesignateDate(bindid);
	}

	@Override
	public List<Map<String, Object>> CustomerDesignateDate2(Integer bindid, String userid) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bindid", bindid);
		map.put("userid", userid);
		//查询当前用户子部门和兼职部门及其子部门 SQL替换@manager.dept.all(bmid)
		List<Map<String, Object>> list1 = ZpjlDao.getbmid1(userid);
		StringBuilder str = new StringBuilder();
		str.append(1);
		for(int i=0;i<list1.size();i++){
			Map<String,Object> map1 = list1.get(i);
			str.append(" OR bmid="+String.valueOf(map1.get("BMID")));
		}
		
		List<Map<String, Object>> list2 = ZpjlDao.getbmid2(userid);
		for(int i=0;i<list2.size();i++){
			Map<String,Object> map2 = list2.get(i);
			str.append(" OR bmid="+String.valueOf(map2.get("BMID")));
		}
		map.put("str", str);
		return ZpjlDao.CustomerDesignateDate2(map);
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insertCrmClientbusP(List<Map<String, Object>> vc, Integer processId, String userid, Map<String, Object> currentStep) {
		int count = 0;
		for(int i=0;i<vc.size();i++) {
			Map<String, Object> map = vc.get(i);
			map.put("processId", processId);
			map.put("userid", userid);
			map.putAll(currentStep);
			Integer ss = ZpjlDao.insertCrmClientbusP(map);
			count =Integer.valueOf(String.valueOf(map.get("projectId")));
		}
		return count;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public Integer insertCrmClientbusSerdpt(List<Map<String, Object>> vc1, Integer processId, String userid, Map<String, Object> currentStep) {
		int count = 0;
		for(int i=0;i<vc1.size();i++) {
			Map<String, Object> map = vc1.get(i);
			map.put("processId", processId);
			map.put("userid", userid);
			map.putAll(currentStep);
			if(StringUtils.isEmpty(map.get("KHJLZH"))) {
				map.put("KHJLZH", "");
			}
			if(StringUtils.isEmpty(map.get("KHJLXM"))) {
				map.put("KHJLXM", "");
			}
			count = ZpjlDao.insertCrmClientbusSerdpt(map);
		}
		return count;
	}

	@Override
	public Integer updClientbusPYKHJLID(String ykhjlid, Integer crmClientbusPID) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("ykhjlid", ykhjlid);
		map.put("crmClientbusPID", crmClientbusPID);
		return ZpjlDao.updClientbusPYKHJLID(map);
	}

	@Override
	public Integer delzpjl(Integer id) {
		return ZpjlDao.delzpjl(id);
	}

	@Override
	public Map<String, Object> getSerDptById(Integer id) {
		return ZpjlDao.getSerDptById(id);
	}

	@Override
	public List<Map<String, Object>> getkhbmList(String khbh) {
		return ZpjlDao.getkhbmList(khbh);
	}

	@Override
	@Transactional
	public List<Map<String, Object>> getKhjlAndBM(String khbh, String userid,String val) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("khbh", khbh);
		map.put("val", val);
		map.put("userid", userid);
		return ZpjlDao.getKhjlAndBM(map);
	}

	@Override
	public List<Map<String, Object>> getZpjlList(Integer bindid) {
		List<Map<String, Object>> list = ZpjlDao.getZpjlList(bindid);
		List<Map<String, Object>> list2 = new ArrayList<>();
		for(int i=0;i<list.size();i++){
			Map<String, Object> map = list.get(i);
			if(StringUtils.isEmpty(map.get("KHJLZH"))) {
				map.put("KHJLZH", "");
			}
			if(StringUtils.isEmpty(map.get("KHJLXM"))) {
				map.put("KHJLXM", "");
			}
			list2.add(map);
		}
		return list2;
	}

	@Override
	public String pdztlc(String khbh,String userid) {
		Map<String, Object> map = new HashMap<>();
		map.put("khbh", khbh);
		map.put("userid", userid);
		String flag = "true";
		Integer num = ZpjlDao.pdztlc(map);
		if(num >0) {
			flag = "false";
		}
		return flag;
	}

	@Override
	public String addDate(Map<String, Object> currentStep,Integer bindid, String khbmbh, String khbmmc, String khjlxm, String khjlzh, String bmid,
			String bmmc) {
		String flag = "true";
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("bindid", bindid);
		map.put("khbmbh", khbmbh);
		map.put("khbmmc", khbmmc);
		map.put("khjlxm", khjlxm);
		map.put("khjlzh", khjlzh);
		map.put("bmid", bmid);
		map.put("bmmc", bmmc); 
		map.putAll(currentStep);
		Integer count = ZpjlDao.addDate(map);
		if(count >0) {
			flag = "true";
		}else{
			flag = "false";
		}
		return flag;
	}

	@Override
	public String updDate(Integer bindid, Integer id, String khbmbh, String khbmmc, String khjlxm, String khjlzh, String bmid,
			String bmmc,String userid) {
		String flag = "true";
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("bindid", bindid);
		map.put("id", id);
		map.put("userid", userid);
		map.put("khbmbh", khbmbh);
		map.put("khbmmc", khbmmc);
		map.put("khjlxm", khjlxm);
		map.put("khjlzh", khjlzh);
		map.put("bmid", bmid);
		map.put("bmmc", bmmc); 
		Integer count = ZpjlDao.updDate(map);
		if(count >0) {
			flag = "true";
		}else{
			flag = "false";
		}
		return flag;
	}

	@Override
	public String getDbStatusOfTask(String bindid, String taskid) {
		Map<String, String> m = new HashMap<>();
		m.put("bindid", bindid);
		m.put("taskid", taskid);
		String status = ZpjlDao.getDbStatusOfTask(m);
		status = status == null ? "" : status;
		return status;
	}

	@Override
	public String getYbStatusOfTask(String bindid, String taskid) {
		Map<String, String> m = new HashMap<>();
		m.put("bindid", bindid);
		m.put("taskid", taskid);
		String status = ZpjlDao.getYbStatusOfTask(m);
		status = status == null ? "" : status;
		return status;
	}

	@Override
	public Integer delData(Integer bindid) {
		 ZpjlDao.delbo_crm_clientbus_p(bindid);
		 ZpjlDao.delbo_crm_clientbus_serdpt(bindid);
		 return 0;
	}

	@Override
	public Map<String, String> getMailXX() {
		return ZpjlDao.getMailXX();
	}

	@Override
	public Map<String, Object> getkhxxForClientSerdpt(String clientSerdptId) {
		return ZpjlDao.getkhxxForClientSerdpt(clientSerdptId);
	}

}
