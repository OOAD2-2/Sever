package xmu.crms.view.vo;

import java.math.BigInteger;

import xmu.crms.entity.ClassInfo;

/**
 * @author LUWEIW
 */

public class CourseClassVO {

    private Integer id;
    private String name;
    private Integer numStudent;
    private String time;
    private String site;
    private String courseName;
    private String courseTeacher;
    private BigInteger courseId;

    public CourseClassVO() {
    }

    public CourseClassVO(ClassInfo classInfo, int numStudent) {
        this.id = classInfo.getId().intValue();
        this.name = classInfo.getName();
        this.numStudent = numStudent;
        this.time = classInfo.getClassTime();
        this.site = classInfo.getSite();
        this.courseName = classInfo.getCourse().getName();
        this.courseId=classInfo.getCourse().getId();
        this.courseTeacher = classInfo.getCourse().getTeacher().getName();
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

    public int getNumStudent() {
        return numStudent;
    }

    public void setNumStudent(int numStudent) {
        this.numStudent = numStudent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "CourseClassVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numStudent=" + numStudent +
                ", time='" + time + '\'' +
                ", site='" + site + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseTeacher='" + courseTeacher + '\'' +
                ", courseId=" + courseId +
                '}';
    }
}