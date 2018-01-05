package xmu.crms.view.vo;

import xmu.crms.entity.FixGroup;
import xmu.crms.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ClassGroupVO {
    private StudentVO leader;
    private List<StudentVO> members;

    public ClassGroupVO(FixGroup fixGroup, List<User> members) {
        this.leader = new StudentVO(fixGroup.getLeader().getId().intValue(), fixGroup.getLeader().getName(), fixGroup.getLeader().getNumber());
        this.members = new ArrayList<StudentVO>();
        for (User member : members) {
            this.members.add(new StudentVO(member.getId().intValue(),
                    member.getName(), member.getNumber()));
        }
    }

    public ClassGroupVO() {
        members = new ArrayList<StudentVO>();
    }

    public ClassGroupVO(StudentVO leader, List<StudentVO> members) {
        this.leader = leader;
        this.members = members;
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
}
