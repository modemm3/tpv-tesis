package com.tesis.tpv.ejb.builder;

import java.math.BigDecimal;
import java.util.Calendar;

import org.junit.Test;

import com.tesis.tpv.jpa.Discount;

public class DiscountBuilderTest {

	@Test
	public void test() {
		Discount discount= new Discount();
	
		Calendar d=Calendar.getInstance();
		d.set(2015, 7, 1);
		
		discount.setId(1);
		discount.setName("Verano");
		discount.setStartDate(d.getTime());
		discount.setEndDate(d.getTime());
		discount.setStatus(true);
		discount.setAmount(BigDecimal.valueOf(145.678));
		
	}

}
