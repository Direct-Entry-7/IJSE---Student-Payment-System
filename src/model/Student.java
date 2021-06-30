package model;

import com.sun.xml.internal.ws.server.sei.SEIInvokerTube;
import javafx.scene.control.Button;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Student implements Serializable {
    private String nic;
    private String name;
    private String email;
    private String mobileNumber;
    private LocalDate dob;
    private String gender;
    private String address;
    private Map<String,String> courseWithBatch = new HashMap<>();

    public Student() {

    }

    public Student(String nic, String name, String email, String mobileNumber, LocalDate dob, String gender, String address) {
        this.nic = nic;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
    }

    public Student(String nic, String name, String email, String mobileNumber, LocalDate dob, String gender, String address, Map<String, String> courseWithBatch) {
        this.nic = nic;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.courseWithBatch = courseWithBatch;
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


    public Map<String, String> getCourseWithBatch() {
        return courseWithBatch;
    }

    public void setCourseWithBatch(Map<String, String> courseWithBatch) {
        this.courseWithBatch = courseWithBatch;
    }

    @Override
    public String toString() {
        return "Student{" +
                "nic='" + nic + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", courseWithBatch=" + courseWithBatch +
                '}';
    }
}
