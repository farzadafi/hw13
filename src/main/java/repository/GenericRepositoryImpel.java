package repository;

import org.hibernate.SessionFactory;

public class GenericRepositoryImpel<K> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    public void add(K entity) {
        var session = sessionFactory.getCurrentSession();
        session.save(entity);
    }

    public void update(K entity) {
        var session = sessionFactory.getCurrentSession();
        session.update(entity);
    }

    public void delete(K entity) {
        var session = sessionFactory.getCurrentSession();
        session.delete(entity);
    }

}
