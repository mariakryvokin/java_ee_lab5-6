package testjdbc.entity.jpa;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name")
    String name;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    Teacher teacher;

    @ManyToMany(mappedBy = "courses", cascade = CascadeType.ALL)
    List<JpaStudent> jpaStudents;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<JpaStudent> getJpaStudents() {
        return jpaStudents;
    }

    public void setJpaStudents(List<JpaStudent> jpaStudents) {
        this.jpaStudents = jpaStudents;
    }
}
