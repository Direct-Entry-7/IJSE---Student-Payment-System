package controller;

import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import model.BatchTM;

public class TestFormController {
    public AnchorPane pneTest;

    public void initialize(){
        Platform.runLater(()->{
            System.out.println("Loaded");
            System.out.println((BatchTM)pneTest.getUserData());
        });
    }
}
