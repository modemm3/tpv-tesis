package com.tesis.tpv.ejb.builder;

import java.math.BigDecimal;
import org.junit.Test;
import com.tesis.tpv.dto.SaleDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Department;
import com.tesis.tpv.jpa.Employee;
import com.tesis.tpv.jpa.EmployeeCategory;
import com.tesis.tpv.jpa.PaymentType;
import com.tesis.tpv.jpa.Sale;

public class SaleTest {

	@Test
	public void test() {
		Sale sale=new Sale();
		Employee employee=new Employee();
		PaymentType paymentType=new PaymentType();
		Department department=new Department();
		EmployeeCategory employeeCategory=new EmployeeCategory();
		
		sale.setId(34);
		sale.setAmount(BigDecimal.valueOf(234.65));
		sale.setDiscount(BigDecimal.valueOf(245.567));
		employee.setId(50);
		department.setId(20);
		employee.setDepartment(department);
		employeeCategory.setId(30);
		employee.setEmployeeCategory(employeeCategory);
		sale.setEmployee(employee);
		
		paymentType.setId(14);
		sale.setPaymentType(paymentType);
		
		
		SaleDTO saleDTO=TransferObjectAssembler.getInstance().assembleTO(SaleDTO.class, sale);
		System.out.print(saleDTO);
	}
}
