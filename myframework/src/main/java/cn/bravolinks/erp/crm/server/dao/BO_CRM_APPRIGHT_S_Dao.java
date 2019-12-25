package cn.bravolinks.erp.crm.server.dao;

import cn.bravolinks.erp.crm.server.model.BO_CRM_APPRIGHT_S;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface BO_CRM_APPRIGHT_S_Dao {
    /**
     *  根据主键删除数据库的记录,BO_CRM_APPRIGHT_S
     *
     * @param id
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     *  新写入数据库记录,BO_CRM_APPRIGHT_S
     *
     * @param record
     */
    Integer insert(BO_CRM_APPRIGHT_S record);

    /**
     *  动态字段,写入数据库记录,BO_CRM_APPRIGHT_S
     *
     * @param record
     */
    Integer insertSelective(BO_CRM_APPRIGHT_S record);

    /**
     *  根据指定主键获取一条数据库记录,BO_CRM_APPRIGHT_S
     *
     * @param id
     */
    BO_CRM_APPRIGHT_S selectByPrimaryKey(Integer id);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,BO_CRM_APPRIGHT_S
     *
     * @param record
     */
    Integer updateByPrimaryKeySelective(BO_CRM_APPRIGHT_S record);

    /**
     *  根据主键来更新符合条件的数据库记录,BO_CRM_APPRIGHT_S
     *
     * @param record
     */
    Integer updateByPrimaryKey(BO_CRM_APPRIGHT_S record);

	List<BO_CRM_APPRIGHT_S> getApprightSubByBindId(String bindid);

}