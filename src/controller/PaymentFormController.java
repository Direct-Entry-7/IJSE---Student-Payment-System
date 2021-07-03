package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import model.Payment;
import model.PaymentDetailsTM;
import service.PaymentService;


import java.io.IOException;

public class PaymentFormController {
    public JFXButton btnAddNewPayment;
    public TableView<PaymentDetailsTM> tblPayment;
    public JFXTextField txtQuery;
    private ObservableList<PaymentDetailsTM> paymentsList = FXCollections.observableArrayList();
    private PaymentService paymentService = new PaymentService();
    public void initialize(){
        tblPayment.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("refNo"));
        tblPayment.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblPayment.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("studentNIC"));
        tblPayment.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        tblPayment.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("batchNo"));
        tblPayment.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));
        tblPayment.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblPayment.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("payment"));
        tblPayment.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("deleteButton"));

        TableColumn<PaymentDetailsTM, HBox> receipt = (TableColumn<PaymentDetailsTM, HBox>) tblPayment.getColumns().get(8);

        receipt.setCellValueFactory(param -> {
            ImageView imgEdit = new ImageView("/view/assets/icons/assignment1.png");
            imgEdit.getStyleClass().add("action-icons");


//            imgEdit.setOnMouseClicked(event -> updateStudent(param.getValue()));

            return new ReadOnlyObjectWrapper<>(new HBox(50,imgEdit));
        });

        txtQuery.textProperty().addListener((observable, oldValue, newValue) -> {
            loadData(newValue);
        });


        loadData("");
    }

    private void loadData(String query) {
        paymentsList.clear();
        for (Payment payment: paymentService.getAllPayments(query)) {
            JFXButton deleteButton = new JFXButton("Delete");
            deleteButton.getStyleClass().add("delete-button");
            PaymentDetailsTM pdTM = new PaymentDetailsTM(payment.getRefNo(), payment.getCourseCode(), payment.getBatchNo(), payment.getStudentNIC(), payment.getDescription(), payment.getPaymentMethod(), payment.getDate(), payment.getFile(), payment.getAmount(), payment.getNote(),deleteButton);
            paymentsList.add(pdTM);
        }
        tblPayment.setItems(paymentsList);
    }

    public void btnAddNewPayment_OnAction(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/view/AddNewPaymentForm.fxml"));
        AnchorPane pneMainContext = (AnchorPane) btnAddNewPayment.getParent().getParent();
        pneMainContext.getChildren().clear();
        pneMainContext.getChildren().add(p);
    }
}
