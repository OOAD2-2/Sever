package xmu.crms.view.vo;

/**
 * @author LUWEIW
 */

public class TopicUpdateVO {

    private String serial;
    private String name;
    private String description;
    private Integer groupLimit;
    private Integer groupMemberLimit;

    public TopicUpdateVO(String serial, String name, String description, int groupLimit, int groupMemberLimit) {
        this.serial = serial;
        this.name = name;
        this.description = description;
        this.groupLimit = groupLimit;
        this.groupMemberLimit = groupMemberLimit;
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

    @Override
    public String toString() {
        return "TopicUpdateVO{" +
                "serial='" + serial + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", groupLimit=" + groupLimit +
                ", groupMemberLimit=" + groupMemberLimit +
                '}';
    }
}
