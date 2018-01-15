package xmu.crms.view.vo;

import xmu.crms.entity.SeminarGroup;

import java.util.List;

public class GroupGradeVO {

    /**
     * @author: LUWEIW
     */

    private List<PresentationGradeVO> presentationGrade;
    private int reportGrade;
    private int grade;

    public GroupGradeVO(List<PresentationGradeVO> presentationGradeVO, SeminarGroup seminarGroup) {
        this.presentationGrade = presentationGradeVO;
        this.reportGrade = seminarGroup.getReportGrade();
        this.grade = seminarGroup.getFinalGrade();
    }

    public GroupGradeVO(List<PresentationGradeVO> presentationGradeVO, int reportGrade, int grade) {
        this.presentationGrade = presentationGradeVO;
        this.reportGrade = reportGrade;
        this.grade = grade;
    }

    public List<PresentationGradeVO> getPresentationGradeVO() {
        return presentationGrade;
    }

    public void setPresentationGradeVO(List<PresentationGradeVO> presentationGradeVO) {
        this.presentationGrade = presentationGradeVO;
    }

    public int getReportGrade() {
        return reportGrade;
    }

    public void setReportGrade(int reportGrade) {
        this.reportGrade = reportGrade;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "GroupGradeVO{" +
                "presentationGrade=" + presentationGrade +
                ", reportGrade=" + reportGrade +
                ", grade=" + grade +
                '}';
    }
}
