package xmu.crms.view.vo;

import xmu.crms.entity.Course;

import java.text.SimpleDateFormat;

/**
 * @author LUWEIW
 */

public class UserCourseVO {

    private Integer id;
    private String name;
    private Integer numClass;
    private Integer numStudent;
    private String startTime;
    private String endTime;

    public UserCourseVO(Course course, int numClass, int numStudent) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.id = course.getId().intValue();
        this.name = course.getName();
        this.numClass = numClass;
        this.numStudent = numStudent;
        this.startTime = simpleDateFormat.format(course.getStartDate());
        this.endTime = simpleDateFormat.format(course.getEndDate());
    }

    public UserCourseVO(int id, String name, int numClass, int numStudent, String startTime, String endTime) {
        this.id = id;
        this.name = name;
        this.numClass = numClass;
        this.numStudent = numStudent;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumClass() {
        return numClass;
    }

    public void setNumClass(int numClass) {
        this.numClass = numClass;
    }

    public int getNumStudent() {
        return numStudent;
    }

    public void setNumStudent(int numStudent) {
        this.numStudent = numStudent;
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

    @Override
    public String toString() {
        return "UserCourseVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numClass=" + numClass +
                ", numStudent=" + numStudent +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
