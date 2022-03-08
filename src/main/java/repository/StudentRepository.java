package repository;

import entity.Student;

import java.util.List;

public class StudentRepository implements Repository<Student> {

    private final Connection connection = new Connection();

    @Override
    public Student findById(int id) {
        try (var session = connection.openCurrentSession()) {
            Student student = session.find(Student.class,id);
            if(student == null )
                return null;
            else
                return student;
        }
    }

    @Override
    public List<Student> findAll() {
        try (var session = connection.openCurrentSession()) {
            var query = session.createQuery("FROM UserAccount ", Student.class);
            List<Student> studentList = query.list();
            return studentList;
        }
    }
}
