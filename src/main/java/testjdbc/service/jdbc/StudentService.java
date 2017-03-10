package testjdbc.service.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testjdbc.dao.jdbc.StudentDao;
import testjdbc.entity.jdbc.Student;

@Service
public class StudentService {

    private StudentDao studentDao;

    @Autowired
    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public int createStudent(Student student) {
        if (isValid(student))
            return studentDao.insert(student);
        else
            throw new IllegalArgumentException();
    }

    private boolean isValid(Student student) {
        return student.getPib() != null && !student.getPib().equals("") &&
                (student.getCourse() == null || (student.getCourse() > 0 && student.getCourse() < 9));
    }
}
