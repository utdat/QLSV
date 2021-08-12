package entity;

public class Departments {
	private int id;
	private String name;
	private int sch_id;
	
	
	public Departments() {
	}

	public Departments(int id, String name, int sch_id) {
		this.id = id;
		this.name = name;
		this.sch_id = sch_id;
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

	public int getSch_id() {
		return sch_id;
	}

	public void setSch_id(int sch_id) {
		this.sch_id = sch_id;
	}

	@Override
	public String toString() {
		return "Departments [id=" + id + ", name=" + name + ", sch_id=" + sch_id + "]";
	}
	
}
