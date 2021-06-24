package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable {
    private String name;
    private String email;
    private String mobileNumber;
    private String nic;
    private LocalDate dob;
    private String gender;
    private String address;

    public Student() {
    }

    public Student(String name, String email, String mobileNumber, String nic, LocalDate dob, String gender, String address) {
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.nic = nic;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
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
}
