package priv.yimeng.common.core.repository;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import priv.yimeng.common.core.repository.impl.BaseRepositoryImpl;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2018-01-25
 *
 * @author yimeng
 * @version 1.0
 */
public class BaseRepositoryFactoryBean<R extends Repository<T, ID>, T, ID extends Serializable> extends JpaRepositoryFactoryBean<R, T, ID> {

    /**
     * Creates a new {@link JpaRepositoryFactoryBean} for the given repository interface.
     *
     * @param repositoryInterface must not be {@literal null}.
     */
    public BaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new BaseRepositoryFactory(entityManager);
    }

    private static class BaseRepositoryFactory<T, ID extends Serializable> extends JpaRepositoryFactory {

        private final EntityManager entityManager;

        /**
         * Creates a new {@link JpaRepositoryFactory}.
         *
         * @param entityManager must not be {@literal null}
         */
        BaseRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
            this.entityManager = entityManager;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected Object getTargetRepository(RepositoryInformation information) {
            return new BaseRepositoryImpl<T, ID>((Class<T>) information.getDomainType(), entityManager);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            return BaseRepositoryImpl.class;
        }
    }

}
