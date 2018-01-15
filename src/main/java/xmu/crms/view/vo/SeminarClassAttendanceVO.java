package xmu.crms.view.vo;

/**
 * @author LUWEIW
 */

public class SeminarClassAttendanceVO {

    private Integer numPresent;
    private Integer numStudent;
    private String status;
    private String group;


    public SeminarClassAttendanceVO() {
    }
    public SeminarClassAttendanceVO(int numPresent, int numStudent, String status, String group) {
        this.numPresent = numPresent;
        this.numStudent = numStudent;
        this.status = status;
        this.group = group;
    }

    public int getNumPresent() {
        return numPresent;
    }

    public void setNumPresent(int numPresent) {
        this.numPresent = numPresent;
    }

    public int getNumStudent() {
        return numStudent;
    }

    public void setNumStudent(int numStudent) {
        this.numStudent = numStudent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "SeminarClassAttendanceVO{" +
                "numPresent=" + numPresent +
                ", numStudent=" + numStudent +
                ", status='" + status + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}
