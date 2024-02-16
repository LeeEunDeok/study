package com.shopping.model.bean;

public class Combo01 {
	private String name;
	private String subject;
	private String contents;
	private String regdate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	public Combo01(String name, String subject, String contents, String regdate) {
		super();
		this.name = name;
		this.subject = subject;
		this.contents = contents;
		this.regdate = regdate;
	}
	
	public Combo01() {}
	
	@Override
	public String toString() {
		return "Combo01 [name=" + name + ", subject=" + subject + ", contents=" + contents + ", regdate=" + regdate
				+ "]";
	}
	
}
