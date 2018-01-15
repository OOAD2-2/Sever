package xmu.crms.view.vo;

/**
 * @author LUWEIW
 */

public class StudentSeminarVO {

	private Integer id;
	private String name;
	private String groupingMethod;
	private String courseName;
	private String startTime;
	private String endTime;
	private Integer classCalling;
	private Boolean isLeader;
	private Boolean areTopicsSeletced;
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
	public boolean isLeader() {
		return isLeader;
	}
	public void setLeader(boolean isLeader) {
		this.isLeader = isLeader;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getGroupingMethod() {
		return groupingMethod;
	}
	public String getCourseName() {
		return courseName;
	}
	public String getStartTime() {
		return startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public int getClassCalling() {
		return classCalling;
	}
	public boolean isAreTopicsSeletced() {
		return areTopicsSeletced;
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
