package service;

import entity.UserAccount;
import org.hibernate.SessionFactory;
import repository.LoginRepository;
import repository.SessionFactorySingleton;

public class LoginService {
    private final LoginRepository loginRepository = new LoginRepository();
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    public UserAccount findByUserName(String userName) {
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            try {
                return loginRepository.findByUserName(userName);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return null;
            }
        }
    }
}
