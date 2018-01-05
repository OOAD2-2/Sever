package xmu.crms.view.vo;

import xmu.crms.entity.ClassInfo;

public class ClassByIdVO {
    private int id;
    private String name;
    private int numStudent;
    private String time;
    private String site;
    private int calling;
    private String roster;
    private Proportions proportions;

    public ClassByIdVO() {

    }

    public ClassByIdVO(ClassInfo classInfo, int numStudent) {
        this.id = classInfo.getId().intValue();
        this.name = classInfo.getName();
        this.numStudent = numStudent;
        this.time = classInfo.getClassTime();
        this.site = classInfo.getSite();
        this.calling = -1;
        this.roster = "";
        this.proportions = new Proportions(classInfo.getReportPercentage(), classInfo.getPresentationPercentage(),
                classInfo.getFivePointPercentage(), classInfo.getFourPointPercentage(), classInfo.getThreePointPercentage());
    }

    public ClassByIdVO(int id, String name, int numStudent, String time, String site, int calling, String roster, Proportions proportions) {
        this.id = id;
        this.name = name;
        this.numStudent = numStudent;
        this.time = time;
        this.site = site;
        this.calling = calling;
        this.roster = roster;
        this.proportions = proportions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
