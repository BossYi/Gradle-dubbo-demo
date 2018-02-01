package priv.yimeng.demo.listener;

import priv.yimeng.demo.persistence.domain.BaseDO;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Description: Listener - 创建日期、修改日期处理
 * CreateDate:  2018-02-01
 *
 * @author yimeng
 * @version 1.0
 */
public class EntityListener {

    /**
     * 保存前处理
     *
     * @param baseDO 基类
     */
    @PrePersist
    public void prePersist(BaseDO<?> baseDO) {
        baseDO.setCreateDate(new Date());
        baseDO.setModifyDate(new Date());
    }

    /**
     * 更新前处理
     *
     * @param entity 基类
     */
    @PreUpdate
    public void preUpdate(BaseDO<?> baseDO) {
        baseDO.setModifyDate(new Date());
    }

}
