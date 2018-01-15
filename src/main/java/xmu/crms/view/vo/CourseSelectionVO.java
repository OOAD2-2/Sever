package xmu.crms.view.vo;

/**
 * @author LUWEIW
 */

public class CourseSelectionVO {
    public int numStudent;

    public CourseSelectionVO(int numStudent) {
        this.numStudent = numStudent;
    }

    public int getNumStudent() {
        return numStudent;
    }

    public void setNumStudent(int numStudent) {
        this.numStudent = numStudent;
    }

    @Override
    public String toString() {
        return "CourseSelectionVO{" +
                "numStudent=" + numStudent +
                '}';
    }
}
