package priv.yimeng.demo.configuration.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
import priv.yimeng.demo.interceptor.LogInterceptor;
import priv.yimeng.demo.interceptor.SessionInterceptor;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-29
 *
 * @author yimeng
 * @version 1.0
 */
@Configuration
@ConditionalOnClass
public class CommonWebMvcConfiguration extends WebMvcConfigurerAdapter {

    private final FreeMarkerViewResolver freeMarkerViewResolver;

    @Autowired
    public CommonWebMvcConfiguration(FreeMarkerViewResolver freeMarkerViewResolver) {
        this.freeMarkerViewResolver = freeMarkerViewResolver;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(freeMarkerViewResolver);
        super.configureViewResolvers(registry);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/ci/resources/**").addResourceLocations("classpath:/static/");
    }
}
