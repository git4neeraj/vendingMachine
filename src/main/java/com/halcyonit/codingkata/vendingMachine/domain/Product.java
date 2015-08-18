package com.halcyonit.codingkata.vendingMachine.domain;

public class Product {
	
	private Integer productCode;
	
	private String description;
	
	private Double price = 0.0;

	
	
	public Product(Integer productCode, String description, Double price) {
		super();
		this.productCode = productCode;
		this.description = description;
		this.price = price;
	}

	public Integer getProductCode() {
		return productCode;
	}	

	public String getDescription() {
		return description;
	}	

	public Double getPrice() {
		return price;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result
				+ ((productCode == null) ? 0 : productCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (productCode == null) {
			if (other.productCode != null)
				return false;
		} else if (!productCode.equals(other.productCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", description="
				+ description + ", price=" + price + "]";
	}

	
}
