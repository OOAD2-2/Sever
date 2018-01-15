package xmu.crms.view.vo;

import xmu.crms.entity.Topic;

public class TopicByIdVO {

    /**
     * @author: LUWEIW
     */

    private int id;
    private String serial;
    private String name;
    private String description;
    private int groupLimit;
    private int groupMemberLimit;
    private int groupLeft;

    public TopicByIdVO() {
    }

    public TopicByIdVO(Topic topic, int groupLeft) {
        this.id = topic.getId().intValue();
        this.serial = topic.getSerial();
        this.name = topic.getName();
        this.description = topic.getDescription();
        this.groupLimit = topic.getGroupNumberLimit();
        this.groupMemberLimit = topic.getGroupStudentLimit();
        this.groupLeft = groupLeft;
    }

    public TopicByIdVO(int id, String serial, String name, String description, int groupLimit, int groupMemberLimit, int groupLeft) {
        this.id = id;
        this.serial = serial;
        this.name = name;
        this.description = description;
        this.groupLimit = groupLimit;
        this.groupMemberLimit = groupMemberLimit;
        this.groupLeft = groupLeft;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
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

    public int getGroupLimit() {
        return groupLimit;
    }

    public void setGroupLimit(int groupLimit) {
        this.groupLimit = groupLimit;
    }

    public int getGroupMemberLimit() {
        return groupMemberLimit;
    }

    public void setGroupMemberLimit(int groupMemberLimit) {
        this.groupMemberLimit = groupMemberLimit;
    }

    public int getGroupLeft() {
        return groupLeft;
    }

    public void setGroupLeft(int groupLeft) {
        this.groupLeft = groupLeft;
    }

    @Override
    public String toString() {
        return "TopicByIdVO{" +
                "id=" + id +
                ", serial='" + serial + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", groupLimit=" + groupLimit +
                ", groupMemberLimit=" + groupMemberLimit +
                ", groupLeft=" + groupLeft +
                '}';
    }
}
