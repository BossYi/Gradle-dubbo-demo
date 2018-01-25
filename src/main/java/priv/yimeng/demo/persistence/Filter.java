package priv.yimeng.demo.persistence;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2018-01-25
 *
 * @author yimeng
 * @version 1.0
 */
@Data
public class Filter implements Serializable {

    private static final long serialVersionUID = -5163798933548208508L;

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
     * 返回等于筛选,值为空时返回为Null筛选
     *
     * @param property 属性
     * @param value    值
     * @return 等于筛选
     */
    public static Filter eq(String property, Object value) {
        if (value == null) {
            return Filter.isNull(property);
        }
        return new Filter(property, Operator.eq, value);
    }

    /**
     * 返回等于筛选,值为空时返回为Null筛选
     *
     * @param property   属性
     * @param value      值
     * @param ignoreCase 忽略大小写
     * @return 等于筛选
     */
    public static Filter eq(String property, Object value, boolean ignoreCase) {
        if (value == null) {
            return Filter.isNull(property);
        }
        return new Filter(property, Operator.eq, value, ignoreCase);
    }

    /**
     * 返回不等于筛选,值为空时返回不为Null筛选
     *
     * @param property 属性
     * @param value    值
     * @return 不等于筛选
     */
    public static Filter ne(String property, Object value) {
        if (value == null) {
            return Filter.isNotNull(property);
        }
        return new Filter(property, Operator.ne, value);
    }

    /**
     * 返回不等于筛选,值为空时返回不为Null筛选
     *
     * @param property   属性
     * @param value      值
     * @param ignoreCase 忽略大小写
     * @return 不等于筛选
     */
    public static Filter ne(String property, Object value, boolean ignoreCase) {
        if (value == null) {
            return Filter.isNotNull(property);
        }
        return new Filter(property, Operator.ne, value, ignoreCase);
    }

    /**
     * 返回大于筛选
     *
     * @param property 属性
     * @param value    值
     * @return 大于筛选
     */
    public static Filter gt(String property, Object value) {
        return new Filter(property, Operator.gt, value);
    }

    /**
     * 返回小于筛选
     *
     * @param property 属性
     * @param value    值
     * @return 小于筛选
     */
    public static Filter lt(String property, Object value) {
        return new Filter(property, Operator.lt, value);
    }

    /**
     * 返回大于等于筛选
     *
     * @param property 属性
     * @param value    值
     * @return 大于等于筛选
     */
    public static Filter ge(String property, Object value) {
        return new Filter(property, Operator.ge, value);
    }

    /**
     * 返回小于等于筛选
     *
     * @param property 属性
     * @param value    值
     * @return 小于等于筛选
     */
    public static Filter le(String property, Object value) {
        return new Filter(property, Operator.le, value);
    }

    /**
     * 返回相似筛选
     *
     * @param property 属性
     * @param value    值
     * @return 相似筛选
     */
    public static Filter like(String property, Object value) {
        return Filter.like(property, value, true);
    }

    /**
     * 返回相似筛选
     *
     * @param property     属性
     * @param value        值
     * @param autoWildcard 是否自动增加通配符
     * @return 相似筛选
     */
    public static Filter like(String property, Object value, boolean autoWildcard) {
        Filter filter = new Filter(property, Operator.like, value);
        filter.setAutoWildcard(autoWildcard);
        return filter;
    }

    /**
     * 返回包含筛选
     *
     * @param property 属性
     * @param value    值
     * @return 包含筛选
     */
    public static Filter in(String property, Object value) {
        return new Filter(property, Operator.in, value);
    }

    /**
     * 返回为Null筛选
     *
     * @param property 属性
     * @return 为Null筛选
     */
    private static Filter isNull(String property) {
        return new Filter(property, Operator.isNull, null);
    }

    /**
     * 返回不为Null筛选
     *
     * @param property 属性
     * @return 不为Null筛选
     */
    private static Filter isNotNull(String property) {
        return new Filter(property, Operator.isNotNull, null);
    }

    /**
     * 返回忽略大小写筛选
     *
     * @return 忽略大小写筛选
     */
    public Filter ignoreCase() {
        this.ignoreCase = true;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Filter other = (Filter) obj;
        return new EqualsBuilder().append(getProperty(), other.getProperty()).append(getOperator(), other.getOperator()).append(getValue(), other.getValue())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(getProperty()).append(getOperator()).append(getValue()).toHashCode();
    }

}
