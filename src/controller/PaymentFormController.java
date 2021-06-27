package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;

public class PaymentFormController {
    public JFXButton btnAddNewPayment;

    public void btnAddNewPayment_OnAction(ActionEvent actionEvent) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("/view/AddNewPaymentForm.fxml"));
        AnchorPane pneMainContext = (AnchorPane) btnAddNewPayment.getParent().getParent();
        pneMainContext.getChildren().clear();
        pneMainContext.getChildren().add(p);
    }
}
