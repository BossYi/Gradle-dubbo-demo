package priv.yimeng.common.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.yimeng.common.core.service.impl.BaseServiceImpl;
import priv.yimeng.common.shiro.entity.UserDO;
import priv.yimeng.common.shiro.repositoty.UserRepository;
import priv.yimeng.common.shiro.service.UserService;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-28
 *
 * @author yimeng
 * @version 1.0
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDO, Long> implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDO findOne(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDO findByUsernameCaseInsenstive(String username) {
        return null;
    }
}
