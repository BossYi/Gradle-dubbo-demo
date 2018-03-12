package priv.yimeng.common.core.configuration.jpa;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import priv.yimeng.common.core.repository.BaseRepositoryFactoryBean;

/**
 * Desc
 *
 * @author yimeng
 * @date 2018-01-24
 */
@Configuration
@EnableJpaRepositories(
        basePackages = "priv.yimeng.common.*.repository.**",
        repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class
)
@EntityScan("priv.yimeng.common.*.entity")
@ConditionalOnMissingBean(BaseRepositoryFactoryBean.class)
public class SpringDataJpaConfiguration {
}
