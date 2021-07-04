package service;

import model.Student;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentService {
    private static final List<Student> studentDB = new ArrayList<>();

    static {
        HashMap<String, String> stu2 = new HashMap<>();
        stu2.put("001","3");
        stu2.put("002","4");
        Student student1 = new Student("931293828v","Rajitha","rajitha@gmail.com","0718284821",LocalDate.of(1995,02,10),"Male","address1");
        Student student2 = new Student("951293822v","Pashan","pashan@yahoo.com","0778284111",LocalDate.of(1995,12,15),"Male","address2",stu2);
        Student student3 = new Student("921293827v","Kusal","kusal@hotmail.com","0777284820",LocalDate.of(1995,10,18),"Male","address3");
        Student student4 = new Student("911293826v","Panduka","panduka@gmail.com","0718284512",LocalDate.of(1995,04,13),"Male","address4");
        Student student5 = new Student("951293880v","Lasitha","lasitha@gmail.com","0718284822",LocalDate.of(1995,07,10),"Male","address5");
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

    public List<Student> getAllStudents() {

        return studentDB;
    }

    public Student findStudent(String nic) throws NotFoundException {
        for (Student student : studentDB) {

            if (student.getNic().equals(nic)) {
                return student;
            }
        }
        throw new NotFoundException();

    }
}
