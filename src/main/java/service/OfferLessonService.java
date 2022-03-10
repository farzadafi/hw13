package service;

import entity.OfferLesson;
import entity.Professor;
import repository.GenericRepositoryImpel;
import repository.OfferLessonRepository;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OfferLessonService implements Service {
    private String lessonName,nationalId;
    private Integer unitNumber = 10;
    private Scanner input = new Scanner(System.in);
    private final ProfessorService professorService = new ProfessorService();
    private final GenericRepositoryImpel genericRepositoryImpel = new GenericRepositoryImpel();
    private final OfferLessonRepository offerLessonRepository = new OfferLessonRepository();
    private final GenericServiceImpel genericServiceImpel = new GenericServiceImpel();



    @Override
    public void add() {
        input.nextLine();
        System.out.print("Enter lesson Name:");
        lessonName = input.nextLine();
        System.out.print("Enter unit number: ");
        try {
            unitNumber = input.nextInt();
            input.nextLine();
        }catch (InputMismatchException e){
            input.nextLine();
            System.out.println(e.getMessage());
            return;
        }
        if((unitNumber < 1) || (unitNumber > 4)){
            System.out.println("You enter a wrong unit number");
            return;
        }
        System.out.print("Enter Professor national Id; ");
        nationalId = input.nextLine();
        Professor professor = professorService.findProfessor(nationalId);
        if(professor == null ){
            System.out.println("We dont have any Professor in my College with this national Id!");
            return;
        }
        OfferLesson offerLesson = new OfferLesson(null,lessonName,unitNumber,professor);
        OfferLesson offerLesson1 = (OfferLesson) genericServiceImpel.add(offerLesson);
        if(offerLesson1 == null )
            System.out.println("Something is wrong");
        else
            System.out.println(lessonName + " successful added");
    }

    @Override
    public void delete() {
        System.out.print("Enter Lesson name:");
        lessonName = input.nextLine();
        OfferLesson offerLesson = findLesson(lessonName);
        if(offerLesson != null){
            genericRepositoryImpel.delete(offerLesson);
            System.out.println("This Unit successful deleted!");
        }
        else
            System.out.println("You enter a wrong Unit number!");

    }

    @Override
    public void update() {
        System.out.print("Enter lesson name:");
        lessonName = input.nextLine();
        OfferLesson offerLesson = findLesson(lessonName);
        if(lessonName == null ){
            System.out.println("You enter a wrong lesson name!");
        }
        else {
            System.out.print("Enter unit number: ");
            try {
                unitNumber = input.nextInt();
                input.nextLine();
            }catch (InputMismatchException e){
                input.nextLine();
                System.out.println(e.getMessage());
                return;
            }
            if((unitNumber < 1) || (unitNumber > 4)){
                System.out.println("You enter a wrong unit number");
                return;
            }
            System.out.print("Enter Professor national Id; ");
            nationalId = input.nextLine();
            Professor professor = professorService.findProfessor(nationalId);
            if(professor == null ){
                System.out.println("We dont have any Professor in my College with this national Id!");
                return;
            }
            OfferLesson offerLesson1 = new OfferLesson(offerLesson.getId(),offerLesson.getLessonName(),unitNumber,professor);
            genericRepositoryImpel.update(offerLesson1);
            System.out.println(offerLesson1.getLessonName() + " successful updated!");
        }
    }

    public OfferLesson findLesson(String lessonName){
        try {
            return offerLessonRepository.findLesson(lessonName);
        }catch (Exception e){
        }
        return null;
    }

    public List<OfferLesson> findAll(){
        List<OfferLesson> offerLessonList = null;
        try {
            offerLessonList = offerLessonRepository.findAll();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return offerLessonList;
    }

    public OfferLesson findById(int id){
        try {
            return offerLessonRepository.findById(id);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
