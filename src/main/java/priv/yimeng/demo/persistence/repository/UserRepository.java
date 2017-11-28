package priv.yimeng.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import priv.yimeng.demo.persistence.entity.UserDO;

import java.io.Serializable;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-28
 *
 * @author yimeng
 * @version 1.0
 */
public interface UserRepository extends JpaRepository<UserDO, Long>, JpaSpecificationExecutor<UserDO>, Serializable {
}
