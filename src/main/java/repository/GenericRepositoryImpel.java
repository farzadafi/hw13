package repository;

import org.hibernate.SessionFactory;

public class GenericRepositoryImpel<K> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    public void add(K entity) {
        //var session = connection.getCurrentSession();
        var session = sessionFactory.getCurrentSession();
        session.save(entity);
    }

    public void update(K entity) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                session.update(entity);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }
    }

    public void delete(K entity) {
        try (var session = sessionFactory.getCurrentSession()) {
            var transaction = session.getTransaction();
            try {
                session.delete(entity);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                System.out.println(e.getMessage());
            }
        }

    }

}
