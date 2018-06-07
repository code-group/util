package toolkit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;
import org.apache.commons.beanutils.BeanUtils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author zhull
 * @date 2018/6/6
 * <P>JSON工具</P>
 */
public class JSONUtil {

    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    /**
     * <P>date: 2018/6/6</P>
     * <P>将json字符串转为对象</P>
     *
     * @param json   json字符串
     * @param tClass 类型
     * @return 目标对象
     */
    public static <T> Object json2Object(String json, Class<T> tClass) {
        return gson.fromJson(json, tClass);
    }

    /**
     * <P>date: 2018/6/6</P>
     * <P>对象转为json字符串</P>
     **/
    public static String object2Json(Object object) {
        return gson.toJson(object);
    }

    /**
     * <P>date: 2018/6/6</P>
     * <P>json转Map</P>
     **/
    public static Map<String, Object> json2Map(String json) {
        return json2Map(JSONObject.fromObject(json));
    }

    /**
     * <P>date: 2018/6/6</P>
     * <P>Map转对象</P>
     **/
    public static <T> Object map2Bean(Class<T> tClass, Map<String, Object> map) throws Exception {
        T bean = tClass.newInstance();
        BeanUtils.populate(bean, map);
        return bean;
    }

    /**
     * <P>date: 2018/6/6</P>
     * <P>对象转Map</P>
     **/
    public static Map<String, Object> bean2Map(Object object) throws Exception {
        if (object == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        for (PropertyDescriptor property : Introspector.getBeanInfo(object.getClass()).getPropertyDescriptors()) {
            String key = property.getName();
            if (!"class".equals(key)) {
                map.put(key, property.getReadMethod().invoke(object));
            }
        }
        return map;
    }

    private static Map<String, Object> json2Map(JSONObject jsonObject) {
        Iterator<String> iterator = jsonObject.keys();
        Map<String, Object> result = new HashMap<>();
        while (iterator.hasNext()) {
            String key = iterator.next();
            if (JSONUtils.isString(jsonObject.get(key)) || JSONUtils.isNumber(jsonObject.get(key))) {
                result.put(key, jsonObject.get(key).toString());
            } else if (JSONUtils.isArray(jsonObject.get(key))) {
                result.put(key, jsonObject.getJSONArray(key));
            } else if (JSONUtils.isNull(jsonObject.get(key))) {
                result.put(key, "");
            } else {
                result.put(key, JSONUtil.json2Map(jsonObject.getJSONObject(key)));
            }
        }
        return result;
    }

}
