package controller;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

public class AddNewPaymentFormController {

    public void fleSelectReceipt_OnMouseClicked(MouseEvent mouseEvent) {
        System.out.println("Called");
        Window window = ((Node) (mouseEvent.getSource())).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(window);
        mouseEvent.consume();
    }
}
