import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        AnchorPane root = FXMLLoader.load(this.getClass().getResource("/view/SplashScreenForm.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Payment System");
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.centerOnScreen();


//        Parent mainForm = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
//        Scene mainScene = new Scene(mainForm);
//        primaryStage.setScene(mainScene);
//        primaryStage.setTitle("Student Payment System");
//        primaryStage.setResizable(false);
//        primaryStage.show();
//        primaryStage.centerOnScreen();
    }


}
