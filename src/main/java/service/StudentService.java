package service;

import entity.Student;
import entity.UserAccount;
import repository.GenericRepositoryImpel;
import repository.StudentRepository;

import java.util.Scanner;

public class StudentService implements Service {
    private Scanner input = new Scanner(System.in);
    private String fullName,nationalId,password;
    private final Utility utility = new Utility();
    private final StudentRepository studentRepository = new StudentRepository();
    private final LoginService loginService = new LoginService();
    private final GenericRepositoryImpel genericRepositoryImpel = new GenericRepositoryImpel();

    @Override
    public void add() {
        fullName = utility.setFullName();
        nationalId = utility.setNationalId();
        password = utility.setPassword();
        Student student = new Student(null,fullName,nationalId,password);
        Student student1 = (Student) genericRepositoryImpel.add(student);
        if(student1 == null )
            System.out.println("Something is wrong");
        else
            System.out.println(fullName + " successful added!");
    }

    @Override
    public void delete() {
        System.out.print("Enter nationalId Student:");
        nationalId = input.nextLine();
        UserAccount student =  loginService.findByUserName(nationalId);
        if(student != null && student.getDiscriminatorValue().equals("Student") ){
            genericRepositoryImpel.delete(student);
            System.out.println("This user successful deleted!");
        }
        else
            System.out.println("You enter a wrong national Id!");

    }

    @Override
    public void update() {
        System.out.print("Enter nationalId Student:");
        nationalId = input.nextLine();
        UserAccount student =  loginService.findByUserName(nationalId);
        if(student == null ){
            System.out.println("You enter a wrong national Id!");
        }
        else if(student != null && student.getDiscriminatorValue().equals("Student")){
            fullName = utility.setFullName();
            password = utility.setPassword();
            Student student1 = new Student(student.getId(),fullName,student.getNationalId(),password);
            genericRepositoryImpel.update(student1);
            System.out.println(student1.getFullName() + " successful updated!");
        }
    }

}
