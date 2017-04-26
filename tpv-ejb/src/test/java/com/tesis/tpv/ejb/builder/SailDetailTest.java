package com.tesis.tpv.ejb.builder;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.tesis.tpv.dto.SailDetailDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Category;
import com.tesis.tpv.jpa.Department;
import com.tesis.tpv.jpa.Employee;
import com.tesis.tpv.jpa.EmployeeCategory;
import com.tesis.tpv.jpa.PaymentType;
import com.tesis.tpv.jpa.Product;
import com.tesis.tpv.jpa.SailDetail;
import com.tesis.tpv.jpa.Sale;

public class SailDetailTest {

	@Test
	public void test() {
		SailDetail sailDetail=new SailDetail();
		Product product=new Product();
		Sale sale=new Sale();
		Department department=new Department();
		Category category=new Category();
		PaymentType paymentType=new PaymentType();
		Employee employee=new Employee();
		Department departmentUNO=new Department();
		EmployeeCategory employeeCategory=new EmployeeCategory();

		
		sailDetail.setId(9);
		product.setId(1);
		department.setId(2);
		category.setId(12);
		product.setDepartment(department);
		product.setCategory(category);
		sailDetail.setProduct(product);
		sailDetail.setQuantity(BigDecimal.valueOf(5679.5690));
		sale.setId(14);
		paymentType.setId(12);
		employee.setId(23);
		departmentUNO.setId(23);
		employeeCategory.setId(23);
		employee.setDepartment(departmentUNO);
		employee.setEmployeeCategory(employeeCategory);
		sale.setEmployee(employee);
		sale.setPaymentType(paymentType);
		sailDetail.setSale(sale);
		
		SailDetailDTO sailDetailDTO=TransferObjectAssembler.getInstance().assembleTO(SailDetailDTO.class, sailDetail);
		System.out.println(sailDetailDTO);
	}

}
