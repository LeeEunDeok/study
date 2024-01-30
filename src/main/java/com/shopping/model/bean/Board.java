package com.shopping.model.bean;

public class Board {
	private int no;
	private String id;
	private String password;
	private String subject;
	private String contents;
	private int readhit;
	private String regdate;
	private String remark;
	private int groupno;
	private int orderno;
	private int depth;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public int getReadhit() {
		return readhit;
	}
	public void setReadhit(int readhit) {
		this.readhit = readhit;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getGroupno() {
		return groupno;
	}
	public void setGroupno(int groupno) {
		this.groupno = groupno;
	}
	public int getOrderno() {
		return orderno;
	}
	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public Board() {
	}
	
	public Board(int no, String id, String password, String subject, String contents, int readhit, String regdate,
			String remark, int groupno, int orderno, int depth) {
		super();
		this.no = no;
		this.id = id;
		this.password = password;
		this.subject = subject;
		this.contents = contents;
		this.readhit = readhit;
		this.regdate = regdate;
		this.remark = remark;
		this.groupno = groupno;
		this.orderno = orderno;
		this.depth = depth;
	}
	@Override
	public String toString() {
		return "Board [no=" + no + ", id=" + id + ", password=" + password + ", subject=" + subject + ", contents="
				+ contents + ", readhit=" + readhit + ", regdate=" + regdate + ", remark=" + remark + ", groupno="
				+ groupno + ", orderno=" + orderno + ", depth=" + depth + "]";
	}
	
	

}
