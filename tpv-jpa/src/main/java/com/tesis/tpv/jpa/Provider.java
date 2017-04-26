package com.tesis.tpv.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="provider")
public class Provider implements BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="telephone")
	private String telephone;
	@Column(name="address")
	private String address;
	@Column(name="web_page")
	private String webPage;
	@Column(name="rfc")
	private String rfc;
	@Column(name="maternal_name")
	private String maternalName;
	@Column(name="paternal_name")
	private String paternalName;
	@Column(name="business_name")
	private String businessName;
	
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
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWebPage() {
		return webPage;
	}
	public void setWebPage(String webPage) {
		this.webPage = webPage;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getMaternalName() {
		return maternalName;
	}
	public void setMaternalName(String maternalName) {
		this.maternalName = maternalName;
	}
	public String getPaternalName() {
		return paternalName;
	}
	public void setPaternalName(String paternalName) {
		this.paternalName = paternalName;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Provider [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", telephone=");
		builder.append(telephone);
		builder.append(", address=");
		builder.append(address);
		builder.append(", webPage=");
		builder.append(webPage);
		builder.append(", rfc=");
		builder.append(rfc);
		builder.append(", maternalName=");
		builder.append(maternalName);
		builder.append(", paternalName=");
		builder.append(paternalName);
		builder.append(", businessName=");
		builder.append(businessName);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((businessName == null) ? 0 : businessName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((maternalName == null) ? 0 : maternalName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((paternalName == null) ? 0 : paternalName.hashCode());
		result = prime * result + ((rfc == null) ? 0 : rfc.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
		result = prime * result + ((webPage == null) ? 0 : webPage.hashCode());
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
		Provider other = (Provider) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (businessName == null) {
			if (other.businessName != null)
				return false;
		} else if (!businessName.equals(other.businessName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (maternalName == null) {
			if (other.maternalName != null)
				return false;
		} else if (!maternalName.equals(other.maternalName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (paternalName == null) {
			if (other.paternalName != null)
				return false;
		} else if (!paternalName.equals(other.paternalName))
			return false;
		if (rfc == null) {
			if (other.rfc != null)
				return false;
		} else if (!rfc.equals(other.rfc))
			return false;
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		if (webPage == null) {
			if (other.webPage != null)
				return false;
		} else if (!webPage.equals(other.webPage))
			return false;
		return true;
	}

}
