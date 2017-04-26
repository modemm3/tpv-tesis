package com.tesis.tpv.dto;

import java.util.Date;

public class EmployeeDTO implements BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1801810611145790770L;
	private Integer id;
	private String name;
	private String cellPhone;
	private String telephone;
	private Integer departmentId;
	private Integer employeeCategoryId;
	private Integer age;
	private Date admissionDate;
	private String maternalName;
	private String paternalName;
	private String address;
	
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
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Integer getEmployeeCategoryId() {
		return employeeCategoryId;
	}
	public void setEmployeeCategoryId(Integer employeeCategoryId) {
		this.employeeCategoryId = employeeCategoryId;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getAdmissionDate() {
		return admissionDate;
	}
	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", cellPhone=");
		builder.append(cellPhone);
		builder.append(", telephone=");
		builder.append(telephone);
		builder.append(", departmentId=");
		builder.append(departmentId);
		builder.append(", employeeCategoryId=");
		builder.append(employeeCategoryId);
		builder.append(", age=");
		builder.append(age);
		builder.append(", admissionDate=");
		builder.append(admissionDate);
		builder.append(", maternalName=");
		builder.append(maternalName);
		builder.append(", paternalName=");
		builder.append(paternalName);
		builder.append(", address=");
		builder.append(address);
		builder.append("]");
		return builder.toString();
	}
	
}
