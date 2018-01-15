package xmu.crms.view.vo;

import java.math.BigInteger;
import java.util.List;
import java.util.function.BiFunction;

public class GroupDetailsVO {

    /**
     * @author: LUWEIW
     */

    private BigInteger id;
    private String name;
    private StudentVO leader;
    private List<StudentVO> members;
    private List<TopicGroupVO> topics;
    private String report;

    public GroupDetailsVO(GroupByIdVO groupByIdVO, StudentVO leader, List<StudentVO> members, List<TopicGroupVO> topics, String report) {
        this.id = groupByIdVO.getId();
        this.name = groupByIdVO.getName();
        this.leader = leader;
        this.members = members;
        this.topics = topics;
        this.report = report;
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

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    @Override
    public String toString() {
        return "GroupDetailsVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", leader=" + leader +
                ", members=" + members +
                ", topics=" + topics +
                ", report='" + report + '\'' +
                '}';
    }
}
