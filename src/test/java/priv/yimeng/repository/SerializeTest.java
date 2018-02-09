package priv.yimeng.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import priv.yimeng.demo.persistence.domain.GoodInfoDO;
import priv.yimeng.demo.persistence.domain.GoodTypeDO;
import priv.yimeng.demo.service.GoodInfoService;
import priv.yimeng.demo.service.GoodTypeService;

import java.util.List;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2018-02-08
 *
 * @author yimeng
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class SerializeTest {

    @Autowired
    GoodInfoService goodInfoService;

    @Autowired
    GoodTypeService goodTypeService;

    @Test
    public void testSave() {
        for (int i = 0; i < 10; i++) {
            GoodInfoDO goodInfoDO = new GoodInfoDO();
            goodInfoDO.setTitle("笔记本电脑" + i);
            goodInfoDO.setPrice((double) (6888 + i));
            goodInfoDO.setOrder(1 + i);

            GoodTypeDO goodTypeDO = goodTypeService.get(1L);

            goodInfoDO.setGoodTypeDO(goodTypeDO);
            goodInfoService.save(goodInfoDO);
        }
    }

    @Test
    public void testSerialize() {
        List<GoodInfoDO> list = goodInfoService.list();
        for (GoodInfoDO goodInfoDO : list) {
            System.out.println(goodInfoDO.getTitle() + ":" + goodInfoDO.getGoodTypeDO().getName());
        }
    }

}
