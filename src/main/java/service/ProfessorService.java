package service;

import entity.Professor;
import entity.Student;
import entity.UserAccount;
import entity.enomuration.KindProfessor;
import repository.GenericRepositoryImpel;

import javax.swing.*;
import java.util.Scanner;

public class ProfessorService implements Service {
    private Scanner input = new Scanner(System.in);
    private String fullName,nationalId,password;
    private final GenericRepositoryImpel genericRepositoryImpel = new GenericRepositoryImpel();
    private final Utility utility = new Utility();
    private final LoginService loginService = new LoginService();



    @Override
    public void add() {
        fullName = utility.setFullName();
        nationalId = utility.setNationalId();
        password = utility.setPassword();
        System.out.print(fullName + " is a science?(Y)");
        String command = input.nextLine();
        Professor professor;
        if(command.equals("Y"))
            professor = new Professor(null,fullName,nationalId,password,KindProfessor.SCIENCE);
        else
            professor = new Professor(null,fullName,nationalId,password,KindProfessor.NORMAL);
        Professor professor1 = (Professor) genericRepositoryImpel.add(professor);
        if(professor1 == null )
            System.out.println("Something is wrong");
        else
            System.out.println(fullName + " successful added");
    }

    @Override
    public void delete() {
        System.out.print("Enter nationalId Professor:");
        nationalId = input.nextLine();
        UserAccount professor =  loginService.findByUserName(nationalId);
        if(professor != null && professor.getDiscriminatorValue().equals("Professor") ){
            genericRepositoryImpel.delete(professor);
            System.out.println("This user successful deleted!");
        }
        else
            System.out.println("You enter a wrong national Id!");
    }

    @Override
    public void update() {
        System.out.print("Enter nationalId Professor:");
        nationalId = input.nextLine();
        UserAccount professor = loginService.findByUserName(nationalId);
        if(professor == null ){
            System.out.println("You enter a wrong national Id!");
        }
        else if(professor != null && professor.getDiscriminatorValue().equals("Professor")){
            fullName = utility.setFullName();
            password = utility.setPassword();
            System.out.print(fullName + " is a science?(Y)");
            String command = input.nextLine();
            Professor professor1;
            if(command.equals("Y"))
                professor1 = new Professor(professor.getId(),fullName,professor.getNationalId(),password,KindProfessor.SCIENCE);
            else
                professor1 = new Professor(professor.getId(),fullName,professor.getNationalId(),password,KindProfessor.NORMAL);
            genericRepositoryImpel.update(professor1);
            System.out.println(professor1.getFullName() + " successful updated!");
        }
    }
}
