package cn.bravolinks.erp.crm.server.feign.impl;

import cn.bravolinks.erp.crm.server.feign.CustomService;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 审批流服务 - custom Impl
 * @createDate 2018-1-3
 * @updateDate 2018-3-7
 * @author yanqin
 * @version 1.1
 */
@Component
public class CustomImpl implements CustomService {
	@Override
	public Integer getDaibanTaskCount(Integer bindid) {
		return null;
	}

	/**
	 * 获取任务流转方式
	 *
	 * @param taskid
	 * @return 0=参数错误或者其他错误,1=不是抢签（可能是串签或者并签，在使用审批流服务中，不考虑串签功能），2=抢签
	 */
	@Override
	public int getTaskMethod(Integer taskid) {
		return 0;
	}

	@Override
	public int updateAleadyRead(Integer id) {
		return 0;
	}
	@Override
	public int updateYBAleadyRead(Integer id) {
		return 0;
	}
	@Override
	public String getUUIDByBindid(Integer bindid) {
		return null;
	}

	@Override
	public Map<String, Object> getWFCreateUserByBindid(Integer bindid) {
		return null;
	}

	@Override
	public Map<String, Object> getBindidINFO(Integer bindid, Integer stepno) {
		return null;
	}

	@Override
	public boolean getIsBJGS(String userid) {
		return false;
	}

	@Override
	public Integer getTaskidByBindidAndUserid(Integer bindid, String userid) {
		return null;
	}

	@Override
	public Integer getIsExistDaibanTask(Integer bindid, String userid) {
		return null;
	}

	@Override
	public Integer getIsExistYibanTask(Integer bindid, String userid) {
		return null;
	}

	@Override
	public Integer getTaskIsHandle(Integer taskid) {
		return null;
	}

	/**
	 * 查询加签任务状态，是否是被加签任务加签的
	 *
	 * @param taskid
	 * @return 0是普通的加签   1是被加签任务加签的任务
	 */
	@Override
	public Map<String, Object> selectAppendTaskStatus(Integer taskid) {
		return null;
	}
}
