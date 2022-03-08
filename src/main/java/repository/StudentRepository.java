package repository;

import entity.Student;
import org.hibernate.SessionFactory;

import java.util.List;

public class StudentRepository implements Repository<Student> {

    private final SessionFactory sessionFactory = SessionFactorySingleton.getInstance();

    @Override
    public Student findById(int id) {
        try (var session = sessionFactory.openSession()) {
            Student student = session.find(Student.class,id);
            if(student == null )
                return null;
            else
                return student;
        }
    }

    @Override
    public List<Student> findAll() {
        try (var session = sessionFactory.openSession()) {
            var query = session.createQuery("FROM UserAccount ", Student.class);
            List<Student> studentList = query.list();
            return studentList;
        }
    }
}
