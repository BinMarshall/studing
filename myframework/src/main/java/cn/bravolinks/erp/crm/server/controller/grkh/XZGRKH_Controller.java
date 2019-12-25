package cn.bravolinks.erp.crm.server.controller.grkh;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bravolinks.erp.crm.server.feign.*;
import cn.bravolinks.erp.crm.server.service.grkh.XZGRKHService;
import cn.bravolinks.erp.crm.server.util.OutputDatas;

/**
 * 新增个人客户
 * 
 * @author ck
 *
 */
@Controller
@RequestMapping("/xzgrkh")
public class XZGRKH_Controller {

	private static final Logger logger = LoggerFactory.getLogger(XZGRKH_Controller.class);

	@Autowired
	private XZGRKHService xzgrkhService;

	@Autowired
	private RestWorkFlowService restWorkFlowService;

	@Autowired
	private WorkFlowService workFlowService;

	@Autowired
	private CustomService customService;

	@Autowired
	private BOService boService;

	/**
	 * 初始化页面
	 * @param bindid	流程bindid
	 * @param taskid	任务id
	 * @param userid	当前登录用户
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getGrkh")
	public String getGrkh(String bindid, String taskid, String userid) {
		OutputDatas output = null;
		try {
			Map<String, Object> resultMap = xzgrkhService.getGrkh(bindid);
			if (resultMap == null) {
				output = new OutputDatas(OutputDatas.RESULT_SUCCESS);
				output.setDatas("");
				return output.toString();
			}
			// 根据代码查找名称
			resultMap = xzgrkhService.getMC(resultMap);
			// 查找子表数据
			resultMap = xzgrkhService.getXq(resultMap, bindid);
			// 获取其他业务数据
			resultMap = xzgrkhService.getYwsj(resultMap, bindid, taskid, userid);

			output = new OutputDatas(OutputDatas.RESULT_SUCCESS);
			output.setDatas(resultMap);
		} catch (Exception e) {
			output = new OutputDatas(OutputDatas.RESULT_FAILURE);
			output.setErrinfo(e.getMessage() == null ? e.toString() : e.getMessage());
			output.setDatas("");
			logger.error(e.getMessage(), e);
			e.printStackTrace();

		}
		return output.toString();
	}

	/**
	 * 办理或打回
	 * @param bindid	流程bindid
	 * @param taskid	任务id
	 * @param isagree	是否同意
	 * @param userid	当前登录用户
	 * @param opinion	留言
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/handle")
	public String handle(String bindid, String taskid, String isagree, String userid, String opinion) {
		OutputDatas output = null;
		String TaskTitle = xzgrkhService.getTaskTitle(taskid);
		try {
			// 任务是否存在
			Integer tasktype = customService.getTaskIsHandle(Integer.parseInt(taskid));
			if (tasktype != 1) {
				output = new OutputDatas(OutputDatas.RESULT_SUCCESS);
				output.setDatas("任务已办理或不存在,请重新打开任务");
			}

			// 查询当前节点号
			Integer nextStepNo = restWorkFlowService.getStpeno(Integer.parseInt(taskid));
			if (nextStepNo == 2) {
				if (isagree.equals("同意")) {
					Map<String, Object> handleTaskResult = restWorkFlowService.ExcuteTask(Integer.parseInt(bindid),
							userid, Integer.parseInt(taskid), TaskTitle, "", "同意", opinion);
					// 办理至下一节点，返回消息
					String message = (String) handleTaskResult.get("message");
					output = new OutputDatas(OutputDatas.RESULT_SUCCESS);
					output.setDatas(message);
				} else {
					// 打回
					Map<String, Object> repulseTaskResult = restWorkFlowService.RepulseTask(Integer.parseInt(bindid),
							userid, Integer.parseInt(taskid), TaskTitle, "不同意", opinion);
					String message = (String) repulseTaskResult.get("message");
					output = new OutputDatas(OutputDatas.RESULT_SUCCESS);
					output.setDatas(message);
				}
			} else if (nextStepNo == 3) {
				if (isagree.equals("同意")) {
					// 更新个人客户信息主表的lx类型字段为“个人客户”,修改联系人编号
					String IndKHBH = xzgrkhService.getSuffixNumberByPrefixValue("C", 9);
					xzgrkhService.updateKhlx(bindid, IndKHBH);

					// 流程创建者
					Map<String, Object> createUserMap = customService.getWFCreateUserByBindid(Integer.parseInt(bindid));
					String createUser = (String) createUserMap.get("USERID");

					// 启动档案记录的视图,插入个人客户档案主表
					Map<String, Object> resultInstance = workFlowService
							.createBOInstance("c85d25ada603d25f66c6c29d24ef8d18", userid, "个人客户档案主表");
					String msg = (String) resultInstance.get("msg");
					if (!msg.equals("success")) {
						output = new OutputDatas(OutputDatas.RESULT_FAILURE);
						output.setDatas("创建档案记录视图失败");
						return output.toString();
					}
					String bind = resultInstance.get("data").toString();
					Map<String, Object> grkhMap = xzgrkhService.getGrkh(bindid);
					xzgrkhService.initParam(grkhMap, bind, createUser);
					grkhMap.put("LX", "个人客户"); // 联系人类型
					grkhMap.put("LXRBH", IndKHBH); // 联系人编号
					grkhMap.put("KHJLMC", (String) grkhMap.get("KHJLXM"));
					xzgrkhService.insertKhda(grkhMap);

					// 插入档案表子表相关数据
					xzgrkhService.insertArchives(bindid, bind, createUser);

					// 获取创建人用户信息
					Map<String, Object> userMap = xzgrkhService.getUserByUserid(createUser);
					// 获取当前节点信息
					Map<String, Object> currentStep = xzgrkhService
							.getCurrentStepTaskByTaskid(Integer.parseInt(taskid));
					userMap.putAll(currentStep);
					// 日志记录信息
					userMap.put("KHBH", IndKHBH);
					String khmc = (String) grkhMap.get("XM") == null ? "" : (String) grkhMap.get("XM"); // 维护类型
					String whlxdm = (String) grkhMap.get("WHLXDM") == null ? "" : (String) grkhMap.get("WHLXDM"); // 客户名称
					xzgrkhService.insertLog(userMap, Integer.parseInt(bindid), khmc, IndKHBH, whlxdm);

					// 办理至下一节点
					Map<String, Object> handleTaskResult = restWorkFlowService.ExcuteTask(Integer.parseInt(bindid),
							userid, Integer.parseInt(taskid), TaskTitle, "", "同意", opinion);
					String message = (String) handleTaskResult.get("message");
					output = new OutputDatas(OutputDatas.RESULT_SUCCESS);
					output.setDatas(message);

				} else {
					// 打回
					Map<String, Object> repulseTaskResult = restWorkFlowService.RepulseTask2(Integer.parseInt(bindid),
							userid, Integer.parseInt(taskid), TaskTitle, "不同意", opinion);
					String message = (String) repulseTaskResult.get("message");
					output = new OutputDatas(OutputDatas.RESULT_SUCCESS);
					output.setDatas(message);
				}
			}

		} catch (Exception e) {
			output = new OutputDatas(OutputDatas.RESULT_FAILURE);
			output.setErrinfo(e.getMessage() == null ? e.toString() : e.getMessage());
			output.setOthinfo("业务办理失败");
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return output.toString();
	}

	/**
	 * 文件下载
	 * @param bindid	流程bindid
	 * @param fileName	文件名
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/download")
	public ResponseEntity<byte[]> download(String bindid, String fileName) {
		OutputDatas output = null;
		try {
			// 获取文件uuid
			String fileUUID = xzgrkhService.getFileUUID("FJ", "BO_CRM_INDCLIENTBUS_P");
			// 查询ID
			String id = xzgrkhService.getId(bindid);
			byte[] bytes = boService.downloadFileByField(Integer.parseInt(id), fileUUID, fileName);
			//添加响应头
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName,"UTF-8"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //bodys响应体，headers响应头，status响应状态码
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
            return responseEntity;
		} catch (Exception e) {
			output = new OutputDatas(OutputDatas.RESULT_FAILURE);
			output.setErrinfo(e.getMessage() == null ? e.toString() : e.getMessage());
			output.setOthinfo("下载文件失败，请重新下载");
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 追踪返回链接
	 * 
	 * @param bindid
	 *            流程id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/trace")
	public String zhuiZong(String bindid) {
		OutputDatas output = null;
		try {
			List<Map<String, Object>> list = xzgrkhService.zhuiZong(bindid);
			if (list.size() > 0) {
				output = new OutputDatas(OutputDatas.RESULT_SUCCESS);
				output.setDatas(list);
			} else {
				output = new OutputDatas(OutputDatas.RESULT_FAILURE);
			}
		} catch (Exception e) {
			output = new OutputDatas(OutputDatas.RESULT_FAILURE);
			output.setErrinfo(e.getMessage() == null ? e.toString() : e.getMessage());
			output.setOthinfo("追踪数据失败");
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return output.toString();
	}

}
