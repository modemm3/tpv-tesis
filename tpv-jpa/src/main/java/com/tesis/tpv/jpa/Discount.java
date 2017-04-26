package com.tesis.tpv.jpa;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name="discount")
@NamedQueries({
	@NamedQuery(name="Discount.getAll", query="SELECT disc FROM Discount disc ORDER BY disc.id"),
	@NamedQuery(name="Discount.getCount", query="SELECT count(disc) FROM Discount disc")
})
public class Discount implements BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4702230907517414013L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="name")
	private String name;
	@JoinColumn(name="type_discount_id", referencedColumnName="id")
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	private TypeDiscount typeDiscount;
	@Column(name="amount")
	private BigDecimal amount;
	@Column(name="start_date")
	@Temporal (TemporalType.DATE)
	private Date startDate;
	@Column(name="end_date")
	@Temporal (TemporalType.DATE)
	private Date endDate;
	@JoinColumn(name="product_id", referencedColumnName="id")
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	private Product product;
	@Column(name="status")
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
	public TypeDiscount getTypeDiscount() {
		return typeDiscount;
	}
	public void setTypeDiscount(TypeDiscount typeDiscount) {
		this.typeDiscount = typeDiscount;
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
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Discount [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", typeDiscount=");
		builder.append(typeDiscount);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", startDate=");
		builder.append(startDate);
		builder.append(", endDate=");
		builder.append(endDate);
		builder.append(", product=");
		builder.append(product);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((typeDiscount == null) ? 0 : typeDiscount.hashCode());
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
		Discount other = (Discount) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (typeDiscount == null) {
			if (other.typeDiscount != null)
				return false;
		} else if (!typeDiscount.equals(other.typeDiscount))
			return false;
		return true;
	}
}
