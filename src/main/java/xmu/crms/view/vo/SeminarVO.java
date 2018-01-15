package xmu.crms.view.vo;

import xmu.crms.entity.Seminar;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author LUWEIW
 */

public class SeminarVO {

    private int id;
    private String name;
    private String description;
    private String groupingMethod;
    private String startTime;
    private String endTime;
    private int grade;


    public SeminarVO(Seminar seminar, int grade) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.id = seminar.getId().intValue();
        this.name = seminar.getName();
        this.description = seminar.getDescription();
        this.groupingMethod = seminar.getFixed()?"fixed":"random";
        this.startTime = simpleDateFormat.format(seminar.getStartTime());
        this.endTime = simpleDateFormat.format(seminar.getEndTime());
        this.grade = grade;

    }

    public SeminarVO(int id, String name, String description,
                     String groupingMethod, String startTime, String endTime, int grade) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.groupingMethod = groupingMethod;
        this.startTime = startTime;
        this.endTime = endTime;
        this.grade = grade;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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


	@Override
	public String toString() {
		return "SeminarVO [id=" + id + ", name=" + name + ", description=" + description + ", groupingMethod="
				+ groupingMethod + ", startTime=" + startTime + ", endTime=" + endTime + ", grade=" + grade + "]";
	}

	public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
