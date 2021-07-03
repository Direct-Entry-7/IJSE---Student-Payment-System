package model;

import javafx.scene.control.Button;

import java.io.Serializable;
import java.math.BigDecimal;

public class CourseTM implements Serializable {
    private String courseCode;
    private String name;
    private BigDecimal courseFee;
    private String duration;
    private String description;
    private Button btn;

    public CourseTM() {
    }

    public CourseTM(String courseCode, String name, BigDecimal courseFee, String duration, String description, Button btn) {
        this.courseCode = courseCode;
        this.name = name;
        this.courseFee = courseFee;
        this.duration = duration;
        this.description = description;
        this.btn = btn;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(BigDecimal courseFee) {
        this.courseFee = courseFee;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
