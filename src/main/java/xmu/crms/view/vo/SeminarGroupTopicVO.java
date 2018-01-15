package xmu.crms.view.vo;

/**
 * @author LUWEIW
 */

public class SeminarGroupTopicVO {

private Integer id;
private String topicName;
public SeminarGroupTopicVO(Integer id,String topicName)
{
	this.id=id;
	this.topicName=topicName;
}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	@Override
	public String toString() {
		return "SeminarGroupTopicVO{" +
				"id=" + id +
				", topicName='" + topicName + '\'' +
				'}';
	}
}
