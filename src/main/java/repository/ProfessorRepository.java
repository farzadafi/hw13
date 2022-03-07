package repository;

import entity.Professor;
import entity.UserAccount;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProfessorRepository implements Repository<Professor> {

    private SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public Professor findById(int id) {
        return null;
    }

    @Override
    public List<Professor> findAll() {
        return null;
    }

    public Professor findProfessor(String nationalId) {
        try (var session = sessionFactory.openSession()) {
            var query = session.createQuery("FROM Professor as a WHERE a.nationalId = :nationalId", Professor.class);
            query.setParameter("nationalId", nationalId);
            var result = query.getSingleResult();
            return result;
        }
    }
}
