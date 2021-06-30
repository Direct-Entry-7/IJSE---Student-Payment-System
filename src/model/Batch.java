package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Batch implements Serializable {
    private String courseCode;
    private String batchNo;
    private LocalDate commenceDate;
    private LocalDate completedDate;
    private String description;
    private BigDecimal courseFee;
    private List<Student> students = new ArrayList<>();


    public Batch(String courseCode, String batchNo, LocalDate commenceDate, LocalDate completedDate, String description, BigDecimal courseFee, List<Student> students) {
        this.courseCode = courseCode;
        this.batchNo = batchNo;
        this.commenceDate = commenceDate;
        this.completedDate = completedDate;
        this.description = description;
        this.courseFee = courseFee;
        this.setStudents(students);
    }

    public Batch() {

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

    public BigDecimal getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(BigDecimal courseFee) {
        this.courseFee = courseFee;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Batch{" +
                "courseCode='" + courseCode + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", commenceDate=" + commenceDate +
                ", completedDate=" + completedDate +
                ", description='" + description + '\'' +
                ", courseFee=" + courseFee +
                ", students=" + students +
                '}';
    }
}
