package testjdbc.dao.jpa;

import org.springframework.stereotype.Repository;
import testjdbc.entity.jpa.Course;

@Repository
public class CourseDao extends AbstractJpaDao<Course, Integer>{
    public CourseDao() {
        super(Course.class);
    }
}
