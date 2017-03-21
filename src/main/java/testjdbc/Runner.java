package testjdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import testjdbc.config.AppConfig;
import testjdbc.dao.jpa.CourseDao;
import testjdbc.dao.jpa.JpaStudentDao;
import testjdbc.dao.jpa.TeacherDao;
import testjdbc.entity.jdbc.Student;
import testjdbc.entity.jpa.Course;
import testjdbc.entity.jpa.JpaStudent;
import testjdbc.entity.jpa.Teacher;
import testjdbc.service.jdbc.StudentService;

import java.util.ArrayList;

@Component
public class Runner {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ctx.getBean(Runner.class).run(ctx);
    }


    @Transactional
    public void run(ApplicationContext ctx) {
        //jdbc
        StudentService studentService = ctx.getBean(StudentService.class);
        Student student = new Student();
        student.setPib("maria");
        student.setCourse(3);
        int generatedId = studentService.createStudent(student);

        //jpa
        TeacherDao teacherDao = ctx.getBean(TeacherDao.class);
        Teacher teacher = new Teacher();
        teacher.setName("Galina Petrovna");
        teacher = teacherDao.save(teacher);

        CourseDao courseDao = ctx.getBean(CourseDao.class);
        JpaStudentDao jpaStudentDao = ctx.getBean(JpaStudentDao.class);

        JpaStudent createdStudent = jpaStudentDao.findById(generatedId);
        ArrayList<JpaStudent> students = new ArrayList<>();
        students.add(createdStudent);

        Course course = new Course();
        course.setName("mathematics");
        course.setTeacher(teacher);
        course.setJpaStudents(students);
        course = courseDao.save(course);

        Course course1 = new Course();
        course1.setName("biology");
        course1.setTeacher(teacher);
        course1.setJpaStudents(students);
        course1 = courseDao.save(course1);

        ArrayList<Course> courses = new ArrayList<>();
        courses.add(course);
        courses.add(course1);

        if (createdStudent.getCourses() == null) {
            createdStudent.setCourses(courses);
        } else {
            createdStudent.getCourses().addAll(courses);
        }
    }
}
