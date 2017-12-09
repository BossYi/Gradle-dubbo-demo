package priv.yimeng.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.yimeng.demo.persistence.entity.AuthorityDO;

/**
 * Desc
 *
 * @author yimeng
 * @date 2017-12-09
 */
public interface AuthorityRepository extends JpaRepository<AuthorityDO,String> {
}
