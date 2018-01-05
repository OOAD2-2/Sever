package xmu.crms.view.vo;

public class SeminarDetailVO {
	private int id;
	private String name;
	private String startTime;
	private String endTime;
	private String site;
	private String teacherName;
	private String teacherEmail;
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
	
	@Override
    public String toString() {

        return "SeminarDetialVO{" +
        		"id='" + id + +'\''+
                "name='" + name + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", site='" + site + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teacherEmail='" + teacherEmail + '\'' +
                '}';

    }
}
