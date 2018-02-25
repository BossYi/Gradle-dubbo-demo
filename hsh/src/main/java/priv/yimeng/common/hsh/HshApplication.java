package priv.yimeng.common.hsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author yimeng
 */
@SpringBootApplication
@ServletComponentScan
public class HshApplication {
    public static void main(String[] args) {
        SpringApplication.run(HshApplication.class, args);
    }
}
