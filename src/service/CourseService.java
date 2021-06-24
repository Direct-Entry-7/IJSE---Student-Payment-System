package service;

import model.Course;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private static final List<Course> coursesDB = new ArrayList<>();

    static {
        Course course1 = new Course("001", "AAA", 10000.0, "6", "Description1");
        Course course2 = new Course("002", "BBB", 20000.0, "7", "Description2");
        Course course3 = new Course("003", "CCC", 30000.0, "8", "Description3");
        Course course4 = new Course("004", "DDD", 40000.0, "9", "Description4");
        Course course5 = new Course("005", "EEE", 50000.0, "10", "Description5");

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
