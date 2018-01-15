package xmu.crms.view.vo;

import java.math.BigInteger;

import xmu.crms.entity.Topic;

public class TopicVO {

	/**
	 * @author: LUWEIW
	 */

	private BigInteger id;
	private String name;
	private String serial;
	private String description;
	private Integer groupNumberLimit;
	private Integer groupStudentLimit;
	private Integer groupLeft;

	public TopicVO(Topic topic)
	{
		this.id=topic.getId();
		this.name=topic.getName();
		this.serial=topic.getSerial();
		this.description=topic.getDescription();
		this.groupNumberLimit=topic.getGroupNumberLimit();
		this.groupStudentLimit=topic.getGroupStudentLimit();
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

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getGroupNumberLimit() {
		return groupNumberLimit;
	}

	public void setGroupNumberLimit(Integer groupNumberLimit) {
		this.groupNumberLimit = groupNumberLimit;
	}

	public Integer getGroupStudentLimit() {
		return groupStudentLimit;
	}

	public void setGroupStudentLimit(Integer groupStudentLimit) {
		this.groupStudentLimit = groupStudentLimit;
	}


	public Integer getGroupLeft() {
		return groupLeft;
	}
	public void setGroupLeft(Integer groupLeft) {
		this.groupLeft = groupLeft;
	}
	@Override
	public String toString() {
		return "Topic{" +
				"id=" + id +
				", name='" + name + '\'' +
				", serial='" + serial + '\'' +
				", description='" + description + '\'' +
				", groupNumberLimit=" + groupNumberLimit +
				", groupStudentLimit=" + groupStudentLimit +
				", groupLeftr=" + groupLeft +
				'}';
	}
}
