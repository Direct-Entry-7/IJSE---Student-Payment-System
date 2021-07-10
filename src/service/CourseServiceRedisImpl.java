package service;

import model.Course;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;

import java.util.List;

public class CourseServiceRedisImpl {

    public CourseServiceRedisImpl() {
    }

    public void saveCourse(Course course) throws DuplicateEntryException {

    }

    private boolean exitsCourse(String courseCode) {

        return false;
    }

    public void updateCourse(Course course) throws NotFoundException {

    }

    public void deleteCourse(String courseCode) throws NotFoundException {

    }

    public List<Course> getAllCourses(){

        return null;
    }

    public Course findCourse(String courseCode) throws NotFoundException {

        return null;
    }
}
