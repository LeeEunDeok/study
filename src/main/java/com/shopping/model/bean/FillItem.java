package com.shopping.model.bean;

public class FillItem {
	private String module;
	private String field;
	private Integer ordering;
	private String engname;
	private String korname;
	
	public FillItem() {}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Integer getOrdering() {
		return ordering;
	}

	public void setOrdering(Integer ordering) {
		this.ordering = ordering;
	}

	public String getEngname() {
		return engname;
	}

	public void setEngname(String engname) {
		this.engname = engname;
	}

	public String getKorname() {
		return korname;
	}

	public void setKorname(String korname) {
		this.korname = korname;
	}

	public FillItem(String module, String field, Integer ordering, String engname, String korname) {
		super();
		this.module = module;
		this.field = field;
		this.ordering = ordering;
		this.engname = engname;
		this.korname = korname;
	}

	@Override
	public String toString() {
		return "FillItem [module=" + module + ", field=" + field + ", ordering=" + ordering + ", engname=" + engname
				+ ", korname=" + korname + "]";
	}
	
	
}
