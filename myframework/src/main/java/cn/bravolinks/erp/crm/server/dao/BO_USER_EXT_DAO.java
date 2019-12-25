package cn.bravolinks.erp.crm.server.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.BO_USER_EXT;

@Mapper
public interface BO_USER_EXT_DAO {
    /**
     *  根据user查询用户扩展属性
     *
     * @param userid
     */
	BO_USER_EXT getUserExtByUserid(String userid);

}