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
import model.Batch;
import model.Payment;
import model.PaymentDetailsTM;
import model.Student;
import service.BatchService;
import service.PaymentService;
import service.StudentService;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public Label lblBatchFee;
    public Label lblCurrentPayment;
    public Label lblBalance;
    private Student student;
    private StudentService studentService = new StudentService();
    private PaymentService paymentService = new PaymentService();
    private BatchService batchService = new BatchService();
    private ObservableList<PaymentDetailsTM> paymentList = FXCollections.observableArrayList();

    public void initialize() {
        txtSearchByNIC.setText("931293828v");
        tblPaymentDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblPaymentDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        tblPaymentDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblPaymentDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("refNo"));
        tblPaymentDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("payment"));

        cmbSelectCoursePaymentDetails.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    loadPaymentDetails(newValue.toString());
                });

        cmbSelectCoursePayment.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if(newValue == null) return;
                    List<Payment> payments = null;
                    try {
                        Batch batch = batchService.findBatch(student.getCourseWithBatch().get(newValue.toString()));
                        lblBatchFee.setText(batch.getCourseFee().toString());
                        lblCurrentPayment.setText("0.00");
                        lblBalance.setText(batch.getCourseFee().toString());

                        payments = paymentService.findPayments(student.getNic(), newValue.toString());
                        BigDecimal courseFee = batch.getCourseFee();

                        BigDecimal amount = BigDecimal.ZERO;
                        for (Payment payment : payments) {
                            amount = amount.add(payment.getAmount());
                            lblCurrentPayment.setText(amount.toString());
                            lblBalance.setText(courseFee.subtract(amount).toString());
                        }
                    } catch (NotFoundException e) {
                        return;
                    }

                });

        ObservableList<String> description = cmbDescription.getItems();
        description.add("Registration Fee");
        description.add("Installment");
        description.add("Full Payment");

        ObservableList<String> paymentMethods = cmbPaymentMethod.getItems();
        paymentMethods.add("Online Payment");
        paymentMethods.add("Bank Transfer");
        paymentMethods.add("Handover");


    }

    private void loadPaymentDetails(String courseCode) {
        lblCourseCode.setText("");
        lblBatchNo.setText("");
        lblPayment.setText("");
        lblCourseFee.setText("");
        paymentList.clear();
        try {
            Batch batch = batchService.findBatch(student.getCourseWithBatch().get(courseCode));
            lblCourseCode.setText(courseCode);
            lblBatchNo.setText(batch.getBatchNo());
            lblPayment.setText("0");
            lblCourseFee.setText(batch.getCourseFee().toString());
            tblPaymentDetails.setItems(paymentList);

            List<Payment> payments = paymentService.findPayments(student.getNic(), courseCode);


            BigDecimal amount = BigDecimal.ZERO;
            for (Payment payment : payments) {
                amount = amount.add(payment.getAmount());
                PaymentDetailsTM pdTM = new PaymentDetailsTM(payment.getRefNo(), payment.getCourseCode(), payment.getBatchNo(), payment.getStudentNIC(), payment.getDescription(), payment.getPaymentMethod(), payment.getDate(), payment.getFile(), payment.getAmount(), payment.getNote(), new Button("Delete"));
                paymentList.add(pdTM);

                lblPayment.setText(amount.toString());
            }


        } catch (NotFoundException e) {
            return;
        }


    }

    public void fleSelectReceipt_OnMouseClicked(MouseEvent mouseEvent) {
        Window window = ((Node) (mouseEvent.getSource())).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(window);
        mouseEvent.consume();
    }

    public void btnSearchStudent_OnAction(ActionEvent actionEvent) {
        String nic = txtSearchByNIC.getText();
        ObservableList<String> paymentDetails = cmbSelectCoursePaymentDetails.getItems();
        ObservableList<String> payment = cmbSelectCoursePayment.getItems();

        this.student = null;
        paymentDetails.clear();
        payment.clear();
        paymentList.clear();

        if (!(txtSearchByNIC.getText().matches("(^\\d{9}[vV])|(^\\d{11}[vV])"))) {
            new Alert(Alert.AlertType.ERROR, "Invalid NIC number").show();
            txtSearchByNIC.requestFocus();
            return;
        }

        try {
            student = studentService.findStudent(nic);
            lblName.setText(student.getName());
            lblNIC.setText(student.getNic());
            lblMobileNo.setText(student.getMobileNumber());
            lblEmail.setText(student.getEmail());
            lblAddress.setText(student.getAddress());
            System.out.println(student);
            Map<String, String> courseWithBatch = student.getCourseWithBatch();

            for (String course : courseWithBatch.keySet()) {
                paymentDetails.add(course);
                payment.add(course);
            }
        } catch (NotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Not found student with this NIC. Try again", ButtonType.OK).show();
            txtSearchByNIC.requestFocus();
            return;
        }

    }

    public void btnPay_OnAction(ActionEvent actionEvent) {
        if (!isValidated()) {
            return;
        }

        String courseCode = cmbSelectCoursePayment.getSelectionModel().getSelectedItem().toString();
        String description = cmbDescription.getSelectionModel().getSelectedItem().toString();
        String paymentMethod = cmbPaymentMethod.getSelectionModel().getSelectedItem().toString();
        LocalDate selectedDate = LocalDate.parse(dtSelectDate.getValue().toString());
        BigDecimal amount = BigDecimal.valueOf(Integer.valueOf(txtAmount.getText()));
        String refNo = txtRefNo.getText();
        String note = txtNote.getText();
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to make this payment?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            try {
                BigDecimal balance = BigDecimal.valueOf(Integer.valueOf(lblBalance.getText()));
                if(amount.compareTo(balance)>0){
                    new Alert(Alert.AlertType.ERROR, "Cannot add amount greater than balance").show();
                    return;
                }
                Batch batch = batchService.findBatch(student.getCourseWithBatch().get(courseCode));
                Payment payment = new Payment(refNo, courseCode, batch.getBatchNo(), student.getNic(), description, paymentMethod, selectedDate, null, amount, note);
                paymentService.savePayment(payment);
                new Alert(Alert.AlertType.INFORMATION, "Payment successfully added", ButtonType.OK).showAndWait();
            } catch (NotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Cannot find this batch").show();
                return;
            } catch (DuplicateEntryException e) {
                new Alert(Alert.AlertType.ERROR, "Duplicate payment.").show();
                return;
            }
        }

    }

    public void btnClear_OnAction(ActionEvent actionEvent) {
        cmbSelectCoursePayment.getSelectionModel().clearSelection();
        cmbDescription.getSelectionModel().clearSelection();
        cmbPaymentMethod.getSelectionModel().clearSelection();
        dtSelectDate.setValue(null);
        txtAmount.setText("");
        txtRefNo.setText("");
        txtNote.setText("");
    }

    public void btnPrint_OnAction(ActionEvent actionEvent) {
    }

    private boolean isValidated() {
        try {
            cmbSelectCoursePayment.getSelectionModel().getSelectedItem().toString();
        } catch (NullPointerException e) {
            new Alert(Alert.AlertType.ERROR, "Course should be selected").show();
            cmbSelectCoursePayment.requestFocus();
            return false;
        }

        try {
            cmbDescription.getSelectionModel().getSelectedItem().toString();
        } catch (NullPointerException e) {
            new Alert(Alert.AlertType.ERROR, "Description should be selected").show();
            cmbDescription.requestFocus();
            return false;
        }

        try {
            cmbPaymentMethod.getSelectionModel().getSelectedItem().toString();
        } catch (NullPointerException e) {
            new Alert(Alert.AlertType.ERROR, "Payment method should be selected").show();
            cmbPaymentMethod.requestFocus();
            return false;
        }

        try {
            LocalDate selectedDate = LocalDate.parse(dtSelectDate.getValue().toString());
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Invalid Date. Please select the date").show();
            dtSelectDate.requestFocus();
            return false;
        }

        if (!(txtRefNo.getText().matches("[A-Za-z0-9]+"))) {
            new Alert(Alert.AlertType.ERROR, "Enter Valid Ref no").show();
            return false;
        }

        try {
            BigDecimal.valueOf(Integer.valueOf(txtAmount.getText()));
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Enter Valid Amount").show();
            txtAmount.requestFocus();
            return false;
        }



        if (!(txtNote.getText().matches("[A-Za-z0-9]*"))) {
            new Alert(Alert.AlertType.ERROR, "Enter Valid Note").show();
            return false;
        }

        return true;
    }
}
