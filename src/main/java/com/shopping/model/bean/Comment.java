package com.shopping.model.bean;

// 게시물에 따른 댓글(코멘트) Bean 클래스
public class Comment {
	private int cnum;// 댓글 번호
	private int no; // 게시물 번호(FK)
	private String id; // 작성자
	private String contents; // 댓글 내용
	private String regdate; // 생성 일자
	
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
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
	
	
	public Comment(int cnum, int no, String id, String contents, String regdate) {
		super();
		this.cnum = cnum;
		this.no = no;
		this.id = id;
		this.contents = contents;
		this.regdate = regdate;
	}
	
	public Comment() {}
	
	@Override
	public String toString() {
		return "Comment [cnum=" + cnum + ", no=" + no + ", id=" + id + ", contents=" + contents + ", regdate=" + regdate
				+ "]";
	}
	
	
}
