package priv.yimeng.demo.persistence.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.CompareToBuilder;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2018-01-31
 *
 * @author yimeng
 * @version 1.0
 */
@Data
public abstract class BaseOrderDO<ID extends Serializable> extends BaseDO<ID> implements Serializable, Comparable<BaseOrderDO<ID>> {
    private static final long serialVersionUID = -4866879967367628058L;

    /**
     * "排序"属性名称
     */
    public static final String ORDER_PROPERTY_NAME = "order";

    /**
     * 排序
     */
    @Min(0)
    @Column(name = "orders")
    private Integer order;

    @Override
    public int compareTo(BaseOrderDO<ID> orderDO) {
        return new CompareToBuilder().append(getOrder(), orderDO.getOrder()).append(getId(), orderDO.getId()).toComparison();
    }
}
