package repository;

import entity.Student;
import service.Service;

import java.util.List;

public class StudentRepository implements Repository<Student> {
    @Override
    public Student findById(int id) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return null;
    }
}
