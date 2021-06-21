package model;

import java.io.Serializable;

public class Course implements Serializable {
    private String courseCode;
    private String name;
    private Double courseFee;
    private String duration;
    private String description;

    public Course() {

    }

    public Course(String courseCode, String name, Double courseFee, String duration, String description) {
        this.courseCode = courseCode;
        this.name = name;
        this.courseFee = courseFee;
        this.duration = duration;
        this.description = description;
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

    @Override
    public String toString() {
        return "Course{" +
                "courseCode='" + courseCode + '\'' +
                ", name='" + name + '\'' +
                ", courseFee=" + courseFee +
                ", duration='" + duration + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
