package entity;

import java.util.Date;

public class Accounts {
	private String id;
	private String avatar;
	private String firstname;
	private String lastname;
	private String phone;
	private String address;
	private String email;
	private String gender;
	private Date birthdate;
	private int type;
	private int dep_id;
	private String password;
	
	public Accounts() {
	}

	public Accounts(String id, String avatar, String firstname, String lastname, String phone, String address,
			String email, String gender, Date birthdate, int dep_id, int type, String password) {
		this.id = id;
		this.avatar = avatar;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.gender = gender;
		this.birthdate = birthdate;
		this.type = type;
		this.dep_id = dep_id;
		this.password = password;
	}

	public int getDep_id() {
		return dep_id;
	}

	public void setDep_id(int dep_id) {
		this.dep_id = dep_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Accounts [id=" + id + ", avatar=" + avatar + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", phone=" + phone + ", address=" + address + ", email=" + email + ", gender=" + gender
				+ ", birthdate=" + birthdate + ", type=" + type + ", dep_id=" + dep_id + ", password=" + password + "]";
	}
	
}
