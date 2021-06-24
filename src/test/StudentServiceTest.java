import model.Course;
import model.Student;
import service.CourseService;
import service.StudentService;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;

import java.time.LocalDate;

public class StudentServiceTest {
    public static void main(String[] args) throws DuplicateEntryException, NotFoundException {
        saveStudent();
        updateStudent();
        deleteStudent();
        getAllCourses();
    }

    private static void saveStudent() throws DuplicateEntryException, NotFoundException {
        StudentService studentService = new StudentService();
        Student student1 = new Student("Nimal","nimal@gmail.com","0788284866","9712938283", LocalDate.of(1995,06,11),"male","address10");
        Student student2 = new Student("Kamal","kamal@yahoo.com","0777284144","9812938220",LocalDate.of(1992,03,02),"male","address11");
        studentService.saveStudent(student1);
        studentService.saveStudent(student2);
        assert studentService.findStudent("9712938283") != null : "failed save test";
        assert studentService.findStudent("9812938220") != null : "failed save test";
    }

    private static void updateStudent() throws NotFoundException {
        StudentService studentService = new StudentService();
        Student student1 = new Student("Sunil","nimal@gmail.com","0788284866","9712938283", LocalDate.of(1995,06,11),"male","address10");
        studentService.updateStudent(student1);
        assert studentService.findStudent("9712938283") != null : "failed update test";
        assert studentService.findStudent("9712938283").getName().equals("Sunil") : "failed update test";
    }

    private static void deleteStudent() throws NotFoundException {
        StudentService studentService = new StudentService();
        studentService.deleteStudent("9712938283");
        assert studentService.findStudent("9712938283") == null : "failed delete test";
    }

    private static void getAllCourses() {
        StudentService studentService = new StudentService();
        assert studentService.getAllStudents().size() == 1 : "failed findAllStudents test";
    }

}

