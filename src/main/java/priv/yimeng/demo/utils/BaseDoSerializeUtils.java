package priv.yimeng.demo.utils;

import priv.yimeng.demo.annotations.JsonSerializeField;

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
