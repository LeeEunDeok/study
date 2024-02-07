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
	
	// 원글 : 사용자가 게시한 최초 작성 글(groupno 는 시퀀스가 생성한 번호와 동일, orderno = 0, depth = 0 인 글)
	// 답글은 최대 5개까지 작성 가능함(정하기 나름)
	private int groupno; // 그룹 번호 : 답글을 작성한 게시물들은 동일한 그룹 번호를 가집니다.
	private int orderno; // 순서 번호 : 조회 시 보여지는 순서를 의미합니다.
	private int depth; // 글의 깊이 : 해당 숫자만큼 화면에 're' 표시를 해줍니다.(최대 3까지 허용_정하기 나름)
	
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
