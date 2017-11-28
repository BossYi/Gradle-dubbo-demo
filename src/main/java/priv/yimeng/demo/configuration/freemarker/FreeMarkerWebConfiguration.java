package priv.yimeng.demo.configuration.freemarker;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.annotation.PostConstruct;

/**
 * Desc:
 * Author: yimeng
 * Date:  2017-10-04
 * Time:   14:04
 */
@Configuration
public class FreeMarkerWebConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    private freemarker.template.Configuration configuration;
    @Autowired
    private FreeMarkerViewResolver viewResolver;

    @PostConstruct
    public void setSharedVariable() {
        configuration.setDateFormat("yyyy/MM/dd");
        configuration.setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
        configuration.setTemplateUpdateDelayMilliseconds(3000);

        try {
            configuration.setSetting("template_update_delay", "0");
            configuration.setSetting("default_encoding", "UTF-8");
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        //配置Freemarker视图解析器
        viewResolver.setSuffix(".ftl");
        //是否缓存模板
        viewResolver.setCache(false);
        //为模板调用时，调用request对象的变量名
        viewResolver.setRequestContextAttribute("request");
        viewResolver.setOrder(0);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }

}
