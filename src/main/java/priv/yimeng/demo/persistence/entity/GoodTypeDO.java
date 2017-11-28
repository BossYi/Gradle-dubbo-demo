package priv.yimeng.demo.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-28
 *
 * @author yimeng
 * @version 1.0
 */
@Entity
@Table(name = "good_type")
public @Data
class GoodTypeDO {

    //主键
    @Id
    @GeneratedValue
    @Column(name = "t_id")
    private Long id;
    //类型名称
    @Column(name = "t_name")
    private String name;
    //是否显示
    @Column(name = "t_show")
    private Integer show;
    //排序
    @Column(name = "t_order")
    private Integer order;

    @OneToMany(mappedBy = "goodTypeDO", fetch = FetchType.LAZY)
    private Set<GoodInfoDO> goodInfoDOSet;


}
