import model.Course;
import service.CourseService;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;

public class CourseServiceTest {
    public static void main(String[] args) throws DuplicateEntryException, NotFoundException {
        saveCourse();
        updateCourse();
        deleteCourse();
        getAllCourses();
    }

    private static void saveCourse() throws DuplicateEntryException, NotFoundException {
        CourseService courseService = new CourseService();
        Course course1 = new Course("014", "AAA", 10000.0, "6", "Description1");
        Course course2 = new Course("015", "AAA", 10000.0, "6", "Description1");
        courseService.saveCourse(course1);
        courseService.saveCourse(course2);
        assert courseService.findCourse("001") != null : "failed save test";
        assert courseService.findCourse("002") != null : "failed save test";
    }

    private static void updateCourse() throws NotFoundException {
        CourseService courseService = new CourseService();
        Course course1 = new Course("014", "Dep", 10000.0, "6", "Description1");
        courseService.updateCourse(course1);
        assert courseService.findCourse("014") != null : "failed update test";
        assert courseService.findCourse("014").getName().equals("Dep") : "failed update test";
    }

    private static void deleteCourse() throws NotFoundException {
        CourseService courseService = new CourseService();
        courseService.deleteCourse("001");
        assert courseService.findCourse("001") == null : "failed delete test";
    }

    private static void getAllCourses() {
        CourseService courseService = new CourseService();
        assert courseService.getAllCourses().size() == 1 : "failed findAllCourses test";
    }

}
