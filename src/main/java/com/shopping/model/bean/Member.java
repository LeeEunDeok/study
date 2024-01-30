package com.shopping.model.bean;

public class Member {
	private String id;
	private String name;
	private String password;
	private String gender;
	
	// 날짜 형식이더라도 문자열로 처리하면 코딩량이 줄어듭니다.
	private String birth;
	private String marriage;
	private String hobby;
	private String address;
	private int mpoint;
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getMpoint() {
		return mpoint;
	}
	public void setMpoint(int mpoint) {
		this.mpoint = mpoint;
	}
	
	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", password=" + password + ", gender=" + gender + ", birth="
				+ birth + ", marriage=" + marriage + ", hobby=" + hobby + ", address=" + address + ", mpoint=" + mpoint
				+ "]";
	}
	
	public Member() {
		// TODO Auto-generated constructor stub
	}
	
	public Member(String id, String name, String password, String gender, String birth, String marriage, String hobby,
			String address, int mpoint) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.birth = birth;
		this.marriage = marriage;
		this.hobby = hobby;
		this.address = address;
		this.mpoint = mpoint;
	}
	
}
