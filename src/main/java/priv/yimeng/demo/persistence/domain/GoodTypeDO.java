package priv.yimeng.demo.persistence.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import priv.yimeng.demo.annotations.JsonSerializeField;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Set;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-28
 *
 * @author yimeng
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "good_type")
@JsonSerializeField(value = {"id", "name"})
public @Data
class GoodTypeDO extends BaseDO<Long> implements Serializable {

    private static final long serialVersionUID = 7880468187445980944L;
    /**
     * 类型名称
     */
    @Column(name = "t_name")
    private String name;
    /**
     * 是否显示
     */
    @Column(name = "t_show")
    private Integer show;
    /**
     * 排序
     */
    @Column(name = "t_order")
    private Integer order;

    @Transient
    private Set<GoodInfoDO> goodInfoDOSet;


}
