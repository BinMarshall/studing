package cn.bravolinks.erp.crm.server.dao;

import org.apache.ibatis.annotations.Mapper;

import cn.bravolinks.erp.crm.server.model.BO_CRM_APPRIGHT;
@Mapper
public interface BO_CRM_APPRIGHT_Dao {
    /**
     *  根据主键删除数据库的记录,BO_CRM_APPRIGHT
     *
     * @param id
     */
    Integer deleteByPrimaryKey(Integer id);

    /**
     *  新写入数据库记录,BO_CRM_APPRIGHT
     *
     * @param record
     */
    Integer insert(BO_CRM_APPRIGHT record);

    /**
     *  动态字段,写入数据库记录,BO_CRM_APPRIGHT
     *
     * @param record
     */
    Integer insertSelective(BO_CRM_APPRIGHT record);

    /**
     *  根据指定主键获取一条数据库记录,BO_CRM_APPRIGHT
     *
     * @param id
     */
    BO_CRM_APPRIGHT selectByPrimaryKey(Integer bindid);

    /**
     *  动态字段,根据主键来更新符合条件的数据库记录,BO_CRM_APPRIGHT
     *
     * @param record
     */
    Integer updateByPrimaryKeySelective(BO_CRM_APPRIGHT record);

    /**
     *  根据主键来更新符合条件的数据库记录,BO_CRM_APPRIGHT
     *
     * @param record
     */
    Integer updateByPrimaryKey(BO_CRM_APPRIGHT record);
}