package com.tesis.tpv.dto;

public class ClientDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4180717806088628998L;
	private Integer id;
	private Integer paymentTypeId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPaymentTypeId() {
		return paymentTypeId;
	}
	public void setPaymentTypeId(Integer paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Client [id=");
		builder.append(id);
		builder.append(", paymentTypeId=");
		builder.append(paymentTypeId);
		builder.append("]");
		return builder.toString();
	}
		
}
