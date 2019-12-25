package cn.bravolinks.erp.crm.server.service.impl;

import cn.bravolinks.erp.crm.server.dao.CustomerContractInfoDao;
import cn.bravolinks.erp.crm.server.dao.CustomerInfoDao;
import cn.bravolinks.erp.crm.server.feign.RestWorkFlowService;
import cn.bravolinks.erp.crm.server.model.CustomerContractInfo;
import cn.bravolinks.erp.crm.server.model.CustomerInfo;
import cn.bravolinks.erp.crm.server.service.CustomerContractInfoService;
import cn.bravolinks.erp.crm.server.util.JsonUtils;
import cn.bravolinks.erp.crm.server.util.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerContractInfoImpl implements CustomerContractInfoService {



    private final static Logger logger = LoggerFactory.getLogger(CustomerContractInfoImpl.class);

    @Autowired
    private CustomerInfoDao customerInfoDao;
    @Autowired
    private CustomerContractInfoDao customerContractInfoDao;
    @Autowired
    private RestWorkFlowService restWorkFlowService;


    /**
     * 查询条数，根据bindid
     *
     * @param bindid
     * @return
     */
    @Override
    public Integer getCountByBindid(Integer bindid) {
        return customerInfoDao.getCountByBindid(bindid);
    }

    /**
     * 删除数据，根据bindid
     *
     * @param bindid
     * @return
     */
    @Override
    @Transactional
    public Integer deleteDataByBindid(Integer bindid) {
        Integer i = customerInfoDao.delCustomerInfoByBindid(bindid);
        i = i+ customerContractInfoDao.deleteCustomerContractByBindid(bindid);
        logger.info("删除主子表数据条数="+i);
        return i;
    }

    /**
     * 查询该客户有没有其他在途的客户档案扩展合同信息流程
     *
     * @param khmc
     * @return
     */
    @Override
    public Integer getCountWorkflowBykhmc(String khmc) {
        return customerInfoDao.getCountWorkflowBykhmc(khmc);
    }

    /**
     * 查询客户名称
     *
     * @param bindid
     * @return
     */
    @Override
    public String getKHMC(Integer bindid) {
        return customerInfoDao.getKHMC(bindid);
    }

    /**
     * 查询用户名
     *
     * @param userid
     * @return
     */
    @Override
    public String getUsername(String userid) {
        return customerInfoDao.getUsername(userid);
    }

    /**
     * 跟新流程是否结束标识
     *
     * @param bindid
     * @return
     */
    @Override
    public Integer updateIsend(Integer bindid) {
        customerContractInfoDao.updateIsend(bindid);
        return customerInfoDao.updateIsend(bindid);
    }

    /**
     * 查询重复的条数
     *
     * @param khmc
     * @param bindid
     * @return
     */
    @Override
    public Integer getChongfuCount(String khmc, Integer bindid) {
        return customerContractInfoDao.getChongfuCount(khmc,bindid);
    }

    /**
     * 删除，根据id
     *
     * @param id
     * @return
     */
    @Override
    public Integer delCustomerContractInfoById(Integer id) {
        return customerContractInfoDao.delCustomerContractInfoById(id);
    }

    /**
     * 获取下拉列表数据
     *
     * @return
     */
    @Override
    public Map<String, Object> getOptions() {
        Map<String,Object> map = new HashMap<>();
        //放入业务属性信息
        map.put("YWSX_OPTIONS", JsonUtils.objectToJson(customerInfoDao.getYWSX()));
        //放入目的地类型
        map.put("MDDLX_OPTIONS",JsonUtils.objectToJson(customerInfoDao.getMDDLX()));
        //放入币种
        map.put("BZ_OPTIONS", JsonUtils.objectToJson(customerInfoDao.getBIZHONG()));
        return map;
    }

    /**
     * 根据bindid查询客户信息
     *
     * @param bindid
     * @return
     */
    @Override
    public Map<String, Object> getKhxxByBindid(Integer bindid) {
        return customerInfoDao.getKhxxByBindid(bindid);
    }

    /**
     * 更新客户合同信息表
     *
     * @param c
     * @return
     */
    @Override
    public Integer updateByPrimaryKey(CustomerContractInfo c) {
        return customerContractInfoDao.updateByPrimaryKey(c);
    }

    /**
     * 查询客户合同信息表条数
     *
     * @param khmc
     * @return
     */
    @Override
    public Integer getCustomerContractInfoCount(String khmc, Integer bindid) {
        return customerContractInfoDao.getCustomerContractInfoCount(khmc, bindid);
    }

    /**
     * 查询客户合同信息表
     *
     * @param khmc
     * @return
     */
    @Override
    public List<CustomerContractInfo> getCustomerContractInfo(String khmc, Integer min, Integer max, Integer bindid) {
        return customerContractInfoDao.getCustomerContractInfo(khmc, min, max, bindid);
    }

    /**
     * 添加记录到临时表
     *
     * @param c
     * @return
     */
    @Override
    public Integer insertCustomerContractInfo(CustomerContractInfo c) {
        return customerContractInfoDao.insertCustomerContractInfo(c);
    }

    /**
     * 查询供应商条数
     *
     * @return
     */
    @Override
    public Integer getGYSCOUNT(String serach, String qygs) {
        return customerInfoDao.getGYSCOUNT(serach,qygs);
    }

    /**
     * 查询供应商
     *
     * @param min
     * @param max
     * @return
     */
    @Override
    public List<Map<String, Object>> getGYS(Integer min, Integer max,String serach, String qygs) {
        return customerInfoDao.getGYS(min, max,serach,qygs);
    }

    /**
     * 查询客户的合同信息条数
     *
     * @param khmc
     * @return
     */
    @Override
    public Integer getHTXXCOUNT(String khmc) {
        return customerInfoDao.getHTXXCOUNT(khmc);
    }

    /**
     * 查询客户的合同信息
     *
     * @param khmc
     * @return
     */
    @Override
    public List<Map<String, Object>> getHTXX(String khmc, Integer min, Integer max) {
        return customerInfoDao.getHTXX(khmc, min, max);
    }

    /**
     * 更新客户信息
     *
     * @return
     */
    @Override
    public Map<String, Object> updateKHINFO(Integer bindid, String khmc) {
        //获取客户信息
        Map<String, Object> map = customerInfoDao.getKHXXByKHMC(khmc);
        CustomerInfo customerInfo = new CustomerInfo();
        //bindid
        customerInfo.setBindid(bindid);
        //update Date
        customerInfo.setUpdateDate(new Date());
        //客户信息
        khInfoToObject(map, customerInfo);
        //更新客户信息
        Integer i = customerInfoDao.updateKHINFO(customerInfo);
        return i == 1 ? map : null;
    }

    /**
     * 根据客户名称查询，客户信息
     *
     * @param khmc
     * @return
     */
    @Override
    public Map<String, Object> getKHXXByKHMC(String khmc) {
        return customerInfoDao.getKHXXByKHMC(khmc);
    }

    /**
     * 创建流程
     *
     * @param userid
     * @return
     */
    @Override
    @Transactional
    public Map<String, Object> createWorkFlow(String userid, String khmc) {
        String username = customerInfoDao.getUsername(userid);
        //创建流程
        String workflowuuid = PropertiesUtils.getPropertyByCommon("CustomerContractInfoWorkFlowUUID");
        String workflowtitle = "单位客户档案合同扩展信息变更流程";
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String d = sdf.format(date);
        String tasktitle;
        if(khmc!=null && !khmc.equals("")){
            tasktitle = username+"_"+d+"_合同扩展信息变更";
        }else{
            tasktitle = username+"_"+d+"_"+khmc+"_合同扩展信息变更";
        }

        //返回的流程信息
        Map<String, Object> map = restWorkFlowService.StartWorkFlow(workflowuuid,userid,workflowtitle,tasktitle,userid);
        Integer bindid = (Integer) map.get("processId");

        //插入数据
        CustomerInfo customerInfo = new CustomerInfo();
        if (khmc != null && !khmc.equals("")) {
            //获取客户信息
            Map<String, Object> khxxMap = customerInfoDao.getKHXXByKHMC(khmc);
            khInfoToObject(khxxMap, customerInfo);
        }
        //基本信息
        customerInfo.setBindid(bindid);//bindid
        customerInfo.setIsend(0);//isend
        customerInfo.setCreateDate(date);
        customerInfo.setUpdateDate(date);
        customerInfo.setIsDelete(1);

        customerInfoDao.insert(customerInfo);
        return map;
    }

    private void khInfoToObject(Map<String, Object> khxxMap, CustomerInfo customerInfo) {
        String KHMC = (String) khxxMap.get("KHMC");//客户编号
        String KHBH = (String) khxxMap.get("KHBH");//客户编号
        String KHJC = (String) khxxMap.get("KHJC");//客户简称
        String DWXZ = (String) khxxMap.get("DWXZ");//单位性质
        String HYDM = (String) khxxMap.get("HYDM");//行业大类
        String HYXLDM = (String) khxxMap.get("HYXLDM");//行业小类
        String KHZT = (String) khxxMap.get("KHZT");//客户状态
        String ZGJG = (String) khxxMap.get("ZGJG");//主管机构
        String ZJDH = (String) khxxMap.get("ZJDH");//总机电话
        String GFWZ = (String) khxxMap.get("GFWZ");//官方网站
        String BGDZ = (String) khxxMap.get("BGDZ");//办公地址
        //客户信息
        customerInfo.setKhmc(KHMC);
        customerInfo.setKhbh(KHBH);
        customerInfo.setKhjc(KHJC);
        customerInfo.setDwxz(DWXZ);
        customerInfo.setHydl(HYDM);
        customerInfo.setHyxl(HYXLDM);
        customerInfo.setKhzt(KHZT);
        customerInfo.setZgjg(ZGJG);
        customerInfo.setZjdh(ZJDH);
        customerInfo.setGfwz(GFWZ);
        customerInfo.setBgdz(BGDZ);
    }

    /**
     * 查询客户
     *
     * @param min
     * @param max
     * @return
     */
    @Override
    public List<Map<String, Object>> getKhInfo(String userid,Integer min, Integer max,String serach) {
        return customerInfoDao.getKhInfo(userid,min, max,serach);
    }

    /**
     * 查询客户总数
     *
     * @return
     */
    @Override
    public Integer getKhCount(String userid,String serach) {
        return customerInfoDao.getKhCount(userid,serach);
    }


}
