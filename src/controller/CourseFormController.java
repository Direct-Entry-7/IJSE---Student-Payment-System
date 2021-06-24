package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Course;
import model.CourseTM;
import service.CourseService;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseFormController {
    public JFXTextField txtCourseCode;
    public JFXTextField txtName;
    public JFXTextField txtCourseFee;
    public JFXTextField txtDuration;
    public JFXTextField txtDescription;
    public JFXButton btnAddCourse;
    public TableView<CourseTM> tblCourses;
    public JFXButton btnSaveCourse;

    private CourseService courseService = new CourseService();
    private ObservableList<CourseTM> coursesList = FXCollections.observableArrayList();


    public void initialize(){
        tblCourses.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        tblCourses.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCourses.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("courseFee"));
        tblCourses.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("duration"));
        tblCourses.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblCourses.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("btn"));

        btnAddCourse.setVisible(false);

        tblCourses.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        setData(newValue);
                    }

                });

        loadCourses();
    }

    private void setData(CourseTM value) {
        btnAddCourse.setVisible(true);
        txtCourseCode.setText(value.getCourseCode());
        txtCourseCode.setDisable(true);
        txtName.setText(value.getName());
        txtCourseFee.setText(String.valueOf(value.getCourseFee()));
        txtDuration.setText(value.getDuration());
        txtDescription.setText(value.getDescription());
        btnSaveCourse.setText("Update");
    }

    private void loadCourses() {
        coursesList.clear();

        for (Course course: courseService.getAllCourses()) {
//            Button btnDelete = new Button("Delete");
            JFXButton btnDelete = new JFXButton("Delete");
            btnDelete.getStyleClass().add("delete-button");

            CourseTM courseTM = new CourseTM(course.getCourseCode(), course.getName(), course.getCourseFee(), course.getDuration(), course.getDescription(), btnDelete);
            coursesList.add(courseTM);

            btnDelete.setOnAction(event -> {
                Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this course?", ButtonType.YES, ButtonType.NO).showAndWait();
                if (buttonType.get() == ButtonType.YES) {
                    System.out.println("Called");
                    try {
                        courseService.deleteCourse(course.getCourseCode());
                        tblCourses.getItems().remove(courseTM);
                    } catch (NotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        tblCourses.setItems(coursesList);
    }

    public void btnSaveCourse_OnAction(ActionEvent actionEvent) {
        String courseCode = txtCourseCode.getText();
        String name = txtName.getText();
        Double courseFee = Double.parseDouble(txtCourseFee.getText());
        String duration = txtDuration.getText();
        String description = txtDescription.getText();
        Course course = new Course(courseCode, name, courseFee, duration, description);


        if(btnSaveCourse.getText().equals("Save")){
            try {
                courseService.saveCourse(course);
            } catch (DuplicateEntryException e) {
                e.printStackTrace();
            }
        } else{
            try {
                courseService.updateCourse(course);
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }


        new Alert(Alert.AlertType.CONFIRMATION,"Course Added Successfully", ButtonType.OK).show();

        loadCourses();
    }

    public void btnAddCourse_OnAction(ActionEvent actionEvent) {
        txtCourseCode.clear();
        txtCourseCode.setDisable(false);
        txtName.clear();
        txtCourseFee.clear();
        txtDuration.clear();
        txtDescription.clear();
    }
}
