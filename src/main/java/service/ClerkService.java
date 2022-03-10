package service;

import entity.Clerk;
import entity.UserAccount;
import org.hibernate.SessionFactory;
import repository.ClerkRepository;
import repository.SessionFactorySingleton;

import java.util.Scanner;

public class ClerkService implements Service{
    private Scanner input = new Scanner(System.in);
    private Integer salary;
    private String fullName,nationalId,password;
    private final Utility utility = new Utility();
    private final GenericServiceImpel<UserAccount> genericServiceImpel= new GenericServiceImpel<>();
    private final LoginService loginService = new LoginService();
    private final ClerkRepository clerkRepository = new ClerkRepository();
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();


    @Override
    public void add() {
        fullName = utility.setFullName();
        nationalId = utility.setNationalId();
        password = utility.setPassword();
        System.out.print("Enter salary for " + fullName);
        try {
            salary = input.nextInt();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        Clerk clerk = new Clerk(null,fullName,nationalId,password,salary);
        Clerk clerk1 = (Clerk) genericServiceImpel.add(clerk);
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
            genericServiceImpel.delete(clerk);
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
            System.out.print("Enter salary for " + fullName);
            try {
                salary = input.nextInt();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            Clerk clerk1 = new Clerk(clerk.getId(),fullName,clerk.getNationalId(),password,salary);
            genericServiceImpel.update(clerk1);
            System.out.println(clerk1.getFullName() + " successful updated!");
        }
    }

    public void showSalary(UserAccount user){
        Clerk clerk = findById(user.getId());
        System.out.println("Your salary is:" + clerk.getSalary());
    }

    public Clerk findById(int id){
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            Clerk clerk = clerkRepository.findById(id);
            if(clerk == null )
                return null;
            else
                return clerk;
        }
    }

}
