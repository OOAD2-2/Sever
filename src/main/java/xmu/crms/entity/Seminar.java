package xmu.crms.entity;

import xmu.crms.view.vo.SeminarCreateVO;
import xmu.crms.view.vo.SeminarUpdateVO;
import xmu.crms.view.vo.SeminarVO;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author LUWEIW
 */

public class Seminar {
    private BigInteger id;
    private String name;
    private String description;
    private Course course;
    private Boolean fixed;
    private Date startTime;
    private Date endTime;

    public Seminar(SeminarVO seminarVO) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.id = null;
        this.name = seminarVO.getName();
        this.description = seminarVO.getDescription();
        this.course = null;
        this.fixed = "fixed".equals(seminarVO.getGroupingMethod());
        try {
            this.startTime = simpleDateFormat.parse(seminarVO.getStartTime());
            this.endTime = simpleDateFormat.parse(seminarVO.getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Seminar(SeminarCreateVO seminarCreateVO, Course course) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.id = null;
        this.name = seminarCreateVO.getName();
        this.description = seminarCreateVO.getDescription();
        this.course = course;
        this.fixed = (seminarCreateVO.getGroupingMethod() == "fixed")? true : false;
        try {
            this.startTime = simpleDateFormat.parse(seminarCreateVO.getStartTime());
            this.endTime = simpleDateFormat.parse(seminarCreateVO.getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Seminar(SeminarUpdateVO seminarUpdateVO) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.id = null;
        this.name = seminarUpdateVO.getName();
        this.description = seminarUpdateVO.getDescription();
        this.course = null;
        this.fixed = "fixed".equals(seminarUpdateVO.getGroupingMethod());
        try {
            this.startTime = simpleDateFormat.parse(seminarUpdateVO.getStartTime());
            this.endTime = simpleDateFormat.parse(seminarUpdateVO.getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("error!!");
        }
    }

    public Seminar() {


    }

    public Seminar(BigInteger id, String name, String description, Course course, Boolean fixed, Date startTime, Date endTime) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.course = course;
        this.fixed = fixed;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Boolean getFixed() {
        return fixed;
    }

    public void setFixed(Boolean fixed) {
        this.fixed = fixed;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Seminar{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", course=" + course +
                ", fixed=" + fixed +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
