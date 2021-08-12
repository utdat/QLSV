package entity;

public class SubjectGroup {
	private int id;
	private String name;
	private int dep_id;
	
	public SubjectGroup() {
	}

	public SubjectGroup(int id, String name, int dep_id) {
		super();
		this.id = id;
		this.name = name;
		this.dep_id = dep_id;
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

	public int getDep_id() {
		return dep_id;
	}

	public void setDep_id(int dep_id) {
		this.dep_id = dep_id;
	}

	@Override
	public String toString() {
		return "SubjectGroup [id=" + id + ", name=" + name + ", dep_id=" + dep_id + "]";
	}
	
}
