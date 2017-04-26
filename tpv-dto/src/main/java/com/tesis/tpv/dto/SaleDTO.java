package com.tesis.tpv.dto;

import java.math.BigDecimal;

public class SaleDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5443922974488731115L;
	private Integer id;
	private Integer paymentTypeId;
	private BigDecimal amount;
	private BigDecimal discount;
	private Integer employeeId;
	
	public Integer getPaymentTypeId() {
		return paymentTypeId;
	}
	public void setPaymentTypeId(Integer paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SaleDTO [id=");
		builder.append(id);
		builder.append(", paymentTypeId=");
		builder.append(paymentTypeId);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", discount=");
		builder.append(discount);
		builder.append(", employeeId=");
		builder.append(employeeId);
		builder.append("]");
		return builder.toString();
	}

}
