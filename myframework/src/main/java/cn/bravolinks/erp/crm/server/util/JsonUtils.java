package cn.bravolinks.erp.crm.server.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by yanqin on 2017/4/26.
 */
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();
    private final static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    public static ObjectMapper getMapper(){
        return mapper;
    }

    /**
     * object To Json
     */
    public static String objectToJson(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            logger.error("对象转json失败", e);
        }
        return null;
    }

    /**
     * json to object
     */
    public static <T> T jsonToObject(String s, Class<T> c) {
        JavaType type = mapper.getTypeFactory().constructType(c);
        return jsonToObject(s,type);
    }

    /**
     * json to object
     */
    public static <T> T jsonToObject(String s, JavaType type) {
        try {
            return mapper.readValue(s, type);
        } catch (IOException e) {
            logger.error("json转对象失败", e);
        }
        return null;
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

}
