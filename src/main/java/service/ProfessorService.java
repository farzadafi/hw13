package service;

import entity.*;
import entity.enomuration.KindProfessor;
import org.hibernate.SessionFactory;
import repository.ProfessorRepository;
import repository.SessionFactorySingleton;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProfessorService implements Service {
    private Scanner input = new Scanner(System.in);
    private Integer idInput,grade;
    private String fullName,nationalId,password;
    private final Utility utility = new Utility();
    private final LoginService loginService = new LoginService();
    private final ProfessorRepository professorRepository = new ProfessorRepository();
    private static final OfferLessonService offerLessonService = new OfferLessonService();
    private static final LessonService lessonService = new LessonService();
    private final GenericServiceImpel genericServiceImpel = new GenericServiceImpel();
    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

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
        Professor professor1 = (Professor) genericServiceImpel.add(professor);
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
            genericServiceImpel.delete(professor);
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
            genericServiceImpel.update(professor1);
            System.out.println(professor1.getFullName() + " successful updated!");
        }
    }

    public Professor findProfessor(String nationalId) {
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            try {
                return professorRepository.findProfessor(nationalId);
            } catch (Exception e) {
            }
            return null;
        }
    }

    public Professor findById(int id) {
        try (var session = sessionFactory.getCurrentSession()) {
            session.getTransaction().begin();
            try {
                return professorRepository.findById(id);
            }catch (Exception e){
                System.out.println(e.getMessage());
                return null;
            }
        }
    }

    public void registerGrade(UserAccount account){
        List<Lesson> lessons = lessonService.showLessonForProfessor(account.getId());
        if(lessons == null ){
            System.out.println("You dont have any Lesson for register grade!");
            return;
        }
        System.out.println("You have this Lesson:");
        for (Lesson less:lessons
             ) {
            if(less.getGrade() == -1 ) {
                Student student = less.getStudents().stream().iterator().next();
                OfferLesson offerLesson = offerLessonService.findById(less.getIdOfferLesson());
                System.out.println("\nid:" + less.getId()
                        + "   FullName:" + student.getFullName()
                        + "   nationalId:" + student.getNationalId()
                        + "   LessonName:" + offerLesson.getLessonName());
            }
        }
        System.out.print("Enter Id for register grade:");
        try {
            idInput = input.nextInt();
            input.nextLine();
        }catch (InputMismatchException e){
            input.nextLine();
            System.out.println("Just enter number!");
            return;
        }
        System.out.print("Enter grade:");
        try {
            grade = input.nextInt();
            input.nextLine();
        }catch (InputMismatchException e){
            input.nextLine();
            System.out.println("Just enter number!");
            return;
        }
        if(grade < 0 || grade > 20 ){
            System.out.println("Grade a number between 0 and 20,Professor!");
            return;
        }
        Lesson lesson = null;
        for (Lesson less:lessons
             ) {
            if(less.getId() == idInput){
                lesson = new Lesson(less.getId(),less.getStudents(),less.getIdOfferLesson(),less.getQuarterNumber(),less.getYearEducation(),grade);
                break;
            }
        }
        if(lesson == null){
            System.out.println("Doesn't have this Id!");
            return;
        }
        genericServiceImpel.update(lesson);
        System.out.println("Grade successful inserted!");
    }

    public void calcSalary(UserAccount account){
        List<Lesson> lessons = lessonService.showLessonForProfessor(account.getId());
        Integer sum = 0;
        for (Lesson less:lessons
             ) {
            OfferLesson offerLesson = offerLessonService.findById(less.getIdOfferLesson());
            sum += offerLesson.getUnitNumber();
        }
        Professor professor = findById(account.getId());
        if(professor.getKindProfessor().equals(KindProfessor.SCIENCE))
            System.out.println("your salary is ::" + (1000 + (sum * 500)));
        else
            System.out.println("your salary is :" + (sum * 500));
    }
}
