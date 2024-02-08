package com.shopping.model.bean;

public class Emoticon {
	private int no;
	private int likes;
	private int hates;
	private int love;
	private int sad;
	private int angry;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getHates() {
		return hates;
	}
	public void setHates(int hates) {
		this.hates = hates;
	}
	public int getLove() {
		return love;
	}
	public void setLove(int love) {
		this.love = love;
	}
	public int getSad() {
		return sad;
	}
	public void setSad(int sad) {
		this.sad = sad;
	}
	public int getAngry() {
		return angry;
	}
	public void setAngry(int angry) {
		this.angry = angry;
	}
	
	public Emoticon(int no, int likes, int hates, int love, int sad, int angry) {
		super();
		this.no = no;
		this.likes = likes;
		this.hates = hates;
		this.love = love;
		this.sad = sad;
		this.angry = angry;
	}
	
	public Emoticon() {};
	
	@Override
	public String toString() {
		return "Emoticon [no=" + no + ", likes=" + likes + ", hates=" + hates + ", love=" + love + ", sad=" + sad
				+ ", angry=" + angry + "]";
	}
	
}
