package service;

import org.hibernate.SessionFactory;
import repository.GenericRepositoryImpel;
import repository.SessionFactorySingleton;

public class GenericServiceImpel<K> {

    private final GenericRepositoryImpel genericRepositoryImpel = new GenericRepositoryImpel();
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    public K add(K entity) {
        sessionFactory.openSession();
        try (var session = sessionFactory.getCurrentSession()){
            var transaction = session.getTransaction();
            try {
                transaction.begin();
                genericRepositoryImpel.add(entity);
                transaction.commit();
                return entity;
            } catch (Exception e) {
                transaction.rollback();
                e.getStackTrace();
                System.out.println("Insert not successful!");
                return null;
            }
        }
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
