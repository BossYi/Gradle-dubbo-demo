package priv.yimeng.demo.persistence.repository.impl;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;
import priv.yimeng.demo.persistence.Filter;
import priv.yimeng.demo.persistence.Order;
import priv.yimeng.demo.persistence.repository.BaseRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Description: ${DESCRIPTION}
 * CreateDate:  2018-01-25
 *
 * @author yimeng
 * @version 1.0
 */
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final Class<T> domainClass;
    private final EntityManager em;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.domainClass = domainClass;
        this.em = em;
    }

    @Override
    public void persist(T entity) {
        Assert.notNull(entity, "null");
        em.persist(entity);
    }

    @Override
    public T getOneObject(ID id) {
        return em.find(domainClass, id);
    }

    @Override
    public List<T> list(Filter... filters) {
        return null;
    }

    @Override
    public List<T> list(Integer first, Integer count, List<Filter> filters, List<Order> orders) {
        return null;
    }
}
