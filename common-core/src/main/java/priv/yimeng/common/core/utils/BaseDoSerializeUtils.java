package priv.yimeng.common.core.utils;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;
import priv.yimeng.common.core.annotations.JsonSerializeField;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Description: 适用于一对多和多对一场景
 * CreateDate:  2018-02-07
 *
 * @author yimeng
 * @version 1.0
 */
public class BaseDoSerializeUtils {

    /**
     * 获取字段上的JsonSerializeField注解
     *
     * @param clazz     类型
     * @param filedName 字段名
     * @return 注解
     */
    public static JsonSerializeField getJsonSerializeFiled(Class<?> clazz, String filedName) {
        Field classPrivateField = getClassPrivateField(clazz, filedName);
        if (classPrivateField != null) {
            return classPrivateField.getAnnotation(JsonSerializeField.class);
        }
        return null;
    }

    /**
     * 获取类上的JsonSerializeField注解
     *
     * @param clazz 类型
     * @return JsonSerializeField
     */
    public static JsonSerializeField getJsonSerializedFieldOnClass(Class<?> clazz) {
        Assert.notNull(clazz, "clazz must not be null!");
        return AnnotationUtils.getAnnotation(clazz, JsonSerializeField.class);
    }

    /**
     * 序列化object的指定字段 beanUtils
     *
     * @param serializer serializer
     * @param object     object
     * @param fields     fields
     */
    public static void serialize(JSONSerializer serializer, Object object, String[] fields) {
        List<Field> classPrivateField = SpringUtils.getClassPrivateField(object.getClass());
        if (fields != null && fields.length > 0) {
            try {
                Object newObject = object.getClass().newInstance();
                for (String field : fields) {
                    for (Field declaredField : classPrivateField) {
                        if (field.equals(declaredField.getName())) {
                            Object simpleProperty = PropertyUtils.getSimpleProperty(object, field);
                            PropertyUtils.setSimpleProperty(newObject, field, simpleProperty);
                        }
                    }
                }
                serializer.write(newObject);
            } catch (Exception e) {
                throw new RuntimeException("序列化指定字段失败！" + e);
            }
        } else {
            serializer.writeNull();
        }
    }

    /**
     * 序列化object的指定字段 UsingPropertyPreFilter
     *
     * @param serializer serializer
     * @param object     object
     * @param fields     fields
     */
    public static void serializeUsingPropertyPreFilter(JSONSerializer serializer, Object object, String[] fields) {
        if (fields != null && fields.length > 0) {
            SimplePropertyPreFilter simplePropertyPreFilter = new SimplePropertyPreFilter(object.getClass(), fields);
            List<PropertyPreFilter> propertyPreFilters = serializer.getPropertyPreFilters();
            propertyPreFilters.add(simplePropertyPreFilter);
            serializer.write(object);
        } else {
            serializer.writeNull();
        }
    }

    /**
     * 根据filedName获取field
     *
     * @param clazz     类型
     * @param filedName fieldName
     * @return field
     */
    private static Field getClassPrivateField(Class clazz, String filedName) {
        List<Field> classPrivateFields = SpringUtils.getClassPrivateField(clazz);
        for (Field field : classPrivateFields) {
            if (filedName.equals(field.getName())) {
                return field;
            }
        }
        return null;
    }

}
