package xmu.crms.view.vo;

import java.util.List;

public class GroupMemberTopicGradeVO {

    /**
     * @author: LUWEIW
     */

    private int id;
    private String name;
    private StudentVO leader;
    private List<StudentVO> members;
    private List<TopicGroupVO> topics;
    private GroupGradeVO group;
    private String report;

    public GroupMemberTopicGradeVO(int id, String name, StudentVO leader, List<StudentVO> members, List<TopicGroupVO> topics, GroupGradeVO group, String report) {
        this.id = id;
        this.name = name;
        this.leader = leader;
        this.members = members;
        this.topics = topics;
        this.group = group;
        this.report = report;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StudentVO getLeader() {
        return leader;
    }

    public void setLeader(StudentVO leader) {
        this.leader = leader;
    }

    public List<StudentVO> getMembers() {
        return members;
    }

    public void setMembers(List<StudentVO> members) {
        this.members = members;
    }

    public List<TopicGroupVO> getTopics() {
        return topics;
    }

    public void setTopics(List<TopicGroupVO> topics) {
        this.topics = topics;
    }

    public GroupGradeVO getGroup() {
        return group;
    }

    public void setGroup(GroupGradeVO group) {
        this.group = group;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "GroupMemberTopicGradeVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", leader=" + leader +
                ", members=" + members +
                ", topics=" + topics +
                ", group=" + group +
                ", report='" + report + '\'' +
                '}';
    }
}
