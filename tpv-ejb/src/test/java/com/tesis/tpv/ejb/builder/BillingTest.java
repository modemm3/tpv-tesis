package com.tesis.tpv.ejb.builder;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import org.junit.Test;

import com.tesis.tpv.dto.BillingDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Billing;
import com.tesis.tpv.jpa.BillingData;
import com.tesis.tpv.jpa.Department;
import com.tesis.tpv.jpa.Employee;
import com.tesis.tpv.jpa.EmployeeCategory;
import com.tesis.tpv.jpa.PaymentType;
import com.tesis.tpv.jpa.Sale;

public class BillingTest {

	@Test
	public void test() {
		Billing billing=new Billing();
		Sale sale=new Sale();
		BillingData billingData=new BillingData();
		SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
		Employee employee=new Employee();
		Department department=new Department();
		PaymentType paymentType=new PaymentType();
		EmployeeCategory employeeCategory=new EmployeeCategory();
		
		billing.setId(24);
		sale.setId(20);
		employee.setId(94);
		department.setId(99);
		employee.setDepartment(department);
		employeeCategory.setId(2222);
		employee.setEmployeeCategory(employeeCategory);
		paymentType.setId(16);
		sale.setEmployee(employee);
		sale.setPaymentType(paymentType);
		paymentType.setId(43);
		billing.setSale(sale);
		billing.setSubtotal(BigDecimal.valueOf(2345.24));
		billing.setTotal(BigDecimal.valueOf(3490.12));
		try {
			billing.setBillingDate(formato.parse("12/05/2020"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		billingData.setId(40);
		billing.setBillingData(billingData);
	
		
		BillingDTO billingDTO=TransferObjectAssembler.getInstance().assembleTO(BillingDTO.class, billing);
		System.out.println(billingDTO);
	}

}
