package repository;

import entity.Clerk;
import org.hibernate.SessionFactory;

import java.util.List;

public class ClerkRepository implements Repository<Clerk> {

    private final Connection connection = new Connection();

    @Override
    public Clerk findById(int id) {
        try (var session = connection.openCurrentSession()) {
            Clerk clerk = session.find(Clerk.class,id);
            if(clerk == null )
                return null;
            else
                return clerk;
        }

    }

    @Override
    public List<Clerk> findAll() {
        try (var session = connection.openCurrentSession()) {
            var query = session.createQuery("FROM UserAccount ", Clerk.class);
            List<Clerk> clerkList = query.list();
            return clerkList;
        }
    }
}
