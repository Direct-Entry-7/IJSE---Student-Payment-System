package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Batch;
import model.BatchDetailTM;
import model.BatchTM;
import model.Student;
import service.BatchService;
import service.StudentService;
import service.exception.NotFoundException;

import java.util.*;


public class BatchDetailFormController {

    public TableView<BatchDetailTM> tblStudents;
    public ScrollPane pneBatchDetails;
    public Label lblBatchNo;
    public Label lblCommenceDate;
    public Label lblCompletedDate;
    public Label lblCourseFee;
    public Label lblNoOfStudents;
    public Label lblName;
    public Label lblNIC;
    public Label lblMobileNo;
    public Label lblEmail;
    public JFXTextField txtSearchByNIC;
    public JFXButton btnAdd;
    public JFXButton btnSearch;
    private Batch batch;
    private Student student;
    private String courseCode;
    private BatchService batchService = new BatchService();
    ;
    private StudentService studentService = new StudentService();

    private List<Student> batchStudents;
    private ObservableList<BatchDetailTM> studentsTableList = FXCollections.observableArrayList();

    public void initialize() {
        Platform.runLater(() -> {
            tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nic"));
            tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("studentName"));
            tblStudents.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("email"));
            tblStudents.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
            tblStudents.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("address"));
            tblStudents.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

            loadBatch((BatchTM) pneBatchDetails.getUserData());
            txtSearchByNIC.setText("951293822v");

            if(student ==null){
                btnAdd.setDisable(true);
            }

        });
    }

    private void loadBatch(BatchTM batchTM) {
        try {
            courseCode = batchTM.getCourseCode();
            batch = batchService.findBatch(batchTM.getBatchNo());
            List<Student> students = batch.getStudents();
            if (students != null) {
                batchStudents = students;
            } else {
                batchStudents = new ArrayList<>();
            }

            lblBatchNo.setText(batch.getBatchNo());
            lblCommenceDate.setText(batch.getCommenceDate().toString());
            lblCompletedDate.setText(batch.getCompletedDate().toString());
            lblCourseFee.setText(batch.getCourseFee().toString());
            lblNoOfStudents.setText(String.valueOf(batchStudents.size()));

            loadStudentsTable();
        } catch (NotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Batch not found.Try again", ButtonType.OK).show();
        }
    }

    private void loadStudentsTable() {
        if (batchStudents.size() == 0)
            return;
        studentsTableList.clear();
        for (Student student : batchStudents) {
            JFXButton btnDelete = new JFXButton("Delete");
            btnDelete.getStyleClass().add("delete-button");

            BatchDetailTM batchDetailTM = new BatchDetailTM(student.getNic(), student.getName(), student.getEmail(), student.getMobileNumber(), student.getAddress(), btnDelete);

            studentsTableList.add(batchDetailTM);

            btnDelete.setOnAction(event -> {
                Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete this student?", ButtonType.YES, ButtonType.NO).showAndWait();
                if (buttonType.get() == ButtonType.YES) {
                    tblStudents.getItems().remove(batchDetailTM);
                    batchStudents.removeIf(e -> e.getNic().equals(student.getNic()));
                    lblNoOfStudents.setText(String.valueOf(batchStudents.size()));
                    Platform.runLater(() -> {
                        try {
                            batch.setStudents(batchStudents);
                            batchService.updateBatch(batch);
                            Map<String, String> courseWithBatch = student.getCourseWithBatch();
                            courseWithBatch.remove(courseCode);
                            student.setCourseWithBatch(courseWithBatch);
                            studentService.updateStudent(student);
                        } catch (NotFoundException e) {
                            new Alert(Alert.AlertType.ERROR, "Student deletion failed.", ButtonType.OK).show();
                        }
                    });
                }
            });

        }
        tblStudents.setItems(studentsTableList);

    }


    public void btnSearch_OnAction(ActionEvent actionEvent) {
        String nic = txtSearchByNIC.getText();
        try {
            student = studentService.findStudent(nic);
            lblName.setText(student.getName());
            lblNIC.setText(student.getNic());
            lblMobileNo.setText(student.getMobileNumber());
            lblEmail.setText(student.getEmail());

            btnAdd.setDisable(false);

        } catch (NotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Not found student with with this NIC. Try again", ButtonType.OK).show();
            student = null;
            return;
        }


    }

    public void btnAdd_OnAction(ActionEvent actionEvent) {
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to Add this student to this batch?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            JFXButton btnDelete = new JFXButton("Delete");
            btnDelete.getStyleClass().add("delete-button");
            try {
                for (Student student : batchStudents) {
                    if(student.getNic().equals(this.student.getNic())){
                        new Alert(Alert.AlertType.ERROR, "Student is already in the batch.", ButtonType.OK).show();
                        clearFields();
                        return;
                    }
                }

                Map<String, String> courseWithBatch = student.getCourseWithBatch();
                if (courseWithBatch == null) {
                    courseWithBatch = new HashMap<>();
                }

                if(courseWithBatch.keySet().contains(courseCode)){
                    new Alert(Alert.AlertType.ERROR, "Student is already in the course in a different batch.", ButtonType.OK).show();
                    clearFields();
                    return;
                }

                batchStudents.add(student);
                lblNoOfStudents.setText(String.valueOf(batchStudents.size()));
                courseWithBatch.put(batch.getCourseCode(), batch.getBatchNo());
                student.setCourseWithBatch(courseWithBatch);
                batch.setStudents(batchStudents);
                studentService.updateStudent(student);
                batchService.updateBatch(batch);

                clearFields();

                Platform.runLater(() -> {
                    loadStudentsTable();
                });
                new Alert(Alert.AlertType.INFORMATION, "Student Added successfully.", ButtonType.OK).show();
            } catch (NotFoundException e) {
                new Alert(Alert.AlertType.ERROR, "Student Add failed. Try again", ButtonType.OK).show();
            }


        }
    }

    private void clearFields() {
        student = null;
        txtSearchByNIC.setText("");
        lblName.setText("");
        lblNIC.setText("");
        lblMobileNo.setText("");
        lblEmail.setText("");
    }
}
