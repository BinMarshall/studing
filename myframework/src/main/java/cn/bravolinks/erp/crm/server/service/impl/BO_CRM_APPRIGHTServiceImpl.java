package cn.bravolinks.erp.crm.server.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bravolinks.erp.crm.server.dao.BO_CRM_APPRIGHT_Dao;
import cn.bravolinks.erp.crm.server.dao.BO_CRM_APPRIGHT_S_Dao;
import cn.bravolinks.erp.crm.server.dao.BO_USER_EXT_DAO;
import cn.bravolinks.erp.crm.server.dao.COMMON_BO_CRM_APPRIGHT_Dao;
import cn.bravolinks.erp.crm.server.dao.WF_TASK_DAO;
import cn.bravolinks.erp.crm.server.dao.WF_TASK_LOG_DAO;
import cn.bravolinks.erp.crm.server.model.BO_CRM_APPRIGHT;
import cn.bravolinks.erp.crm.server.model.BO_CRM_APPRIGHT_S;
import cn.bravolinks.erp.crm.server.model.BO_USER_EXT;
import cn.bravolinks.erp.crm.server.model.Dept;
import cn.bravolinks.erp.crm.server.model.User;
import cn.bravolinks.erp.crm.server.model.WF_TASK;
import cn.bravolinks.erp.crm.server.service.BO_CRM_APPRIGHTService;
import cn.bravolinks.erp.crm.server.util.ERPCommonUtils;
/**
 * 客户权限申请 录入 节点表单保存后 客户信息数据处理类
 * @author Chenwd
 * @date 2017-07-07
 *
 */
@Service
public class BO_CRM_APPRIGHTServiceImpl implements BO_CRM_APPRIGHTService {
	
	private final static Logger logger = LoggerFactory.getLogger(BO_CRM_APPRIGHTServiceImpl.class);
	
	@Autowired
	BO_CRM_APPRIGHT_S_Dao bo_crm_appright_s_dao;
	@Autowired
	BO_CRM_APPRIGHT_Dao bo_crm_appright_dao;
	@Autowired
	COMMON_BO_CRM_APPRIGHT_Dao common_bo_crm_appright_dao;
	@Autowired
	private WF_TASK_DAO wfTaskDao;
	@Autowired
	private WF_TASK_LOG_DAO wfTaskLogDao;
	@Autowired
	private BO_USER_EXT_DAO bO_USER_EXT_DAO;
	
	
	@Override
	public boolean execute(Integer bindid) {
		boolean flag = true;
		try {
			// 表单数据
			BO_CRM_APPRIGHT APPRIGHT = new BO_CRM_APPRIGHT(); 
			APPRIGHT = bo_crm_appright_dao.selectByPrimaryKey(bindid);
			// 获取ID
			Integer id = APPRIGHT.getId();
			String khbh = APPRIGHT.getKhbh();
			String gsdm = APPRIGHT.getGsdm();
			//根据服务部门获取大客户总监账号
			String dkhzjzhString = common_bo_crm_appright_dao.getDKHZJZH(khbh);
			if (dkhzjzhString != null && !dkhzjzhString.equals("")) {
				APPRIGHT.setDkhzjzh(dkhzjzhString);;
			}			
			// 获取服务部门所属公司是否为单个公司
			Integer count = common_bo_crm_appright_dao.isSingleCompany(khbh);
			if(count == 1){
				//获取客户所属公司
				String khssgsdm = common_bo_crm_appright_dao.getKHSSGS(khbh);
				if(gsdm.equals(khssgsdm)){
					APPRIGHT.setSftjbjscb("0");
				}else{
					APPRIGHT.setSftjbjscb("1");
				}
			}else{
				APPRIGHT.setSftjbjscb("1");
			}
			Integer upid = bo_crm_appright_dao.updateByPrimaryKeySelective(APPRIGHT);
			logger.info("更新客户申请");
			if (upid == 1) {
				flag = true;
			}
		} catch (Exception e) {
			flag=false;
			logger.error("",e);
		}
		return flag;
	}
	
	/**
	 * 功能:更新子表的公司信息
	 * 创建人：Chenwd  
	 * 创建时间：2015-7-19 下午4:43:44   
	 * @param bindid
	 * @param conn  
	 *
	 */
	public void updateGSXX(Integer bindid){
		BO_CRM_APPRIGHT_S APPRIGHT_S = new BO_CRM_APPRIGHT_S();
		APPRIGHT_S = bo_crm_appright_s_dao.selectByPrimaryKey(bindid);
		try {
			if(APPRIGHT_S.equals(null)){
				String bmid = APPRIGHT_S.getBmid();
				Map<String, String> gsxx = common_bo_crm_appright_dao.getGSXX(bmid);
				String depid = (String)gsxx.get("id");
				String gsmc = (String)gsxx.get("GSMC");
				String gsdm = (String)gsxx.get("GSDM");
				Integer status = common_bo_crm_appright_dao.up_APPRIGHT_S(gsmc,gsdm,bindid,depid);
				if(status <= 0){
					logger.info("子表公司信息更新失败！");
//					MessageQueue.getInstance().putMessage(getUserContext().getUID(),"子表公司信息更新失败！");
				}
			}
		} catch (Exception e) {
			logger.error("",e);
		} 
	}
	/**
	 * 判断当前客户的服务部门个数，如果服务部门个数大于1，并且未配置大客户总监（或者大客户总监账号不唯一）、返回true
	 * @param khbh
	 * @param uc
	 * @return
	 * @author Chenwd
	 */
	public boolean checkCountOfFwbm(String khbh){
		boolean res = false;
		Integer fwbmCount = common_bo_crm_appright_dao.countFwbmSql(khbh);//服务部门数量
		Integer dkhzjCount = common_bo_crm_appright_dao.countDkhzjSql(khbh);//服务部门配置大客户总监的数量
		Integer distinctDkhzjCount = common_bo_crm_appright_dao.distinctdkhzjcount(khbh);//去重大客户总监数量
		if(fwbmCount!=null && fwbmCount>1){
			if(!fwbmCount.equals(dkhzjCount)){
				res = true;			
			}else if(fwbmCount.equals(dkhzjCount) ){
				if(distinctDkhzjCount>1){
					res = true;
				}
			}
		}
		return res;
	}

	@Override
	public BO_CRM_APPRIGHT getApprightByBindId(String bindid) {
		Integer bid = Integer.valueOf(bindid);
		return bo_crm_appright_dao.selectByPrimaryKey(bid);
	}

	@Override
	public String getDbStatusOfTask(String bindid, String taskid) {
		Map<String, String> m = new HashMap<>();
		m.put("bindid", bindid);
		m.put("taskid", taskid);
		String status = wfTaskDao.getStatusByTaskIdAndBindid(m);
		status = ERPCommonUtils.defaultValue(status);
		return status;
	}
	@Override
	public String getYbStatusOfTask(String bindid, String taskid) {
		Map<String, String> m = new HashMap<>();
		m.put("bindid", bindid);
		m.put("taskid", taskid);
		String status = wfTaskLogDao.getStatusByTaskIdAndBindid(m);
		status = ERPCommonUtils.defaultValue(status);
		return status;
	}

	@Override
	public List<Dept> getRootDept() {
		List<Dept> list = common_bo_crm_appright_dao.getRootDept();
		return list;
	}

	@Override
	public BO_USER_EXT getUserExtByUserid(String userid) {
		return bO_USER_EXT_DAO.getUserExtByUserid(userid);
	}

	@Override
	public List<BO_CRM_APPRIGHT_S> getApprightSubByBindId(String bindid) {
		List<BO_CRM_APPRIGHT_S> list = bo_crm_appright_s_dao.getApprightSubByBindId(bindid);
		return list;
	}

	@Override
	public List<Dept> getDeptsByID(Integer id) {
		List<Dept> list = common_bo_crm_appright_dao.getDeptsByID(id);
		return list;
	}
	@Override
	public List<User> getUsersByID(Integer id) {
		List<User> list = common_bo_crm_appright_dao.getUsersByID(id);
		return list;
	}
	@Override
	public List<User> getAllOrgUser() {
		List<User> list = common_bo_crm_appright_dao.getAllOrgUser();
		return list;
	}
	@Override
	public String getTaskTitleByTaskid(Integer taskid) {
		String taskTitle = wfTaskDao.getTaskTitleByTaskid(taskid);
		if(null == taskTitle){
			taskTitle = "";
		}
		return taskTitle;
	}
	@Override
	public String getUrLOfTodoTask(String dl, String lx, String ywmc) {
		Map<String, String> m = new HashMap<>();
		m.put("dl", dl);
		m.put("lx", lx);
		m.put("ywmc", ywmc);
		String url = common_bo_crm_appright_dao.getUrLOfTodoTask(m);
		return url;
	}
	@Override
	public Map<String, String> getTaskMessageByTaskid(String task_id) {
		WF_TASK wt = wfTaskDao.getTaskMessageByTaskid(task_id);
		String fromTaskid = String.valueOf(wt.getFromPoint());
		String fromUserid = wt.getOwner().toString();
		Map<String, String> m = new HashMap<>();
		m.put("fromTaskid", fromTaskid);
		m.put("fromUserid", fromUserid);
		return m;
	}

	@Override
	public void updateByCurrent(BO_CRM_APPRIGHT p) {
		bo_crm_appright_dao.updateByPrimaryKeySelective(p);
		
	}

	@Override
	public List<Map<String, Object>> fetchTracingDatas(Map<String, Object> condition) throws Exception {
		return common_bo_crm_appright_dao.fetchTracingDatas(condition);
	}

}
