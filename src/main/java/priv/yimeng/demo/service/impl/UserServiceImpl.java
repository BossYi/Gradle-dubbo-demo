package priv.yimeng.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import priv.yimeng.demo.persistence.entity.UserDO;
import priv.yimeng.demo.persistence.repository.UserRepository;
import priv.yimeng.demo.service.UserService;

import java.util.List;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-28
 *
 * @author yimeng
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDO> list() {
        return userRepository.findAll();
    }

    @Override
    public void save(UserDO userDO) {
        userRepository.save(userDO);
    }

    @Override
    public UserDO findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public UserDO findByUsernameCaseInsenstive(String username) {
        return userRepository.findByUsernameCaseInsenstive(username);
    }

}
