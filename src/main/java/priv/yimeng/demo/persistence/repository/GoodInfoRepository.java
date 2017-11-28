package priv.yimeng.demo.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import priv.yimeng.demo.persistence.entity.GoodInfoDO;

import java.util.List;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-28
 *
 * @author yimeng
 * @version 1.0
 */
public interface GoodInfoRepository extends JpaRepository<GoodInfoDO, Long> {

    /**
     * findByGoodTypeId
     *
     * @param id id
     * @return list
     */
    @Query(value = "select g from GoodInfoDO g where goodTypeDO.id = :id")
    List<GoodInfoDO> findByGoodTypeId(@Param("id") Long id);

}
