package cn.bravolinks.erp.crm.server.service.xzkh;

import java.util.List;

import cn.bravolinks.erp.crm.server.model.xzkh.Dept;
import cn.bravolinks.erp.crm.server.model.xzkh.User;




public interface UserService {
	
	/**
	 * 查询ERP组织架构部门根部门
	 * @return
	 */
	public List<Dept> getRootDept();

	/**根据部门id查询子部门
	 * 
	 * @return
	 */
	public List<Dept> getDeptsByID(Integer id);
	/**
	 *  根据部门id查询部门下的人员
	 * @return
	 */
	public List<User> getUsersByID(Integer id);
	/**
	 *  查询ERP所有人员信息
	 * @return
	 */
	public List<User> getAllOrgUser();
	
	/**
	 * 查询指定人员的名称
	 * @param userid
	 * @return
	 */
	public User getUserExtByUserid(String userid);
	
	/**
	 * 查询指定公司下的部门
	 * @return
	 */
	public List<Dept> getRootDeptById(Integer id);
	
	/**
	 * 查询公司名称和公司代码
	 * @return
	 */
	public Dept getGS(Integer id);
	
	public List<Dept> getUser(Integer bmid);
	
	public Dept getUserGSDM(String userid);
	
	
	public Dept getGsdmGSMC(String gsdm);
	
	public Dept getKhBMID(String gsdm);
	
	
	public Dept getGsdmGSID(String gsdm);
	
	public Dept getBMidGS(String bmid);
	
	public Dept getBMidGSID(String bmid);
	
	
	/**
	 * 查询公司
	 * @return
	 */
	List<Dept> getGSName();
	
	/**
	 * 查询bm
	 * @return
	 */
	List<Dept> getDeptByGSId(String id);
	
	/**
	 * 查询部门下的人
	 * @return
	 */
	List<Dept> getPeopleByBMId(String bmid);
	
	
	String getGsid(String gsdm);

}
