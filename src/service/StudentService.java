package service;

import model.Student;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private static final List<Student> studentDB = new ArrayList<>();

    static {
        Student student1 = new Student("Rajitha","rajitha@gmail.com","0718284821","9312938282",LocalDate.of(1995,02,10),"male","address1");
        Student student2 = new Student("Pashan","pashan@yahoo.com","0778284111","9512938224",LocalDate.of(1995,12,15),"male","address2");
        Student student3 = new Student("Kusal","kusal@hotmail.com","0777284820","9212938278",LocalDate.of(1995,10,18),"male","address3");
        Student student4 = new Student("Panduka","panduka@gmail.com","0718284512","9112938269",LocalDate.of(1995,04,13),"male","address4");
        Student student5 = new Student("Lasitha","lasitha@gmail.com","0718284822","9512938803",LocalDate.of(1995,07,10),"male","address5");
        studentDB.add(student1);
        studentDB.add(student2);
        studentDB.add(student3);
        studentDB.add(student4);
        studentDB.add(student5);
    }

    public StudentService() {
    }

    public void saveStudent(Student student) throws DuplicateEntryException {
        if (exitsStudent(student.getNic())) {

        }
        studentDB.add(student);
    }

    private boolean exitsStudent(String nic) {
        for (Student student : studentDB) {

            if (student.getNic().equals(nic)) {
                return true;
            }
        }
        return false;
    }

    public void updateStudent(Student student) throws NotFoundException {
        Student s = findStudent(student.getNic());
        int index = studentDB.indexOf(s);
        studentDB.set(index, student);
    }

    public void deleteStudent(String nic) throws NotFoundException {
        Student student = findStudent(nic);
        studentDB.remove(student);
    }

    public List<Student> getAllCourses() {

        return studentDB;
    }

    private Student findStudent(String nic) throws NotFoundException {
        for (Student student : studentDB) {

            if (student.getNic().equals(nic)) {
                return student;
            }
        }
        throw new NotFoundException();

    }
}
