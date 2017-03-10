package testjdbc.dao.jpa;

import org.springframework.stereotype.Repository;
import testjdbc.entity.jpa.Teacher;

@Repository
public class TeacherDao extends AbstractJpaDao<Teacher, Integer> {
    public TeacherDao() {
        super(Teacher.class);
    }
}
