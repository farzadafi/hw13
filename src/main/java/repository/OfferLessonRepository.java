package repository;

import entity.OfferLesson;
import entity.Professor;
import org.hibernate.SessionFactory;

import java.util.List;

public class OfferLessonRepository implements Repository<OfferLesson> {
    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public OfferLesson findById(int id) {
        return null;
    }

    @Override
    public List<OfferLesson> findAll() {
        return null;
    }

    public OfferLesson findLesson(String lessonName) {
        try (var session = sessionFactory.openSession()) {
            var query = session.createQuery("FROM OfferLesson as a WHERE a.LessonName = :LessonName", OfferLesson.class);
            query.setParameter("LessonName", lessonName);
            var result = query.getSingleResult();
            return result;
        }
    }
}
