package priv.yimeng.demo.persistence.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Desc
 *
 * @author yimeng
 * @date 2017-12-09
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "authority")
@Data
public class AuthorityDO extends BaseDO<String> implements Serializable {
    private static final long serialVersionUID = -384700689174503566L;

    @NotNull
    @Size(max = 50)
    private String name;

}
