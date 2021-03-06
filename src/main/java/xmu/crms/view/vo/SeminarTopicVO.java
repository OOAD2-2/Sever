package xmu.crms.view.vo;

/**
 * @author LUWEIW
 */

public class SeminarTopicVO {

    private Integer id;
    private String name;

    public SeminarTopicVO(int id, String name) {
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
        return "SeminarTopicVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
