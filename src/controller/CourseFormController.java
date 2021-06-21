package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Course;
import service.CourseService;

public class CourseFormController {
    public JFXTextField txtCourseCode;
    public JFXTextField txtName;
    public JFXTextField txtCourseFee;
    public JFXTextField txtDuration;
    public JFXTextField txtDescription;
    public JFXButton btnAddCourse;
    public TableView tblCourses;

    private CourseService courseService = new CourseService();

    public void initialize(){

    }

    public void btnAddCourse_OnAction(ActionEvent actionEvent) {
        String courseCode = txtCourseCode.getText();
        String name = txtName.getText();
        Double courseFee = Double.parseDouble(txtCourseFee.getText());
        String duration = txtDuration.getText();
        String description = txtDescription.getText();
        Course course = new Course(courseCode, name, courseFee, duration, description);

        courseService.saveCourse(course);

        new Alert(Alert.AlertType.CONFIRMATION,"Course Added Successfully", ButtonType.OK).show();

    }
}
