package priv.yimeng.demo.persistence.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Desc
 *
 * @author yimeng
 * @date 2017-12-09
 */
@Entity
@Table(name = "authority")
@Data
public class AuthorityDO {

    @Id
    @NotNull
    @Size(max = 50)
    private String name;

}
