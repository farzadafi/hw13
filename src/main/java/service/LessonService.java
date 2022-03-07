package service;

import entity.Lesson;
import entity.OfferLesson;
import entity.Student;
import entity.UserAccount;
import repository.GenericRepositoryImpel;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class LessonService implements Service{
    private final Scanner input = new Scanner(System.in);
    private final GenericRepositoryImpel genericRepositoryImpel = new GenericRepositoryImpel();
    private final StudentService studentService = new StudentService();
    private Integer lessonId,quarterNumber,year;
    private OfferLessonService offerLessonService = new OfferLessonService();

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
