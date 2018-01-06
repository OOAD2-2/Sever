package xmu.crms.view.vo;

public class SeminarOtherGradeVO {
private int id;
private int topicId;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public SeminarOtherGradeVO(int id, int topicId) {
	this.id = id;
	this.topicId = topicId;
}
public int getTopicId() {
	return topicId;
}
public void setTopicId(int topicId) {
	this.topicId = topicId;
}
}
