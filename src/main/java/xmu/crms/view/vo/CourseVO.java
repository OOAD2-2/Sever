package xmu.crms.view.vo;

import xmu.crms.entity.Course;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CourseVO {
    private String name;
    private String description;
    private String startTime;
    private String endTime;
    private Proportions proportions;

    public CourseVO(String name, String description, String startTime, String endTime, Proportions proportions) {
        this.name = name;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.proportions = proportions;
    }

    public CourseVO(Course course) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.name = course.getName();
        this.description = course.getDescription();
        this.startTime = simpleDateFormat.format(course.getStartDate());
        this.endTime = simpleDateFormat.format(course.getEndDate());
        this.proportions = new Proportions(course.getReportPercentage(), course.getPresentationPercentage(),
                course.getThreePointPercentage(), course.getFourPointPercentage(), course.getFivePointPercentage());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Proportions getProportions() {
        return proportions;
    }

    public void setProportions(Proportions proportions) {
        this.proportions = proportions;
    }

    @Override
    public String toString() {
        return "CourseVO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", proportions=" + proportions +
                '}';
    }
}
