package priv.yimeng.common.configuration.druid;

import com.alibaba.druid.support.console.DruidStat;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-28
 *
 * @author yimeng
 * @version 1.0
 */
@Configuration
@ConditionalOnClass(DruidStat.class)
public class DruidConfiguration {
    @Bean
    public FilterRegistrationBean statViewFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
