package cn.bravolinks.erp.crm.server.service.grkh.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bravolinks.erp.crm.server.dao.grkh.XZGRKHDao;
import cn.bravolinks.erp.crm.server.feign.*;
import cn.bravolinks.erp.crm.server.service.grkh.XZGRKHService;

@Service
public class XZGRKHServiceImpl implements XZGRKHService {

	@Autowired
	private XZGRKHDao xzgrkhDao; 
	
	@Autowired
	private RestWorkFlowService restWorkFlowService;
	
	@Autowired
	private CustomService customService;
	
	@Override
	public Map<String, Object> getGrkh(String bindid) {
		return xzgrkhDao.getGrkh(bindid);
	}

	@Override
	public int updateKhlx(String bindid,String IndKHBH) {
		Map<String,Object> map = new HashMap<>();
		map.put("bindid", bindid);
		map.put("khbh", IndKHBH);
		return xzgrkhDao.updateKhlx(map);
	}

	@Override
	public int insertKhda(Map<String, Object> grkhMap) {
		return xzgrkhDao.insertKhda(grkhMap);
	}

	@Override
	public int insertLog(Map<String, Object> userMap, int bindid, String khmc, String khbh, String whlxdm) {
		userMap.put("bindid", bindid);
		userMap.put("khmc", khmc);
		userMap.put("khbh", khbh);
		userMap.put("whlxdm", whlxdm);
		return xzgrkhDao.insertLog(userMap);
	}

	@Override
	public List<Map<String, Object>> getGzll(String bindid) {
		return xzgrkhDao.getGzll(bindid);
	}

	@Override
	public List<Map<String, Object>> getXglxr(String bindid) {
		return xzgrkhDao.getXglxr(bindid);
	}

	@Override
	public List<Map<String, Object>> getGrph(String bindid) {
		return xzgrkhDao.getGrph(bindid);
	}

	@Override
	public List<Map<String, Object>> getLssj(String bindid) {
		return xzgrkhDao.getLssj(bindid);
	}

	@Override
	public int insertGzll(Map<String, Object> gzllMap) {
		return xzgrkhDao.insertGzll(gzllMap);
	}

	@Override
	public int insertXglxr(Map<String, Object> xglxrMap) {
		return xzgrkhDao.insertXglxr(xglxrMap);
	}

	@Override
	public int insertGrph(Map<String, Object> grphMap) {
		return xzgrkhDao.insertGrph(grphMap);
	}

	@Override
	public int insertLssj(Map<String, Object> lssjMap) {
		return xzgrkhDao.insertLssj(lssjMap);
	}

	@Override
	public List<Map<String, Object>> zhuiZong(String bindid) {
		return xzgrkhDao.zhuiZong(bindid);
	}

	@Override
	public String getTaskTitle(String taskid) {
		return xzgrkhDao.getTaskTitle(taskid);
	}

	@Override
	public Map<String, Object> getUserByUserid(String createUser) {
		return xzgrkhDao.getUserByUserid(createUser);
	}

	@Override
	public Map<String, Object> getCurrentStepTaskByTaskid(int taskid) {
		return xzgrkhDao.getCurrentStepTaskByTaskid(taskid);
	}

	@Override
	public String getFileUUID(String fieldName, String tableName) {
		Map<String,Object> map = new HashMap<>();
		map.put("fieldName", fieldName);
		map.put("tableName", tableName);
		return xzgrkhDao.getFileUUID(map);
	}

	@Override
	public String getId(String bindid) {
		return xzgrkhDao.getId(bindid);
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
	public void insertArchives(String bindid,String bind,String createUser) {
		//联系人工作履历
		List<Map<String,Object>> gzll = xzgrkhDao.getGzll(bindid);
		if(gzll != null && gzll.size()>0){
			for(Map<String,Object> gzllMap : gzll){
				gzllMap = initParam(gzllMap,bind,createUser);
				xzgrkhDao.insertGzll(gzllMap);
			}
		}
		//联系人相关联系人
		List<Map<String,Object>> xglxr = xzgrkhDao.getXglxr(bindid);
		if(xglxr != null && xglxr.size()>0){
			for(Map<String,Object> xglxrMap : xglxr){
				xglxrMap = initParam(xglxrMap,bind,createUser);
				xzgrkhDao.insertXglxr(xglxrMap);
			}
		}
		//联系人个人偏好
		List<Map<String,Object>> grph = xzgrkhDao.getGrph(bindid);
		if(grph != null && grph.size()>0){
			for(Map<String,Object> grphMap : grph){
				grphMap = initParam(grphMap,bind,createUser);
				xzgrkhDao.insertGrph(grphMap);
			}
		}
		//联系人历史事件
		List<Map<String,Object>> lssj = xzgrkhDao.getLssj(bindid);
		if(lssj != null && lssj.size()>0){
			for(Map<String,Object> lssjMap : lssj){
				lssjMap = initParam(lssjMap,bind,createUser);
				xzgrkhDao.insertLssj(lssjMap);
			}
		}
	}
	
	/**
	 * 初始化新增的系统字段
	 * @param map
	 * @param bindid
	 * @param userid
	 * @return
	 */
	@Override
	public Map<String,Object> initParam(Map<String,Object> map,String bindid,String userid){
		map.put("BINDID", bindid);
		map.put("ORGNO", 1);
		map.put("CREATEUSER", userid);
		map.put("UPDATEUSER", userid);
		map.put("WFID", 0);
		map.put("WFSID", 0);
		map.put("ISEND", "0");
		return map;
	}

	@Override
	public Map<String, Object> getXq(Map<String, Object> resultMap,String bindid) {
		//相关联系人
		List<Map<String,Object>> lxrList = xzgrkhDao.getXglxr(bindid);
		if(lxrList != null && lxrList.size()>0){
			for (Map<String, Object> lxr : lxrList) {
				String gxdm = (String) lxr.get("GXDM");
				if(gxdm != null && !"".equals(gxdm)){
					String gxmc = xzgrkhDao.getGxmc(gxdm);
					lxr.put("GXMC", gxmc);
				}
			}
			resultMap.put("xglxr", lxrList);
		}
		//工作履历
		List<Map<String,Object>> gzllList = xzgrkhDao.getGzll(bindid);
		if(gzllList != null && gzllList.size()>0){
			resultMap.put("gzll", gzllList);
		}
		//个人偏好
		List<Map<String,Object>> grphList = xzgrkhDao.getGrph(bindid);
		if(grphList != null && grphList.size()>0){
			for (Map<String, Object> grph : grphList) {
				String xqahfldm = (String) grph.get("XQAHFLDM");
				if(xqahfldm != null && !"".equals(xqahfldm)){
					String xqahflmc = xzgrkhDao.getXqahflmc(xqahfldm);
					grph.put("XQAHFLMC", xqahflmc);
				}
			}
			resultMap.put("grph", grphList);
		}
		//历史事件
		List<Map<String,Object>> lssjList = xzgrkhDao.getLssj(bindid);
		if(lssjList != null && lssjList.size()>0){
			resultMap.put("lssj", lssjList);
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> getYwsj(Map<String, Object> resultMap, String bindid, String taskid, String userid) {
		//判断当前用户是否审批人
		String shenpiren = restWorkFlowService.isTaskShenpiren(Integer.parseInt(taskid),userid);
		resultMap.put("shenpiren", shenpiren);
		
		//获取当前流程节点数
		int stepNo = restWorkFlowService.getStpeno(Integer.parseInt(taskid));
		resultMap.put("stepNo", stepNo);
		
		//查询是待办还是已办（1.待办，2.已办）
		Integer isHandle = customService.getTaskIsHandle(Integer.parseInt(taskid));
		resultMap.put("isHandle", isHandle);
		
		//流程创建者
		Map<String, Object> createUserMap = customService.getWFCreateUserByBindid(Integer.parseInt(bindid));
		String createUser = (String) createUserMap.get("USERID");
		resultMap.put("createUser", createUser);
		
		//判断是否有多个附件
		String fj = (String) resultMap.get("FJ");
		if(fj != null && !"".equals(fj)){
			if(fj.contains("@@@@")){
				String[] fjStr = fj.split("@@@@");
				resultMap.put("FJ", fjStr);
			} else {
				String[] fjStr = {fj};
				resultMap.put("FJ", fjStr);
			}
		}
		
		return resultMap;
	}

	@Override
	public String getSuffixNumberByPrefixValue(String prefix, int prefixLength) {
		int length =prefixLength+prefix.length();
		List<String> lxrbhList = xzgrkhDao.getLxrbh(length);
		int suffixValue=1;//序号值
		String returnSuffixValue="-1";
		try{
			ArrayList<Integer> al=new ArrayList<Integer>();
			for(String lxrbh : lxrbhList){
				String oldSuffValueStr=lxrbh;
				int oldSuffValue=Integer.parseInt(oldSuffValueStr.replaceAll(prefix, "0"));
				if(oldSuffValue>0){
		            boolean flag=true;
					for(int i=0;i<al.size();i++){
		            	if(al.get(i)==oldSuffValue){
		            		flag=false;
		            		break;
		            	}
		            }
					if(flag){
					    al.add(oldSuffValue);
					}
				}
			}
			
			if(al.size()==0){
				suffixValue=1;
			}else{		
				/*
				 * 冒泡排序
				 */
				
				Object [] objects=al.toArray();
				
				for(int i=0;i<objects.length-1;i++){
					for(int j=0;j<objects.length-1-i;j++){
						if(Integer.parseInt(objects[j].toString())<Integer.parseInt(objects[j+1].toString())){
							suffixValue=Integer.parseInt(objects[j+1].toString());
							objects[j]=objects[j+1];
							objects[j+1]=new Integer(suffixValue);
						}
					}
				}
				
				/*
				 * 获取序号
				 */
				int k=0;
				for(;k<objects.length;k++){
					if(k+1!=Integer.parseInt(objects[k].toString())){
						break;
					}
				}
				suffixValue=suffixValue+1;
			}
			String str="";
			for(int i=0;i<prefixLength-(suffixValue+"").length();i++){
				str=str+"0";
			}
			returnSuffixValue=prefix+str+suffixValue+"";
		}catch(Exception ex){
			ex.printStackTrace();
			return "-1";
		}
		return returnSuffixValue;
	}

}
