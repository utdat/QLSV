package entity;

public class StuSubject {
	private String stu_id;
	private int sub_id;
	private float midterm;
	private float endterm;
	
	public StuSubject() {
	}

	public StuSubject(String stu_id, int sub_id, float midterm, float endterm) {
		this.stu_id = stu_id;
		this.sub_id = sub_id;
		this.midterm = midterm;
		this.endterm = endterm;
	}

	public String getStu_id() {
		return stu_id;
	}

	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}

	public int getSub_id() {
		return sub_id;
	}

	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}

	public float getMidterm() {
		return midterm;
	}

	public void setMidterm(float midterm) {
		this.midterm = midterm;
	}

	public float getEndterm() {
		return endterm;
	}

	public void setEndterm(float endterm) {
		this.endterm = endterm;
	}

	@Override
	public String toString() {
		return "StuSubject [stu_id=" + stu_id + ", sub_id=" + sub_id + ", midterm=" + midterm + ", endterm=" + endterm
				+ "]";
	}
	
}
