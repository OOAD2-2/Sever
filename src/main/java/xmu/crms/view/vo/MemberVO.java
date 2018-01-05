package xmu.crms.view.vo;

import java.math.BigInteger;

public class MemberVO {
    private String id;
    private String name;
    private String number;

    public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public MemberVO() {
    }

    public MemberVO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
