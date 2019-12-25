package cn.bravolinks.erp.crm.server.service.xzkh.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bravolinks.erp.crm.server.dao.xzkh.USER_DAO;
import cn.bravolinks.erp.crm.server.model.xzkh.Dept;
import cn.bravolinks.erp.crm.server.model.xzkh.User;
import cn.bravolinks.erp.crm.server.service.xzkh.UserService;



@Service
public class UserServiceimpl implements UserService {

	
	@Autowired
	private USER_DAO user_dao;
	
	@Override
	public List<Dept> getRootDept() {
		// TODO Auto-generated method stub
		return user_dao.getRootDept();
	}

	@Override
	public List<Dept> getDeptsByID(Integer id) {
		// TODO Auto-generated method stub
		return user_dao.getDeptsByID(id);
	}

	@Override
	public List<User> getUsersByID(Integer id) {
		// TODO Auto-generated method stub
		return user_dao.getUsersByID(id);
	}

	@Override
	public List<User> getAllOrgUser() {
		// TODO Auto-generated method stub
		return user_dao.getAllOrgUser();
	}

	@Override
	public User getUserExtByUserid(String userid) {
		
		return user_dao.getUserExtByUserid(userid);
	}

	@Override
	public List<Dept> getRootDeptById(Integer id) {
		// TODO Auto-generated method stub
		return user_dao.getDeptsByID(id);
	}

	@Override
	public Dept getGS(Integer id) {
		// TODO Auto-generated method stub
		return user_dao.getGS(id);
	}

	@Override
	public List<Dept> getUser(Integer bmid) {
		// TODO Auto-generated method stub
		return user_dao.getUser(bmid);
	}

	@Override
	public Dept getUserGSDM(String userid) {
		// TODO Auto-generated method stub
		return user_dao.getUserGSDM(userid);
	}

	@Override
	public Dept getGsdmGSMC(String gsdm) {
		// TODO Auto-generated method stub
		return user_dao.getGsdmGSMC(gsdm);
	}

	@Override
	public Dept getKhBMID(String gsdm) {
		// TODO Auto-generated method stub
		return user_dao.getKhBMID(gsdm);
	}

	@Override
	public Dept getGsdmGSID(String gsdm) {
		// TODO Auto-generated method stub
		return user_dao.getGsdmGSID(gsdm);
	}

	@Override
	public Dept getBMidGS(String bmid) {
		// TODO Auto-generated method stub
		return user_dao.getBMidGS(bmid);
	}

	@Override
	public Dept getBMidGSID(String bmid) {
		// TODO Auto-generated method stub
		return user_dao.getBMidGSID(bmid);
	}

	@Override
	public List<Dept> getGSName() {
		// TODO Auto-generated method stub
		return user_dao.getGSName();
	}

	@Override
	public List<Dept> getDeptByGSId(String id) {
		// TODO Auto-generated method stub
		return user_dao.getDeptByGSId(id);
	}

	@Override
	public List<Dept> getPeopleByBMId(String bmid) {
		// TODO Auto-generated method stub
		return user_dao.getPeopleByBMId(bmid);
	}

	@Override
	public String getGsid(String gsdm) {
		// TODO Auto-generated method stub
		return user_dao.getGsid(gsdm);
	}

}
