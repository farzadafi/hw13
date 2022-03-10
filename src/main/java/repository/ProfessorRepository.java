package repository;

import entity.Professor;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProfessorRepository implements Repository<Professor> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public Professor findById(int id) {
        var session = sessionFactory.getCurrentSession();
        return session.find(Professor.class,id);
    }

    @Override
    public List<Professor> findAll() {
        return null;
    }

    public Professor findProfessor(String nationalId) {
        var session = sessionFactory.getCurrentSession();
        var query = session.createQuery("FROM Professor as a WHERE a.nationalId = :nationalId", Professor.class);
        query.setParameter("nationalId", nationalId);
        var result = query.getSingleResult();
        return result;
    }
}
