package service;

import model.Course;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private static final List<Course> coursesDB = new ArrayList<>();

    static {
        Course course1 = new Course("001", "AAA", BigDecimal.valueOf(10000), "6", "Description1");
        Course course2 = new Course("002", "BBB",  BigDecimal.valueOf(20000), "7", "Description2");
        Course course3 = new Course("003", "CCC", BigDecimal.valueOf(30000), "8", "Description3");
        Course course4 = new Course("004", "DDD",  BigDecimal.valueOf(40000), "9", "Description4");
        Course course5 = new Course("005", "EEE", BigDecimal.valueOf(50000), "10", "Description5");

        coursesDB.add(course1);
        coursesDB.add(course2);
        coursesDB.add(course3);
        coursesDB.add(course4);
        coursesDB.add(course5);
    }

    public CourseService() {
    }

    public void saveCourse(Course course) throws DuplicateEntryException {
        if (exitsCourse(course.getCourseCode())) {
            throw new DuplicateEntryException();
        }
        coursesDB.add(course);
    }

    private boolean exitsCourse(String courseCode) {
        for (Course course : coursesDB) {

            if (course.getCourseCode().equals(courseCode)) {
                return true;
            }
        }
        return false;
    }

    public void updateCourse(Course course) throws NotFoundException {
        Course c = findCourse(course.getCourseCode());
        int index = coursesDB.indexOf(c);
        coursesDB.set(index, course);
    }

    public void deleteCourse(String courseCode) throws NotFoundException {
        Course course = findCourse(courseCode);
        coursesDB.remove(course);
    }

    public List<Course> getAllCourses() {
        return coursesDB;
    }

    public Course findCourse(String courseCode) throws NotFoundException {
        for (Course course : coursesDB) {

            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        throw new NotFoundException();

    }

}
