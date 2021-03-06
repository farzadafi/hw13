package service;

import entity.Lesson;
import entity.OfferLesson;
import entity.Student;
import entity.UserAccount;
import org.hibernate.SessionFactory;
import repository.SessionFactorySingleton;
import repository.StudentRepository;

import java.util.List;
import java.util.Scanner;

public class StudentService implements Service {
    private Scanner input = new Scanner(System.in);
    private String fullName,nationalId,password;
    private final Utility utility = new Utility();
    private final StudentRepository studentRepository = new StudentRepository();
    private final LoginService loginService = new LoginService();
    private final GenericServiceImpel<UserAccount> genericServiceImpel = new GenericServiceImpel<>();
    private final OfferLessonService offerLessonService = new OfferLessonService();
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public void add() {
        fullName = utility.setFullName();
        nationalId = utility.setNationalId();
        password = utility.setPassword();
        Student student = new Student(null,fullName,nationalId,password);
        Student student1 = (Student) genericServiceImpel.add(student);
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
            genericServiceImpel.delete(student);
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
            genericServiceImpel.update(student1);
            System.out.println(student1.getFullName() + " successful updated!");
        }
    }

    public void showAllUnit(){
        List<OfferLesson> offerLessonList = offerLessonService.findAll();
        if(offerLessonList == null )
            System.out.println("We dont have any Offer Lesson now!");
        else{
            for (OfferLesson offer:offerLessonList
                 ) {
                System.out.print(offer.CustomToString() +  "   Professor:");
                System.out.println(offer.getProfessor().getFullName());
            }
        }
    }

    public void showMyLesson(UserAccount account){
        LessonService lessonService = new LessonService();
        List<Lesson> lessons = lessonService.showMyLesson(account.getId());
        for (Lesson a:lessons
             ) {
            OfferLesson offerLesson = offerLessonService.findById(a.getIdOfferLesson());
            System.out.println("lessonName:" + offerLesson.getLessonName() + "   quarterNumber:" + a.getQuarterNumber()
            + "   year:" + a.getYearEducation() + "   grade:" + a.getGrade());
        }
    }

    public Student findById(int id) {
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            try {
                return studentRepository.findById(id);
            }catch (Exception e){
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

}
