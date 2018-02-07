package priv.yimeng.demo.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.StringValueResolver;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description: springUtils
 * CreateDate:  2018-02-07
 *
 * @author yimeng
 * @version 1.0
 */
@Component("springUtils")
public final class SpringUtils implements ApplicationContextAware, EmbeddedValueResolverAware, DisposableBean {

    private static ApplicationContext applicationContext;
    private static StringValueResolver stringValueResolver;

    /** class私有域缓存 **/
    private static final ConcurrentReferenceHashMap<Class<?>, List<Field>> CLASS_PRIVATE_FIELD_CACHE = new ConcurrentReferenceHashMap<>(256);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtils.applicationContext = applicationContext;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        SpringUtils.stringValueResolver = resolver;
    }

    @Override
    public void destroy() throws Exception {
        applicationContext = null;
        stringValueResolver = null;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static StringValueResolver getStringValueResolver() {
        return stringValueResolver;
    }

    /**
     * 获取容器中实例
     *
     * @param name bean name
     * @return object
     */
    public static Object getBean(String name) {
        Assert.hasText(name, "name must not be null!");
        return applicationContext.getBean(name);
    }

    /**
     * 获取容器中实例
     *
     * @param name beanName
     * @param type type
     * @param <T>  resultType
     * @return t object
     */
    public static <T> T getBean(String name, Class<T> type) {
        Assert.hasText(name, "name must not be null!");
        Assert.notNull(type, "type must not be null!");
        return applicationContext.getBean(name, type);
    }

    /**
     * 获取容器中实例
     *
     * @param type type
     * @param <T>  resultType
     * @return t object
     */
    public static <T> T getBean(Class<T> type) {
        Assert.notNull(type, "type must not be null!");
        return applicationContext.getBean(type);
    }


    /**
     * 获取properties属性值
     *
     * @param property     name
     * @param defaultValue defaultValue
     * @return value
     */
    public static String getPropertieValue(String property, String defaultValue) {
        String temp = "${" + property;
        if (defaultValue != null) {
            temp += ":" + defaultValue;
        }
        temp += "}";
        return stringValueResolver.resolveStringValue(temp);
    }

    /**
     * 获取类型的私有域
     *
     * @param clazz 类型
     * @return list
     */
    public static List<Field> getClassPrivateField(Class clazz) {
        List<Field> fields = CLASS_PRIVATE_FIELD_CACHE.get(clazz);
        if (fields == null) {
            synchronized (CLASS_PRIVATE_FIELD_CACHE) {
                fields = CLASS_PRIVATE_FIELD_CACHE.get(clazz);
                if (fields == null) {
                    if (clazz == null || clazz.equals(Object.class)) {
                        fields = Collections.emptyList();
                    } else {
                        Class<?> superclass = clazz.getSuperclass();
                        fields = new ArrayList<>(getClassPrivateField(superclass));
                        for (Field field : clazz.getDeclaredFields()) {
                            if (Modifier.PRIVATE == field.getModifiers()) {
                                fields.add(field);
                            }
                        }
                    }
                    CLASS_PRIVATE_FIELD_CACHE.put(clazz, fields);
                }
            }
        }
        return fields;
    }
}
