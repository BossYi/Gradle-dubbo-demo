package priv.yimeng.common.hsh;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yimeng
 */

@SpringBootApplication
@EnableDubboConfiguration
public class HshConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(HshConsumerApplication.class, args);
    }
}
