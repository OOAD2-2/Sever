package xmu.crms.view.vo;

import xmu.crms.entity.User;

public class UserVO {
    private int id;
    private String type;
    private String name;

    public UserVO() {
    }

    public UserVO(User user) {
        this.id = user.getId().intValue();
        this.type = (user.getType()==1)? "teacher" : "student";
        this.name = user.getName();
    }

    public UserVO(int id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
