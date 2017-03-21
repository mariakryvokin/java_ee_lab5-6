package testjdbc.entity.jpa;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teachers")
@NamedQuery(name = "findTeacherByName", query = "select teacher from Teacher teacher where teacher.name = :name")
public class Teacher {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "teacher")
    List<Course> courses;


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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        return id.equals(teacher.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
