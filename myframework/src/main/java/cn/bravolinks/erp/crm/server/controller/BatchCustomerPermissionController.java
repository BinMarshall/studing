package cn.bravolinks.erp.crm.server.controller;

import cn.bravolinks.erp.crm.server.model.BoCrmProject;
import cn.bravolinks.erp.crm.server.model.MaxProjectFilter;
import cn.bravolinks.erp.crm.server.model.Result;
import cn.bravolinks.erp.crm.server.service.BatchCustomerPermissionsService;
import cn.bravolinks.erp.crm.server.service.MaxProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 批量处理客户权限-controller
 *
 * @author yangbo
 * @create 2018-08-09
 **/
@RestController
@RequestMapping("/batchCusPer")
public class BatchCustomerPermissionController extends BaseController{
	@Autowired
	private BatchCustomerPermissionsService batchCustomerPermissionsService;
	private final static Logger logger = LoggerFactory.getLogger(BatchCustomerPermissionController.class);


	/**
	 * 查询用户信息
	 * @return
	 */
	@RequestMapping("/selectList")
	public Result selectClientInfo(String cusname,String sfType,int page,int rows){
		return batchCustomerPermissionsService.selectClientInfo(cusname,sfType,page,rows);
	}

	/** 
	 * 更新大客户总监
	 * @return
	 */
	@RequestMapping("/updateDKHZJ")
	public Result updateDKHZJ(String IDS,String NDKHZJ,String NDKHZJID,String KHBH){
		return batchCustomerPermissionsService.updateDKHZJ(IDS,NDKHZJ,NDKHZJID,KHBH);
	}

	/**
	 * 删除客户经理
	 * @return
	 */
	@RequestMapping("/updateKHJL")
	public Result updateKHJL(String IDS){
		return batchCustomerPermissionsService.updateKHJL(IDS);
	}

	/**	 * 查询身份
	 * @return
	 */
	@RequestMapping("/selectSf")
	public Result selectSf(){
		return batchCustomerPermissionsService.selectSf();
	}

	/**
	 * 查询用户根据userid
	 * @return
	 */
	@RequestMapping("/selectYhm")
	public Result selectYhm(String userid,Integer id){
		return batchCustomerPermissionsService.selectYhm(userid,id);
	}

	/**
	 * 查询维护数据
	 * @return
	 */
	@RequestMapping("/selectWeihuData")
	public Result selectWeihuData(){
		return batchCustomerPermissionsService.selectWeihuData();
	}

	/**
	 * 模糊查询用户
	 * @return
	 */
	@RequestMapping("/checkLikeUsername")
	public Result checkLikeUsername(String username){
		return batchCustomerPermissionsService.checkLikeUsername(username);
	}

	/**
	 * 查询客户信息
	 * @return
	 */
	@RequestMapping("/getUsers")
	public Result getUsers(){
		return batchCustomerPermissionsService.getUsers();
	}
}
