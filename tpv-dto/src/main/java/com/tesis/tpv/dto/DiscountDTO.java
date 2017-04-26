package com.tesis.tpv.dto;

import java.math.BigDecimal;
import java.util.Date;

public class DiscountDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8711873238892105974L;
	private Integer id;
	private String name;
	private TypeDiscountDTO typeDiscountDTO;
	private BigDecimal amount;
	private Date startDate;
	private Date endDate;
	private ProductDTO productDTO;
	private Boolean status;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public TypeDiscountDTO getTypeDiscountDTO() {
		return typeDiscountDTO;
	}
	public void setTypeDiscountDTO(TypeDiscountDTO typeDiscountDTO) {
		this.typeDiscountDTO = typeDiscountDTO;
	}
	public ProductDTO getProductDTO() {
		return productDTO;
	}
	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DiscountDTO [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", typeDiscountDTO=");
		builder.append(typeDiscountDTO);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", productDTO=");
		builder.append(productDTO);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
	
}
