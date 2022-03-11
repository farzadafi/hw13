package service;

import entity.OfferLesson;
import entity.Professor;
import entity.enomuration.KindProfessor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OfferLessonServiceTest {

    private static GenericServiceImpel genericServiceImpel = new GenericServiceImpel();
    private OfferLessonService offerLessonService = new OfferLessonService();
    static Professor professor = new Professor(null,"farzad","399","a", KindProfessor.SCIENCE);
    static OfferLesson offerLesson = new OfferLesson(1,"math1",3,professor);

    @BeforeAll
    public static void add(){
        genericServiceImpel.add(professor);
        genericServiceImpel.add(offerLesson);
    }

    @Test
    public void testFindLesson(){
        OfferLesson offerLesson1 = offerLessonService.findLesson(offerLesson.getLessonName());

        assertNotNull(offerLesson1);
        assertEquals(offerLesson1.getLessonName(),offerLesson.getLessonName());
    }

    @Test
    public void testFindAll(){
        List<OfferLesson> offerLessonList = offerLessonService.findAll();

        if(offerLessonList.size() < 1 )
            fail();
    }

    @Test
    public void testFindById(){
        OfferLesson offerLesson = offerLessonService.findById(1);

        assertNotNull(offerLesson);
    }

    @AfterAll
    public static void deleteAll(){
        genericServiceImpel.delete(offerLesson);
        genericServiceImpel.delete(professor);
    }

}