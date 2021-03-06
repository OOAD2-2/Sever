package xmu.crms.view.vo;

import xmu.crms.entity.ClassInfo;

/**
 * @author LUWEIW
 */

public class ClassVO {

    private Integer id;
    private String name;

    public ClassVO() {

    }
    public ClassVO(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "ClassVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
