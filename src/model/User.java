package model;

import java.io.Serializable;

public class User implements Serializable {
    private String userType;
    private String name;
    private String email;
    private String mobileNumber;
    private String password;

    public User() {

    }

    public User(String userType, String name, String email, String mobileNumber, String password) {
        this.userType = userType;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.password = password;
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
}
