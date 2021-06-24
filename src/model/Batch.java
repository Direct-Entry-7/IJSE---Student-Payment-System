package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Batch implements Serializable {
    private String courseCode;
    private String batchNo;
    private LocalDate commenceDate;
    private LocalDate completedDate;
    private String description;

    public Batch() {

    }

    public Batch(String courseCode, String batchNo, LocalDate commenceDate, LocalDate completedDate, String description) {
        this.courseCode = courseCode;
        this.batchNo = batchNo;
        this.commenceDate = commenceDate;
        this.completedDate = completedDate;
        this.description = description;
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
}
