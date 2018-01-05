package xmu.crms.view.vo;

import java.math.BigInteger;

public class MemberVO {
    private String id;
    private String name;

    public MemberVO() {
    }

    public MemberVO(String id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
