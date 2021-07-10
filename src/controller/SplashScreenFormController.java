package controller;

import com.jfoenix.controls.JFXProgressBar;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SplashScreenFormController {
    public JFXProgressBar pgb;

    public void initialize() {

        pgb.setProgress(0);

        Timeline tl = new Timeline(new KeyFrame(Duration.millis(50), event -> {
            if (pgb.getProgress() <= 1) {
                pgb.setProgress(pgb.getProgress() + 0.01);
            }
        }));

        tl.setRate(3);
        tl.setCycleCount(Animation.INDEFINITE);
        tl.playFromStart();

        pgb.progressProperty().addListener((observable, oldValue, newValue) -> {
            try {

                if (newValue.intValue() == 1) {
                    tl.stop();
                    initializeUI();
                } else if (newValue.doubleValue() >= 0.95) {
                    spinUpRedisServerInstance();
                }

            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, "Failed to load the system, Please try again").showAndWait();
                System.exit(1);
            }
        });
    }

    private void initializeUI() throws IOException {
        Stage stage = (Stage) pgb.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent mainForm = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        Scene mainScene = new Scene(mainForm);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Student Payment System");
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }

    private void spinUpRedisServerInstance() throws Exception {
        String[] commands = {"redis-server", "redis.conf"};
        Process redisServer = Runtime.getRuntime().exec(commands);
        int exitCode = redisServer.waitFor();

        if (exitCode != 0) {
            throw new Exception("Failed to spin up the redis server instance");
        }

    }
}
