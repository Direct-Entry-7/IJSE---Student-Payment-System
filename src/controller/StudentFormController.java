package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Course;
import model.CourseTM;
import model.Student;
import model.StudentTM;
import service.CourseService;
import service.StudentService;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;

import java.time.LocalDate;
import java.util.Optional;
import java.util.zip.DataFormatException;

public class StudentFormController {
    public JFXTextField txtName;
    public JFXTextField txtEmail;
    public JFXTextField txtMobileNumber;
    public JFXTextField txtNIC;
    public JFXDatePicker txtDOB;
    public JFXTextField txtAddress;
    public TableView<StudentTM> tblStudents;
    public JFXButton btnSave;
    public JFXButton btnAddNewStudent;
    public JFXRadioButton rdoMale;
    public JFXRadioButton rdoFemale;
    public ToggleGroup gender;

    private StudentService studentService = new StudentService();
    private ObservableList<StudentTM> studentsList = FXCollections.observableArrayList();
    public void initialize(){

        tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudents.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("email"));
        tblStudents.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        tblStudents.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudents.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        btnAddNewStudent.setVisible(false);
        loadStudents();

        tblStudents.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        setData(newValue);
                    }

                });
    }

    private void setData(StudentTM value) {
        btnAddNewStudent.setVisible(true);
        txtNIC.setText(value.getNic());
        txtNIC.setDisable(true);
        txtName.setText(value.getName());
        txtEmail.setText(String.valueOf(value.getEmail()));
        txtMobileNumber.setText(value.getMobileNumber());
        gender.selectToggle(value.getGender().equals("Male") ? rdoMale:rdoFemale);
        System.out.println("Gender : " + value.getGender());
        System.out.println(value.getGender().equals("Male"));
        txtDOB.setValue(value.getDob());
        txtAddress.setText(value.getAddress());
        btnSave.setText("Update");

    }

    private void loadStudents() {
        studentsList.clear();

        for (Student student: studentService.getAllStudents()) {
//            Button btnDelete = new Button("Delete");
            JFXButton btnDelete = new JFXButton("Delete");
            btnDelete.getStyleClass().add("delete-button");

            StudentTM studentTM = new StudentTM(student.getNic(), student.getName(), student.getEmail(), student.getMobileNumber(), student.getDob(), student.getGender(), student.getAddress(), btnDelete);
            studentsList.add(studentTM);

            btnDelete.setOnAction(event -> {
                Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to delete this student?", ButtonType.YES, ButtonType.NO).showAndWait();
                if (buttonType.get() == ButtonType.YES) {
                    System.out.println("Called");
                    try {
                        studentService.deleteStudent(student.getNic());
                        tblStudents.getItems().remove(studentTM);
                    } catch (NotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        tblStudents.setItems(studentsList);
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        String nic = txtNIC.getText();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String mobileNumber = txtMobileNumber.getText();
//        LocalDate.parse(txtDOB.getValue())
//        System.out.println();
        LocalDate date = LocalDate.parse(txtDOB.getValue().toString());
        String selectedGender = gender.getSelectedToggle() == rdoMale ? "Male" : "Female";
        String address = txtAddress.getText();

        Student student = new Student(nic, name, email, mobileNumber,date, selectedGender, address);

        if(btnSave.getText().equals("Save")){
            try {
                studentService.saveStudent(student);
                new Alert(Alert.AlertType.CONFIRMATION,"Student Added Successfully", ButtonType.OK).show();
                btnAddNewStudent_OnAction(new ActionEvent());
            } catch (DuplicateEntryException e) {
                e.printStackTrace();
            }
        } else{
            try {
                studentService.updateStudent(student);
                new Alert(Alert.AlertType.CONFIRMATION,"Student Updated Successfully", ButtonType.OK).show();
                btnAddNewStudent_OnAction(new ActionEvent());
            } catch (NotFoundException e) {
                e.printStackTrace();
            }
        }




        loadStudents();
    }

    public void btnAddNewStudent_OnAction(ActionEvent actionEvent) {
        txtNIC.setDisable(false);
        txtNIC.clear();
        txtName.clear();
        txtEmail.clear();
        txtMobileNumber.clear();
        txtDOB.setValue(null);
        txtAddress.clear();
        btnSave.setText("Save");
    }
}
