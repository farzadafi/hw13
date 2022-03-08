package repository;

import entity.UserAccount;
import org.hibernate.SessionFactory;


public class LoginRepository {

    private final Connection connection = new Connection();

    public UserAccount findByUserName(String nationalId){
        try (var session = connection.openCurrentSession()) {
            var query = session.createQuery("FROM UserAccount as a WHERE a.nationalId = :nationalId",UserAccount.class);
            query.setParameter("nationalId",nationalId);
            var result =  query.getSingleResult();
            return result;
        }

    }

}
