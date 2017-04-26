package com.tesis.tpv.dto;

import java.math.BigDecimal;

public class SailDetailDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 267318740610652072L;
	private Integer id;
	private Integer saleId;
	private BigDecimal quantity;
	private Integer productId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSaleId() {
		return saleId;
	}
	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SailDetail [id=");
		builder.append(id);
		builder.append(", saleId=");
		builder.append(saleId);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", productId=");
		builder.append(productId);
		builder.append("]");
		return builder.toString();
	}

}
