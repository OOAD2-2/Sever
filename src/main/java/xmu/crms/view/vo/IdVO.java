package xmu.crms.view.vo;

/**
 * @author LUWEIW
 */

public class IdVO {

    private Integer id;

    public IdVO() {
    }

    public IdVO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IdVO{" +
                "id=" + id +
                '}';
    }
}
