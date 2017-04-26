package com.tesis.tpv.dto;

public class ProviderDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8557053257644461652L;
	private Integer id;
	private String name;
	private String telephone;
	private String address;
	private String webPage;
	private String rfc;
	private String maternalName;
	private String paternalName;
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

}
