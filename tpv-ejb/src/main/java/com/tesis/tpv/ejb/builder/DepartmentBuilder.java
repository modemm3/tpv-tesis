package com.tesis.tpv.ejb.builder;

import com.tesis.tpv.dto.BaseDTO;
import com.tesis.tpv.dto.DepartmentDTO;
import com.tesis.tpv.ejb.builder.config.AbstractDTOBuilder;
import com.tesis.tpv.ejb.builder.config.BuilderConfiguration;
import com.tesis.tpv.jpa.BaseEntity;
import com.tesis.tpv.jpa.Department;

@BuilderConfiguration(dtoClass=DepartmentDTO.class, entityClass=Department.class)
public class DepartmentBuilder extends AbstractDTOBuilder {

	@Override
	public BaseDTO createDTO(BaseEntity entity) {
		final DepartmentDTO departmentDTO= new DepartmentDTO();
		final Department department=(Department)entity;
		
		departmentDTO.setId(department.getId());
		departmentDTO.setName(department.getName());
		// TODO Auto-generated method stub
		return departmentDTO;
	}

}
