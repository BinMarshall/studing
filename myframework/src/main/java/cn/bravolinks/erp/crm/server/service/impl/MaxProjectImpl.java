package cn.bravolinks.erp.crm.server.service.impl;

import cn.bravolinks.erp.crm.server.dao.BoCrmProjectDao;
import cn.bravolinks.erp.crm.server.dao.BoCrmProjectMemberDao;
import cn.bravolinks.erp.crm.server.dao.BoCrmProjectOtherDao;
import cn.bravolinks.erp.crm.server.dao.BoCrmUpdrecordDao;
import cn.bravolinks.erp.crm.server.feign.IMService;
import cn.bravolinks.erp.crm.server.model.BoCrmProject;
import cn.bravolinks.erp.crm.server.model.BoCrmProjectMember;
import cn.bravolinks.erp.crm.server.model.BoCrmUpdrecord;
import cn.bravolinks.erp.crm.server.model.MaxProjectFilter;
import cn.bravolinks.erp.crm.server.model.Result;
import cn.bravolinks.erp.crm.server.model.UpdFiled;
import cn.bravolinks.erp.crm.server.service.MaxProjectService;
import cn.bravolinks.erp.crm.server.util.SysUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 大项目-impl
 *
 * @author yanqin
 * @create 2018-03-05
 **/
@Service
public class MaxProjectImpl implements MaxProjectService {

	@Autowired
	private BoCrmProjectOtherDao projectOtherDao;
	@Autowired
	private BoCrmProjectDao projectDao;
	@Autowired
	private BoCrmProjectMemberDao projectMemberDao;
	@Autowired
	private BoCrmUpdrecordDao updrecordDao;
	@Autowired
	private IMService imService;

	private final static Integer MAX_PAGESIZE = 1000;
	private final static Logger logger = LoggerFactory.getLogger(MaxProjectImpl.class);
	/**
	 * 需要记录变更的字段
	 */
	private static List<UpdFiled> updFileds = new ArrayList<>();

	@PostConstruct
	public void initUpdFiledMap() {
		if (updFileds.size() == 0) {
			updFileds.add(new UpdFiled("cusname", "客户名称", 1, 1));
			updFileds.add(new UpdFiled("proname", "项目名称", 1, 0));
//			updFileds.add(new UpdFiled("serviceType", "服务类型", 1, 1));
			updFileds.add(new UpdFiled("serviceContent", "服务内容", 1, 1));
			updFileds.add(new UpdFiled("startDateYear", "预计出发日期-年", 1, 0));
			updFileds.add(new UpdFiled("startDateMonth", "预计出发日期-月", 1, 0));
			updFileds.add(new UpdFiled("startDateDay", "预计出发日期-日", 1, 0));
			updFileds.add(new UpdFiled("endDateYear", "预计结束日期-年", 1, 0));
			updFileds.add(new UpdFiled("endDateMonth", "预计结束日期-月", 1, 0));
			updFileds.add(new UpdFiled("endDateDay", "预计结束日期-日", 1, 0));
			updFileds.add(new UpdFiled("regionType", "项目区域类型", 1, 1));
			updFileds.add(new UpdFiled("city", "项目举办地", 1, 1));
			updFileds.add(new UpdFiled("ygrs", "预估人数", 1, 0));
			updFileds.add(new UpdFiled("money", "项目金额", 1, 0));
			updFileds.add(new UpdFiled("problem", "项目难点", 1, 0));
			updFileds.add(new UpdFiled("expectAssistDept", "期望协助部门", 1, 1));
			updFileds.add(new UpdFiled("newStatus", "最新项目状态", 1, 1));
			updFileds.add(new UpdFiled("dataSource", "影像资料收集渠道", 1, 1));
			updFileds.add(new UpdFiled("statusDesc", "项目状态说明", 1, 0));
			updFileds.add(new UpdFiled("zzbm", "主责部门", 1, 1));
			updFileds.add(new UpdFiled("ssgs", "所属公司", 1, 1));
			updFileds.add(new UpdFiled("xmfzr", "项目经理", 1, 1));
			updFileds.add(new UpdFiled("xmzgld", "项目主管领导", 1, 1));
			updFileds.add(new UpdFiled("xtbm", "协同部门", 1, 1));
			updFileds.add(new UpdFiled("note", "备注", 1, 0));
		}
	}

	/**
	 * 更新填报人
	 *
	 * @param userid
	 * @return
	 */
	@Override
	public Result updateCreuser(String newCreuser,String userid,MaxProjectFilter filter) {
		if(userid==null){
			return new Result(false,"不能变更，因为找不到登录人信息");
		}
		if(newCreuser==null){
			return new Result(false,"不能变更，请选择新的对接人");
		}
		List<String> list = selectCreuser(filter,userid);
		if(list.size()!=1){
			return new Result(false,"当前项目存在多个填报人，不能进行对接人变更");
		}
		filter.setUserid(userid);
		filter.setNewCreuser(newCreuser);
		Integer i = projectOtherDao.updateCreuser(filter);
		if(i>0){
			return new Result(true,"保存成功");
		}else{
			return new Result(false,"保存失败");
		}
	}

	/**
	 * 查询用户信息
	 *
	 * @param userid
	 * @return
	 */
	@Override
	public Map<String, Object> selectUserInfo(String userid) {
		return projectOtherDao.selectUserInfo(userid);
	}

	/**
	 * 是不是一个填报人
	 *
	 * @param filter
	 * @return
	 */
	@Override
	public List<String> selectCreuser(MaxProjectFilter filter,String userid) {
		filter.setUserid(userid);
		return projectOtherDao.selectCreuserByFilter(filter);
	}

	/**
	 * 判断是不是有改变填报人的权限
	 *
	 * @param userid
	 * @return
	 */
	@Override
	public Integer isChangeCreuserAuth(String userid) {
		return isSpecialAuth(userid);

	}

	/**
	 * 是否有特殊权限
	 * @param userid
	 * @return
	 */
	private Integer isSpecialAuth(String userid) {
		//判断是不是特殊角色
		List<Map<String,Object>> auths = projectOtherDao.selectUserAuth(userid);
		for (Map<String, Object> auth : auths) {
			if("特殊人员".equals(auth.get("ROLE_NAME"))){
				return 1;
			}
		}
		return 0;
	}

	/**
	 * 判断是不是有添加的权限
	 * @param userid
	 * @return
	 */
	@Override
	public Integer isInsertAuth(String userid){
		//判断是不是对接人
		Integer count = projectOtherDao.isDjr(userid);
		if(count>0){
			return 1;
		}
		//判断是不是特殊角色
		return isSpecialAuth(userid);
	}

	/**
	 * 是否有更新权限
	 *
	 * @param userid
	 * @return 0 无权限    1有权限
	 */
	private Integer isUpdateAuth(String userid,Integer projectId) {
		Integer id = projectOtherDao.selectId(userid);
		BoCrmProject project = projectDao.selectById(projectId);
		//判断是不是项目经理
		if(id!=null && id.equals(project.getXmfzr())){
			return 1;
		}
		//判断是不是对接人，并且是填报人
		Integer count = projectOtherDao.isDjr(userid);
		if (count > 0) {
			if (userid!=null && userid.equals(project.getCreuser())) {
				return 1;
			}
		}
		//判断是不是拥有可以修改权限的 大项目角色
		List<Map<String,Object>> auths = projectOtherDao.selectUserAuth(userid);
		for (Map<String, Object> auth : auths) {
			Integer isUpdate = ((BigDecimal)auth.get("IS_UPDATE")).intValue();
			if(isUpdate==1){
				return 1;
			}
		}
		return 0;
	}

	/**
	 * 更新
	 *
	 * @param project
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result upd(BoCrmProject project) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		String loginname = project.getUpduser();
		if(loginname==null)return new Result(false,"请先登录");
		int auth = isUpdateAuth(loginname,project.getId());
		if(auth!=1){
			return new Result(false,"您没有权限更新");
		}
		Date date = new Date();
		project.setUpdtime(date);

		/*旧数据*/
		BoCrmProject oldProject = projectDao.selectById(project.getId());
		/**
		 * 记录更新字段
		 */
		saveUpdFiledData(project, date,oldProject);
		/**
		 * 更新项目信息
		 */
		projectDao.updateByPrimaryKey(project);
		/**
		 * 更新项目成员
		 */
		saveProjectMember(project);
		/**
		 * 发送邮件
		 */
		assistDeptOrProblemChange(oldProject,project);
		return new Result(true, "保存成功", project);
	}

	/**
	 * 保存更新字段记录
	 */
	private void saveUpdFiledData(BoCrmProject project, Date date,BoCrmProject oldProject) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Class clazz = BoCrmProject.class;
		for (UpdFiled updFiled : updFileds) {
			String filed = updFiled.getFiled();
			String methodName = "get" + SysUtils.upperCase(filed);
			Method method = clazz.getMethod(methodName);
			Object old = method.invoke(oldProject);
			Object neww = method.invoke(project);
			String oldValue = (old == null || old =="") ? null : old.toString();
			String newValue = (neww == null || neww == "") ? null : neww.toString();

			BoCrmUpdrecord updrecord = new BoCrmUpdrecord();
			updrecord.setProid(project.getId());
			updrecord.setUpdfield(filed);
			updrecord.setUpdtype(1);
			updrecord.setUpdtime(date);
			updrecord.setUser(project.getUpduser());
			boolean isNotEquals = (oldValue == null && newValue != null) || (oldValue != null && !oldValue.equals(newValue));
			if (isNotEquals) {
				if (updFiled.getIsSaveCode() == 1) {
					updrecord.setUpdbeforeCode(oldValue);
					updrecord.setUpdafterCode(newValue);

					String oldValueName = null, newValueName = null;
					if(filed.equals("cusname")){
						oldValueName = getMcByDm4(oldValue);
						newValueName = getMcByDm4(newValue);
					}
//					服务类型
//					else if (filed.equals("serviceType")) {
//						Map<String, Object> weihuData = projectOtherDao.selectWeihuData();
//						oldValueName = getMcByDm1(weihuData, "fwlxs", oldValue);
//						newValueName = getMcByDm1(weihuData, "fwlxs", newValue);
//					}
//					服务内容
					else if (filed.equals("serviceContent")) {
						Map<String, Object> weihuData = projectOtherDao.selectWeihuData();
						oldValueName = getMcByDm2(weihuData,"fwnrs", oldValue);
						newValueName = getMcByDm2(weihuData,"fwnrs", newValue);
					}
//					区域类型
					else if (filed.equals("regionType")) {
						Map<String, Object> weihuData = projectOtherDao.selectWeihuData();
						oldValueName = getMcByDm1(weihuData, "qylxs", oldValue);
						newValueName = getMcByDm1(weihuData, "qylxs", newValue);
					}
//					城市
					else if (filed.equals("city")) {
						oldValueName = getMcByDm5(oldValue);
						newValueName = getMcByDm5(newValue);
					}
//					期望协助部门
					else if (filed.equals("expectAssistDept")) {
						Map<String, Object> weihuData = projectOtherDao.selectWeihuData();
						oldValueName = getMcByDm2(weihuData,"qwbms", oldValue);
						newValueName = getMcByDm2(weihuData,"qwbms", newValue);
					}
//					项目状态
					else if (filed.equals("newStatus")) {
						Map<String, Object> weihuData = projectOtherDao.selectWeihuData();
						oldValueName = getMcByDm1(weihuData, "xmzts", oldValue);
						newValueName = getMcByDm1(weihuData, "xmzts", newValue);
					}
//					资料渠道
					else if (filed.equals("dataSource")) {
						Map<String, Object> weihuData = projectOtherDao.selectWeihuData();
						oldValueName = getMcByDm1(weihuData, "yxzls", oldValue);
						newValueName = getMcByDm1(weihuData, "yxzls", newValue);
					}
//					主责部门
					else if (filed.equals("zzbm")) {
						List<Map<String, Object>> list = projectOtherDao.selectDeptByCompany(null, Integer.valueOf(oldValue));
						if (list != null && list.size() > 0) {
							oldValueName = (String) list.get(0).get("text");
						}
						list = projectOtherDao.selectDeptByCompany(null, Integer.valueOf(newValue));
						if (list != null && list.size() > 0) {
							newValueName = (String) list.get(0).get("text");
						}
					}
//					所属公司
					else if (filed.equals("ssgs")) {
						List<Map<String, Object>> list = projectOtherDao.selectDeptByCompany(Integer.valueOf(oldValue), null);
						if (list != null && list.size() > 0) {
							oldValueName = (String) list.get(0).get("ssgs");
						}
						list = projectOtherDao.selectDeptByCompany(Integer.valueOf(newValue), null);
						if (list != null && list.size() > 0) {
							newValueName = (String) list.get(0).get("ssgs");
						}
					}
//					项目负责人
					else if (filed.equals("xmfzr")) {
						List<Map<String, Object>> list = projectOtherDao.selectUser(null, null, 0, 1, Integer.valueOf(oldValue));
						if (list != null && list.size() > 0) {
							oldValueName = (String) list.get(0).get("USERNAME");
						}
						list = projectOtherDao.selectUser(null, null, 0, 1,Integer.valueOf(newValue));
						if (list != null && list.size() > 0) {
							newValueName = (String) list.get(0).get("USERNAME");
						}
					}
//					主管领导
					else if (filed.equals("xmzgld")) {
						Map<String, Object> weihuData = projectOtherDao.selectWeihuData();
						oldValueName = getMcByDm1(weihuData, "zglds", oldValue);
						newValueName = getMcByDm1(weihuData, "zglds", newValue);
					}
//					协同部门
					else if (filed.equals("xtbm")) {
						oldValueName = getMcByDm3(oldValue);
						newValueName = getMcByDm3(newValue);
					}
					updrecord.setUpdbefore(oldValueName);
					updrecord.setUpdafter(newValueName);
				} else {
					updrecord.setUpdbefore(oldValue);
					updrecord.setUpdafter(newValue);
				}
				updrecordDao.insert(updrecord);
			}
		}
	}

	/**
	 * 根据代码获取名称  : 举办地
	 *
	 */
	private String getMcByDm5(String value) {
		String valueName = "";
		if (value != null) {
			String[] arr = ((String) value).split(",");
			for (int i = 0; i < arr.length; i++) {
				String s = arr[i];
				List<Map<String,Object>> list = projectOtherDao.selectCity(null, 0, 1, Integer.valueOf(s));
				if(list!=null && list.size()>0){
					Map<String,Object> map = list.get(0);
					String khmc = map==null?"": (String) map.get("mddxs");
					valueName += khmc + ",";
				}else{
					valueName += ",";
				}
			}
			valueName = valueName.substring(0, valueName.length() - 1);
		}
		return valueName;
	}

	/**
	 * 根据代码获取名称  :  客户名称
	 *
	 */
	private String getMcByDm4(String value) {
		String valueName = "";
		if (value != null) {
			String[] arr = ((String) value).split(",");
			for (int i = 0; i < arr.length; i++) {
				String s = arr[i];
				Map<String,Object> map = projectOtherDao.selectKhInfo(Integer.valueOf(s));
				String khmc = map==null?"": (String) map.get("khmc");
				valueName += khmc + ",";
			}
			valueName = valueName.substring(0, valueName.length() - 1);
		}
		return valueName;
	}

	/**
	 * 根据代码获取名称  : 部门
	 *
	 * @param value
	 */
	private String getMcByDm3(Object value) {
		String valueName = "";
		if (value != null) {
			String[] arr = ((String) value).split(",");
			for (String s : arr) {
				List<Map<String, Object>> list = projectOtherDao.selectDeptByCompany(null, Integer.valueOf(s));
				if (list != null && list.size() > 0) {
					valueName += (String) list.get(0).get("text") + ",";
				}
			}
			valueName = valueName.substring(0, valueName.length() - 1);
		}
		return valueName;
	}

	/**
	 * 根据代码获取名称   :  维护项 ，代码 名称  (多选择)
	 *
	 * @param weihuData
	 * @param value
	 */
	private String getMcByDm2(Map<String, Object> weihuData,String key, Object value) {
		String valueName = "";
		List<Map<String, Object>> list = (List<Map<String, Object>>) weihuData.get(key);
		if (value != null) {
			String[] arr = ((String) value).split(",");
			for (int i = 0; i < arr.length; i++) {
				String s = arr[i];
				List<Map<String, Object>> filterList = list.stream().filter(map ->
						map.get("DM") != null && map.get("DM").equals(s))
						.collect(Collectors.toList());
				if (filterList != null && filterList.size() != 0) {
					valueName += filterList.get(0).get("MC") + ",";
				}
			}
			valueName = valueName.substring(0, valueName.length() - 1);
		}
		return valueName;
	}

	/**
	 * 根据代码获取名称    :  维护项 ，代码 名称  (单选择)
	 *
	 * @param key
	 * @param value
	 */
	private String getMcByDm1(Map<String, Object> weihuData, String key, Object value) {
		if(value==null)return null;
		String valueName = "";
		List<Map<String, Object>> list = (List<Map<String, Object>>) weihuData.get(key);
		list = list.stream().filter(map ->
				map.get("DM") != null && map.get("DM").equals(value.toString()))
				.collect(Collectors.toList());
		if (list != null && list.size() != 0) {
			valueName = (String) list.get(0).get("MC");
		}
		return valueName;
	}

	/**
	 * 保存项目成员信息
	 *
	 * @param project
	 */
	private void saveProjectMember(BoCrmProject project) {
		/*保存项目成员信息*/
		List<BoCrmProjectMember> list = project.getProjectMembers();
		for (BoCrmProjectMember projectMember : list) {
			Integer projectId = projectMember.getId();
			if (projectId == null) {
				projectMember.setCreateDate(new Date());
				projectMember.setProjectId(project.getId());
				projectMemberDao.insert(projectMember);
			} else if (projectId != null && projectMember.getProjectUser() != null && projectMember.getUserRole() != null) {
				projectMemberDao.updateByPrimaryKey(projectMember);
			} else {
				projectMemberDao.deleteByPrimaryKey(projectId);
			}
		}
	}

	/**
	 * 查询
	 *
	 * @param id
	 * @return
	 */
	@Override
	public Result selectByPrimaryKey(Integer id,String userid) {
		if(userid==null){
			return new Result(false,"请先登录");
		}
		Map<String, Object> data = projectOtherDao.selectByPrimaryKey(id,userid,SysUtils.getDate());

		if (data != null) {
			/*客户信息*/
			String kh = (String) data.get("CUSNAME");
			String[] arr = kh.split(",");
			List<Map<String, Object>> khList = new ArrayList<>();
			for (String s : arr) {
			/*查询客户信息*/
				Map<String, Object> khInfo = projectOtherDao.selectKhInfo(Integer.valueOf(s));
				khList.add(khInfo);
			}
			data.put("KHS", khList);
			/*协同部门信息*/
			String xtbm = (String) data.get("XTBM");
			List<Map<String, Object>> xtbmList = new ArrayList<>();
			if (xtbm != null) {
				arr = xtbm.split(",");
				for (String s : arr) {
					Map<String, Object> deptInfo = projectOtherDao.selectDeptById(Integer.valueOf(s));
					xtbmList.add(deptInfo);
				}
			}
			data.put("XTBMS", xtbmList);
			/*更新记录*/
			List<Map<String, Object>> updrecords = updrecordDao.select(id);
			for (Map<String, Object> map : updrecords) {
				String field = (String) map.get("UPDFIELD");
				List<UpdFiled> filterList = updFileds.stream().filter(updFiled -> updFiled.getFiled().equals(field)).collect(Collectors.toList());
				if (filterList != null && filterList.size() > 0) {
					map.put("UPDFIELD", filterList.get(0).getFiledName());
				}
			}
			data.put("updrecords", updrecords);
			/*举办地*/
			String city = (String) data.get("CITY");
			List<Map<String,Object>> cityList = new ArrayList<>();
			if(city!=null){
				String[] cityArr = city.split(",");
				for (String c : cityArr) {
					List<Map<String,Object>> list = projectOtherDao.selectCity(null,0,1,Integer.valueOf(c));
					if(list!=null && list.size()>0){
						cityList.addAll(list);
					}
				}
			}
			data.put("CITYS",cityList);
			/*服务内容*/
			String fwnr = (String) data.get("SERVICE_CONTENT");
			List<Map<String,Object>> fwnrList = new ArrayList<>();
			if(fwnr!=null){
				String[] fwnrArr = fwnr.split(",");

			}
			return new Result(true, "", data);
		} else {
			return new Result(false, "查不到项目信息，有以下几种可能：<br>1.项目不存在<br>2.没有权限查看<br>3.已作废");
		}
	}

	/**
	 * 查询大项目list
	 *
	 * @param filter
	 * @return
	 */
	@Override
	public Result selectList(MaxProjectFilter filter, String userid) {
		if(userid==null){
			return new Result(false,"请先登录");
		}
		filter.setUserid(userid);
		filter.setCurrDate(SysUtils.getDate());
		if (filter.getRows()!=null) {
			if (filter.getRows() > MAX_PAGESIZE) {
				return new Result(false, "页面规格过大");
			}
			Integer min = filter.getRows() * (filter.getPage() - 1);
			Integer max = filter.getRows() * filter.getPage();
			filter.setMin(min);
			filter.setMax(max);
		}
		//data
		List<Map<String, Object>> data = projectOtherDao.selectList(filter);

		for (Map<String, Object> project : data) {
			/**
			 * 客户处理
			 */
			String kh = (String) project.get("CUSNAME");
			String[] arr = kh.split(",");
			List<Map<String, Object>> list = new ArrayList<>();
			for (String s : arr) {
				/*查询客户信息*/
				Map<String, Object> khInfo = projectOtherDao.selectKhInfo(Integer.valueOf(s));
				list.add(khInfo);
			}
			String khmc = "", khhy = "";
			/*拼接客户信息*/
			for (Map<String, Object> map : list) {
				String mc = (String) map.get("khmc");
				String hy = (String) map.get("hymc");
				khmc += mc + ";";
				khhy += hy + ";";
			}
			khmc = khmc.substring(0, khmc.length() - 1);
			khhy = khhy.substring(0, khhy.length() - 1);
			project.put("KHMC", khmc);
			project.put("KHHY", khhy);

			/**
			 * 举办地
			 */
			String jbdId  = (String) project.get("JBD");
			String city = "";
			if(!StringUtils.isEmpty(jbdId)){
				arr = jbdId.split(",");
				for (String id : arr) {
					List<Map<String,Object>> jbdList = projectOtherDao.selectCity(null,0,1,Integer.valueOf(id));
					if(jbdList!=null && jbdList.size()>0){
						city += jbdList.get(0).get("mddxs") + ";";
					}
				}
				city = city.substring(0,city.length()-1);
				project.put("city",city);
			}
		}

		Integer count = projectOtherDao.selectCount(filter);
		Map<String, Object> map = new HashMap<>();
		map.put("rows", data);
		map.put("total", count);
		return new Result(true, "", map);
	}

	/**
	 * 保存
	 *
	 * @param project
	 * @return
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Result save(BoCrmProject project) {
		String loginname = project.getCreuser()==null?project.getUpduser():project.getCreuser();
		if (loginname == null) {
			return new Result(false, "请先登录");
		}
		if(isInsertAuth(loginname)!=1){
			return new Result(false,"您没有填报权限");
		}
		/*保存大项目信息*/
		if (project.getId() == null) {
			project.setProsource(1);
			project.setCretime(new Date());
			projectDao.insert(project);
			assistDeptOrProblemChange(new BoCrmProject(),project);
		} else {
			/*旧数据*/
			BoCrmProject oldProject = projectDao.selectById(project.getId());
			project.setUpdtime(new Date());
			/*更新*/
			projectDao.updateByPrimaryKey(project);
			assistDeptOrProblemChange(oldProject,project);
		}
		saveProjectMember(project);
		return new Result(true, "", project);
	}

	/**
	 * 期望协助部门变更
	 */
	private void assistDeptOrProblemChange(BoCrmProject oldProject,BoCrmProject newProject){
		logger.warn("开始检查部门变更");
		/*新增的部门*/
		List<String> changeDepts = new ArrayList<>();

		/*项目难点*/
		String oldProblem = oldProject.getProblem();
		String newProblem = newProject.getProblem();
		/*新的难点不为空，并且难点变更了*/
		if(newProblem!=null && !newProblem.equals(oldProblem)){
			String newDept = newProject.getExpectAssistDept();
			if(newDept!=null){
				changeDepts = Arrays.asList(newDept.split(","));
			}
		}
		/*新的难点不为空，难点无变更*/
		else if(newProblem!=null && newProblem.equals(oldProblem)){
			String oldDept = oldProject.getExpectAssistDept();
			String newDept = newProject.getExpectAssistDept();
			if(newDept!=null && !newDept.equals(oldDept)){
				if(oldDept==null){
					changeDepts = Arrays.asList(newDept.split(","));
				}else{
					List<String> oldDepts = Arrays.asList(oldDept.split(","));
					List<String> newDepts = Arrays.asList(newDept.split(","));
					for (String dept : newDepts) {
						if(!oldDepts.contains(dept)){
							changeDepts.add(dept);
						}
					}
				}
			}
		}

		/*根据部门id查询处，部门信息*/
		List<Map<String,Object>> receiverDepts = new ArrayList<>();
		List<Map<String,Object>> qwbms = projectOtherDao.selectQwbm();
		for (String deptId : changeDepts) {
			List<Map<String,Object>> tempList = qwbms.stream().filter(map -> map.get("DM").equals(deptId)).collect(Collectors.toList());
			if(tempList!=null && tempList.size()>0){
				Map<String,Object> dept = tempList.get(0);
				receiverDepts.add(dept);
			}
		}
		sendMail(receiverDepts,newProject);
	}

	/**
	 * 发送邮件
	 */
	private void sendMail(List<Map<String,Object>> receiverDepts,BoCrmProject project){
		logger.warn("开始发送邮件，需要发送的部门数量："+receiverDepts.size());
		final String mailFrom = "admin";
		String title = "项目难点通知";
		Integer projectManagerId = project.getXmfzr();
		String projectManager = projectOtherDao.selectUsernameById(projectManagerId);
		String projectName = project.getProname();
		String projectProblem = project.getProblem();

		for (Map<String,Object> dept : receiverDepts) {
			/*部门邮箱*/
			String mail = (String) dept.get("BZ");
			if(mail!=null) {
				/*部门名*/
				String deptName = (String) dept.get("MC");
				/*发送内容*/
				String content = deptName + "，您好：\n" +
						"项目经理“" + projectManager + "”的“" + projectName + "”项目中遇到的项目难点为：" + projectProblem + "（项目难点内容）" +
						"（数据来于大项目填报页面项目难点字段），需要您的配合，请协助处理。\n"+
						"此邮件不接受回复\n"+
						"中青博联整合营销顾问股份有限公司\n"+
						DateFormat.getInstance().format(new Date()) +"（北京时间）\n"
						;
				String t = imService.sendMail(mailFrom,mail,title,content);
				try {
					Integer i = Integer.valueOf(t);
					if(i<0)logger.error("发送邮件失败，返回值"+t+",content:"+content);
				}catch (Exception e){
					logger.error("发送邮件失败，返回值"+t+",content:"+content);
				}
			}
		}
	}

	/**
	 * 查询用户
	 *
	 * @param deptId
	 * @param like
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	@Override
	public Result selectUser(Integer deptId, String like, Integer pageSize, Integer pageNumber) {
		if (deptId == null && StringUtils.isEmpty(like)) return new Result(true, "", null);
		if (pageSize > MAX_PAGESIZE) {
			return new Result(false, "页面规格过大");
		}
		Integer min = pageSize * (pageNumber - 1);
		Integer max = pageSize * pageNumber;
		List<Map<String, Object>> data = projectOtherDao.selectUser(deptId, like, min, max, null);
		Integer count = projectOtherDao.selectUserCount(deptId, like, null);
		Map<String, Object> map = new HashMap<>();
		map.put("rows", data);
		map.put("total", count);
		return new Result(true, "", map);
	}

	/**
	 * 查询公司根据userid
	 *
	 * @param userid
	 * @return
	 */
	@Override
	public Result selectZzbm(String userid, Integer id) {
		if (id != null) {
			return new Result(true, "", null);
		}
		Map<String, Object> companyInfo = projectOtherDao.selectCompanyByUserid(userid);
		BigDecimal companyId = (BigDecimal) companyInfo.get("id");
		List<Map<String, Object>> list = projectOtherDao.selectCompany();
		Map<String, Object> myCompany = list.stream().filter(map ->
				companyId.compareTo((BigDecimal) map.get("id")) == 0)
				.collect(Collectors.toList()).get(0);
		myCompany.put("state", "open");
		return new Result(true, "", list);
	}

	/**
	 * 新建客户
	 *
	 * @param data
	 * @return
	 */
	@Override
	public Result createKh(Map<String, Object> data) {
		if (data.get("loginname") == null) {
			return new Result(false, "请先登录");
		}
		String name = (String) data.get("khmc");
		data.put("createDate", new Date());
		data.put("isqzkh", 1);
		try {
			Integer count = projectOtherDao.selectKhByName(name);
			if (count > 0) {
				return new Result(false, "该客户名称已经存在，不能创建");
			}
			projectOtherDao.insertKh(data);
			return new Result(true, "", data);
		} catch (Exception e) {
			logger.error("创建客户失败", e);
			return new Result(false, "创建客户失败");
		}
	}

	/**
	 * 查询行业小类
	 *
	 * @param hylx 行业大类
	 * @return
	 */
	@Override
	public Result selectHyxl(Integer hylx) {
		return new Result(true, "", projectOtherDao.selectHyxl(hylx));
	}

	/**
	 * 查询城市
	 *
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	@Override
	public Result selectCity(String like, Integer pageSize, Integer pageNumber) {
		if (pageSize > MAX_PAGESIZE) {
			return new Result(false, "页面规格过大");
		}
		Integer min = pageSize * (pageNumber - 1);
		Integer max = pageSize * pageNumber;
		List<Map<String, Object>> data = projectOtherDao.selectCity(like, min, max, null);
		Integer count = projectOtherDao.selectCityCount(like, null);
		Map<String, Object> map = new HashMap<>();
		map.put("rows", data);
		map.put("total", count);
		return new Result(true, "", map);
	}

	/**
	 * 查询客户
	 *
	 * @param like
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	@Override
	public Result selectKh(String like, Integer pageSize, Integer pageNumber) {
		if (pageSize > MAX_PAGESIZE) {
			return new Result(false, "页面规格过大");
		}
		Integer min = pageSize * (pageNumber - 1);
		Integer max = pageSize * pageNumber;
		List<Map<String, Object>> data = projectOtherDao.selectKh(like, min, max);
		Integer count = projectOtherDao.selectKhCount(like);
		Map<String, Object> map = new HashMap<>();
		map.put("rows", data);
		map.put("total", count);
		return new Result(true, "", map);
	}

	/**
	 * 查询维护数据
	 *
	 * @return
	 */
	@Override
	public Result selectWeihuData() {
		return new Result(true, "", projectOtherDao.selectWeihuData());
	}
}
