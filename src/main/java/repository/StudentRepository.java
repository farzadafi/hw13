package repository;

import entity.Student;
import org.hibernate.SessionFactory;

import java.util.List;

public class StudentRepository implements Repository<Student> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public Student findById(int id) {
        try (var session = sessionFactory.getCurrentSession()) {
            Student student = session.find(Student.class,id);
            if(student == null )
                return null;
            else
                return student;
        }
    }

    @Override
    public List<Student> findAll() {
        return null;
    }
}
