package chat.techink.common.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class JSONUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.findAndRegisterModules();
        //反序列化配置
        OBJECT_MAPPER.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        OBJECT_MAPPER.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //序列化配置
        OBJECT_MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
    }

    private JSONUtils() {
    }

    public static ObjectMapper getMapper() {
        return OBJECT_MAPPER;
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            if (StringUtils.isBlank(json)) {
                return null;
            }
            return getMapper().readValue(json.getBytes(StandardCharsets.UTF_8), clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将JSON转换成Map对象
     *
     * @param json
     * @return
     */
    public static Map<String, Object> toMap(String json) {
        try {
            if (StringUtils.isBlank(json)) {
                return null;
            }
            return getMapper().readValue(json, new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJSON(Object bean) {
        try {
            return getMapper().writeValueAsString(bean);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> List<T> toList(String jsonStr, Class<T> t) {
        if (StringUtils.isBlank(jsonStr)) {
            return Collections.emptyList();
        }
        JavaType javaType = getMapper().getTypeFactory().constructParametricType(List.class, t);
        try {
            return getMapper().readValue(jsonStr, javaType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
