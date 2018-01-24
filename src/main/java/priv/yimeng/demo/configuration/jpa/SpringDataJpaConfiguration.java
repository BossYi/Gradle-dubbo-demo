package priv.yimeng.demo.configuration.jpa;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import priv.yimeng.demo.persistence.repository.BaseRepositoryFactoryBean;

import javax.sql.DataSource;

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
@ConditionalOnBean(DataSource.class)
@ConditionalOnMissingBean(BaseRepositoryFactoryBean.class)
public class SpringDataJpaConfiguration {
}
