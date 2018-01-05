package xmu.crms.view.vo;

import java.util.List;

public class GroupMemberGradeVO {
    private int id;
    private String name;
    private StudentVO leader;
    private List<StudentVO> members;
    private GroupGradeVO group;
    private String report;

    public GroupMemberGradeVO(int id, String name, StudentVO leader, List<StudentVO> members, GroupGradeVO group, String report) {
        this.id = id;
        this.name = name;
        this.leader = leader;
        this.members = members;
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
}
