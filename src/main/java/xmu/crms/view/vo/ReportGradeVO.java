package xmu.crms.view.vo;

public class ReportGradeVO {

    /**
     * @author: LUWEIW
     */

    private int reportGrade;

    public ReportGradeVO() {
    }

    public ReportGradeVO(int reportGrade) {
        this.reportGrade = reportGrade;
    }

    public int getReportGrade() {
        return reportGrade;
    }

    public void setReportGrade(int reportGrade) {
        this.reportGrade = reportGrade;
    }

    @Override
    public String toString() {
        return "ReportGradeVO{" +
                "reportGrade=" + reportGrade +
                '}';
    }
}
