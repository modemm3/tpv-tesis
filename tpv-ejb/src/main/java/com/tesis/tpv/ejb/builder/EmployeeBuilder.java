package com.tesis.tpv.ejb.builder;

import com.tesis.tpv.dto.BaseDTO;
import com.tesis.tpv.dto.DepartmentDTO;
import com.tesis.tpv.dto.EmployeeCategoryDTO;
import com.tesis.tpv.dto.EmployeeDTO;
import com.tesis.tpv.ejb.builder.config.AbstractDTOBuilder;
import com.tesis.tpv.ejb.builder.config.BuilderConfiguration;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.BaseEntity;
import com.tesis.tpv.jpa.Employee;

@BuilderConfiguration(dtoClass=EmployeeDTO.class, entityClass=Employee.class)
public class EmployeeBuilder extends AbstractDTOBuilder {

	@Override
	public BaseDTO createDTO(BaseEntity entity) {
		final EmployeeDTO employeeDTO=new EmployeeDTO();
		final Employee employee=(Employee)entity;
		
		employeeDTO.setAddress(employee.getAddress());
		employeeDTO.setAdmissionDate(employee.getAdmissionDate());
		employeeDTO.setAge(employee.getAge());
		employeeDTO.setCellPhone(employee.getCellPhone());
		System.out.println("employee: "+employee);
		employeeDTO.setDepartmentId(TransferObjectAssembler.getInstance().assembleTO(DepartmentDTO.class, employee.getDepartment()).getId());
		employeeDTO.setEmployeeCategoryId(TransferObjectAssembler.getInstance().assembleTO(EmployeeCategoryDTO.class, employee.getEmployeeCategory()).getId());
		employeeDTO.setId(employee.getId());
		employeeDTO.setMaternalName(employee.getMaternalName());
		employeeDTO.setName(employee.getName());
		employeeDTO.setPaternalName(employee.getPaternalName());
		employeeDTO.setTelephone(employee.getTelephone());

		return employeeDTO;
	}

}
