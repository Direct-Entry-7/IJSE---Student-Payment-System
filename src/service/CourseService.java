package service;

import model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseService {

    private static final List<Course> coursesDB = new ArrayList<>();

    public CourseService() {
    }

    public void saveCourse(Course course){
        System.out.println(course);
    }

    public void updateCourse(Course course){

    }

    public void deleteCourse(String courseCode){

    }

    public List<Course> getAllCourses(){

        return null;
    }


}
