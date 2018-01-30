package priv.yimeng.demo.persistence.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.hibernate.validator.constraints.Email;

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
@Entity
@Table(name = "user")
public @Data
class UserDO implements Serializable {

    private static final long serialVersionUID = -1696091784929073089L;

    @Id
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
            joinColumns = @JoinColumn(name = "username"),
            inverseJoinColumns = @JoinColumn(name = "authority"))
    @JSONField(serialize = false)
    private Set<AuthorityDO> authorities;

}
