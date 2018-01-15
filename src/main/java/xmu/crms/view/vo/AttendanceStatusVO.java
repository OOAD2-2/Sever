package xmu.crms.view.vo;

public class AttendanceStatusVO {

	/**
	 * @author: LUWEIW
	 */

private String status;
public AttendanceStatusVO(String status)
{
	this.status=status;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

	@Override
	public String toString() {
		return "AttendanceStatusVO{" +
				"status='" + status + '\'' +
				'}';
	}
}
