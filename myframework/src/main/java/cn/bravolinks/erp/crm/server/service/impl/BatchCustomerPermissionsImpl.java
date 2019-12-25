package cn.bravolinks.erp.crm.server.service.impl;

import cn.bravolinks.erp.crm.server.dao.*;
import cn.bravolinks.erp.crm.server.feign.IMService;
import cn.bravolinks.erp.crm.server.model.*;
import cn.bravolinks.erp.crm.server.service.BatchCustomerPermissionsService;
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
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 大项目-impl
 *
 * @author yanqin
 * @create 2018-03-05
 **/
@Service
public class BatchCustomerPermissionsImpl implements BatchCustomerPermissionsService {

	@Autowired
	private BatchCustomerPermissionsDao batchCustomerPermissionsDao;



	@Override
	public Result selectClientInfo(String cusname, String sfType, int page, int rows) {
		//data
		int min = rows * (page - 1);
		int max = rows * page;
		List<Map<String, Object>> data = batchCustomerPermissionsDao.selectList(cusname, sfType, min, max);
		Map<String, Object> map = new HashMap<>();
		int count = batchCustomerPermissionsDao.selectCounts(cusname, sfType);
		map.put("total", count);
		map.put("rows", data);
		return new Result(true, "", map);
	}

	@Override
	public Result updateDKHZJ(String ids, String ndkhzj,String ndkhzjId,String khbh) {
		//查询新大客户总监的用户账号
		//String ndkzjId = batchCustomerPermissionsDao.selectUserId(ndkhzj);
		if(ndkhzjId==null || "".equals(ndkhzjId)){
			return new Result(false,"更新失败，新大客户总监不存在或信息维护不全");
		}
		//int n = Integer.parseInt(ids);
		String arr[] = ids.split(",");
		List<String> idarr = Arrays.asList(arr);
		String arrKhbh[] = khbh.split(",");
		List<String> khbhArr = Arrays.asList(arrKhbh);
		Integer i = batchCustomerPermissionsDao.updateDKHZJ(idarr,ndkhzj,ndkhzjId,khbhArr);
		if(i>=0){
			return new Result(true,"更新成功");
		}else{
			return new Result(false,"更新失败");
		}
	}

	@Override
	public Result updateKHJL(String ids) {
		String arr[] = ids.split(",");
		List<String> idarr = Arrays.asList(arr);
		Integer i = batchCustomerPermissionsDao.updateKHJL(idarr);
		if(i>0){
			return new Result(true,"更新成功");
		}else{
			return new Result(false,"更新失败");
		}
	}

	/**
	 * 查询客户根据userid
	 * @return
	 */
	@Override
	public Result selectYhm(String userids, Integer ids) {
		if (ids != null) {
			return new Result(true, "", null);
		}
		/*List<Map<String, Object>> list = batchCustomerPermissionsDao.selectYhm();*/
		/*return new Result(true, "", list);*/

		List<Map<String, Object>> listTree = new ArrayList<Map<String,Object>>();
		/*
		String sql = "select dep.id, dep.departmentname depname, dep.parentdepartmentid pid, u.departmentid depid, u.userid, u.username name, u.disenable "
				+ "from orgdepartment dep left join orguser u on dep.id = u.departmentid where dep.companyid = 1";
		*/
		List<Map<String, Object>> list = batchCustomerPermissionsDao.selectYhm();

		Map<Integer, Integer> depGxMap = new HashMap<Integer, Integer>();//部门关系map
		Map<Integer, List<Map<String, Object>>> userGxMap = new HashMap<Integer, List<Map<String, Object>>>();//员工部门关系map
		Map<Integer, String> depIdToName = new HashMap<Integer, String>();//部门ID对应部门名称
		for(Map<String, Object> map : list){
			Integer id = objToInt(map.get("ID"));//部门ID
			Integer pid = objToInt(map.get("PID"));//父部门ID
			Integer depid = objToInt(map.get("DEPID"));//员工部门ID
			String depName = String.valueOf(map.get("DEPNAME"));//部门名称
			String userid = String.valueOf(map.get("USERID"));//员工账号
			String name = String.valueOf(map.get("NAME"));//员工姓名
			Integer disenable = objToInt(map.get("DISENABLE"));//是否注销
			depGxMap.put(id, pid);
			depIdToName.put(id, depName);
			if(userid==null||"null".equals(userid)||disenable==1){
				continue;
			}
			if(userGxMap.get(depid)==null){
				List<Map<String, Object>> userList = new ArrayList<Map<String,Object>>();
				Map<String, Object> userMap = new HashMap<String, Object>();
				userMap.put("id", userid);
				userMap.put("text", name);
				//userMap.put("state", "closed");
				//userMap.put("checkbox", true);
				userMap.put("iconCls", "icon-man");
				userList.add(userMap);
				userGxMap.put(depid, userList);
			}else{
				List<Map<String, Object>> userList = userGxMap.get(depid);
				Map<String, Object> userMap = new HashMap<String, Object>();
				userMap.put("id", userid);
				userMap.put("text", name);
				//userMap.put("state", "closed");
				//userMap.put("checkbox", true);
				userMap.put("iconCls", "icon-man");
				userList.add(userMap);
			}
		}
		for(Map<String, Object> map : list){
			Integer id = objToInt(map.get("ID"));//部门ID

			List<Integer> findList = new ArrayList<Integer>();//当前部门id及上级部门id数组
			Map<Integer, Integer> parentDepToChildDep = new HashMap<Integer, Integer>();
			//获取当前部门id及上级部门id数组
			findTreeConut(findList, depGxMap, parentDepToChildDep, id);
			Integer gid = -1;
			Integer pid = -1;
			for(int i=findList.size()-1; i>=0; i--){
				Integer findid = findList.get(i);
				String depName = depIdToName.get(findid);//部门名称
				List<Map<String, Object>> findListTree = findTreeList(listTree, parentDepToChildDep, pid, gid);
				if(findListTree==null){
					findListTree = listTree;
				}
				boolean isHave = false;//当前部门ID是否在树种存在
				for(Map<String, Object> mapTree : findListTree){
					if(!isNumeric(String.valueOf(mapTree.get("id")))){
						continue;
					}
					Integer treeId = objToInt(mapTree.get("id"));
					if(treeId.equals(findid)){
						isHave = true;
						break;
					}
				}
				if(!isHave){
					Map<String, Object> mapTree = new HashMap<String, Object>();
					mapTree.put("id", findid);
					mapTree.put("text", depName);
					mapTree.put("state", "closed");
					if(userGxMap.get(findid)!=null){
						mapTree.put("children", userGxMap.get(findid));
					}else{
						mapTree.put("children", new ArrayList<Map<String,Object>>());
					}
					//判断是否是根目录
					if(gid==-1){
						listTree.add(mapTree);
					}else{
						findListTree.add(mapTree);
					}
				}
				if(gid==-1){
					gid = findid;
				}
				pid = findid;
			}
		}
		return new Result(true, "", listTree);
	}

	/**
	 * object类型转int
	 * @param obj
	 */
	public static Integer objToInt(Object obj) {
		if (obj == null) {
			return null;
		} else {
			return Integer.parseInt(String.valueOf(obj));
		}

	}

	/**
	 * 判断是否为数字
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() ){
			return false;
		}
		return true;
	}

	/**
	 * 获取当前部门各级目录id
	 * @param findList
	 * @param depGxMap
	 * @param id
	 * @return
	 */
	public List<Integer> findTreeConut(List<Integer> findList, Map<Integer, Integer> depGxMap,
									   Map<Integer, Integer> parentDepToChildDep, Integer id){
		findList.add(id);
		Integer pid = depGxMap.get(id);
		parentDepToChildDep.put(pid, id);
		if(pid==0){
			return findList;
		}else{
			return findTreeConut(findList, depGxMap, parentDepToChildDep, pid);
		}
	}

	/**
	 * 获取父目录child数组
	 * @return
	 */
	public List<Map<String, Object>> findTreeList(List<Map<String, Object>> listTree, Map<Integer, Integer> parentDepToChildDep,
												  Integer pid, Integer gid){
		List<Map<String, Object>> childListTree = null;
		List<Map<String, Object>> findListTree = null;
		for(Map<String, Object> mapTree : listTree){
			Integer treeId = objToInt(mapTree.get("id"));
			if(treeId.equals(pid)){
				findListTree = (ArrayList<Map<String,Object>>)mapTree.get("children");
				break;
			}
			if(treeId.equals(gid)){
				childListTree = (ArrayList<Map<String,Object>>)mapTree.get("children");
				break;
			}

		}
		gid = parentDepToChildDep.get(gid);
		if(gid==null||findListTree!=null){
			return findListTree;
		}else{
			return findTreeList(childListTree, parentDepToChildDep, pid, gid);
		}
	}
	/**
	 * 查询维护数据
	 *
	 * @return
	 */
	@Override
	public Result selectWeihuData() {
		return new Result(true, "", batchCustomerPermissionsDao.selectWeihuData());
	}

	@Override
	public Result selectSf() {
		return new Result(true, "", batchCustomerPermissionsDao.selectSf());
	}

	@Override
	public Result checkLikeUsername(String username) {
		return new Result(true, "", batchCustomerPermissionsDao.checkLikeUsername(username));
	}

	@Override
	public Result getUsers() {
		return new Result(true, "", batchCustomerPermissionsDao.getUsers());
	}
}
