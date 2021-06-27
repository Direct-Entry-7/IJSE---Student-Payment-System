package model;

import javafx.scene.control.Button;

import java.io.Serializable;
import java.time.LocalDate;

public class StudentTM implements Serializable {
    private String nic;
    private String name;
    private String email;
    private String mobileNumber;
    private LocalDate dob;
    private String gender;
    private String address;
    private Button btnDelete;

    public StudentTM() {

    }

    public StudentTM(String nic, String name, String email, String mobileNumber, LocalDate dob, String gender, String address, Button btnDelete) {
        this.nic = nic;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.btnDelete = btnDelete;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }
}
