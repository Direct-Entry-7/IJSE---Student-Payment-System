package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Student;
import model.User;
import model.UserTM;
import service.UserService;
import service.exception.DuplicateEntryException;

import java.util.List;
import java.util.Optional;


public class UserFormController {

    public TableView<UserTM> tblUsers;
    public JFXButton btnSave;
    public JFXComboBox cmbUserType;
    public JFXTextField txtName;
    public JFXTextField txtEmail;
    public JFXTextField txtMobileNumber;
    public JFXPasswordField txtPassword;
    public JFXPasswordField txtConfirmPassword;

    private List<User> allUsers;
    private UserService userService = new UserService();
    private ObservableList<UserTM> userList = FXCollections.observableArrayList();

    public void initialize() {
        tblUsers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userType"));
        tblUsers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblUsers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("email"));
        tblUsers.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        tblUsers.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("btn"));

        ObservableList<String> paymentMethods = cmbUserType.getItems();
        paymentMethods.add("Admin");
        paymentMethods.add("User");

        loadData();
    }

    private void loadData() {
        userList.clear();
        allUsers = userService.getAllUsers();
        for (User user : allUsers) {
            JFXButton btnDelete = new JFXButton("Delete");
            btnDelete.getStyleClass().add("delete-button");

            UserTM userTM = new UserTM(user.getUserType(), user.getName(), user.getEmail(), user.getMobileNumber(), user.getPassword(), btnDelete);
            userList.add(userTM);
        }
        tblUsers.setItems(userList);

    }


    public void btnSave_OnAction(ActionEvent actionEvent) {
        if (!isValidated()) {
            return;
        }
        String userType = cmbUserType.getSelectionModel().getSelectedItem().toString();
        String name = txtName.getText();
        String email = txtEmail.getText();
        String mobileNumber = txtMobileNumber.getText();
        String password = txtPassword.getText();

        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure want to add this user?", ButtonType.YES, ButtonType.NO).showAndWait();
        if (buttonType.get() == ButtonType.YES) {
            User user = new User(userType, name, email, mobileNumber, password);
            try {
                userService.saveUser(user);
                new Alert(Alert.AlertType.INFORMATION, "User added successfully").show();
                loadData();
            } catch (DuplicateEntryException e) {
                new Alert(Alert.AlertType.ERROR, "User is already in the system.").show();
                return;
            }
        }
    }

    private boolean isValidated() {
        try {
            cmbUserType.getSelectionModel().getSelectedItem().toString();
        } catch (NullPointerException e) {
            new Alert(Alert.AlertType.ERROR, "User Type should be selected").show();
            cmbUserType.requestFocus();
            return false;
        }
        if(!(txtName.getText().matches("[A-Za-z]{3,}"))){
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtName.requestFocus();
            return false;
        }else if(!(txtEmail.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))){
            new Alert(Alert.AlertType.ERROR, "Enter valid email address").show();
            txtEmail.requestFocus();
            return false;
        }else if(!(txtMobileNumber.getText().matches("\\d{10}"))){
            new Alert(Alert.AlertType.ERROR, "Enter valid Mobile Number").show();
            txtMobileNumber.requestFocus();
            return false;
        }else if(!(txtPassword.getText().matches(".{4,}"))){
            new Alert(Alert.AlertType.ERROR, "Enter valid password. Password should be at least 5 characters").show();
            txtPassword.setText("");
            txtConfirmPassword.setText("");
            txtPassword.requestFocus();
            return false;
        }else if(!(txtPassword.getText().equals(txtConfirmPassword.getText()))){
            new Alert(Alert.AlertType.ERROR, "Password did not match").show();
            txtPassword.setText("");
            txtConfirmPassword.setText("");
            txtPassword.requestFocus();
            return false;
        }
        return true;
    }



}
