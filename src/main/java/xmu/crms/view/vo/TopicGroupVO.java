package xmu.crms.view.vo;

/**
 * @author LUWEIW
 */

public class TopicGroupVO {

    private int id;
    private String name;

    public TopicGroupVO(int id, String name) {
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
        return "TopicGroupVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
