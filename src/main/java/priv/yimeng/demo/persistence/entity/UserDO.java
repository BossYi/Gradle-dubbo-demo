package priv.yimeng.demo.persistence.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

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

    @Id
    @GeneratedValue
    @Column(name = "t_id")
    private Long id;

    @Column(name = "t_name")
    private String name;

    @Column(name = "t_age")
    private Integer age;

    @Column(name = "t_address")
    private String address;

    @Column(name = "t_password")
    private String password;

}
