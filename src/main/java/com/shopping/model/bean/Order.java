package com.shopping.model.bean;

public class Order {
	private int oid;
	private String id;
	private String orderdate;
	private String remark;
	
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", id=" + id + ", orderdate=" + orderdate + ", remark=" + remark + "]";
	}
	
	public Order() {}
	
	public Order(int oid, String id, String orderdate, String remark) {
		super();
		this.oid = oid;
		this.id = id;
		this.orderdate = orderdate;
		this.remark = remark;
	}
	
	
}
