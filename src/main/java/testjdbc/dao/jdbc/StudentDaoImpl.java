package testjdbc.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import testjdbc.entity.jdbc.Student;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class StudentDaoImpl implements StudentDao {

//    private static final String QUERY_INSERT_STUDENT = "insert into students (pib, course) values(?, ?)"; //replaced by SimpleJdbcInsert to return generated key
    private static final String QUERY_SELECT_STUDENT_BY_ID = "select id, pib, course from student where id = ?";
    private static final String QUERY_DELETE_STUDENT_BY_ID = "delete from student where id = ?";

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertStudent;

    @Autowired
    public StudentDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertStudent = new SimpleJdbcInsert(dataSource).withTableName("students").usingGeneratedKeyColumns("id");
    }

    public int insert(Student student) {
//        jdbcTemplate.update(QUERY_INSERT_STUDENT, student.getPib(), student.getCourse()); //replaced by SimpleJdbcInsert to return generated key
        Map<String, Object> args = new HashMap<>();
        args.put("pib", student.getPib());
        args.put("course", student.getCourse());
        return insertStudent.executeAndReturnKey(args).intValue();
    }

    public Student getById(Integer id) {
        return jdbcTemplate.queryForObject(QUERY_SELECT_STUDENT_BY_ID, (rs, rowNum) -> {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setPib(rs.getString("pib"));
            student.setCourse(rs.getInt("course"));
            return student;
        }, id);
    }

    public void delete(Integer id) {
        jdbcTemplate.update(QUERY_DELETE_STUDENT_BY_ID, id);
    }


}
