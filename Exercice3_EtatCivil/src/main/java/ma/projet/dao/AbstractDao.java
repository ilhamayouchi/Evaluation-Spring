package ma.projet.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public abstract class AbstractDao<T> implements IDao<T> {

    @Autowired
    protected SessionFactory sessionFactory;

    private final Class<T> entityClass;

    protected AbstractDao(Class<T> entityClass) {

        this.entityClass = entityClass;
    }

    protected Session session() {

        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(T entity) {
        session().save(entity);
    }

    @Override
    public void update(T entity) {

        session().update(entity);
    }

    @Override
    public void delete(T entity) {

        session().delete(entity);
    }

    @Override
    public T findById(Long id) {

        return session().get(entityClass, id);
    }

    @Override
    public List<T> findAll() {
        return session().createQuery("FROM " + entityClass.getSimpleName(), entityClass).list();
    }
}
