package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class MainFormController {
    public AnchorPane pneMainContext;

    public void initialize() throws IOException {
        pneMainContext.getChildren().clear();
        URL resource = getClass().getResource("/view/BatchForm.fxml");
        Parent p = FXMLLoader.load(resource);
        pneMainContext.getChildren().add(p);
    }


    public void hbxHome_OnMouseClicked(MouseEvent mouseEvent) {
        System.out.println("Clicked");
    }
}
