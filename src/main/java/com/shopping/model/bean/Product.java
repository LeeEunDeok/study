package com.shopping.model.bean;

public class Product {
	private int pnum;
	private String name;
	private String company;
	private String image01;
	private String image02;
	private String image03;
	private int stock;
	private int price;
	private String category;
	private String contents;
	private int point;
	private String inputDate;
	
	public Product() {
		
	}
	
	public Product(int pnum, String name, String company, String image01, String image02, String image03, int stock,
			int price, String category, String contents, int point, String inputDate) {
		super();
		this.pnum = pnum;
		this.name = name;
		this.company = company;
		this.image01 = image01;
		this.image02 = image02;
		this.image03 = image03;
		this.stock = stock;
		this.price = price;
		this.category = category;
		this.contents = contents;
		this.point = point;
		this.inputDate = inputDate;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getImage01() {
		return image01;
	}

	public void setImage01(String image01) {
		this.image01 = image01;
	}

	public String getImage02() {
		return image02;
	}

	public void setImage02(String image02) {
		this.image02 = image02;
	}

	public String getImage03() {
		return image03;
	}

	public void setImage03(String image03) {
		this.image03 = image03;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getInputDate() {
		return inputDate;
	}

	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	@Override
	public String toString() {
		return "Product [pnum=" + pnum + ", name=" + name + ", company=" + company + ", image01=" + image01
				+ ", image02=" + image02 + ", image03=" + image03 + ", stock=" + stock + ", price=" + price
				+ ", category=" + category + ", contents=" + contents + ", point=" + point + ", inputDate=" + inputDate
				+ "]";
	}
	
	
}
