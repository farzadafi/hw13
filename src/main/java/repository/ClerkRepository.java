package repository;

import entity.Clerk;
import org.hibernate.SessionFactory;

import java.util.List;

public class ClerkRepository implements Repository<Clerk> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public Clerk findById(int id) {
        var session = sessionFactory.getCurrentSession();
            Clerk clerk = session.find(Clerk.class,id);
            return clerk;
    }

    @Override
    public List<Clerk> findAll() {
        return null;
    }
}
