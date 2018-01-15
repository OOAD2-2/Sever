package xmu.crms.view.vo;

/**
 * @author LUWEIW
 */

public class PresentationGradeVO {

    private Integer topicId;
    private Integer grade;

    public PresentationGradeVO(int topicId, int grade) {
        this.topicId = topicId;
        this.grade = grade;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "PresentationGradeVO{" +
                "topicId=" + topicId +
                ", grade=" + grade +
                '}';
    }
}
