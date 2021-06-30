package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.Payment;
import model.Student;
import service.StudentService;
import service.exception.NotFoundException;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AddNewPaymentFormController {

    public JFXTextField txtSearchByNIC;
    public Label lblName;
    public Label lblNIC;
    public Label lblEmail;
    public Label lblMobileNo;
    public Label lblAddress;
    public JFXComboBox cmbSelectCourse;
    public JFXComboBox cmbDescription;
    public JFXComboBox cmbPaymentMethod;
    public JFXTextField txtAmount;
    public JFXTextField txtNote;
    public ImageView fleSelectReceipt;
    public JFXDatePicker dtSelectDate;
    public JFXTextField txtRefNo;
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

    public void btnPay_OnAction(ActionEvent actionEvent) {
        String courseCode = cmbSelectCourse.getSelectionModel().getSelectedItem().toString();
        String description = cmbDescription.getSelectionModel().getSelectedItem().toString();
        String paymentMethod = cmbPaymentMethod.getSelectionModel().getSelectedItem().toString();
        LocalDate selectedDate = LocalDate.parse(dtSelectDate.getValue().toString());
        BigDecimal amount = BigDecimal.valueOf(Integer.valueOf(txtAmount.getText()));
        String refNo = txtRefNo.getText();        
        String note = txtNote.getText();

        new Payment(refNo, courseCode, description, paymentMethod, selectedDate, null, amount, note, student)
    }

    public void btnClear_OnAction(ActionEvent actionEvent) {
    }

    public void btnPrint_OnAction(ActionEvent actionEvent) {
    }
}
