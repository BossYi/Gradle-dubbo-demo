package priv.yimeng.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import priv.yimeng.demo.persistence.Filter;
import priv.yimeng.demo.persistence.Page;
import priv.yimeng.demo.persistence.Pageable;
import priv.yimeng.demo.persistence.domain.UserDO;
import priv.yimeng.demo.service.UserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2018-01-25
 *
 * @author yimeng
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class RepositoryTest {

    @Autowired
    private UserService userService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testListUser() {
        List userList = testRestTemplate.getForObject("/user/list", List.class);
        for (Object o : userList) {
            String username = (String) ((HashMap) o).get("username");
            System.out.println(username);
        }
    }

    @Test
    public void testGetOneObject() {
        UserDO user = userService.findOne("admin");
        log.info(user.toString());
    }

    @Test
    public void saveUser() {
        UserDO userDO = new UserDO();
        userDO.setUsername("yimeng" + new Date().getTime());
        userDO.setEmail("123@163.com");
        userDO.setActivated(true);
        userDO.setPassword("123456");
        userService.save(userDO);
    }

    @Test
    public void testBaseRepositoryList() {
        List<UserDO> list = userService.list(Filter.eq("id", 4));
        System.out.println(list.get(0).getUsername());
    }

    @Test
    public void testLe() {
        List<UserDO> list = userService.list(Filter.le("id", 4L));
        for (UserDO userDO : list) {
            System.out.println(userDO.getId());
        }
    }

    @Test
    public void testQuerySelectName() {
        List<UserDO> list = userService.list("usernameAndPassword");
        for (UserDO userDO : list) {
            System.out.println(userDO.getUsername() + ":" + userDO.getPassword());
        }
    }

    @Test
    public void testListPage() {
        Pageable pageable = new Pageable();
        pageable.setPageNumber(1);
        pageable.setPageSize(2);
        pageable.setQuerySelectName("idsAndNames");
        Page<UserDO> userDOPage = userService.listPage(pageable);
        List<UserDO> userDOList = userDOPage.getContent();
        for (UserDO userDO : userDOList) {
            System.out.println(userDO.getPassword() + " : " + userDO.getUsername());
        }
    }

    @Test
    public void testGroupBy() {
        Pageable pageable = new Pageable();
        pageable.setGroupBy(true);
        pageable.setQuerySelectName("idsAndNames");
        Page<UserDO> userDOPage = userService.listPage(pageable);
        List<UserDO> userDOList = userDOPage.getContent();
        for (UserDO userDO : userDOList) {
            System.out.println(userDO.toString());
        }
    }

}
