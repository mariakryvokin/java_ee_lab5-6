package testjdbc.dao.jpa;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import testjdbc.entity.jpa.JpaStudent;

import java.util.List;

@Repository
public class JpaStudentDao extends AbstractJpaDao<JpaStudent, Integer> {
    public JpaStudentDao() {
        super(JpaStudent.class);
    }

    public List<JpaStudent> getStudentsByNameLike(String part) {
        return entityManager.createNamedQuery("findStudentsByNameLike", JpaStudent.class).getResultList();
    }

    @Override
    @Cacheable(cacheNames = "studentsCache")
    public JpaStudent findById(Integer integer) {
        return super.findById(integer);
    }
}
