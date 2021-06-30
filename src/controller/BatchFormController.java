package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Batch;
import model.BatchTM;
import model.Course;
import service.BatchService;
import service.CourseService;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class BatchFormController {
    public TableView<BatchTM> tblBatches;
    public JFXComboBox cmbCourse;
    public JFXTextField txtBatchNo;
    public JFXDatePicker dtCommenceDate;
    public JFXDatePicker dtCompletedDate;
    public JFXTextField txtDescription;
    public JFXComboBox cmbTableCourse;
    public JFXButton btnSave;
    public JFXComboBox cmbBatch;
    public JFXButton btnAddNewBatch;
    public JFXTextField txtCourseFee;

    private BatchService batchService = new BatchService();
    private CourseService courseService = new CourseService();
    private ObservableList<BatchTM> batchesList = FXCollections.observableArrayList();

    public void initialize() {
        tblBatches.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        tblBatches.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("batchNo"));
        tblBatches.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("commenceDate"));
        tblBatches.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("completedDate"));
        tblBatches.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("courseFee"));
        tblBatches.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("viewBatchDetails"));
        tblBatches.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("delete"));

        btnAddNewBatch.setVisible(false);
        loadCourses();
        loadBatches();

        tblBatches.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        setData(newValue);
                    }

                });

        cmbTableCourse.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                tblBatches.setItems(batchesList);
            } else {
                ObservableList<BatchTM> filtered = batchesList.filtered(batch -> batch.getCourseCode().equals(newValue));
                tblBatches.setItems(filtered);
            }

        });

    }

    private void loadCourses() {
        ObservableList<String> courseList = cmbCourse.getItems();
        ObservableList<String> courseTableList = cmbTableCourse.getItems();
        for (Course course : courseService.getAllCourses()) {
            courseList.add(course.getCourseCode());
            cmbCourse.getSelectionModel().select(-1);
            courseTableList.add(course.getCourseCode());
            cmbTableCourse.getSelectionModel().select(-1);
        }
    }

    private void setData(BatchTM value) {
        btnAddNewBatch.setVisible(true);
        cmbCourse.getSelectionModel().select(value.getCourseCode());
        cmbCourse.setDisable(true);
        txtBatchNo.setText(value.getBatchNo());
        txtBatchNo.setDisable(true);
        dtCommenceDate.setValue(value.getCommenceDate());
        dtCompletedDate.setValue(value.getCompletedDate());
        txtCourseFee.setText(value.getCourseFee().toString());
        txtDescription.setText(value.getDescription());
        btnSave.setText("Update");
    }

    private void loadBatches() {
        batchesList.clear();

        for (Batch batch : batchService.getAllBatches()) {
            JFXButton btnDelete = new JFXButton("Delete");
            btnDelete.getStyleClass().add("delete-button");

            JFXButton btnViewBatchDetails = new JFXButton("View Details");
            btnViewBatchDetails.getStyleClass().add("batch-details-button");

//
            BatchTM batchTM = new BatchTM(batch.getCourseCode(), batch.getBatchNo(), batch.getCommenceDate(), batch.getCompletedDate(), batch.getDescription(), batch.getCourseFee(), btnViewBatchDetails, btnDelete);
            batchesList.add(batchTM);

            btnDelete.setOnAction(event -> {
                Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete this batch?", ButtonType.YES, ButtonType.NO).showAndWait();
                if (buttonType.get() == ButtonType.YES) {
                    System.out.println("Called");
                    try {
                        batchService.deleteBatch(batch.getBatchNo());
                        tblBatches.getItems().remove(batchTM);
                    } catch (NotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });


            btnViewBatchDetails.setOnAction(event -> {
                System.out.println("Button selected");

                try {
                    Parent p = FXMLLoader.load(getClass().getResource("/view/BatchDetailForm.fxml"));
                    AnchorPane pneMainContext = (AnchorPane) tblBatches.getParent().getParent();
                    pneMainContext.getChildren().clear();

                    p.setUserData(batchTM);
                    pneMainContext.getChildren().add(p);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


        }
        tblBatches.setItems(batchesList);
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        String courseCode = cmbCourse.getSelectionModel().getSelectedItem().toString();
        String batchNo = txtBatchNo.getText();
        LocalDate commenceDate = LocalDate.parse(dtCommenceDate.getValue().toString());
        LocalDate completedDate = LocalDate.parse(dtCompletedDate.getValue().toString());
        BigDecimal courseFee = BigDecimal.valueOf(Integer.valueOf(txtCourseFee.getText()));
        String description = txtDescription.getText();

        Batch batch = new Batch(courseCode, batchNo, commenceDate, completedDate, description, courseFee,null);

        if (btnSave.getText().equals("Save")) {
            try {
                batchService.saveBatch(batch);
                new Alert(Alert.AlertType.CONFIRMATION, "Batch Added Successfully", ButtonType.OK).show();
                btnAddNewBatch_OnAction(new ActionEvent());
            } catch (DuplicateEntryException e) {
                e.printStackTrace();
            }
        } else {
            try {
                batchService.updateBatch(batch);
                new Alert(Alert.AlertType.CONFIRMATION, "Student Updated Successfully", ButtonType.OK).show();
                btnAddNewBatch_OnAction(new ActionEvent());
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }
        loadBatches();
    }


    public void btnAddNewBatch_OnAction(ActionEvent actionEvent) {
        cmbCourse.getSelectionModel().select(-1);
        cmbTableCourse.getSelectionModel().select(-1);
        txtBatchNo.setDisable(false);
        cmbCourse.setDisable(false);
        txtBatchNo.clear();
        dtCommenceDate.setValue(null);
        dtCompletedDate.setValue(null);
        txtCourseFee.clear();
        txtDescription.clear();
        btnSave.setText("Save");
    }
}
