package entity;

import java.util.Date;

public class Subjects {
	private int id;
	private String name;
	private Date startdate;
	private Date enddate;
	private String schedule;
	private String room;
	private String tea_id;
	private int sg_id;
	
	public Subjects() {
	}

	

	public Subjects(int id, String name, Date startdate, Date enddate, String schedule, String room, String tea_id,
			int sg_id) {
		super();
		this.id = id;
		this.name = name;
		this.startdate = startdate;
		this.enddate = enddate;
		this.schedule = schedule;
		this.room = room;
		this.tea_id = tea_id;
		this.sg_id = sg_id;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
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

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getTea_id() {
		return tea_id;
	}

	public void setTea_id(String tea_id) {
		this.tea_id = tea_id;
	}



	public int getSg_id() {
		return sg_id;
	}

	public void setSg_id(int sg_id) {
		this.sg_id = sg_id;
	}

	public Date getEnddate() {
		return enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@Override
	public String toString() {
		return "Subjects [id=" + id + ", name=" + name + ", startdate=" + startdate + ", enddate=" + enddate
				+ ", schedule=" + schedule + ", room=" + room + ", tea_id=" + tea_id + ", sg_id=" + sg_id + "]";
	}
	
}
