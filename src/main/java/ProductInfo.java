package main.java;

import java.math.BigDecimal;

/**
* This ProductInfo PoJo class represent a product information
*
* @author  Vandan Hatkar
* @version 1.0
* @since   2018-11-22 
*/
public class ProductInfo {

	public ProductInfo(String productType, BigDecimal productPrice, BigDecimal totalAmount, int productQuantity) {
		this.productType = productType;
		this.productPrice = productPrice;
		this.totalAmount = totalAmount;
		this.productQuantity = productQuantity;
	}

	private String productType;
	
	private BigDecimal productPrice;
	
	private BigDecimal totalAmount;

	private int productQuantity;
	
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}
	
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

}
