package xmu.crms.view.vo;

import java.util.List;

public class GroupMembersVO {

    /**
     * @author: LUWEIW
     */

    private int id;
    private String name;
    private StudentVO leader;
    private List<StudentVO> members;
    private String report;

    public GroupMembersVO(int id, String name, StudentVO leader, List<StudentVO> members, String report) {
        this.id = id;
        this.name = name;
        this.leader = leader;
        this.members = members;
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

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "GroupMembersVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", leader=" + leader +
                ", members=" + members +
                ", report='" + report + '\'' +
                '}';
    }
}
