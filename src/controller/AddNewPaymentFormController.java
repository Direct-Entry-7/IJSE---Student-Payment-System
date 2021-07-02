package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.*;
import service.PaymentService;
import service.StudentService;
import service.exception.NotFoundException;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
    public JFXComboBox cmbSelectCoursePaymentDetails;
    public JFXComboBox cmbSelectCoursePayment;
    public TableView<PaymentDetailsTM> tblPaymentDetails;
    public Label lblCourseCode;
    public Label lblCourseFee;
    public Label lblBatchNo;
    public Label lblPayment;
    private Student student;
    private StudentService studentService = new StudentService();
    private PaymentService paymentService = new PaymentService();
    private ObservableList<PaymentDetailsTM> batchesList = FXCollections.observableArrayList();

    public void initialize(){
        txtSearchByNIC.setText("9512938224v");
        tblPaymentDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblPaymentDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        tblPaymentDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblPaymentDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("refNo"));
        tblPaymentDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("payment"));

        cmbSelectCoursePaymentDetails.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    System.out.println(newValue);
                    loadPaymentDetails(newValue.toString());
                });
    }

    private void loadPaymentDetails(String courseCode) {
        batchesList.clear();
        try {
            List<Payment> payments = paymentService.findPayments(student.getNic(), courseCode);
            BigDecimal amount = BigDecimal.ZERO;
            for (Payment payment:payments) {
                amount = amount.add(payment.getAmount());
                PaymentDetailsTM pdTM = new PaymentDetailsTM(payment.getRefNo(), payment.getCourseCode(), payment.getBatchNo(), payment.getStudentNIC(), payment.getDescription(), payment.getPaymentMethod(), payment.getDate(), payment.getFile(), payment.getAmount(), payment.getNote(), new Button("Delete"));
                batchesList.add(pdTM);
                lblCourseCode.setText(courseCode);
                lblBatchNo.setText(payment.getBatchNo());
                lblPayment.setText(amount.toString());
            }
            tblPaymentDetails.setItems(batchesList);

        } catch (NotFoundException e) {
            e.printStackTrace();
        }


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

            Map<String, String> courseWithBatch = student.getCourseWithBatch();
            ObservableList<String> paymentDetails = cmbSelectCoursePaymentDetails.getItems();
            ObservableList<String> payment = cmbSelectCoursePayment.getItems();

            for (String course:courseWithBatch.keySet()) {
               paymentDetails.add(course);
               payment.add(course);
            }
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


    }

    public void btnClear_OnAction(ActionEvent actionEvent) {
    }

    public void btnPrint_OnAction(ActionEvent actionEvent) {
    }
}
