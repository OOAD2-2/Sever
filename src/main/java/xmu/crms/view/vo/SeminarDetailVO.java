package xmu.crms.view.vo;

import java.text.SimpleDateFormat;

import xmu.crms.entity.Seminar;

public class SeminarDetailVO {
	private int id;
	private String name;
	private String startTime;
	private String endTime;
	private String site;
	private String teacherName;
	private String teacherEmail;
	
	public SeminarDetailVO(Seminar seminar)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.id=seminar.getId().intValue();
		this.name=seminar.getName();
		this.startTime=simpleDateFormat.format(seminar.getStartTime());
		this.endTime = simpleDateFormat.format(seminar.getEndTime());
	}
	public void setId(int id)
	{
		this.id=id;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public void setStartTime(String startTime)
	{
		this.startTime=startTime;
	}
	public void setEndTime(String endTime)
	{
		this.endTime=endTime;
	}
	public void setSite(String site)
	{
		this.site=site;
	}
	public void setTeacherName(String teacherName)
	{
		this.teacherName=teacherName;
	}
	public void setTeacherEmail(String teacherEmail)
	{
		this.teacherEmail=teacherEmail;
	}
	
	
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getStartTime() {
		return startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public String getSite() {
		return site;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public String getTeacherEmail() {
		return teacherEmail;
	}
	@Override
    public String toString() {

        return "SeminarDetialVO{" +
        		"id='" + id + '\''+
                "name='" + name + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", site='" + site + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teacherEmail='" + teacherEmail + '\'' +
                '}';

    }
}
