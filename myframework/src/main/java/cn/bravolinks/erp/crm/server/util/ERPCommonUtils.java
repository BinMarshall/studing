package cn.bravolinks.erp.crm.server.util;

/**
 * Created by yanqin on 2017/2/15.
 */
public class ERPCommonUtils {

    /**
     * 根据sid获取userid
     * @param sid
     * @return
     */
    public static String getUserid(String sid){
        return sid.substring(0,sid.substring(0,sid.lastIndexOf("_")).lastIndexOf("_"));
    }

	public static String defaultValue(String arg){
		return (arg == null ? "" : arg);
	}


}
