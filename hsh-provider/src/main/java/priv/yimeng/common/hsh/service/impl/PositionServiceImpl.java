package priv.yimeng.common.hsh.service.impl;

import org.springframework.stereotype.Component;
import priv.yimeng.common.core.entity.Position;
import priv.yimeng.common.core.service.PositionService;
import priv.yimeng.common.core.service.impl.BaseServiceImpl;

/**
 * Desc
 *
 * @author yimeng
 * @date 2018-02-23
 */
@com.alibaba.dubbo.config.annotation.Service(
        interfaceClass = PositionService.class,
        version = "1.0.0"
)
@Component
public class PositionServiceImpl extends BaseServiceImpl<Position, Long> implements PositionService {
}
