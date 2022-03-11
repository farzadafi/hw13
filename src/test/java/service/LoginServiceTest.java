package service;

import entity.Student;
import entity.UserAccount;
import org.junit.jupiter.api.Test;
import service.GenericServiceImpel;
import service.LoginService;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    private final GenericServiceImpel genericServiceImpel = new GenericServiceImpel();
    private final LoginService loginService = new LoginService();

    @Test
    public void testLogin(){
        Student student = new Student(null,"farzad","399","aaaa");
        genericServiceImpel.add(student);

        UserAccount userAccount = loginService.findByUserName(student.getNationalId());

        assertNotNull(userAccount);
        assertEquals(userAccount.getFullName(),student.getFullName());
    }

}