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
        Student student1 = new Student("9712938283v", "Nimal","nimal@gmail.com","0788284866",LocalDate.of(1995,06,11),"Male","address10");
        Student student2 = new Student("9812938220v","Kamal","kamal@yahoo.com","0777284144",LocalDate.of(1992,03,02),"Male","address11");
        studentService.saveStudent(student1);
        studentService.saveStudent(student2);
        assert studentService.findStudent("9712938283v") != null : "failed save test";
        assert studentService.findStudent("9812938220v") != null : "failed save test";
    }

    private static void updateStudent() throws NotFoundException {
        StudentService studentService = new StudentService();
        Student student1 = new Student("9712938283v","Sunil","nimal@gmail.com","0788284866", LocalDate.of(1995,06,11),"male","address10");
        studentService.updateStudent(student1);
        assert studentService.findStudent("9712938283v") != null : "failed update test";
        assert studentService.findStudent("9712938283v").getName().equals("Sunil") : "failed update test";
    }

    private static void deleteStudent() throws NotFoundException {
        StudentService studentService = new StudentService();
        studentService.deleteStudent("9712938283v");
        assert studentService.findStudent("9712938283v") == null : "failed delete test";
    }

    private static void getAllCourses() {
        StudentService studentService = new StudentService();
        assert studentService.getAllStudents().size() == 1 : "failed findAllStudents test";
    }

}

