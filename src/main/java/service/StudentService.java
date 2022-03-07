package service;

import entity.Student;
import repository.GenericRepositoryImpel;
import repository.StudentRepository;

public class StudentService implements Service<Student> {
    private String fullName,nationalId,password;
    private final Utility utility = new Utility();
    private final StudentRepository studentRepository = new StudentRepository();
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
    public void delete(Student student) {

    }

    @Override
    public void update(Student student) {

    }
}
