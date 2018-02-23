package priv.yimeng.demo.persistence.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Desc
 *
 * @author yimeng
 * @date 2018-02-23
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "position")
public class Position extends BaseDO<Long> {

    private static final long serialVersionUID = 4183846752632003599L;

    @Column
    private String logo;
    @Column
    private Integer sex;
    @Column
    private String numName;
    @Column
    private String cityName;
    @Column
    private Integer collection;
    @Column
    private String companyName;
    @Column
    private Integer applyStatus;
    @Column
    private String postDetail;
    @Column
    private Double minMoney;
    @Column
    private String areaName;
    @Column
    private String pName;
    @Column
    private Double maxMoney;
    @Column
    private String synopsis;
    @Column
    private String images;
    @Column
    private Integer companyId;

}
