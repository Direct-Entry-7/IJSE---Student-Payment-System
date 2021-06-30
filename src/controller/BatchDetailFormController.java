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
    private Batch batch;
    private Student student;
    private BatchService batchService = new BatchService();
    ;
    private StudentService studentService = new StudentService();

    private List<Student> batchStudents = new ArrayList<>();
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
            txtSearchByNIC.setText("9512938224v");

        });
    }

    private void loadBatch(BatchTM batchTM) {
        try {
            batch = batchService.findBatch(batchTM.getBatchNo());
            if (!(batch.getStudents() == null)) {
                for (Student student : batch.getStudents()) {
                    batchStudents.add(student);
                }
            }

            lblBatchNo.setText(batch.getBatchNo());
            lblCommenceDate.setText(batch.getCommenceDate().toString());
            lblCompletedDate.setText(batch.getCompletedDate().toString());
            lblCourseFee.setText(batch.getCourseFee().toString());
            lblNoOfStudents.setText(String.valueOf(batchStudents.size()));

            loadStudentsTable();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadStudentsTable() {
        if (batch.getStudents() == null)
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

                            Batch updatedBatch = new Batch(batch.getCourseCode(), batch.getBatchNo(), batch.getCommenceDate(), batch.getCompletedDate(), batch.getDescription(), batch.getCourseFee(), batchStudents);
                            batchService.updateBatch(updatedBatch);
                        } catch (NotFoundException e) {
                            e.printStackTrace();
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

        } catch (NotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Not found student with with this NIC. Try again", ButtonType.OK).show();
        }


    }

    public void btnAdd_OnAction(ActionEvent actionEvent) {
        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to Add this student to this batch?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            JFXButton btnDelete = new JFXButton("Delete");
            btnDelete.getStyleClass().add("delete-button");
            batchStudents.add(student);
            lblNoOfStudents.setText(String.valueOf(batchStudents.size()));
            try {

                Map<String, String> courseWithBatch = student.getCourseWithBatch();
                courseWithBatch.put(batch.getCourseCode(),batch.getBatchNo());

                Student updatedStudent = new Student(this.student.getNic(), this.student.getName(), this.student.getEmail(), this.student.getMobileNumber(), this.student.getDob(), this.student.getGender(), this.student.getAddress(), courseWithBatch);
                studentService.updateStudent(updatedStudent);
                Batch updatedBatch = new Batch(batch.getCourseCode(), batch.getBatchNo(), batch.getCommenceDate(), batch.getCompletedDate(), batch.getDescription(), batch.getCourseFee(), batchStudents);
                batchService.updateBatch(updatedBatch);
                Platform.runLater(() -> {
                    loadStudentsTable();
                });
                System.out.println(student.getCourseWithBatch().get("001"));
            } catch (NotFoundException e) {
                e.printStackTrace();
            }


        }
    }
}
