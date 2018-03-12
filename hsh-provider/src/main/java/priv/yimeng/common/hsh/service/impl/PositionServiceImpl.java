package priv.yimeng.common.hsh.service.impl;

import org.springframework.stereotype.Component;
import priv.yimeng.common.core.service.impl.BaseServiceImpl;
import priv.yimeng.common.hsh.entity.Position;
import priv.yimeng.common.hsh.service.PositionService;

/**
 * Desc
 *
 * @author yimeng
 * @date 2018-02-23
 */
@com.alibaba.dubbo.config.annotation.Service(
        interfaceClass = PositionService.class
)
@Component
public class PositionServiceImpl extends BaseServiceImpl<Position, Long> implements PositionService {
}
