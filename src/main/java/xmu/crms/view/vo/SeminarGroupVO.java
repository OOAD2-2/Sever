package xmu.crms.view.vo;

public class SeminarGroupVO {
	private Integer id;
	public SeminarGroupVO(Integer id)
	{
		this.id=id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "SeminarGroupVO{" +
				"id=" + id +
				'}';
	}
}
