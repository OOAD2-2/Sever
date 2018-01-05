package xmu.crms.view.vo;

public class StudentSeminarVO {
	private int id;
	private String name;
	private String groupingMethod;
	private String courseName;
	private String startTime;
	private String endTime;
	private int classCalling;
	private boolean isLeader;
	private boolean areTopicsSeletced;
	public void setId(int id)
	{
		this.id=id;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public void setGroupingMethod(String groupingMethod)
	{
		this.groupingMethod=groupingMethod;
	}
	public void setCourseName(String courseName)
	{
		this.courseName=courseName;
	}
	public void setStartTime(String startTime)
	{
		this.startTime=startTime;
	}
	public void setEndTime(String endTime)
	{
		this.endTime=endTime;
	}
	public void setClassCalling(int classCalling)
	{
		this.classCalling=classCalling;
	}
	public void setIsleader(boolean isLeader)
	{
		this.isLeader=isLeader;
	}
	public void setAreTopicsSeletced(boolean areTopicsSeletced)
	{
		this.areTopicsSeletced=areTopicsSeletced;
	}

	@Override
    public String toString() {

        return "StudentSeminarVO{" +
        		"id='" + id + +'\''+
                "name='" + name + '\'' +
                ", groupingMethod='" + groupingMethod + '\'' +
                ", courseName='" + courseName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", classCalling='" + classCalling + '\'' +
                ", isLeader='" + isLeader + '\'' +
                ", areTopicsSeletced='" + areTopicsSeletced + '\'' +
                '}';

    }
}
