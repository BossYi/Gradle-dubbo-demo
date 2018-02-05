package priv.yimeng.demo.persistence.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Email;
import priv.yimeng.demo.persistence.annotation.QuerySelect;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
@Table(name = "user")
@Data
public class UserDO extends BaseDO<Long> implements Serializable {
    private static final long serialVersionUID = -8035876376127995860L;

    public UserDO() {
    }

    @QuerySelect(name = "usernameAndPassword", parameters = {"username", "password"})
    public UserDO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Column(updatable = false, nullable = false)
    @Size(max = 50)
    private String username;

    @Size(max = 500)
    private String password;

    @Email
    @Size(max = 50)
    private String email;

    private Boolean activated;

    @Size(max = 100)
    @Column(name = "activationKey")
    private String activationKey;

    @Size(max = 100)
    @Column(name = "resetPasswordKey")
    private String resetPasswordKey;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_authority",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    @JSONField(serialize = false)
    private Set<AuthorityDO> authorities;

}
