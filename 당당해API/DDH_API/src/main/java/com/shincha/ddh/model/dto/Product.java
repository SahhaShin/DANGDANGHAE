package com.shincha.ddh.model.dto;

//프론트에서 상품사기 버튼을 누르면 유저와 연결됨 (users_has_product)
public class Product {
	private int no; //auto_increment
	private String name;
	private String content;
	private String image;
	private long point;
	private String company;
	private int count;
	private boolean sale;//판매여부
	private String type; //식료품 / 생활용품 / 가전
	
	public Product() {}
	public Product(int no, String name, String content, String image, long point, String company, int count,
			boolean sale, String type) {
		super();
		this.no = no;
		this.name = name;
		this.content = content;
		this.image = image;
		this.point = point;
		this.company = company;
		this.count = count;
		this.sale = sale;
		this.type = type;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public long getPoint() {
		return point;
	}

	public void setPoint(long point) {
		this.point = point;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isSale() {
		return sale;
	}

	public void setSale(boolean sale) {
		this.sale = sale;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Product [no=" + no + ", name=" + name + ", content=" + content + ", image=" + image + ", point=" + point
				+ ", company=" + company + ", count=" + count + ", sale=" + sale + ", type=" + type + "]";
	}
	
	
	

}
