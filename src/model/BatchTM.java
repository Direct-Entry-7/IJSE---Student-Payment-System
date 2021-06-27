package model;

import javafx.scene.control.Button;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BatchTM implements Serializable {
    private String courseCode;
    private String batchNo;
    private LocalDate commenceDate;
    private LocalDate completedDate;
    private String description;
    private BigDecimal courseFee;
    private Button viewBatchDetails;
    private Button delete;

    public BatchTM() {
    }

    public BatchTM(String courseCode, String batchNo, LocalDate commenceDate, LocalDate completedDate, String description, BigDecimal courseFee, Button viewBatchDetails, Button delete) {
        this.courseCode = courseCode;
        this.batchNo = batchNo;
        this.commenceDate = commenceDate;
        this.completedDate = completedDate;
        this.description = description;
        this.courseFee = courseFee;
        this.viewBatchDetails = viewBatchDetails;
        this.delete = delete;
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

    public LocalDate getCommenceDate() {
        return commenceDate;
    }

    public void setCommenceDate(LocalDate commenceDate) {
        this.commenceDate = commenceDate;
    }

    public LocalDate getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(LocalDate completedDate) {
        this.completedDate = completedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Button getViewBatchDetails() {
        return viewBatchDetails;
    }

    public void setViewBatchDetails(Button viewBatchDetails) {
        this.viewBatchDetails = viewBatchDetails;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    public BigDecimal getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(BigDecimal courseFee) {
        this.courseFee = courseFee;
    }
}
