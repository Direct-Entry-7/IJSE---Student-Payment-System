package model;

import javafx.scene.control.Button;

public class BatchDetailTM {
    private String nic;
    private String studentName;
    private String email;
    private String mobileNumber;
    private String address;
    private Button btnDelete;

    public BatchDetailTM() {
    }

    public BatchDetailTM(String nic, String studentName, String email, String mobileNumber, String address, Button btnDelete) {
        this.setNic(nic);
        this.studentName = studentName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.btnDelete = btnDelete;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }
}
