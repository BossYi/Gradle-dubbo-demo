package priv.yimeng.common.configuration.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2017-11-29
 *
 * @author yimeng
 * @version 1.0
 */
@Configuration
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
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/ci/resources/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(false).maxAge(3600);
    }
}