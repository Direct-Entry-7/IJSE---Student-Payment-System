package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;

public class MainFormController {
    public AnchorPane pneMainContext;
    public HBox hbxHome;
    public HBox hbxCourse;
    public HBox hbxStudent;
    public HBox hbxBatch;
    public HBox hbxPayment;
    public HBox hbxReport;
    public HBox hbxUser;
    public HBox hbxLogOut;

    public void initialize() throws IOException {
        loadUI("Home");
    }


    public void hbxHome_OnMouseClicked(MouseEvent mouseEvent) {
        loadUI("Home");
    }

    public void hbxCourses_OnMouseClicked(MouseEvent mouseEvent) {
        loadUI("Course");
    }

    public void hbxStudents_OnMouseClicked(MouseEvent mouseEvent) {
        loadUI("Student");
    }

    public void hbxBatches_OnMouseClicked(MouseEvent mouseEvent) {
        loadUI("Batch");
    }

    public void hbxPayments_OnMouseClicked(MouseEvent mouseEvent) {
        loadUI("Payment");
    }

    public void hbxReports_OnMouseClicked(MouseEvent mouseEvent) {
        loadUI("Report");
    }

    public void hbxUser_OnMouseClicked(MouseEvent mouseEvent) {
        loadUI("User");
    }

    public void hbxLogOut_OnMouseClicked(MouseEvent mouseEvent) {
        loadUI("Logout");
    }

    private void loadUI(String name) {
        pneMainContext.getChildren().clear();
        URL resource = getClass().getResource("/view/" + name + "Form.fxml");
        try {
            Parent p = FXMLLoader.load(resource);
            pneMainContext.getChildren().add(p);
            setActive(name);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setActive(String name) {
        String active = "-fx-background-color: black;-fx-opacity: 0.69;";
        String inActive = "-fx-background-color: #ED1C1C";
        switch(name) {
            case "Home":
                hbxHome.setStyle(active);
                hbxCourse.setStyle(inActive);
                hbxStudent.setStyle(inActive);
                hbxBatch.setStyle(inActive);
                hbxPayment.setStyle(inActive);
                hbxReport.setStyle(inActive);
                hbxUser.setStyle(inActive);
                hbxLogOut.setStyle(inActive);
                break;
            case "Course":
                hbxHome.setStyle(inActive);
                hbxCourse.setStyle(active);
                hbxStudent.setStyle(inActive);
                hbxBatch.setStyle(inActive);
                hbxPayment.setStyle(inActive);
                hbxReport.setStyle(inActive);
                hbxUser.setStyle(inActive);
                hbxLogOut.setStyle(inActive);
                break;
            case "Student":
                hbxHome.setStyle(inActive);
                hbxCourse.setStyle(inActive);
                hbxStudent.setStyle(active);
                hbxBatch.setStyle(inActive);
                hbxPayment.setStyle(inActive);
                hbxReport.setStyle(inActive);
                hbxUser.setStyle(inActive);
                hbxLogOut.setStyle(inActive);
                break;
            case "Batch":
                hbxHome.setStyle(inActive);
                hbxCourse.setStyle(inActive);
                hbxStudent.setStyle(inActive);
                hbxBatch.setStyle(active);
                hbxPayment.setStyle(inActive);
                hbxReport.setStyle(inActive);
                hbxUser.setStyle(inActive);
                hbxLogOut.setStyle(inActive);
                break;
            case "Payment":
                hbxHome.setStyle(inActive);
                hbxCourse.setStyle(inActive);
                hbxStudent.setStyle(inActive);
                hbxBatch.setStyle(inActive);
                hbxPayment.setStyle(active);
                hbxReport.setStyle(inActive);
                hbxUser.setStyle(inActive);
                hbxLogOut.setStyle(inActive);
                break;
            case "Report":
                hbxHome.setStyle(inActive);
                hbxCourse.setStyle(inActive);
                hbxStudent.setStyle(inActive);
                hbxBatch.setStyle(inActive);
                hbxPayment.setStyle(inActive);
                hbxReport.setStyle(active);
                hbxUser.setStyle(inActive);
                hbxLogOut.setStyle(inActive);
                break;
            case "User":
                hbxHome.setStyle(inActive);
                hbxCourse.setStyle(inActive);
                hbxStudent.setStyle(inActive);
                hbxBatch.setStyle(inActive);
                hbxPayment.setStyle(inActive);
                hbxReport.setStyle(inActive);
                hbxUser.setStyle(active);
                hbxLogOut.setStyle(inActive);
                break;
            case "LogOut":
                hbxHome.setStyle(inActive);
                hbxCourse.setStyle(inActive);
                hbxStudent.setStyle(inActive);
                hbxBatch.setStyle(inActive);
                hbxPayment.setStyle(inActive);
                hbxReport.setStyle(inActive);
                hbxUser.setStyle(inActive);
                hbxLogOut.setStyle(active);
                break;
        }

    }


}
