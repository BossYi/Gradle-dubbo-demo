package priv.yimeng.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.yimeng.demo.persistence.entity.UserDO;

import java.io.Serializable;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-28
 *
 * @author yimeng
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<UserDO, Long>, Serializable {

    /**
     * 根据用户和年龄查询
     *
     * @param name name
     * @param age  age
     * @return user
     */
    UserDO findByNameAndAge(String name, Integer age);

    /**
     * 通过用户和面密码查找
     *
     * @param username name
     * @param password pwd
     * @return user
     */
    UserDO findByNameAndPassword(String username, String password);
}
