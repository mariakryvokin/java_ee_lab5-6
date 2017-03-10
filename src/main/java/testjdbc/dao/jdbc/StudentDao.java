package testjdbc.dao.jdbc;

import testjdbc.entity.jdbc.Student;

public interface StudentDao {
    /**
     * Inserts student and returns generated id
     * @param student - entity to insert
     * @return generated id
     */
    int insert(Student student);

    Student getById(Integer id);

    void delete(Integer id);
}
