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
    private Long id;

    @Column
    private String name;

    @Column
    private Integer age;

    @Column
    private String address;

}
