package com.tesis.tpv.ejb.builder;

import com.tesis.tpv.dto.BaseDTO;
import com.tesis.tpv.dto.EmployeeCategoryDTO;
import com.tesis.tpv.ejb.builder.config.AbstractDTOBuilder;
import com.tesis.tpv.ejb.builder.config.BuilderConfiguration;
import com.tesis.tpv.jpa.BaseEntity;
import com.tesis.tpv.jpa.EmployeeCategory;

@BuilderConfiguration(dtoClass=EmployeeCategoryDTO.class, entityClass=EmployeeCategory.class)
public class EmployeeCategoryBuilder extends AbstractDTOBuilder {

	@Override
	public BaseDTO createDTO(BaseEntity entity) {
		final EmployeeCategoryDTO employeeCategoryDTO=new EmployeeCategoryDTO();
		final EmployeeCategory employeeCategory=(EmployeeCategory) entity;
		
		employeeCategoryDTO.setId(employeeCategory.getId());
		employeeCategoryDTO.setName(employeeCategory.getName());
		
		return employeeCategoryDTO;
	}

}
