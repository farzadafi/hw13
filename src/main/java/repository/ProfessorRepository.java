package repository;

import entity.Professor;

import java.util.List;

public class ProfessorRepository implements Repository<Professor> {

    private final Connection connection = new Connection();

    @Override
    public Professor findById(int id) {
        try (var session = connection.openCurrentSession()) {
            Professor professor = session.find(Professor.class,id);
            if(professor == null )
                return null;
            else
                return professor;
        }
    }

    @Override
    public List<Professor> findAll() {
        try (var session = connection.openCurrentSession()) {
            var query = session.createQuery("FROM UserAccount ", Professor.class);
            List<Professor> professorList = query.list();
            return professorList;
        }
    }

    public Professor findProfessor(String nationalId) {
        try (var session = connection.openCurrentSession()) {
            var query = session.createQuery("FROM Professor as a WHERE a.nationalId = :nationalId", Professor.class);
            query.setParameter("nationalId", nationalId);
            var result = query.getSingleResult();
            return result;
        }
    }
}
