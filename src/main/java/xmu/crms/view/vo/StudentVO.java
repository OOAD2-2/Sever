package xmu.crms.view.vo;

import xmu.crms.entity.User;

/**
 * @author LUWEIW
 */

public class StudentVO {

    private int id;
    private String name;
    private String number;

    public StudentVO(User user) {
        this.id = user.getId().intValue();
        this.name = user.getName();
        this.number = user.getNumber();
    }

    public StudentVO(int id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "StudentVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
