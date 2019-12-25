package cn.bravolinks.erp.crm.server.feign;

import cn.bravolinks.erp.crm.server.feign.impl.BOImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 审批流服务 - BO 接口
 * @createDate 2018-1-3
 * @updateDate 2018-1-3
 * @author yanqin
 * @version 1.0
 */
@FeignClient(value = "${wfservice}",path = "/bo",fallback = BOImpl.class)
public interface BOService {

	/**
	 * downloadFileByField
	 * 读取【附件】类型字段存储在文件系统的文件内容。
	 * @param boId  BO 表的 ID 值，即该 BO 表中 ID 字段对应的值
	 * @param fieldUUID  附件类型字段的模型 UUID
	 * @param fileName 已上传附件的文件名
	 * @return  byte[] 文件字节数组，失败抛出 AWSSDKException 异常
	 */
	@RequestMapping(value = "/downloadFileByField/{boId}/{fieldUUID}/{fileName}",method = RequestMethod.GET)
	byte[] downloadFileByField(@PathVariable("boId") int boId, @PathVariable("fieldUUID") String fieldUUID, @PathVariable("fileName") String fileName);


	/**
	 *upFileByFiled
	 * 将附件上传到 BO 表【附件】字段值中，文件以加密方式存储到文件系统，如果同一记录中附件文件名字重复，系统以覆盖方式保存。
	 * @param boId  BO 表 ID，即该 BO 表中 ID 字段对应的值
	 * @param bytes  文件字节数组
	 * @param fieldUUID  附件类型字段的模型 UUID
	 * @param fileName  上传附件的文件名
	 * @return  无，失败抛出 AWSSDKException 异常
	 */
	@RequestMapping(value = "/upFileByFiled/{boId}/{fieldUUID}/{fileName:.+}",method = RequestMethod.POST)
	String upFileByFiled(@PathVariable("boId") int boId, @RequestBody byte[] bytes, @PathVariable("fieldUUID") String fieldUUID, @PathVariable("fileName") String fileName);

	/**
	 * removeFileByName
	 * 删除 BO 表【附件】字段值，并从文件系统中物理性删除存储的文件
	 * @param boId BO 表 ID，即该 BO 表中 ID 字段对应的值
	 * @param fieldUUID 附件类型字段的模型 UUID
	 * @param fileName  上传附件的文件名
	 * @return 无，失败抛出 AWSSDKException 异常
	 */
	@RequestMapping(value = "/removeFileByName",method = RequestMethod.GET)
	String removeFileByName(@RequestParam("boId") int boId, @RequestParam("fieldUUID") String fieldUUID, @RequestParam("fileName") String fileName);

	/**
	 * 获取指定BO表在某个工作流实例的第1条业务数据（主表记录或子表第 1条记录）。
	 * @param boTableName 表名称
	 * @param processInstanceId 工作流实例 ID ， 该 值 为 BO 表 bindid 或wf_message 表 id
	 * @return Hashtable 其主键为 String 类型的大写字段名，值为 String 型，当未发现记录时，返回空
	 */
	@RequestMapping(value = "/getBOData",method = RequestMethod.GET)
	Map<String,Object> getBOData(@RequestParam("boTableName") String boTableName, @RequestParam("processInstanceId") int processInstanceId);
}
