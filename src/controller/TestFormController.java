package controller;

import javafx.scene.layout.AnchorPane;
import model.BatchTM;

public class TestFormController {
    public AnchorPane pneTest;

    public void initialize(){
        System.out.println("Loaded");
        System.out.println((BatchTM)pneTest.getUserData());
    }
}
