package xmu.crms.view.vo;

/**
 * @author LUWEIW
 */

public class AttendanceVO {

    private Double longitude;
    private Double latitude;
    private Double elevation;

    public AttendanceVO(double longitude, double latitude, double elevation) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.elevation = elevation;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getElevation() {
        return elevation;
    }

    public void setElevation(double elevation) {
        this.elevation = elevation;
    }

    @Override
    public String toString() {
        return "AttendanceVO{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", elevation=" + elevation +
                '}';
    }
}
