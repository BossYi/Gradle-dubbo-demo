package priv.yimeng.demo.persistence.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import priv.yimeng.demo.annotations.JsonSerializeField;
import priv.yimeng.demo.configuration.fastjson.BaseDoToOneSerializer;

import javax.persistence.*;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-28
 *
 * @author yimeng
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "good_info")
public @Data
class GoodInfoDO extends BaseDO<Long> {

    private static final long serialVersionUID = 6977673057451064563L;

    /** 商品标题 */
    @Column(name = "t_title")
    private String title;

    /** 商品价格 */
    @Column(name = "t_price")
    private Double price;

    /** 商品单位 */
    @Column(name = "t_unit")
    private String unit;

    /** 商品排序 */
    @Column(name = "t_order")
    private Integer order;

    /** 商品类型 */
    @JsonSerializeField(value = {"name", "show"})
    @JSONField(serializeUsing = BaseDoToOneSerializer.class)
    @ManyToOne(targetEntity = GoodTypeDO.class)
    @JoinColumn(name = "good_type_id")
    private GoodTypeDO goodTypeDO;
}
