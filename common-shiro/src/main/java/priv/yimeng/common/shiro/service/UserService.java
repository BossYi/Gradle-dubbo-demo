package priv.yimeng.common.shiro.service;


import priv.yimeng.common.core.service.BaseService;
import priv.yimeng.common.shiro.entity.UserDO;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-28
 *
 * @author yimeng
 * @version 1.0
 */
public interface UserService extends BaseService<UserDO, Long> {

    /**
     * 查询单个用户
     *
     * @param username username
     * @return user
     */
    UserDO findOne(String username);

    /**
     * 查找用户
     *
     * @param  username username
     * @return userDO
     */
    UserDO findByUsernameCaseInsenstive(String username);
}