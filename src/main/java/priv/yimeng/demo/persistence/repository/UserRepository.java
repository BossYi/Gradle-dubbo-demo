package priv.yimeng.demo.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import priv.yimeng.demo.persistence.domain.UserDO;

import java.io.Serializable;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-28
 *
 * @author yimeng
 * @version 1.0
 */
public interface UserRepository extends BaseRepository<UserDO, Long>, Serializable {

    /**
     * 查找用户
     *
     * @param username username
     * @return user
     */
    @Query("select u from UserDO u where LOWER(u.username) = LOWER(:username)")
    UserDO findByUsernameCaseInsenstive(@Param("username") String username);

    /**
     * 根据名字查找
     *
     * @param username username
     * @return user
     */
    UserDO findByUsername(String username);

}
