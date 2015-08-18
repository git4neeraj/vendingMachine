package com.halcyonit.codingkata.vendingMachine.domain;

/**
 * The Class Product.
 */
public class Product {
	
	/** The product code. */
	private Integer productCode;
	
	/** The description. */
	private String description;
	
	/** The price. */
	private Double price = 0.0;

	
	
	/**
	 * Instantiates a new product.
	 *
	 * @param productCode the product code
	 * @param description the description
	 * @param price the price
	 */
	public Product(Integer productCode, String description, Double price) {
		super();
		this.productCode = productCode;
		this.description = description;
		this.price = price;
	}

	/**
	 * Gets the product code.
	 *
	 * @return the product code
	 */
	public Integer getProductCode() {
		return productCode;
	}	

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}	

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [productCode=" + productCode + ", description="
				+ description + ", price=" + price + "]";
	}

	
}
