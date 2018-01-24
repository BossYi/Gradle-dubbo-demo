package priv.yimeng.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import priv.yimeng.demo.persistence.entity.UserDO;

import java.io.Serializable;

/**
 * Desc
 *
 * @author yimeng
 * @date 2018-01-24
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

    /**
     * findByUsername
     *
     * @param username name
     * @return userDo
     */
    UserDO findByUsername(String username);

}
