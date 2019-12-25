package cn.bravolinks.erp.crm.server.util;

import java.util.ArrayList;
import java.util.List;

import cn.bravolinks.erp.crm.server.model.xzkh.BO_CRM_CLIENTBUS_P;
import cn.bravolinks.erp.crm.server.service.xzkh.BO_CRM_CLIENTBUS_LMService;
import cn.bravolinks.erp.crm.server.service.xzkh.BO_CRM_CLIENTBUS_PServer;

public class JDPUtils {

	/**
	 * 功能：判断获取值是否为空；若为空则赋值为空串
	 * 
	 * @author jdp
	 */
	public static String NullValidate(String rsname) {
		return (rsname != null && !"".equals(rsname)) ? rsname : "";
	}

	/**
	 * 自动生成序列号或版本号
	 */
	public static String getSuffixNumberByPrefixValue(String prefix, int prefixLength,
			BO_CRM_CLIENTBUS_PServer boCrmClientbusPServer) {

		int cd = prefixLength + prefix.length();
		int suffixValue = 1;// 序号值
		String returnSuffixValue = "-1";

		try {
			ArrayList<Integer> al = new ArrayList<Integer>();

			List<BO_CRM_CLIENTBUS_P> selectByKhbh = boCrmClientbusPServer.selectByKhbh(cd);
			for (BO_CRM_CLIENTBUS_P string : selectByKhbh) {
				String oldSuffValueStr = string.getKhbh();
				int oldSuffValue = Integer.parseInt(oldSuffValueStr.replaceAll(prefix, "0"));
				if (oldSuffValue > 0) {
					boolean flag = true;
					for (int i = 0; i < al.size(); i++) {
						if (al.get(i) == oldSuffValue) {
							flag = false;
							break;
						}
					}
					if (flag) {
						al.add(oldSuffValue);
					}
				}
			}
			if (al.size() == 0) {
				suffixValue = 1;
			} else {
				/*
				 * 冒泡排序
				 */

				Object[] objects = al.toArray();

				for (int i = 0; i < objects.length - 1; i++) {
					for (int j = 0; j < objects.length - 1 - i; j++) {
						if (Integer.parseInt(objects[j].toString()) < Integer.parseInt(objects[j + 1].toString())) {
							suffixValue = Integer.parseInt(objects[j + 1].toString());
							objects[j] = objects[j + 1];
							objects[j + 1] = new Integer(suffixValue);
						}
					}
				}

				/*
				 * 获取序号
				 */
				int k = 0;
				for (; k < objects.length; k++) {
					if (k + 1 != Integer.parseInt(objects[k].toString())) {
						break;
					}
				}
				suffixValue = suffixValue + 1;
			}
			String str = "";
			for (int i = 0; i < prefixLength - (suffixValue + "").length(); i++) {
				str = str + "0";
			}
			returnSuffixValue = prefix + str + suffixValue + "";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "-1";
		}
		return returnSuffixValue;

	}
}



