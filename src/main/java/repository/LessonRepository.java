package repository;

import entity.Lesson;
import entity.OfferLesson;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import service.Service;

import java.util.ArrayList;
import java.util.List;

public class LessonRepository implements Repository<Lesson> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public Lesson findById(int id) {
        return null;
    }

    @Override
    public List<Lesson> findAll() {
        return null;
    }

    public List<Lesson> showMyLesson(int id){
        try (var session = sessionFactory.openSession()) {
            NativeQuery query = session.createSQLQuery("select * from lesson\n" +
                                                          "join student_lessons sl on lesson.id = sl.lesson_id" +
                                                           " where student_id = :id");
            query.addEntity(Lesson.class);
            query.setParameter("id",id);
            List lessons = query.list();
            return lessons;
        }

    }
}
