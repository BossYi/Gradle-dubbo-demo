package priv.yimeng.demo.persistence.annotation;

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
public @interface QuerySelects {

    /**
     * 查询选择构造器
     */
    QuerySelect[] querySelect();

}
