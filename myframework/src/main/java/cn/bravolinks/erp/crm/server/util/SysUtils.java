package cn.bravolinks.erp.crm.server.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SysUtils {
	private final static Logger logger = LoggerFactory.getLogger(SysUtils.class);

	/**
	 * 获取当前年月日的date对象
	 * @return
	 */
	public static Date getDate(){
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formateDate = sdf.format(date);
		try {
			date = sdf.parse(formateDate);
		} catch (ParseException e) {
			logger.error("",e);
		}
		return date;
	}

	/**
	 * 首字母转大写
	 * @param str
	 * @return
	 */
	public static String upperCase(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}

	/**
     * 公用错误提示
     * @param result 错误消息内容
     */
    public static String errorResult(String result){
    	String errorresult="{\"result\":\""+result+"\",\"type\":\"error\"}";
    	return errorresult;
    }
}
