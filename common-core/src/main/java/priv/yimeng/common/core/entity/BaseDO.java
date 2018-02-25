package priv.yimeng.common.core.entity;

import lombok.Data;
import priv.yimeng.common.core.listener.EntityListener;

import javax.persistence.*;
import javax.validation.groups.Default;
import java.io.Serializable;
import java.util.Date;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2018-01-31
 *
 * @author yimeng
 * @version 1.0
 */
@Data
@MappedSuperclass
@EntityListeners(EntityListener.class)
public abstract class BaseDO<ID extends Serializable> implements Serializable {
    private static final long serialVersionUID = 2348364862900422840L;

    /**
     * "ID"属性名称
     */
    public static final String ID_PROPERTY_NAME = "id";

    /**
     * "创建日期"属性名称
     */
    public static final String CREATE_DATE_PROPERTY_NAME = "createDate";

    /**
     * "修改日期"属性名称
     */
    public static final String MODIFY_DATE_PROPERTY_NAME = "modifyDate";

    /**
     * 保存验证组
     */
    public interface SaveValidateGroup extends Default {
    }

    /**
     * 保存验证组
     */
    public interface UpdateValidateGroup extends Default {
    }

    /**
     * 主键
     */
    @Id
    @Access(AccessType.PROPERTY)
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "id_sequenceGenerator")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "sequenceGenerator")
    private ID id;

    /**
     * 创建日期
     */
    @Column(nullable = false, updatable = false)
    private Date createDate;

    /**
     * 修改日期
     */
    @Column(nullable = false)
    private Date modifyDate;

    /**
     * 版本
     */
    @Version
    @Column(nullable = false)
    private Long version;

}
