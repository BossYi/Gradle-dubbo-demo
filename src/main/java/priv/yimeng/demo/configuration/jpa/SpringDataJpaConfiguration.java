package priv.yimeng.demo.configuration.jpa;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import priv.yimeng.demo.persistence.repository.BaseRepositoryFactoryBean;

/**
 * Desc
 *
 * @author yimeng
 * @date 2018-01-24
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "priv.yimeng.demo.persistence.repository",
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class
)
@EntityScan("priv.yimeng.demo.persistence.domain")
@ConditionalOnMissingBean(BaseRepositoryFactoryBean.class)
public class SpringDataJpaConfiguration {
}
