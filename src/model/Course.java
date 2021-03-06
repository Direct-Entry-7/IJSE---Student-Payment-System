package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Course implements Serializable {
    private String courseCode;
    private String name;
    private BigDecimal courseFee;
    private String duration;
    private String description;

    public Course() {

    }

    public Course(String courseCode, String name, BigDecimal courseFee, String duration, String description) {
        this.courseCode = courseCode;
        this.name = name;
        this.courseFee = courseFee;
        this.duration = duration;
        this.description = description;
    }

    public static Course fromMap(String courseCode, Map<String, String> data) {
        return new Course(courseCode, data.get("name"), BigDecimal.valueOf(Double.valueOf(data.get("courseFee"))), data.get("duration"), data.get("description"));
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

    public Map<String, String> toMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("courseFee", courseFee + "");
        map.put("duration", duration);
        map.put("description", description);

        return map;
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
