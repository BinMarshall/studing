package cn.bravolinks.erp.crm.server.service.grkh.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bravolinks.erp.crm.server.dao.grkh.XGGRKHDao;
import cn.bravolinks.erp.crm.server.dao.grkh.XZGRKHDao;
import cn.bravolinks.erp.crm.server.service.grkh.XGGRKHService;
import cn.bravolinks.erp.crm.server.service.grkh.XZGRKHService;

@Service
public class XGGRKHServiceImpl implements XGGRKHService {
	
	@Autowired
	private XGGRKHDao xggrkhDao;
	
	@Autowired
	private XZGRKHDao xzgrkhDao; 
	
	@Autowired
	private XZGRKHService xzgrkhService;
	
	@Override
	public Map<String,Object> getCustomerId(int oldbindid) {
		return xggrkhDao.getCustomerId(oldbindid);
	}

	@Override
	public int updateLxr(Map<String, Object> grkhMap, String lxrbindid) {
		Map<String,Object> params = new HashMap<>();
		params.put("xbdm", (String) grkhMap.get("XBDM"));
		params.put("yddh", (String) grkhMap.get("YDDH"));
		params.put("email", (String) grkhMap.get("EMAIL"));
		params.put("jsdm", (String) grkhMap.get("JSDM"));
		params.put("lxrbindid", lxrbindid);
		return xggrkhDao.updateLxr(params);
	}

	@Override
	public int insertLog(Map<String, Object> userMap,String lxrbindid) {
		Map<String,Object> resultMap = xggrkhDao.getLogData(Integer.parseInt(lxrbindid));
		String khbh = (String) resultMap.get("LXRBH");
		String khmc = (String) resultMap.get("XM");
		userMap.put("khmc", khmc);
		userMap.put("khbh", khbh);
		userMap.put("whlxdm", "106");
		return xzgrkhDao.insertLog(userMap);
	}

	@Override
	public void insertArchives(String bindid, int oldbindid, String createUser) {
		//联系人工作履历
		List<Map<String,Object>> gzll = xzgrkhDao.getGzll(bindid);
		if(gzll != null && gzll.size()>0){
			xggrkhDao.deleteGrllArchives(oldbindid);
			for(Map<String,Object> gzllMap : gzll){
				gzllMap = xzgrkhService.initParam(gzllMap,String.valueOf(oldbindid),createUser);
				xzgrkhDao.insertGzll(gzllMap);
			}
		}
		//联系人相关联系人
		List<Map<String,Object>> xglxr = xzgrkhDao.getXglxr(bindid);
		if(xglxr != null && xglxr.size()>0){
			xggrkhDao.deleteXglxrArchives(oldbindid);
			for(Map<String,Object> xglxrMap : xglxr){
				xglxrMap = xzgrkhService.initParam(xglxrMap,String.valueOf(oldbindid),createUser);
				xzgrkhDao.insertXglxr(xglxrMap);
			}
		}
		//联系人个人偏好
		List<Map<String,Object>> grph = xzgrkhDao.getGrph(bindid);
		if(grph != null && grph.size()>0){
			xggrkhDao.deleteXqahArchives(oldbindid);
			for(Map<String,Object> grphMap : grph){
				grphMap = xzgrkhService.initParam(grphMap,String.valueOf(oldbindid),createUser);
				xzgrkhDao.insertGrph(grphMap);
			}
		}
		//联系人历史事件
		List<Map<String,Object>> lssj = xzgrkhDao.getLssj(bindid);
		if(lssj != null && lssj.size()>0){
			xggrkhDao.deleteLssjArchives(oldbindid);
			for(Map<String,Object> lssjMap : lssj){
				lssjMap = xzgrkhService.initParam(lssjMap,String.valueOf(oldbindid),createUser);
				xzgrkhDao.insertLssj(lssjMap);
			}
		}
		
	}

	@Override
	public Map<String, Object> getGrkh(String bindid) {
		return xggrkhDao.getGrkh(bindid);
	}

	@Override
	public Map<String, Object> getMC(Map<String, Object> resultMap) {
		//获取国籍名称
		String gjdm = (String) resultMap.get("GJDM");
		if(gjdm != null && !"".equals(gjdm)){
			String gjmc = xzgrkhDao.getGjmc(gjdm);
			resultMap.put("GJMC", gjmc);
		}
		//获取政治面貌
		String zzmmdm = (String) resultMap.get("ZZMMDM");
		if(zzmmdm != null && !"".equals(zzmmdm)){
			String zzmmmc = xzgrkhDao.getZzmmmc(zzmmdm);
			resultMap.put("ZZMMMC", zzmmmc);
		}
		//获取证件类型名称
		String zjlxdm = (String) resultMap.get("ZJLXDM");
		if(zjlxdm != null && !"".equals(zjlxdm)){
			String zjlxmc = xzgrkhDao.getZjlxmc(zjlxdm);
			resultMap.put("ZJLXMC", zjlxmc);
		}
		//获取角色名称
		String jsdm = (String) resultMap.get("JSDM");
		if(jsdm != null && !"".equals(jsdm)){
			String jsmc = xzgrkhDao.getJsmc(jsdm);
			resultMap.put("JSMC", jsmc);
		}
		//获取婚姻状况名称
		String hyzkdm = (String) resultMap.get("HYZKDM");
		if(hyzkdm != null && !"".equals(hyzkdm)){
			String hyzkmc = xzgrkhDao.getHyzkmc(hyzkdm);
			resultMap.put("HYZKMC", hyzkmc);
		}
		//获取宗教信仰名称
		String zjxydm = (String) resultMap.get("ZJXYDM");
		if(zjxydm != null && !"".equals(zjxydm)){
			String zjxymc = xzgrkhDao.getZjxymc(zjxydm);
			resultMap.put("ZJXYMC", zjxymc);
		}
		//获取民族名称
		String mzdm = (String) resultMap.get("MZDM");
		if(mzdm != null && !"".equals(mzdm)){
			String mzmc = xzgrkhDao.getMzmc(mzdm);
			resultMap.put("MZMC", mzmc);
		}
		//获得客户等级名称
		String khdj = (String) resultMap.get("KHDJ");
		if(khdj != null && !"".equals(khdj)){
			String khdjmc = xzgrkhDao.getKhdjmc(khdj);
			resultMap.put("KHDJMC", khdjmc);
		}
		//获得数据来源名称
		String sjlydm = (String) resultMap.get("SJLYDM");
		if(sjlydm != null && !"".equals(sjlydm)){
			String sjlymc = xzgrkhDao.getSjlymc(sjlydm);
			resultMap.put("SJLYMC", sjlymc);
		}
		return resultMap;
	}

	@Override
	public int updateView(Map<String, Object> grkhMap, int oldid) {
		grkhMap.put("oldid", oldid);
		return xggrkhDao.updateView(grkhMap);
	}

	@Override
	public int insertLog(Map<String, Object> userMap, int bindid, String khmc, String khbh, String whlxdm,String userid) {
		userMap.put("loginuser",userid);
		userMap.put("bindid", bindid);
		userMap.put("khmc", khmc);
		userMap.put("khbh", khbh);
		userMap.put("whlxdm", whlxdm);
		return xggrkhDao.insertLog(userMap);
	}
	
}
