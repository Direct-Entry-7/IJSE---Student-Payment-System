package model;

import javafx.scene.control.Button;
import java.io.Serializable;

public class UserTM implements Serializable {
    private String userType;
    private String name;
    private String email;
    private String mobileNumber;
    private String password;
    private Button btn;

    public UserTM() {
    }

    public UserTM(String userType, String name, String email, String mobileNumber, String password, Button btn) {
        this.userType = userType;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.btn = btn;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
