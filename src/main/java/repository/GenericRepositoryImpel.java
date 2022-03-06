package repository;

import org.hibernate.SessionFactory;

public class GenericRepositoryImpel<K> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    public K add(K entity) {
        try (var session = sessionFactory.openSession()){
            var transaction = session.beginTransaction();
            try {
                session.save(entity);
                transaction.commit();
                return entity;
            } catch (Exception e) {
                transaction.rollback();
                System.out.println("Insert not successful!");
                return null;
            }
        }
    }

    public void update(K entity) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
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
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
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
