package priv.yimeng.common.core.annotations;

import java.lang.annotation.*;

/**
 * Description: 序列化json时需要序列化的字段
 * CreateDate:  2018-02-07
 *
 * @author yimeng
 * @version 1.0
 */
@Documented
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonSerializeField {

    /**
     * 需要序列化的字段
     *
     * @return 字段数组
     */
    String[] value();

}
