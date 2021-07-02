package model;

import javafx.scene.control.Button;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentDetailsTM implements Serializable {
    private String refNo;
    private String courseCode;
    private String batchNo;
    private String studentNIC;
    private String description;
    private String paymentMethod;
    private LocalDate date;
    private File file;
    private BigDecimal payment;
    private String note;
    private Button del0ete;

    public PaymentDetailsTM() {
    }

    public PaymentDetailsTM(String refNo, String courseCode, String batchNo, String studentNIC, String description, String paymentMethod, LocalDate date, File file, BigDecimal payment, String note, Button del0ete) {
        this.refNo = refNo;
        this.courseCode = courseCode;
        this.batchNo = batchNo;
        this.studentNIC = studentNIC;
        this.description = description;
        this.paymentMethod = paymentMethod;
        this.date = date;
        this.file = file;
        this.payment = payment;
        this.note = note;
        this.del0ete = del0ete;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getStudentNIC() {
        return studentNIC;
    }

    public void setStudentNIC(String studentNIC) {
        this.studentNIC = studentNIC;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Button getDel0ete() {
        return del0ete;
    }

    public void setDel0ete(Button del0ete) {
        this.del0ete = del0ete;
    }
}
