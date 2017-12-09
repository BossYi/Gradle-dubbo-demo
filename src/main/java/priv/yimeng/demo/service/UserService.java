package priv.yimeng.demo.service;

import priv.yimeng.demo.persistence.entity.UserDO;

import java.util.List;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-28
 *
 * @author yimeng
 * @version 1.0
 */
public interface UserService {

    /**
     * 查询所有学生
     *
     * @return list
     */
    List<UserDO> list();

    /**
     * 保存
     *
     * @param userDO 用户DO
     */
    void save(UserDO userDO);

    /**
     * 查询单个用户
     *
     * @param id id
     * @return user
     */
    UserDO findOne(Long id);

    /**
     * 查找用户
     *
     * @return userDO
     */
    UserDO findByUsernameCaseInsenstive(String username);
}
