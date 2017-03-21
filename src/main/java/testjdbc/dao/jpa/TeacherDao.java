package testjdbc.dao.jpa;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import testjdbc.entity.jpa.Teacher;

@Repository
public class TeacherDao extends AbstractJpaDao<Teacher, Integer> {
    public TeacherDao() {
        super(Teacher.class);
    }

    @Override
    @Cacheable(cacheNames = "teachersCache")
    public Teacher findById(Integer integer) {
        return super.findById(integer);
    }

    public Teacher findByNameLike(String name) {
        return (Teacher) entityManager.createNamedQuery("findTeacherByName").setParameter("name", name).getSingleResult();
    }
}
