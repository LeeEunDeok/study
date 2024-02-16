package com.shopping.model.bean;

// 나의 찜 정보 1개를 저장하고 있는 bean 클래스
public class WishList {
	private String id;
	private int pnum;
	private int qty;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public WishList(String id, int pnum, int qty) {
		super();
		this.id = id;
		this.pnum = pnum;
		this.qty = qty;
	}
	
	public WishList() {}
	
	@Override
	public String toString() {
		return "WishList [id=" + id + ", pnum=" + pnum + ", qty=" + qty + "]";
	}
	
}
