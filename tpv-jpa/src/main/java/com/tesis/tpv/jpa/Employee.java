package com.tesis.tpv.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name="employee")
public class Employee implements BaseEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="cell_phone")
	private String cellPhone;
	@Column(name="telephone")
	private String telephone;
	@JoinColumn(name="department_id", referencedColumnName="id")
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	private Department department;
	@JoinColumn(name="employee_category_id", referencedColumnName="id")
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	private EmployeeCategory employeeCategory;
	@Column(name="age")
	private Integer age;
	@Column(name="admission_date")
	@Temporal (TemporalType.DATE)
	private Date admissionDate;
	@Column(name="maternal_name")
	private String maternalName;
	@Column(name="paternal_name")
	private String paternalName;
	@Column(name="address	")
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
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public EmployeeCategory getEmployeeCategory() {
		return employeeCategory;
	}
	public void setEmployeeCategory(EmployeeCategory employeeCategory) {
		this.employeeCategory = employeeCategory;
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
		builder.append(", department=");
		builder.append(department);
		builder.append(", employeeCategory=");
		builder.append(employeeCategory);
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((admissionDate == null) ? 0 : admissionDate.hashCode());
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((cellPhone == null) ? 0 : cellPhone.hashCode());
		result = prime * result + ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((employeeCategory == null) ? 0 : employeeCategory.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((maternalName == null) ? 0 : maternalName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((paternalName == null) ? 0 : paternalName.hashCode());
		result = prime * result + ((telephone == null) ? 0 : telephone.hashCode());
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
		Employee other = (Employee) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (admissionDate == null) {
			if (other.admissionDate != null)
				return false;
		} else if (!admissionDate.equals(other.admissionDate))
			return false;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (cellPhone == null) {
			if (other.cellPhone != null)
				return false;
		} else if (!cellPhone.equals(other.cellPhone))
			return false;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (employeeCategory == null) {
			if (other.employeeCategory != null)
				return false;
		} else if (!employeeCategory.equals(other.employeeCategory))
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
		if (telephone == null) {
			if (other.telephone != null)
				return false;
		} else if (!telephone.equals(other.telephone))
			return false;
		return true;
	}
	
}
