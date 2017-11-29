package priv.yimeng.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import priv.yimeng.demo.persistence.entity.LogDO;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-29
 *
 * @author yimeng
 * @version 1.0
 */
public interface LogRepository extends JpaRepository<LogDO, Long> {
}
