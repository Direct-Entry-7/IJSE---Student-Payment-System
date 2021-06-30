package model;

import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Payment implements Serializable {
    private String refNo;
    private String courseCode;
    private String description;
    private String paymentMethod;
    private LocalDate date;
    private File file;
    private BigDecimal amount;
    private String note;
    private Student student;

    public Payment() {
    }

    public Payment(String refNo, String courseCode, String description, String paymentMethod, LocalDate date, File file, BigDecimal amount, String note, Student student) {
        this.refNo = refNo;
        this.courseCode = courseCode;
        this.description = description;
        this.paymentMethod = paymentMethod;
        this.date = date;
        this.file = file;
        this.amount = amount;
        this.note = note;
        this.student = student;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
