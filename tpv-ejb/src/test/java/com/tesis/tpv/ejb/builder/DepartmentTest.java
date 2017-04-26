package com.tesis.tpv.ejb.builder;

import org.junit.Test;
import com.tesis.tpv.dto.DepartmentDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Department;

public class DepartmentTest {

	@Test
	public void test() {
		Department department=new Department();
		
		department.setId(2);
		department.setName("Departamento dos");
		
		DepartmentDTO departmentDTO=TransferObjectAssembler.getInstance().assembleTO(DepartmentDTO.class, department);
		System.out.println(departmentDTO);
	}
}
