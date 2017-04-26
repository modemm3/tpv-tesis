package com.tesis.tpv.ejb.builder;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.tesis.tpv.dto.EmployeeDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Department;
import com.tesis.tpv.jpa.Employee;
import com.tesis.tpv.jpa.EmployeeCategory;

public class EmployeeTest {

	@Test
	public void test() {
		Employee employee=new Employee();
		Department department=new Department();
		EmployeeCategory employeeCategory=new EmployeeCategory();
		String fecha="12/03/2015";
		SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
		
		employee.setAddress("direccion de empleado");
		try {
			employee.setAdmissionDate(formato.parse(fecha));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		employee.setAge(23);
		employee.setCellPhone("3454");
		employee.setId(3);
		employee.setMaternalName("materna");
		employee.setName("name employee");
		employee.setPaternalName("paterno");
		employee.setTelephone("4344");
		department.setId(20);
		employee.setDepartment(department);
		employeeCategory.setId(30);
		employee.setEmployeeCategory(employeeCategory);
		
		EmployeeDTO employeeDTO=TransferObjectAssembler.getInstance().assembleTO(EmployeeDTO.class, employee);
		System.out.println(employeeDTO);
	}

}
