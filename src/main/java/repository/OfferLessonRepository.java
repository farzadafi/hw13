package repository;

import entity.OfferLesson;

import java.util.List;

public class OfferLessonRepository implements Repository<OfferLesson> {

    private final Connection connection = new Connection();

    @Override
    public OfferLesson findById(int id) {
        try (var session = connection.openCurrentSession()) {
            OfferLesson offerLesson = session.find(OfferLesson.class,id);
            return offerLesson;
        }
    }

    @Override
    public List<OfferLesson> findAll() {
        try (var session = connection.openCurrentSession()) {
            var query = session.createQuery("FROM OfferLesson ",OfferLesson.class);
            List<OfferLesson> offerLessonList = query.list();
            return offerLessonList;
        }

    }

    public OfferLesson findLesson(String lessonName) {
        try (var session = connection.openCurrentSession()) {
            var query = session.createQuery("FROM OfferLesson as a WHERE a.LessonName = :LessonName", OfferLesson.class);
            query.setParameter("LessonName", lessonName);
            var result = query.getSingleResult();
            return result;
        }
    }
}
