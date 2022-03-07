package service;

import entity.UserAccount;
import repository.LoginRepository;

public class LoginService {
    private final LoginRepository loginRepository = new LoginRepository();

    public UserAccount FindByUserName(String userName){
        return loginRepository.findByUserName(userName);
    }
}
