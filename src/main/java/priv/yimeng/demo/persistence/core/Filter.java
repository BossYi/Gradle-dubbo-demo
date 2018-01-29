package priv.yimeng.demo.persistence.core;

import java.io.Serializable;

/**
 * Desc
 *
 * @author yimeng
 * @date 2018-01-24
 */
public class Filter implements Serializable {
    private static final long serialVersionUID = -7362455034801787531L;

    /**
     * 运算符
     */
    public enum Operator {

        /**
         * 等于
         */
        eq,

        /**
         * 不等于
         */
        ne,

        /**
         * 大于
         */
        gt,

        /**
         * 小于
         */
        lt,

        /**
         * 大于等于
         */
        ge,

        /**
         * 小于等于
         */
        le,

        /**
         * 相似
         */
        like,

        /**
         * 包含
         */
        in,

        /**
         * 为Null
         */
        isNull,

        /**
         * 不为Null
         */
        isNotNull;

        /**
         * 从String中获取Operator
         *
         * @param value 值
         * @return String对应的operator
         */
        public static Operator fromString(String value) {
            return Operator.valueOf(value.toLowerCase());
        }
    }

    /**
     * 默认是否忽略大小写
     */
    private static final boolean DEFAULT_IGNORE_CASE = false;

    /**
     * 默认是否自动增加通配符
     */
    private static final boolean DEFAULT_AUTO_WILDCARD = true;

    /**
     * 属性
     */
    private String property;

    /**
     * 运算符
     */
    private Operator operator;

    /**
     * 值
     */
    private Object value;

    /**
     * 是否忽略大小写
     */
    private Boolean ignoreCase = DEFAULT_IGNORE_CASE;

    /**
     * 是否自动增加通配符
     */
    private Boolean autoWildcard = DEFAULT_AUTO_WILDCARD;

    /**
     * 初始化一个新创建的Filter对象
     */
    protected Filter() {
    }

    /**
     * 初始化一个新创建的Filter对象
     *
     * @param property 属性
     * @param operator 运算符
     * @param value    值
     */
    public Filter(String property, Operator operator, Object value) {
        this.property = property;
        this.operator = operator;
        this.value = value;
    }

    /**
     * 初始化一个新创建的Filter对象
     *
     * @param property   属性
     * @param operator   运算符
     * @param value      值
     * @param ignoreCase 忽略大小写
     */
    public Filter(String property, Operator operator, Object value, boolean ignoreCase) {
        this.property = property;
        this.operator = operator;
        this.value = value;
        this.ignoreCase = ignoreCase;
    }

    /**
     * 返回为Null筛选
     *
     * @param property 属性
     * @return 为Null筛选
     */
    public static Filter isNull(String property) {
        return new Filter(property, Operator.isNull, null);
    }

    /**
     * 返回不为Null筛选
     *
     * @param property 属性
     * @return 不为Null筛选
     */
    public static Filter isNotNull(String property) {
        return new Filter(property, Operator.isNotNull, null);
    }
}
