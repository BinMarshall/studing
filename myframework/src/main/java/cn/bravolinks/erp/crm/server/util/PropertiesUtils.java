package cn.bravolinks.erp.crm.server.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by yanqin on 2017/4/26.
 */
public class PropertiesUtils {

    private final static Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    private static Properties common = new Properties();

    static {
        InputStream commonIn = PropertiesUtils.class.getResourceAsStream("/crmserver.properties");
        try {
            common.load(commonIn);
        } catch (IOException e) {
            logger.error("加载配置文件失败",e);
        }
    }

    /**
     * 从公共配置文件读取
     */
    public static String getPropertyByCommon(String key) {
        return common.getProperty(key).trim();
    }
    
    
	public static String defaultValue(String arg){
		return (arg == null ? "" : arg);
	}

}
