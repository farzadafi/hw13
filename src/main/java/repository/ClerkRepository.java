package repository;

import entity.Clerk;
import entity.UserAccount;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
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
            var query = session.createQuery("FROM UserAccount ", UserAccount.class);
            List<UserAccount> userAccounts = query.list();
            List<Clerk> clerkList = new ArrayList<>();
            for (UserAccount user:userAccounts
                 ) {
                if(user.getDiscriminatorValue().equals("Clerk")) {
                    clerkList.add(new Clerk(user.getId(),user.getFullName(),user.getNationalId(),user.getPassword(),0));
                }
            }
            return clerkList;
        }
    }
}
