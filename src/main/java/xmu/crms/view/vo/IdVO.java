package xmu.crms.view.vo;

public class IdVO {

    /**
     * @author: LUWEIW
     */

    private int id;

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
