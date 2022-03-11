package service;

import entity.Lesson;
import entity.OfferLesson;
import entity.Professor;
import entity.Student;
import entity.enomuration.KindProfessor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LessonServiceTest {
    private static final GenericServiceImpel genericServiceImpel = new GenericServiceImpel<>();
    private final LessonService lessonService = new LessonService();

    @BeforeAll
    public static void addAll(){
        Student student = new Student(1,"farzad","1122334400","aaaaa");
        genericServiceImpel.add(student);
        Set<Student> studentSet = new HashSet<>();
        studentSet.add(student);
        Professor professor = new Professor(1,"aassdd","1122551","33",KindProfessor.SCIENCE);
        genericServiceImpel.add(professor);
        OfferLesson offerLesson = new OfferLesson(1,"math55",3,professor);
        genericServiceImpel.add(offerLesson);
        Lesson lesson = new Lesson(1,studentSet,offerLesson.getId(),3,95,-1);
        genericServiceImpel.add(lesson);
    }

    @Test
    public void testFindById(){
        Lesson lesson2 = lessonService.findById(1);

        assertNotNull(lesson2);
        assertNotEquals(0,lesson2.getId());
    }

    @Test
    public void testShowMyLesson(){
        List<Lesson> studentList = lessonService.showMyLesson(1);

        if(studentList.size() < 1 )
            fail();
    }

    @Test
    public void testShowLessonForProfessor(){
        List<Lesson> lessonList = lessonService.showLessonForProfessor(2);

        if(lessonList.size() < 1)
            fail();
    }

    @AfterAll
    public static void deleteAll(){
        Student student = new Student(1,"farzad","1122334400","aaaaa");
        OfferLesson offerLesson = new OfferLesson(1,"math55",3,new Professor(null,"aassdd","1122551","33", KindProfessor.SCIENCE));
        Lesson lesson = new Lesson(1,null,offerLesson.getId(),3,95,-1);
        Professor professor = new Professor(1,"aassdd","1122551","33",KindProfessor.SCIENCE);
        genericServiceImpel.delete(lesson);
        genericServiceImpel.delete(offerLesson);
        //genericServiceImpel.delete(professor);
        genericServiceImpel.delete(student);
    }

}