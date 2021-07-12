package service;

import model.Course;
import redis.clients.jedis.Jedis;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CourseServiceRedisImpl {
    private static final String DB_PREFIX = "course#";
    private final Jedis client;

    public CourseServiceRedisImpl() {
        client = new Jedis("localhost", 9090);
        client.auth("sps");
    }

    public void saveCourse(Course course) throws DuplicateEntryException {

        if (client.exists(DB_PREFIX + course.getCourseCode())) {
            throw new DuplicateEntryException();
        }

        client.hset(DB_PREFIX + course.getCourseCode(), course.toMap());
    }

    private boolean exitsCourse(String courseCode) {

        return client.exists(DB_PREFIX + courseCode);
    }

    public void updateCourse(Course course) throws NotFoundException {

        if (!client.exists(DB_PREFIX + course.getCourseCode())) {
            throw new NotFoundException();
        }

        client.hset(DB_PREFIX + course.getCourseCode(), course.toMap());
    }

    public void deleteCourse(String courseCode) throws NotFoundException {
        if (!client.exists(DB_PREFIX + courseCode)) {
            throw new NotFoundException();
        }
        client.del(DB_PREFIX + courseCode);
    }

    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        Set<String> courseCodeList = client.keys(DB_PREFIX+"*");

        for (String courseCode : courseCodeList) {
            courseList.add(Course.fromMap(courseCode.replace(DB_PREFIX,""), client.hgetAll(courseCode)));
        }
        return courseList;
    }

    public Course findCourse(String courseCode) throws NotFoundException {
        if (!client.exists(DB_PREFIX + courseCode)) {
            throw new NotFoundException();
        }

        return Course.fromMap(courseCode, client.hgetAll(DB_PREFIX + courseCode));
    }

    public List<Course> findCourses(String query) {
        List<Course> searchResult = new ArrayList<>();
        Set<String> courseCodeList = client.keys(DB_PREFIX+"*");

        for (String courseCode : courseCodeList) {
            if (courseCode.contains(query)) {
                searchResult.add(Course.fromMap(courseCode, client.hgetAll(courseCode)));
            } else {
                List<String> data = client.hvals(courseCode);

                for (String value : data) {
                    if (value.contains(query)) {
                        searchResult.add(Course.fromMap(courseCode, client.hgetAll(courseCode)));
                        break;
                    }
                }

            }
        }

        return searchResult;

    }
}
