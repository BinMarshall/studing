package cn.bravolinks.erp.crm.server.controller;

import cn.bravolinks.erp.crm.server.model.BoCrmProject;
import cn.bravolinks.erp.crm.server.model.MaxProjectFilter;
import cn.bravolinks.erp.crm.server.model.Result;
import cn.bravolinks.erp.crm.server.service.MaxProjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * 大项目-controller
 *
 * @author yanqin
 * @create 2018-03-05
 **/
@RestController
@RequestMapping("/maxproject")
public class MaxProjectController extends BaseController{
	@Autowired
	private MaxProjectService maxProjectService;
	private final static Logger logger = LoggerFactory.getLogger(MaxProjectController.class);

	/**
	 * 更新填报人
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/updateCreuser",method = RequestMethod.POST)
	public Result updateCreuser(String newCreuser,String userid,@RequestBody MaxProjectFilter filter){
		return maxProjectService.updateCreuser(newCreuser,userid,filter);
	}

	/**
	 * 查询用户信息
	 * @param userid
	 * @return
	 */
	@RequestMapping("/selectUserInfo")
	public Map<String,Object> selectUserInfo(String userid){
		return maxProjectService.selectUserInfo(userid);
	}


	/**
	 * 是不是一个填报人
	 * @param filter
	 * @return
	 */
	@RequestMapping(value = "/selectCreuser",method = RequestMethod.POST)
	public List<String> selectCreuser(@RequestBody MaxProjectFilter filter, String userid){
		return maxProjectService.selectCreuser(filter,userid);
	}

	/**
	 * 判断是不是有改变填报人的权限
	 * @param userid
	 * @return
	 */
	@RequestMapping("/isChangeCreuserAuth")
	public Integer isChangeCreuserAuth(String userid){
		return maxProjectService.isChangeCreuserAuth(userid);
	}

	/**
	 * 判断是不是有添加的权限
	 * @param userid
	 * @return
	 */
	@RequestMapping("/isInsertAuth")
	public Integer isInsertAuth(String userid){
		return maxProjectService.isInsertAuth(userid);
	}

	/**
	 * 更新
	 * @param project
	 * @return
	 */
	@RequestMapping(value = "/upd",method = RequestMethod.POST)
	public Result upd(@RequestBody BoCrmProject project){
		try {
			return maxProjectService.upd(project);
		} catch (Exception e) {
			logger.error("更新失败",e);
			return new Result(false,"更新失败");
		}
	}

	/**
	 * 查询
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectByPrimaryKey")
	public Result selectByPrimaryKey(Integer id,String userid){
		return maxProjectService.selectByPrimaryKey(id,userid);
	}

	/**
	 * 查询大项目list
	 * @param filter
	 * @return
	 */
	@RequestMapping(value = "/selectList",method = RequestMethod.POST)
	public Result selectList(@RequestBody MaxProjectFilter filter,String userid){
		return maxProjectService.selectList(filter,userid);
	}

	/**
	 * 保存
	 * @param project
	 * @return
	 */
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public Result save(@RequestBody BoCrmProject project){
		return maxProjectService.save(project);
	}

	/**
	 * 查询用户
	 * @param deptId
	 * @param like
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping("/selectUser")
	public Result selectUser(Integer deptId,String like,Integer pageSize,Integer pageNumber){
		return maxProjectService.selectUser(deptId,like,pageSize,pageNumber);
	}

	/**
	 * 查询公司根据userid
	 * @return
	 */
	@RequestMapping("/selectZzbm")
	public Result selectZzbm(String userid,Integer id){
		return maxProjectService.selectZzbm(userid,id);
	}

	/**
	 * 新建客户
	 * @return
	 */
	@RequestMapping(value = "/createKh",method = RequestMethod.POST)
	public Result createKh(@RequestBody Map<String,Object> data){
		return maxProjectService.createKh(data);
	}

	/**
	 * 查询行业小类
	 * @param hylx 行业大类
	 * @return
	 */
	@RequestMapping("/selectHyxl")
	public Result selectHyxl(Integer hylx){
		return maxProjectService.selectHyxl(hylx);
	}

	/**
	 * 查询城市
	 * @return
	 */
	@RequestMapping("/selectCity")
	public Result selectCity(String like,Integer pageSize,Integer pageNumber){
		return maxProjectService.selectCity(like,pageSize,pageNumber);
	}

	/**
	 * 查询客户
	 * @param like
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value = "/selectKh",method = RequestMethod.GET)
	public Result selectKh(String like, Integer pageSize, Integer pageNumber){
		return maxProjectService.selectKh(like,pageSize,pageNumber);
	}

	/**
	 * 查询维护数据
	 * @return
	 */
	@RequestMapping("/selectWeihuData")
	public Result selectWeihuData(){
		return maxProjectService.selectWeihuData();
	}
}
