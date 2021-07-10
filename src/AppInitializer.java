import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

        try {
            spinUpRediServerInstance();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Unable to load data, Something went wrong").show();
            return;
        }

        Parent mainForm = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        Scene mainScene = new Scene(mainForm);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Student Payment System");
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    private void spinUpRediServerInstance() throws Exception{
        String[] commands = {"redis-server","redis.conf"};
        Process redisServer = Runtime.getRuntime().exec(commands);
        int exitCode = redisServer.waitFor();

        if(exitCode != 0){
            throw new Exception("Failed to spin up the redis server instance");
        }

    }
}
