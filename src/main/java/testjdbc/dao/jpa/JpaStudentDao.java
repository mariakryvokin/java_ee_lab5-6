package testjdbc.dao.jpa;

import org.springframework.stereotype.Repository;
import testjdbc.entity.jdbc.Student;
import testjdbc.entity.jpa.JpaStudent;

@Repository
public class JpaStudentDao extends AbstractJpaDao<JpaStudent, Integer> {
    public JpaStudentDao() {
        super(JpaStudent.class);
    }
}
