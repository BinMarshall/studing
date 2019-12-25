package cn.bravolinks.erp.crm.server.controller.xzkh;

import cn.bravolinks.erp.crm.server.feign.RestWorkFlowService;
import cn.bravolinks.erp.crm.server.model.tssx.BO_CRM_CLIENTBUS_LM;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_DPT;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_P;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_REG;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENT_DPT;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENT_P;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENT_SERDPT;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENTBUS_EVE;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENTBUS_HOB;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENTBUS_TR;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENT_EVE;
import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_INDCLIENT_P;
import cn.bravolinks.erp.crm.server.model.xzkh.Dept;
import cn.bravolinks.erp.crm.server.model.xzkh.User;
import cn.bravolinks.erp.crm.server.service.xzkh.BO_CRM_CLIENTBUS_DPTService;
import cn.bravolinks.erp.crm.server.service.xzkh.BO_CRM_CLIENTBUS_LMService;
import cn.bravolinks.erp.crm.server.service.xzkh.BO_CRM_CLIENTBUS_PServer;
import cn.bravolinks.erp.crm.server.service.xzkh.BO_CRM_CLIENTBUS_REGService;
import cn.bravolinks.erp.crm.server.service.xzkh.BO_CRM_CLIENT_PService;
import cn.bravolinks.erp.crm.server.service.xzkh.BO_CRM_CLIENT_REGService;
import cn.bravolinks.erp.crm.server.service.xzkh.BO_CRM_CLIENT_SERDPTService;
import cn.bravolinks.erp.crm.server.service.xzkh.UserService;
import cn.bravolinks.erp.crm.server.util.JDPUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 新增单位客户服务端接口
 * 
 * @author JDP 2017-6-19
 */
@Controller
@RequestMapping("/xzkh")
public class XZKH_Controller {

	private final static Logger logger = LoggerFactory.getLogger(XZKH_Controller.class);

	@Autowired
	private BO_CRM_CLIENTBUS_PServer boCrmClientbusPServer;

	@Autowired
	private BO_CRM_CLIENTBUS_DPTService boCrmClientbusDptServer;

	@Autowired
	private BO_CRM_CLIENTBUS_LMService boCrmClientbusLmServer;

	@Autowired
	private UserService userService;

	@Autowired
	private BO_CRM_CLIENT_SERDPTService boCrmClientSerdptServer;

	@Autowired
	private BO_CRM_CLIENT_PService boCrmClientptServer;

	@Autowired
	private BO_CRM_CLIENTBUS_REGService boCrmClientregtServer;

	@Autowired
	private BO_CRM_CLIENT_REGService boCrmClientreServer;
	@Autowired
	private RestWorkFlowService restWorkFlowService;

	private ObjectMapper objectMapper = new ObjectMapper();

	/***
	 * 查询新增單位客戶信息
	 * 
	 * @param bindid
	 * @param userid
	 * @return 返回json格式的字符串
	 * @author JDP
	 */
	@RequestMapping(value = "/getXZKHByBindId", method = RequestMethod.GET)
	@ResponseBody
	public String getAll(String bindid, String userid) {
		try {
			Map<String, String> map = new HashMap<String, String>();
			map.put("bindid", bindid);
			map.put("userid", userid);
			// 获取对象
			/**
			 * 部门信息
			 * @author zhangsonghe
			 */
			BO_CRM_CLIENTBUS_P selectBybindid = boCrmClientbusPServer.selectBybindid(bindid);
			/**
			 * 单位客户信息
			 */
			List<BO_CRM_CLIENTBUS_DPT> selectByBMbindid = boCrmClientbusDptServer.selectByBMbindid(bindid);
			/**
			 * 联系人信息
			 * @author zhangsonghe
			 */
			List<BO_CRM_CLIENTBUS_LM> selectByid = boCrmClientbusLmServer.selectByid(bindid);
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("selectBybindid", selectBybindid);
			map1.put("selectByBMbindid", selectByBMbindid);
			map1.put("selectByid", selectByid);
			String json = objectMapper.writeValueAsString(map1);

			return json;
		} catch (Exception e) {
			logger.error("",e);
			return "访问数据有误，请联系系统管理员";
		}
	}

	/**
	 * 获取部门列表数据
	 *
	 * @return
	 */
	@RequestMapping(value = "/getUserOrDeptByID", method = RequestMethod.GET)
	@ResponseBody
	public String getUserOrDeptByID(Integer id) {
		ObjectMapper mapper = new ObjectMapper();
		// 获取子部门信息返回list
		List<Dept> childDepts = userService.getDeptsByID(id);
		// 获取部门下的人员
		List<User> users = userService.getUsersByID(id);

		List<Object> os = new ArrayList<>();
		if (null != childDepts && childDepts.size() > 0) {
			os.addAll(childDepts);
		}
		if (null != users && users.size() > 0) {
			os.addAll(users);
		}
		String res = "";
		try {
			res = mapper.writeValueAsString(os);
		} catch (JsonProcessingException e) {
			logger.error("",e);
		}
		return res;
	}

	/**
	 * 获取部门列表数据
	 *
	 * @return
	 */
	@RequestMapping(value = "/getRootDept", method = RequestMethod.GET)
	@ResponseBody
	public String deptListData() {
		ObjectMapper mapper = new ObjectMapper();
		// 获取部门信息返回list
		List<Dept> childDepts = userService.getRootDept();
		List<Object> os = new ArrayList<>();
		os.addAll(childDepts);
		try {
			return mapper.writeValueAsString(os);
		} catch (JsonProcessingException e) {
			logger.error("",e);
		}
		return "";
	}

	/***
	 * 新增单位客户申请办理按钮，如果czType为“1“：审批流流转到下一个审批人；为”2“：则打回至上一审批人
	 * 
	 * @author jdp
	 * @param bindid
	 *            流程ID
	 * @param task_id
	 *            任务ID
	 * @param userid
	 *            操作人用户
	 * @param opinion
	 *            审批留言
	 * @param zdr
	 *            制单人
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/xzkhbl", method = RequestMethod.GET)
	@ResponseBody // 此注解不能省略 否则ajax无法接受返回值
	public String zcsqsp(HttpServletRequest request, String stepno, String userid, String zdr, String zdrq, String khmc,
			String isagree, String bindid, String task_id, String opinion, String fwbmmc, String fwbmid, String fwr,
			String fwrid) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> resultMap = new HashMap<String, String>();
		String json = "";
		try {
			// 判断是打回还是办理，返回页面值
			if ("办理".equals(isagree)) {
				// 获取当前节点号
				Integer StepNo = restWorkFlowService.getStpeno(Integer.parseInt(task_id));

				// 查询新增单位客户信息
				BO_CRM_CLIENTBUS_P selectBybindid = boCrmClientbusPServer.selectBybindid(bindid);
				Integer id = selectBybindid.getId();
				Integer jtwlkh = selectBybindid.getJtwlkh();
				if (fwbmmc.equals(selectBybindid.getBmmc())) {
					fwbmid = selectBybindid.getBmid();
				}
				// 如果当前节点是2
				if (StepNo == 2 || StepNo == 3) {

					if (StepNo == 2) {

						BO_CRM_CLIENTBUS_P bo_CRM_CLIENTBUS_P = new BO_CRM_CLIENTBUS_P();
						bo_CRM_CLIENTBUS_P.setFwbmmc(fwbmmc);
						bo_CRM_CLIENTBUS_P.setFwbmid(fwbmid);
						bo_CRM_CLIENTBUS_P.setBindid(Integer.parseInt(bindid));
						boCrmClientbusPServer. updateBoCRMBM(bo_CRM_CLIENTBUS_P);
					}
					if (fwbmmc.equals(selectBybindid.getBmmc())) {
						fwbmid = selectBybindid.getBmid();
					}
					BO_CRM_CLIENTBUS_P bo_CRM_CLIENTBUS_P = new BO_CRM_CLIENTBUS_P();
					bo_CRM_CLIENTBUS_P.setFwbmmc(fwbmmc);
					bo_CRM_CLIENTBUS_P.setFwbmid(fwbmid);
					bo_CRM_CLIENTBUS_P.setBindid(Integer.parseInt(bindid));
					boCrmClientbusPServer.updateBoCRMBM(bo_CRM_CLIENTBUS_P);

					JDPUtils.NullValidate(fwbmmc);
					JDPUtils.NullValidate(fwbmid);
					String DKHZJZH = "";// 大客户总监账户
					String DKHZJXM = "";// 大客户总监姓名
					if (fwbmmc != null && !fwbmmc.equals("")) {
						Dept gs = userService.getGS(Integer.parseInt(fwbmid));
						String gsmc = gs.getName();
						String gsdm = gs.getGsdm();
						String sjdwString = selectBybindid.getSjdwbh();// 上级单位
						if (!sjdwString.equals("B0000000")) {
							DKHZJZH = boCrmClientSerdptServer.selectByBMZH(sjdwString);

							if (DKHZJZH == null) {
								DKHZJZH = "";
							}
							DKHZJXM = boCrmClientSerdptServer.selectByBMXM(sjdwString);
							if (DKHZJXM == null) {
								DKHZJXM = "";
							}
						}
						String name = "";
						String uid = "";
						StringBuffer sname = new StringBuffer();
						StringBuffer sid = new StringBuffer();

						List<Dept> user = userService.getUser(Integer.parseInt(fwbmid));
						if (user == null) {
							name = "";
							uid = "";
						} else {
							for (int i = 0; i < user.size(); i++) {
								Dept dept = user.get(i);
								String userid2 = dept.getUserid();
								String name2 = dept.getName();
								sname.append(name2 + ',');
								sid.append(userid2 + ',');
							}
						}
						if (sid == null) {
							sid = sid.append("");
						}
						if (sname == null) {
							sname = sname.append("");
						}
						BO_CRM_CLIENTBUS_P bo_crm = new BO_CRM_CLIENTBUS_P();
						bo_crm.setBmzjxm(sname.toString());
						bo_crm.setBmzjzh(sid.toString());
						bo_crm.setFwbmssgsmc(gsmc);
						bo_crm.setFwbmssgsdm(gsdm);
						bo_crm.setDkhzjxm(DKHZJXM);
						bo_crm.setDkhzjzh(DKHZJZH);
						bo_crm.setId(id);
						boCrmClientbusPServer.UpdateById(bo_crm);
					}
					if (DKHZJZH != null && !DKHZJZH.equals("")) {
						BO_CRM_CLIENTBUS_P bo_crm1 = new BO_CRM_CLIENTBUS_P();
						bo_crm1.setId(id);
						bo_crm1.setJtwlkh(1);
						boCrmClientbusPServer.UpdateByIdJT(bo_crm1);
					} else {
						BO_CRM_CLIENTBUS_P bo_crm2 = new BO_CRM_CLIENTBUS_P();
						bo_crm2.setId(id);
						bo_crm2.setJtwlkh(0);
						boCrmClientbusPServer.UpdateByIdJT(bo_crm2);
					}
					Map<String, Object> handleTaskResult = new HashMap();
					handleTaskResult = restWorkFlowService.ExcuteTask(Integer.parseInt(bindid),userid,Integer.parseInt(task_id),
							zdr + "_" + zdrq + "_" + khmc + "_" + "_新增单位客户申请","", "同意", opinion);
					String message = (String) handleTaskResult.get("message");
					json = message;
					resultMap.put("result", json);
					resultMap.put("type", "success");

				}else if (StepNo == 4 && jtwlkh==1){
					String khjlxm = fwr;
					if (khjlxm == null || khjlxm.equals("")) {
						khjlxm = "";
					}
					String khjlzh = fwrid;
					if (khjlzh == null || khjlzh.equals("")) {
						khjlzh = "";
					}
					String khmc1 = selectBybindid.getKhmc();
					String khbh = selectBybindid.getKhbh();
					if (khbh == null || khbh.equals("")) {
						khbh = "";
					}
					BO_CRM_CLIENTBUS_P bo_CRM_CLIENTBUS_P = new BO_CRM_CLIENTBUS_P();
					bo_CRM_CLIENTBUS_P.setKhjlxm(khjlxm);
					bo_CRM_CLIENTBUS_P.setKhjlzh(khjlzh);
					bo_CRM_CLIENTBUS_P.setBindid(Integer.parseInt(bindid));
					boCrmClientbusPServer.updateBoCRMR(bo_CRM_CLIENTBUS_P);

					List<BO_CRM_CLIENTBUS_DPT> selectByBMbindid = boCrmClientbusDptServer.selectByBMbindid(bindid);
					if (khjlxm != null && !khjlxm.equals("") && selectByBMbindid != null) {
						BO_CRM_CLIENTBUS_DPT bo_crm = new BO_CRM_CLIENTBUS_DPT();
						bo_crm.setKhjlxm(khjlxm);
						bo_crm.setKhjlzh(khjlzh);
						bo_crm.setKhmc(khmc1);
						bo_crm.setKhbh(khbh);
						bo_crm.setBindid(Integer.parseInt(bindid));
						boCrmClientbusDptServer.UpdateByBindid(bo_crm);
					}

					Map<String, Object> handleTaskResult = new HashMap();
					handleTaskResult = restWorkFlowService.ExcuteTask(Integer.parseInt(bindid),userid,Integer.parseInt(task_id),
							zdr + "_" + zdrq + "_" + khmc + "_" + "_新增单位客户申请", "", "同意", opinion);
					String message = (String) handleTaskResult.get("message");
					json = message;
					resultMap.put("result", json);
					resultMap.put("type", "success");
				}else if (StepNo == 4 || StepNo == 6) {
					if(StepNo == 4 ){
						String khjlxm = fwr;
						if (khjlxm == null || khjlxm.equals("")) {
							khjlxm = "";
						}
						String khjlzh = fwrid;
						if (khjlzh == null || khjlzh.equals("")) {
							khjlzh = "";
						}
						String khmc1 = selectBybindid.getKhmc();
						String khbh = selectBybindid.getKhbh();
						if (khbh == null || khbh.equals("")) {
							khbh = "";
						}
						BO_CRM_CLIENTBUS_P bo_CRM_CLIENTBUS_P = new BO_CRM_CLIENTBUS_P();
						bo_CRM_CLIENTBUS_P.setKhjlxm(khjlxm);
						bo_CRM_CLIENTBUS_P.setKhjlzh(khjlzh);
						bo_CRM_CLIENTBUS_P.setBindid(Integer.parseInt(bindid));
						boCrmClientbusPServer.updateBoCRMR(bo_CRM_CLIENTBUS_P);
	
						List<BO_CRM_CLIENTBUS_DPT> selectByBMbindid = boCrmClientbusDptServer.selectByBMbindid(bindid);
						if (khjlxm != null && !khjlxm.equals("") && selectByBMbindid != null) {
							BO_CRM_CLIENTBUS_DPT bo_crm = new BO_CRM_CLIENTBUS_DPT();
							bo_crm.setKhjlxm(khjlxm);
							bo_crm.setKhjlzh(khjlzh);
							bo_crm.setKhmc(khmc1);
							bo_crm.setKhbh(khbh);
							bo_crm.setBindid(Integer.parseInt(bindid));
							boCrmClientbusDptServer.UpdateByBindid(bo_crm);
						}
					}
					String flag = selectBybindid.getFlag();
					if ("1".equals(flag)) {
						Map<String, Object> handleTaskResult = new HashMap();
						handleTaskResult = restWorkFlowService.ExcuteTask(Integer.parseInt(bindid),userid,Integer.parseInt(task_id),
								zdr + "_" + zdrq + "_" + khmc + "_" + "_新增单位客户申请", "", "同意", opinion);
						String message = (String) handleTaskResult.get("message");
						// String message1 = (String)handleTaskResult.get(2);
						json = message;
						resultMap.put("result", json);
						resultMap.put("type", "success");
					} else {
						BO_CRM_CLIENTBUS_P selectBy = boCrmClientbusPServer.selectBybindid(bindid);
						String DWBH = JDPUtils.getSuffixNumberByPrefixValue("B", 7, boCrmClientbusPServer);
						SimpleDateFormat faDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化时间
						BO_CRM_CLIENTBUS_P bo_crm = new BO_CRM_CLIENTBUS_P();
						bo_crm.setFlag("1");
						bo_crm.setKhbh(DWBH);
						bo_crm.setBindid(Integer.parseInt(bindid));
						boCrmClientbusPServer.UpdateByBindid(bo_crm);
						BO_CRM_CLIENT_P bo_CRM_CLIENT_P = new BO_CRM_CLIENT_P();
						bo_CRM_CLIENT_P.setOrgno("1");
						bo_CRM_CLIENT_P.setUpdateuser(selectBybindid.getUpdateuser());
						bo_CRM_CLIENT_P.setWorkflowid(30492);
						bo_CRM_CLIENT_P.setWorkflowstepid(30493);

						bo_CRM_CLIENT_P.setGsid(selectBybindid.getGsid() == null ? "" : selectBybindid.getGsid());
						bo_CRM_CLIENT_P.setGsdm(selectBybindid.getGsdm() == null ? "" : selectBybindid.getGsdm());
						bo_CRM_CLIENT_P.setGsmc(selectBybindid.getGsmc() == null ? "" : selectBybindid.getGsmc());
						bo_CRM_CLIENT_P.setBmid(selectBybindid.getBmid() == null ? "" : selectBybindid.getBmid());
						bo_CRM_CLIENT_P
								.setBmqljid(selectBybindid.getBmqljid() == null ? "" : selectBybindid.getBmqljid());
						bo_CRM_CLIENT_P.setBmmc(selectBybindid.getBmmc() == null ? "" : selectBybindid.getBmmc());
						bo_CRM_CLIENT_P.setSssyb(JDPUtils.NullValidate(selectBybindid.getSssyb()));
						bo_CRM_CLIENT_P.setZdrzh(selectBybindid.getZdrzh() == null ? "" : selectBybindid.getZdrzh());
						bo_CRM_CLIENT_P.setZdr(selectBybindid.getZdr() == null ? "" : selectBybindid.getZdr());
						bo_CRM_CLIENT_P.setZdrq(selectBybindid.getZdrq());
						bo_CRM_CLIENT_P.setKhmc(selectBybindid.getKhmc() == null ? "" : selectBybindid.getKhmc());
						bo_CRM_CLIENT_P.setKhbh(DWBH);

						bo_CRM_CLIENT_P.setKhjc(selectBybindid.getKhjc() == null ? "" : selectBybindid.getKhjc());
						bo_CRM_CLIENT_P.setHydm(selectBybindid.getHydm() == null ? "" : selectBybindid.getHydm());
						bo_CRM_CLIENT_P.setHyxldm(selectBybindid.getHyxldm() == null ? "" : selectBybindid.getHyxldm());
						bo_CRM_CLIENT_P.setDwxzdm(selectBybindid.getDwxzdm() == null ? "" : selectBybindid.getDwxzdm());
						bo_CRM_CLIENT_P.setDwxz(selectBybindid.getDwxzdm() == null ? "" : selectBybindid.getDwxzdm());
						bo_CRM_CLIENT_P.setZjdh(selectBybindid.getZjdh() == null ? "" : selectBybindid.getZjdh());
						bo_CRM_CLIENT_P.setGfwz(selectBybindid.getGfwz() == null ? "" : selectBybindid.getGfwz());
						bo_CRM_CLIENT_P.setZgjg(selectBybindid.getZgjg() == null ? "" : selectBybindid.getZgjg());
						bo_CRM_CLIENT_P.setBgdz(selectBybindid.getBgdz() == null ? "" : selectBybindid.getBgdz());
						bo_CRM_CLIENT_P.setCz(selectBybindid.getCz() == null ? "" : selectBybindid.getCz());
						bo_CRM_CLIENT_P.setFwbmmc(selectBybindid.getFwbmmc() == null ? "" : selectBybindid.getFwbmmc());
						bo_CRM_CLIENT_P.setFwbmid(selectBybindid.getFwbmid() == null ? "" : selectBybindid.getFwbmid());
						bo_CRM_CLIENT_P.setFwbmssgsmc(
								selectBybindid.getFwbmssgsmc() == null ? "" : selectBybindid.getFwbmssgsmc());
						bo_CRM_CLIENT_P.setFwbmssgsdm(
								selectBybindid.getFwbmssgsdm() == null ? "" : selectBybindid.getFwbmssgsdm());
						bo_CRM_CLIENT_P.setSjdw(selectBybindid.getSjdw() == null ? "" : selectBybindid.getSjdw());
						bo_CRM_CLIENT_P.setSjdwbh(selectBybindid.getSjdwbh() == null ? "" : selectBybindid.getSjdwbh());
						bo_CRM_CLIENT_P.setKhjlxm(selectBy.getKhjlxm() ==null ? "" : selectBy.getKhjlxm());
						bo_CRM_CLIENT_P.setKhjlzh(selectBy.getKhjlzh() == null ? "" : selectBy.getKhjlzh());
						bo_CRM_CLIENT_P.setBmzjxm(selectBybindid.getBmzjxm() == null ? "" : selectBybindid.getBmzjxm());
						bo_CRM_CLIENT_P.setBmzjzh(selectBybindid.getBmzjzh() == null ? "" : selectBybindid.getBmzjzh());
						bo_CRM_CLIENT_P.setClrq(selectBybindid.getClrq() == null ? "" : selectBybindid.getClrq());
						bo_CRM_CLIENT_P.setSfss(selectBybindid.getSfss() == null ? "" : selectBybindid.getSfss());
						bo_CRM_CLIENT_P.setZczb(selectBybindid.getZczb() == null ? "" : selectBybindid.getZczb());
						bo_CRM_CLIENT_P.setHydwdm(selectBybindid.getHydwdm() == null ? "" : selectBybindid.getHydwdm());
						bo_CRM_CLIENT_P.setDwgm(selectBybindid.getDwgmdm() == null ? "" : selectBybindid.getDwgmdm());
						bo_CRM_CLIENT_P.setDwgmdm(selectBybindid.getDwgmdm() == null ? "" : selectBybindid.getDwgmdm());

						bo_CRM_CLIENT_P.setScfe(selectBybindid.getScfe());
						bo_CRM_CLIENT_P.setJyfw(selectBybindid.getJyfw() == null ? "" : selectBybindid.getJyfw());
						bo_CRM_CLIENT_P.setZyly(selectBybindid.getZyly() == null ? "" : selectBybindid.getZyly());
						bo_CRM_CLIENT_P.setGj(selectBybindid.getGj() == null ? "" : selectBybindid.getGj());
						bo_CRM_CLIENT_P.setSf(selectBybindid.getSf() == null ? "" : selectBybindid.getSf());
						bo_CRM_CLIENT_P.setCs(selectBybindid.getCs() == null ? "" : selectBybindid.getCs());
						bo_CRM_CLIENT_P.setCsdm(selectBybindid.getCsdm() == null ? "" : selectBybindid.getCsdm());
						String gj = selectBybindid.getGj();
						String sf = selectBybindid.getSf();
						String cs = selectBybindid.getCs();
						String Xxdz = selectBybindid.getXxdz();
						if(Xxdz==null){
							Xxdz="";
						}
						String xxdz=gj+sf+cs+Xxdz;
						bo_CRM_CLIENT_P.setXxdz(xxdz == null ? "" : xxdz);
						bo_CRM_CLIENT_P.setNsrzgdm(selectBybindid.getNsrzgdm() == null ? "" : selectBybindid.getNsrzgdm());
						bo_CRM_CLIENT_P.setDkhzjzh(selectBybindid.getDkhzjzh() == null ? "" : selectBybindid.getDkhzjzh());
						bo_CRM_CLIENT_P.setDkhzjxm(selectBybindid.getDkhzjxm() == null ? "" : selectBybindid.getDkhzjxm());
						bo_CRM_CLIENT_P.setFptt(selectBybindid.getFptt() == null ? "" : selectBybindid.getFptt());
						bo_CRM_CLIENT_P.setFj(selectBybindid.getFj() == null ? "" : selectBybindid.getFj());
						bo_CRM_CLIENT_P.setTzcdz(selectBybindid.getTzcdz() == null ? "" : selectBybindid.getTzcdz());
						bo_CRM_CLIENT_P.setJtwlkh(selectBybindid.getJtwlkh() == null ? 1 : selectBybindid.getJtwlkh());
						bo_CRM_CLIENT_P.setKhzt(selectBybindid.getKhzt() == null ? "" : selectBybindid.getKhzt());
						bo_CRM_CLIENT_P.setZh(selectBybindid.getZh() == null ? "" : selectBybindid.getZh());
						bo_CRM_CLIENT_P.setHm(selectBybindid.getHm() == null ? "" : selectBybindid.getHm());
						bo_CRM_CLIENT_P.setKhh(selectBybindid.getKhh() == null ? "" : selectBybindid.getKhh());
						bo_CRM_CLIENT_P.setBz(selectBybindid.getBz() == null ? "" : selectBybindid.getBz());
						bo_CRM_CLIENT_P.setSfysjdw(selectBybindid.getSfysjdw() == null ? "" : selectBybindid.getSfysjdw());
						bo_CRM_CLIENT_P.setHzcs(0);
						bo_CRM_CLIENT_P.setCreateuser(selectBybindid.getCreateuser());
						bo_CRM_CLIENT_P.setBindid(Integer.parseInt(bindid));
						BO_CRM_CLIENT_P selectById = boCrmClientptServer.selectById(Integer.parseInt(bindid));
						if (selectById == null) {

							boCrmClientptServer.insert(bo_CRM_CLIENT_P);
						}
						BO_CRM_CLIENTBUS_REG regVector = boCrmClientregtServer.selectByBindid(Integer.parseInt(bindid));
						if (regVector != null) {
							boCrmClientreServer.UpdateByBindid(regVector);
						}
						BO_CRM_INDCLIENT_EVE eventVector = boCrmClientregtServer.selectByEve(Integer.parseInt(bindid));
						if (eventVector != null) {
							boCrmClientreServer.UpdateBy(eventVector);
						}
						List<BO_CRM_CLIENTBUS_DPT> deptVector = boCrmClientbusDptServer.selectByBMbindid(bindid);
						String dkhzjString = selectBybindid.getDkhzjzh();
						// 大客户总监所属公司名称
						String ssgsdmString = "";
						String ssgsmcString = "";
						String szbmid = "";
						String szbmmc = "";
						String szgsdm = "";
						String szgsmc = "";
						String BMBH = "";
						String KHBMBH = "";
						String KHBMMC = "";
						String gsid = "";
						BO_CRM_CLIENT_SERDPT bo_CRM_CLIENT_SERDPT = new BO_CRM_CLIENT_SERDPT();
						if (dkhzjString == null) {
							dkhzjString = "";
						}

						if (dkhzjString.contains("<")) {
							dkhzjString = dkhzjString.substring(0, dkhzjString.indexOf("<"));
						}
						if (!dkhzjString.equals("")) {
							Dept userGSDM = userService.getUserGSDM(dkhzjString);
							ssgsdmString = userGSDM.getGsdm();
							// 大客户总监所属公司代码
							Dept gsdmGSMC = userService.getGsdmGSMC(ssgsdmString);
							ssgsmcString = gsdmGSMC.getName();
						}
						String khjlbh = JDPUtils.NullValidate(selectBybindid.getKhjlzh());
						String fwbmssgsmc = JDPUtils.NullValidate(selectBybindid.getFwbmssgsmc());
						String fwbmssgsdm = JDPUtils.NullValidate(selectBybindid.getFwbmssgsdm());
						gsid=userService.getGsid(fwbmssgsdm);
						if (khjlbh == null) {
							khjlbh = "";
						}
						if (khjlbh.contains("<")) {
							khjlbh = khjlbh.substring(0, khjlbh.indexOf("<"));
						}
						if (!khjlbh.equals("")) {
							Dept khBMID = userService.getKhBMID(khjlbh);
							szbmid = khBMID.getGsdm();
						}
						if (!szbmid.equals("")) {
							Dept gsdmGSID = userService.getGsdmGSID(szbmid);
							szbmmc = gsdmGSID.getName();
						}
						if (!szbmid.equals("")) {
							Dept bMidGS = userService.getBMidGS(szbmid);
							szgsdm = bMidGS.getGsdm();
							Dept bMidGSID = userService.getBMidGSID(szbmid);
							szgsmc = bMidGSID.getName();
						}
						BMBH = JDPUtils.getSuffixNumberByPrefixValue("B", 7, boCrmClientbusPServer);
						KHBMBH = BMBH;
						/**
						 * @author zhangsonghe
						 * 修改，由增加一条变多条
						 */
						for(BO_CRM_CLIENTBUS_DPT crmdpt : deptVector) {
							BO_CRM_CLIENT_DPT bo_CRM_CLIENT_DPT = new BO_CRM_CLIENT_DPT();
							bo_CRM_CLIENT_DPT.setOrgno("1");
							bo_CRM_CLIENT_DPT.setBmmc(crmdpt.getBmmc() == null ? "" : crmdpt.getBmmc());
							bo_CRM_CLIENT_DPT.setBmxzdm(crmdpt.getBmxzdm() == null ? "" : crmdpt.getBmxzdm());
							bo_CRM_CLIENT_DPT.setBz(crmdpt.getBz() == null ? "" : crmdpt.getBz());
							bo_CRM_CLIENT_DPT.setKhbh(DWBH);
							bo_CRM_CLIENT_DPT.setBmbh(KHBMBH);
							bo_CRM_CLIENT_DPT.setSfysjbm("0");
							KHBMMC=bo_CRM_CLIENT_DPT.getBmmc();
							bo_CRM_CLIENT_DPT.setSjbmmc("无上级部门");
							bo_CRM_CLIENT_DPT.setSjbmbh(DWBH + "000");
							bo_CRM_CLIENT_DPT.setCreateuser(selectBybindid.getCreateuser());
							bo_CRM_CLIENT_DPT.setUpdateuser(selectBybindid.getUpdateuser());
							bo_CRM_CLIENT_DPT.setOrgno("1");
							bo_CRM_CLIENT_DPT.setBindid(selectBybindid.getBindid());
							boCrmClientbusDptServer.insertClientDPT(bo_CRM_CLIENT_DPT);
						}
						

						bo_CRM_CLIENT_SERDPT
								.setKhjlzh(selectBy.getKhjlzh()  == null ? "" : selectBy.getKhjlzh());
						bo_CRM_CLIENT_SERDPT
								.setKhjlxm(selectBy.getKhjlxm() == null ? "" : selectBy.getKhjlxm());
						bo_CRM_CLIENT_SERDPT.setBmid(selectBybindid.getFwbmid()== null ? "" : selectBybindid.getFwbmid());
						bo_CRM_CLIENT_SERDPT.setBmmc(selectBybindid.getFwbmmc() == null ? "" :selectBybindid.getFwbmmc());
						bo_CRM_CLIENT_SERDPT.setGsdm(fwbmssgsdm == null ? "" : fwbmssgsdm);
						bo_CRM_CLIENT_SERDPT.setGsmc(fwbmssgsmc == null ? "" : fwbmssgsmc);
						bo_CRM_CLIENT_SERDPT.setGsid(gsid);
						bo_CRM_CLIENT_SERDPT
								.setDkhzjxm(selectBybindid.getDkhzjxm() == null ? "" : selectBybindid.getDkhzjxm());
						bo_CRM_CLIENT_SERDPT
								.setDkhzjzh(selectBybindid.getDkhzjzh() == null ? "" : selectBybindid.getDkhzjzh());
						bo_CRM_CLIENT_SERDPT.setKhbmbh(KHBMBH == null ? "" : KHBMBH);
						bo_CRM_CLIENT_SERDPT.setKhbmmc(KHBMMC == null ? "" : KHBMMC);
						bo_CRM_CLIENT_SERDPT.setSsgsdm(ssgsdmString == null ? "" : ssgsdmString);
						bo_CRM_CLIENT_SERDPT.setSsgsmc(ssgsmcString == null ? "" : ssgsmcString);
						bo_CRM_CLIENT_SERDPT.setKhbh(DWBH);
						bo_CRM_CLIENT_SERDPT.setOrgno("1");
						bo_CRM_CLIENT_SERDPT.setCreateuser(selectBybindid.getCreateuser());
						bo_CRM_CLIENT_SERDPT.setUpdateuser(selectBybindid.getUpdateuser());
						bo_CRM_CLIENT_SERDPT.setBindid(selectBybindid.getBindid());
						bo_CRM_CLIENT_SERDPT.setWorkflowid(30492);
						bo_CRM_CLIENT_SERDPT.setWorkflowstepid(30493);
						bo_CRM_CLIENT_SERDPT.setUpdateuser(selectBybindid.getUpdateuser());
						
						boCrmClientbusDptServer.insertSerdpt(bo_CRM_CLIENT_SERDPT);
						
						List<BO_CRM_CLIENTBUS_LM> selectByid = boCrmClientbusLmServer.selectByid(bindid);
						if (selectByid != null && selectByid.size() > 0) {
							for (int i = 0; i < selectByid.size(); i++) {
								BO_CRM_INDCLIENT_P LM = new BO_CRM_INDCLIENT_P();
								BO_CRM_CLIENTBUS_LM bo_CRM_CLIENTBUS_LM = selectByid.get(i);
								List<BO_CRM_INDCLIENTBUS_EVE> selectByEVE = boCrmClientbusLmServer.selectByEVE(bindid,bo_CRM_CLIENTBUS_LM.getId().toString());
								if (selectByEVE != null && selectByEVE.size() > 0) {
									for(int j=0;j<selectByEVE.size();j++){
										BO_CRM_INDCLIENTBUS_EVE bo_CRM_INDCLIENTBUS_EVE = selectByEVE.get(j);
										BO_CRM_INDCLIENTBUS_EVE bo_CRM_INDCLIENTBUS_EVE2 = new BO_CRM_INDCLIENTBUS_EVE();
										bo_CRM_INDCLIENTBUS_EVE2.setFsdd(bo_CRM_INDCLIENTBUS_EVE2.getFsdd() == null ? "" :bo_CRM_INDCLIENTBUS_EVE2.getFsdd());
										bo_CRM_INDCLIENTBUS_EVE2.setFsrq(bo_CRM_INDCLIENTBUS_EVE2.getFsrq());
										bo_CRM_INDCLIENTBUS_EVE2.setLssj(bo_CRM_INDCLIENTBUS_EVE2.getLssj()== null ? "" :bo_CRM_INDCLIENTBUS_EVE2.getLssj());
										bo_CRM_INDCLIENTBUS_EVE2.setParentsubid(bo_CRM_INDCLIENTBUS_EVE2.getParentsubid());
										bo_CRM_INDCLIENTBUS_EVE2.setOrgno("1");
										bo_CRM_INDCLIENTBUS_EVE2.setCreateuser(selectBybindid.getCreateuser());
										bo_CRM_INDCLIENTBUS_EVE2.setUpdateuser(selectBybindid.getUpdateuser());
										bo_CRM_INDCLIENTBUS_EVE2.setBindid(selectBybindid.getBindid());
										boCrmClientbusLmServer.insertSelectiveEVE(bo_CRM_INDCLIENTBUS_EVE2);
										
									}
								}
								 List<BO_CRM_INDCLIENTBUS_HOB> selectByHOB = boCrmClientbusLmServer.selectByHOB(bindid,bo_CRM_CLIENTBUS_LM.getId().toString());
								 if (selectByHOB != null && selectByHOB.size() > 0) {
										for(int j=0;j<selectByEVE.size();j++){
											BO_CRM_INDCLIENTBUS_HOB bo_CRM_INDCLIENTBUS_HOB = selectByHOB.get(j);
											BO_CRM_INDCLIENTBUS_HOB bo_CRM_INDCLIENTBUS_HOB2 = new BO_CRM_INDCLIENTBUS_HOB ();
											bo_CRM_INDCLIENTBUS_HOB2.setXqahfldm(bo_CRM_INDCLIENTBUS_HOB.getXqahfldm()==null?"":bo_CRM_INDCLIENTBUS_HOB.getXqahfldm());
											bo_CRM_INDCLIENTBUS_HOB2.setMs(bo_CRM_INDCLIENTBUS_HOB.getMs()==null?"":bo_CRM_INDCLIENTBUS_HOB.getMs());
											bo_CRM_INDCLIENTBUS_HOB2.setParentsubid(bo_CRM_INDCLIENTBUS_HOB.getParentsubid());										
											bo_CRM_INDCLIENTBUS_HOB2.setCreateuser(selectBybindid.getCreateuser());
											bo_CRM_INDCLIENTBUS_HOB2.setUpdateuser(selectBybindid.getUpdateuser());
											bo_CRM_INDCLIENTBUS_HOB2.setBindid(selectBybindid.getBindid());
											bo_CRM_INDCLIENTBUS_HOB2.setOrgno("1");

											boCrmClientbusLmServer.insertSelectiveHOB(bo_CRM_INDCLIENTBUS_HOB2);
											
										}
									}
								 List<BO_CRM_INDCLIENTBUS_TR> selectByTR = boCrmClientbusLmServer.selectByTR(bindid,bo_CRM_CLIENTBUS_LM.getId().toString());
								 if (selectByTR != null && selectByTR.size() > 0) {
										for(int j=0;j<selectByEVE.size();j++){
											 BO_CRM_INDCLIENTBUS_TR bo_CRM_INDCLIENTBUS_TR = selectByTR.get(j);
											 BO_CRM_INDCLIENTBUS_TR bo_CRM_INDCLIENTBUS_TR2 = new BO_CRM_INDCLIENTBUS_TR();
											 bo_CRM_INDCLIENTBUS_TR2.setGzdw(bo_CRM_INDCLIENTBUS_TR.getGzdw()==null?"":bo_CRM_INDCLIENTBUS_TR.getGzdw());
											 bo_CRM_INDCLIENTBUS_TR2.setSzbm(bo_CRM_INDCLIENTBUS_TR.getSzbm()==null?"":bo_CRM_INDCLIENTBUS_TR.getSzbm());
											 bo_CRM_INDCLIENTBUS_TR2.setDwdh(bo_CRM_INDCLIENTBUS_TR.getDwdh()==null?"":bo_CRM_INDCLIENTBUS_TR.getDwdh());
											 bo_CRM_INDCLIENTBUS_TR2.setZw(bo_CRM_INDCLIENTBUS_TR.getZw()==null?"":bo_CRM_INDCLIENTBUS_TR.getZw());
											 bo_CRM_INDCLIENTBUS_TR2.setZyly(bo_CRM_INDCLIENTBUS_TR.getZyly()==null?"":bo_CRM_INDCLIENTBUS_TR.getZyly());
											 bo_CRM_INDCLIENTBUS_TR2.setRzrq(bo_CRM_INDCLIENTBUS_TR.getRzrq());
											 bo_CRM_INDCLIENTBUS_TR2.setLzrq(bo_CRM_INDCLIENTBUS_TR.getLzrq());
											 bo_CRM_INDCLIENTBUS_TR2.setParentsubid(bo_CRM_INDCLIENTBUS_TR.getParentsubid());
											 bo_CRM_INDCLIENTBUS_TR2.setCreateuser(selectBybindid.getCreateuser());
											 bo_CRM_INDCLIENTBUS_TR2.setUpdateuser(selectBybindid.getUpdateuser());
											 bo_CRM_INDCLIENTBUS_TR2.setBindid(selectBybindid.getBindid());
											 bo_CRM_INDCLIENTBUS_TR2.setOrgno("1");
											boCrmClientbusLmServer.insertSelectiveTR(bo_CRM_INDCLIENTBUS_TR2);
											
										}
									}
								Integer parentid = bo_CRM_CLIENTBUS_LM.getParentsubid();
								String bmbh = boCrmClientbusDptServer.selectBmbh(parentid.toString());
								Integer id2 = bo_CRM_CLIENTBUS_LM.getId();
								String LXRBH = JDPUtils.getSuffixNumberByPrefixValue("B", 3, boCrmClientbusPServer);
								LM.setBindid(selectBybindid.getBindid());
								LM.setLx(bo_CRM_CLIENTBUS_LM.getLx()== null ? "" :bo_CRM_CLIENTBUS_LM.getLx());
								LM.setKhbh(DWBH);
								LM.setBmbh(KHBMBH);
								LM.setBmxzdm(bo_CRM_CLIENTBUS_LM.getBmxzdm()== null ? "" :bo_CRM_CLIENTBUS_LM.getBmxzdm());
								LM.setXm(bo_CRM_CLIENTBUS_LM.getXm()== null ? "" :bo_CRM_CLIENTBUS_LM.getXm());
								LM.setYw(bo_CRM_CLIENTBUS_LM.getYw()== null ? "" :bo_CRM_CLIENTBUS_LM.getYw());
								LM.setGjdm(bo_CRM_CLIENTBUS_LM.getGjdm()== null ? "" :bo_CRM_CLIENTBUS_LM.getGjdm());
								LM.setMzdm(bo_CRM_CLIENTBUS_LM.getMzdm()== null ? "" :bo_CRM_CLIENTBUS_LM.getMzdm());
								LM.setXbdm(bo_CRM_CLIENTBUS_LM.getXbdm()== null ? "" :bo_CRM_CLIENTBUS_LM.getXbdm());
								LM.setCsrq(bo_CRM_CLIENTBUS_LM.getCsrq());
								LM.setHyzkdm(bo_CRM_CLIENTBUS_LM.getHyzkdm()== null ? "" :bo_CRM_CLIENTBUS_LM.getHyzkdm());
								LM.setZjxydm(bo_CRM_CLIENTBUS_LM.getZjxydm()== null ? "" :bo_CRM_CLIENTBUS_LM.getZjxydm());
								LM.setZzmmdm(bo_CRM_CLIENTBUS_LM.getZzmmdm()== null ? "" :bo_CRM_CLIENTBUS_LM.getZzmmdm());
								LM.setQtshjs(bo_CRM_CLIENTBUS_LM.getQtshjs()== null ? "" :bo_CRM_CLIENTBUS_LM.getQtshjs());
								LM.setIfdam(bo_CRM_CLIENTBUS_LM.getIfdam()== null ? "" :bo_CRM_CLIENTBUS_LM.getIfdam());
								LM.setRyztdm(bo_CRM_CLIENTBUS_LM.getRyztdm()== null ? "" :bo_CRM_CLIENTBUS_LM.getRyztdm());
								LM.setJsdm(bo_CRM_CLIENTBUS_LM.getJsdm()== null ? "" :bo_CRM_CLIENTBUS_LM.getJsdm());
								LM.setZwmc(bo_CRM_CLIENTBUS_LM.getZwmc()== null ? "" :bo_CRM_CLIENTBUS_LM.getZwmc());
								LM.setXrzdw(bo_CRM_CLIENTBUS_LM.getXrzdw()== null ? "" :bo_CRM_CLIENTBUS_LM.getXrzdw());
								LM.setXrzdwbh(bo_CRM_CLIENTBUS_LM.getXrzdwbh()== null ? "" :bo_CRM_CLIENTBUS_LM.getXrzdwbh());
								LM.setDrzw(bo_CRM_CLIENTBUS_LM.getDrzw()== null ? "" :bo_CRM_CLIENTBUS_LM.getDrzw());
								LM.setDwdh(bo_CRM_CLIENTBUS_LM.getDwdh()== null ? "" :bo_CRM_CLIENTBUS_LM.getDwdh());
								LM.setYddh(bo_CRM_CLIENTBUS_LM.getYddh()== null ? "" :bo_CRM_CLIENTBUS_LM.getYddh());
								LM.setEmail(bo_CRM_CLIENTBUS_LM.getEmail()== null ? "" :bo_CRM_CLIENTBUS_LM.getEmail());
								LM.setLxrbh(LXRBH);						
								LM.setZjh(bo_CRM_CLIENTBUS_LM.getZjh() == null ? "" : bo_CRM_CLIENTBUS_LM.getZjh());
								LM.setOrgno("1");
								LM.setCreateuser(selectBybindid.getCreateuser());
								LM.setUpdateuser(selectBybindid.getUpdateuser());
								LM.setUpdateuser(selectBybindid.getUpdateuser());
								LM.setWorkflowid(30465);
								LM.setWorkflowstepid(30466);
								boCrmClientbusPServer.insertSelective(LM);

							}

						}
						

						Map<String, Object> handleTaskResult = new HashMap();
						handleTaskResult = restWorkFlowService.ExcuteTask(Integer.parseInt(bindid),userid, Integer.parseInt(task_id),
								zdr + "_" + zdrq + "_" + khmc + "_" + "_新增单位客户申请", "", "同意", opinion);
						String message = (String) handleTaskResult.get("message");
						json = message;
						resultMap.put("result", json);
						resultMap.put("type", "success");

					}
				} else {

					// 获取下个节点号
					Map<String, Object> handleTaskResult = new HashMap();

					Integer nextStepNo = Integer.valueOf(restWorkFlowService.GetNextStepNo(userid,Integer.parseInt(bindid),
							Integer.parseInt(task_id)));
					handleTaskResult = restWorkFlowService.ExcuteTask(Integer.parseInt(bindid),userid, Integer.parseInt(task_id),
							zdr + "_" + zdrq + "_" + khmc + "_" + "_新增单位客户申请", "", "同意", opinion);

					// 判断是否是最后节点
					if (nextStepNo == -1) {
						resultMap.put("result", "任务流转已完毕");
						resultMap.put("type", "success");
					} else {
						// 办理至下一节点，返回消息
						String message = (String) handleTaskResult.get("message");
						// String message1 = (String)handleTaskResult.get(2);
						json = message;
						resultMap.put("result", json);
						resultMap.put("type", "success");

					}

				}

			} else {
				Map<String, Object> repulseTaskResult = restWorkFlowService.RepulseTask3(Integer.parseInt(bindid),userid,
						Integer.parseInt(task_id), zdr + "_" + zdrq + "_" + khmc + "__新增单位客户申请",
						1,"不同意",
						opinion);
				String message = (String) repulseTaskResult.get("message");
				json = message;
				resultMap.put("result", message);
				resultMap.put("type", "success");
			}
			return objectMapper.writeValueAsString(resultMap);
		} catch (Exception e) {
			logger.error(e.getMessage());
 			String json1 = "当前节点办理时出现问题，请联系管理员";
			resultMap.put("result", json1);
			resultMap.put("type", "error");
			return objectMapper.writeValueAsString(resultMap);
		}
	}

	/**
	 * 获取当前单据状态
	 *
	 * @return
	 */
	@RequestMapping(value = "/getStatusOfTask", method = RequestMethod.GET)
	@ResponseBody
	public String getStatusOfTask(String bindid, String taskid) {
		// 待办任务状态
		String dbStatus = boCrmClientbusPServer.getDbStatusOfTask(bindid, taskid);
		// 已办任务状态
		String ybStatus = boCrmClientbusPServer.getYbStatusOfTask(bindid, taskid);

		Map m = new HashMap<>();
		m.put("dbStatus", dbStatus);
		m.put("ybStatus", ybStatus);
		String res = "";
		try {
			res = objectMapper.writeValueAsString(m);
		} catch (JsonProcessingException e) {
			logger.error("",e);
		}
		return res;
	}

	/**
	 * 根据userid查询用户扩展信息
	 * 
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/getUserExtByUserid", method = RequestMethod.GET)
	@ResponseBody
	public String getUserExtByUserid(String userid) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			User user = userService.getUserExtByUserid(userid);
			String json = objectMapper.writeValueAsString(user);
			return json;
		} catch (Exception e) {
			logger.error("",e);
			return "访问数据有误，请联系系统管理员";
		}
	}

	/**
	 * 查询GS
	 *
	 * @return
	 */
	@RequestMapping(value = "/getGS", method = RequestMethod.GET)
	@ResponseBody
	public String getGS() {
		ObjectMapper mapper = new ObjectMapper();

		List<Dept> gsname = userService.getGSName();

		List<Object> os = new ArrayList<>();
		os.addAll(gsname);
		try {
			return mapper.writeValueAsString(os);
		} catch (JsonProcessingException e) {
			logger.error("",e);
		}
		return "";
	}

	/**
	 * 查询bm
	 *
	 * @return
	 */
	@RequestMapping(value = "/getBM", method = RequestMethod.GET)
	@ResponseBody
	public String getBM(String gsid) {
		ObjectMapper mapper = new ObjectMapper();

		List<Dept> bmname = userService.getDeptByGSId(gsid);

		List<Object> os = new ArrayList<>();
		os.addAll(bmname);
		try {
			return mapper.writeValueAsString(os);
		} catch (JsonProcessingException e) {
			logger.error("",e);
		}
		return "";
	}

	/**
	 * 查询部门下的人
	 *
	 * @return
	 */
	@RequestMapping(value = "/getPeopleByBMId", method = RequestMethod.GET)
	@ResponseBody
	public String getPeople(String bmid) {
		ObjectMapper mapper = new ObjectMapper();

		List<Dept> bmname = userService.getPeopleByBMId(bmid);

		List<Object> os = new ArrayList<>();
		os.addAll(bmname);
		try {
			return mapper.writeValueAsString(os);
		} catch (JsonProcessingException e) {
			logger.error("",e);
		}
		return "";
	}

}
