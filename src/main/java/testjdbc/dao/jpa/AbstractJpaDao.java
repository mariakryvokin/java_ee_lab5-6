package testjdbc.dao.jpa;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Repository
public abstract class AbstractJpaDao<T, ID extends Serializable> {

    @PersistenceContext
    EntityManager entityManager;

    protected Class<T> entityClass;

    public AbstractJpaDao() {
    }

    public AbstractJpaDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public T update(T entity) {
        return entityManager.merge(entity);
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public void delete(ID id) {
        entityManager.createQuery("delete from " + entityClass.getName() + " e where e.id = :eid")
                .setParameter("eid", id).executeUpdate();
    }

    public T findById(ID id) {
        return entityManager.find(entityClass, id);
    }

}
