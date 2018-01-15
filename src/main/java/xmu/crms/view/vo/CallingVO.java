package xmu.crms.view.vo;

public class CallingVO {

    /**
     * @author: LUWEIW
     */

    public int calling;

    public CallingVO(int calling) {
        calling = calling;
    }

    public int getCalling() {
        return calling;
    }

    public void setCalling(int calling) {
        calling = calling;
    }

    @Override
    public String toString() {
        return "CallingVO{" +
                "calling=" + calling +
                '}';
    }
}
