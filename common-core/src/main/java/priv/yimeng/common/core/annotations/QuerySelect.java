package priv.yimeng.common.core.annotations;

import java.lang.annotation.*;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2018-01-30
 *
 * @author yimeng
 * @version 1.0
 */
@Target(ElementType.CONSTRUCTOR)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface QuerySelect {

    /**
     * 查询名称
     *
     * @return name
     */
    String name();

    /**
     * 参数列表
     *
     * @return parameters
     */
    String[] parameters();

}
