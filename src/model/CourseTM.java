package model;

import javafx.scene.control.Button;

public class CourseTM {
    private String courseCode;
    private String name;
    private Double courseFee;
    private String duration;
    private String description;
    private Button btn;

    public CourseTM() {
    }

    public CourseTM(String courseCode, String name, Double courseFee, String duration, String description, Button btn) {
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

    public Double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(Double courseFee) {
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
