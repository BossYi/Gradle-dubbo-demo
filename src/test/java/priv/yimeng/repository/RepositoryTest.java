package priv.yimeng.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import priv.yimeng.demo.persistence.entity.UserDO;
import priv.yimeng.demo.service.UserService;

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
        ResponseEntity<List> listResponseEntity = testRestTemplate.getForEntity("/user/list", List.class, "");
        List list = listResponseEntity.getBody();
        for (Object o : list) {
            String username = (String) ((HashMap) o).get("username");
            System.out.println(username);
        }

        List userList = testRestTemplate.getForObject("/user/list", List.class);
        for (Object o : userList) {
            String username = (String) ((HashMap) o).get("username");
            System.out.println(username);
        }
    }

    @Test
    public void testGetOneObject() {
        UserDO user = userService.findOne("yimeng");
        log.info(user.toString());
    }

}
