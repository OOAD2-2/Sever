package xmu.crms.view.vo;

/**
 * @author LUWEIW
 */

public class ClassUpdateVO {

    private String name;
    private Integer numStudent;
    private String time;
    private String site;
    private Integer calling;
    private String roster;
    private Proportions proportions;

    public ClassUpdateVO(String name, int numStudent, String time, String site, int calling, String roster, Proportions proportions) {
        this.name = name;
        this.numStudent = numStudent;
        this.time = time;
        this.site = site;
        this.calling = calling;
        this.roster = roster;
        this.proportions = proportions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumStudent() {
        return numStudent;
    }

    public void setNumStudent(int numStudent) {
        this.numStudent = numStudent;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getCalling() {
        return calling;
    }

    public void setCalling(int calling) {
        this.calling = calling;
    }

    public String getRoster() {
        return roster;
    }

    public void setRoster(String roster) {
        this.roster = roster;
    }

    public Proportions getProportions() {
        return proportions;
    }

    public void setProportions(Proportions proportions) {
        this.proportions = proportions;
    }

    @Override
    public String toString() {
        return "ClassUpdateVO{" +
                "name='" + name + '\'' +
                ", numStudent=" + numStudent +
                ", time='" + time + '\'' +
                ", site='" + site + '\'' +
                ", calling=" + calling +
                ", roster='" + roster + '\'' +
                ", proportions=" + proportions +
                '}';
    }
}
