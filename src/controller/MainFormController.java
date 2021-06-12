package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class MainFormController {
    public AnchorPane pneMainContext;

    public void initialize() throws IOException {
        pneMainContext.getChildren().clear();
        URL resource = getClass().getResource("/view/HomeForm.fxml");
        Parent p = FXMLLoader.load(resource);
        pneMainContext.getChildren().add(p);
    }
}
