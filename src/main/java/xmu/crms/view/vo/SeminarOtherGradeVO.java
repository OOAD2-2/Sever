package xmu.crms.view.vo;

/**
 * @author LUWEIW
 */

public class SeminarOtherGradeVO {

	private Integer id;
	private Integer topicId;
	public int getId() {
	return id;
}
	public void setId(int id) {
	this.id = id;
}

	public SeminarOtherGradeVO() {
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

	@Override
	public String toString() {
		return "SeminarOtherGradeVO{" +
				"id=" + id +
				", topicId=" + topicId +
				'}';
	}
}
