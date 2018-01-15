package xmu.crms.view.vo;

/**
 * @author LUWEIW
 */

public class MyTopicVO {

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
private String id;
private String name;
public MyTopicVO(String id,String name)
{
	this.id=id;
	this.name=name;
}

	@Override
	public String toString() {
		return "MyTopicVO{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
