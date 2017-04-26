package com.tesis.tpv.ejb.builder;
import org.junit.Test;

import com.tesis.tpv.dto.EmployeeCategoryDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.EmployeeCategory;

public class EmployeeCategoryBuilderTest {

	@Test
	public void test() {
		EmployeeCategory employeeCategory=new EmployeeCategory();
		employeeCategory.setId(2);
		employeeCategory.setName("mode");
		
		EmployeeCategoryDTO employeeCategoryDTO=TransferObjectAssembler.getInstance().assembleTO(EmployeeCategoryDTO.class, employeeCategory);
		System.out.println(employeeCategoryDTO);
	}

}
