package service;

import entity.UserAccount;
import repository.LoginRepository;

public class LoginService {
    private final LoginRepository loginRepository = new LoginRepository();

    public UserAccount findByUserName(String userName){
        try {
            return loginRepository.findByUserName(userName);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
