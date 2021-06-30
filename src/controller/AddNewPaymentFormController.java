package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.Student;
import service.StudentService;
import service.exception.NotFoundException;

import java.io.File;

public class AddNewPaymentFormController {

    public JFXTextField txtSearchByNIC;
    public Label lblName;
    public Label lblNIC;
    public Label lblEmail;
    public Label lblMobileNo;
    public Label lblAddress;
    private Student student;
    private StudentService studentService = new StudentService();

    public void initialize(){
        txtSearchByNIC.setText("9512938224v");
    }

    public void fleSelectReceipt_OnMouseClicked(MouseEvent mouseEvent) {
        System.out.println("Called");
        Window window = ((Node) (mouseEvent.getSource())).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(window);
        mouseEvent.consume();
    }

    public void btnSearchStudent_OnAction(ActionEvent actionEvent) {
        String nic = txtSearchByNIC.getText();

        try {
            student = studentService.findStudent(nic);
            lblName.setText(student.getName());
            lblNIC.setText(student.getNic());
            lblMobileNo.setText(student.getMobileNumber());
            lblEmail.setText(student.getEmail());
            lblAddress.setText(student.getAddress());
        } catch (NotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Not found student with this NIC. Try again", ButtonType.OK).show();
        }

    }
}
