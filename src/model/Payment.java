package model;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Payment implements Serializable {
    private String refNo;
    private String courseCode;
    private String batchNo;
    private String studentNIC;
    private String description;
    private String paymentMethod;
    private LocalDate date;
    private File file;
    private BigDecimal amount;
    private String note;

    public Payment() {
    }

    public Payment(String refNo, String courseCode, String batchNo, String studentNIC, String description, String paymentMethod, LocalDate date, File file, BigDecimal amount, String note) {
        this.refNo = refNo;
        this.courseCode = courseCode;
        this.batchNo = batchNo;
        this.studentNIC = studentNIC;
        this.description = description;
        this.paymentMethod = paymentMethod;
        this.date = date;
        this.file = file;
        this.amount = amount;
        this.note = note;
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

    public void setBatchNp(String batchNo) {
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
