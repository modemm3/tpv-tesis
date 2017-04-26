package com.tesis.tpv.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ProviderProductDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7344560581167967800L;
	private Integer id;
	private Integer providerId;
	private Integer productId;
	private BigDecimal price;
	private Date buyDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProviderId() {
		return providerId;
	}
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProviderProduct [id=");
		builder.append(id);
		builder.append(", providerId=");
		builder.append(providerId);
		builder.append(", productId=");
		builder.append(productId);
		builder.append(", price=");
		builder.append(price);
		builder.append(", buyDate=");
		builder.append(buyDate);
		builder.append("]");
		return builder.toString();
	}
		
}
