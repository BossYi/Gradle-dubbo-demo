package priv.yimeng.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.yimeng.demo.persistence.entity.GoodTypeDO;

/**
 * Desc:
 * Author:  yimeng
 * Date:    2017-11-28
 * Time:    22:53
 */
public interface GoodTypeRepository extends JpaRepository<GoodTypeDO, Long> {
}
