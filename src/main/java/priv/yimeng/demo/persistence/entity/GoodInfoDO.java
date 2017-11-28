package priv.yimeng.demo.persistence.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-28
 *
 * @author yimeng
 * @version 1.0
 */
@Entity
@Table(name = "good_info")
public @Data
class GoodInfoDO {

    //主键
    @Id
    @GeneratedValue
    @Column(name = "t_id")
    private Long id;
    //商品标题
    @Column(name = "t_title")
    private String title;
    //商品价格
    @Column(name = "t_price")
    private Double price;
    //商品单位
    @Column(name = "t_unit")
    private String unit;
    //商品排序
    @Column(name = "t_order")
    private Integer order;
    //商品类型
    @ManyToOne(targetEntity = GoodTypeDO.class)
    @JoinColumn(name = "good_type_id")
    private GoodTypeDO goodTypeDO;
}
