package repository;

import entity.Clerk;
import entity.Lesson;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class LessonRepository implements Repository<Lesson> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public Lesson findById(int id) {
        try (var session = sessionFactory.openSession()) {
            Lesson lesson = session.find(Lesson.class,id);
            if(lesson == null )
                return null;
            else
                return lesson;
        }
    }

    @Override
    public List<Lesson> findAll() {
        try (var session = sessionFactory.openSession()) {
            var query = session.createQuery("FROM Lesson ", Lesson.class);
            List<Lesson> lessonList = query.list();
            return lessonList;
        }
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

    public List<Lesson> showLessonForProfessor(int id){
        try (var session = sessionFactory.openSession()) {
            NativeQuery query = session.createSQLQuery("select * from lesson\n" +
                                                          "join offerlesson offer on idofferlesson = offer.id" +
                                                          " where professor_id = :id");
            query.addEntity(Lesson.class);
            query.setParameter("id",id);
            List lessons = query.list();
            return lessons;
        }
    }

}
