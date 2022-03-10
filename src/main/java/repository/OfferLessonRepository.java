package repository;

import entity.OfferLesson;
import org.hibernate.SessionFactory;

import java.util.List;

public class OfferLessonRepository implements Repository<OfferLesson> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public OfferLesson findById(int id) {
        var session = sessionFactory.getCurrentSession();
        OfferLesson offerLesson = session.find(OfferLesson.class,id);
        return offerLesson;
    }

    @Override
    public List<OfferLesson> findAll() {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("FROM OfferLesson ",OfferLesson.class);
        List<OfferLesson> offerLessonList = query.list();
        return offerLessonList;
    }

    public OfferLesson findLesson(String lessonName) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("FROM OfferLesson as a WHERE a.LessonName = :LessonName", OfferLesson.class);
        query.setParameter("LessonName", lessonName);
        var result = query.getSingleResult();
        return result;
    }
}
