package xmu.crms.view.vo;

import xmu.crms.entity.Seminar;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author LUWEIW
 */

public class SeminarCurrentVO {

    private Integer id;
    private String name;
    private String courseName;
    private String groupingMethod;
    private String startTime;
    private String endTime;
    private List<ClassVO> classes;

    public SeminarCurrentVO() {
    }

    public SeminarCurrentVO(Seminar seminar, List<ClassVO> classVOList) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.id = seminar.getId().intValue();
        this.name = seminar.getName();
        this.groupingMethod = (seminar.getFixed()==true)? "fixed" : "random";
        this.startTime = simpleDateFormat.format(seminar.getStartTime());
        this.endTime = simpleDateFormat.format(seminar.getEndTime());
        this.classes = classVOList;
    }

    public SeminarCurrentVO(int id, String name, String courseName,
                            String groupingMethod, String startTime, String endTime, List<ClassVO> classes) {
        this.id = id;
        this.name = name;
        this.courseName = courseName;
        this.groupingMethod = groupingMethod;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classes = classes;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getGroupingMethod() {
        return groupingMethod;
    }

    public void setGroupingMethod(String groupingMethod) {
        this.groupingMethod = groupingMethod;
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

    public List<ClassVO> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassVO> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "SeminarCurrentVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", courseName='" + courseName + '\'' +
                ", groupingMethod='" + groupingMethod + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", classes=" + classes +
                '}';
    }
}
