package service;

import entity.Lesson;
import entity.OfferLesson;
import entity.Student;
import entity.UserAccount;
import repository.GenericRepositoryImpel;
import repository.LessonRepository;

import java.util.*;

public class LessonService implements Service{
    private final Scanner input = new Scanner(System.in);
    private final GenericRepositoryImpel genericRepositoryImpel = new GenericRepositoryImpel();
    private final StudentService studentService = new StudentService();
    private Integer lessonId,quarterNumber,year;
    private OfferLessonService offerLessonService = new OfferLessonService();
    private final LessonRepository lessonRepository = new LessonRepository();

    @Override
    public void add() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void update() {

    }

    public void add(UserAccount user){
        Student student = (Student) user;
        Set<Student> students = new HashSet<>();
        students.add(student);

        studentService.showAllUnit();
        System.out.print("Enter Id Lesson you want:");
        lessonId = giveInput();
        if(lessonId == null )
            return;
        OfferLesson offerLesson = offerLessonService.findById(lessonId);
        if(offerLesson == null ){
            System.out.println("You enter a wrong Lesson Id!");
            return;
        }
        System.out.print("Enter quarter Number:");
        quarterNumber = giveInput();
        if(quarterNumber == null )
            return;
        System.out.print("Enter year:");
        year = giveInput();
        if(year == null )
            return;
        Lesson lesson = new Lesson(null,students,lessonId,quarterNumber,year,-1);
        genericRepositoryImpel.add(lesson);
        System.out.println("This unit successful added!");
    }

    public List<Lesson> showMyLesson(int id){
        try {
            return lessonRepository.showMyLesson(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Integer giveInput(){
        Integer i;
        try {
           i = input.nextInt();
           input.nextLine();
        }catch (InputMismatchException e){
            input.nextLine();
            System.out.println("Just enter number please!");
            return null;
        }
        return i;
    }
}
