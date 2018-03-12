package priv.yimeng.common.hsh;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author yimeng
 */

@SpringBootApplication
@EnableDubboConfiguration
@EntityScan(basePackages = "priv.yimeng.common.hsh.entity")
public class HshProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(HshProviderApplication.class, args);
    }
}
