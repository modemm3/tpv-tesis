package com.tesis.tpv.dto;

import java.math.BigDecimal;
import java.util.Date;

public class BillingDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4637647499347414410L;
	private Integer id;
	private Integer saleId;
	private Date billingDate;
	private BigDecimal subtotal;
	private BigDecimal total;
	private Integer billingDataId;
	
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
	public Date getBillingDate() {
		return billingDate;
	}
	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Integer getBillingDataId() {
		return billingDataId;
	}
	public void setBillingDataId(Integer billingDataId) {
		this.billingDataId = billingDataId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Billing [id=");
		builder.append(id);
		builder.append(", saleId=");
		builder.append(saleId);
		builder.append(", billingDate=");
		builder.append(billingDate);
		builder.append(", subtotal=");
		builder.append(subtotal);
		builder.append(", total=");
		builder.append(total);
		builder.append(", billingDataId=");
		builder.append(billingDataId);
		builder.append("]");
		return builder.toString();
	}		

}
