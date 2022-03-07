package service;

import entity.Clerk;
import entity.Student;
import entity.UserAccount;
import repository.GenericRepositoryImpel;

import java.util.Scanner;

public class ClerkService implements Service{
    private Scanner input = new Scanner(System.in);
    private Integer salary;
    private String fullName,nationalId,password;
    private final Utility utility = new Utility();
    private final GenericRepositoryImpel genericRepositoryImpel = new GenericRepositoryImpel();
    private final LoginService loginService = new LoginService();


    @Override
    public void add() {
        fullName = utility.setFullName();
        nationalId = utility.setNationalId();
        password = utility.setPassword();
        System.out.println("Enter salary for " + fullName);
        try {
            salary = input.nextInt();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Clerk clerk = new Clerk(null,fullName,nationalId,password,salary);
        Clerk clerk1 = (Clerk) genericRepositoryImpel.add(clerk);
        if(clerk1 == null )
            System.out.println("Something is wrong");
        else
            System.out.println(fullName + " successful added!");
    }

    @Override
    public void delete() {
        System.out.print("Enter nationalId clerk:");
        nationalId = input.nextLine();
        UserAccount clerk =  loginService.findByUserName(nationalId);
        if(clerk != null && clerk.getDiscriminatorValue().equals("Clerk") ){
            genericRepositoryImpel.delete(clerk);
            System.out.println("This user successful deleted!");
        }
        else
            System.out.println("You enter a wrong national Id!");

    }

    @Override
    public void update() {
        System.out.print("Enter nationalId Clerk:");
        nationalId = input.nextLine();
        UserAccount clerk =  loginService.findByUserName(nationalId);
        if(clerk == null ){
            System.out.println("You enter a wrong national Id!");
        }
        else if(clerk != null && clerk.getDiscriminatorValue().equals("Clerk")){
            fullName = utility.setFullName();
            password = utility.setPassword();
            try {
                salary = input.nextInt();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            Clerk clerk1 = new Clerk(clerk.getId(),fullName,clerk.getNationalId(),password,salary);
            genericRepositoryImpel.update(clerk1);
            System.out.println(clerk1.getFullName() + " successful updated!");
        }
    }

}
