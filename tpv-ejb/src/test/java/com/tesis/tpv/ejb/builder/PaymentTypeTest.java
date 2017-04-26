package com.tesis.tpv.ejb.builder;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tesis.tpv.dto.PaymentTypeDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.PaymentType;

public class PaymentTypeTest {

	@Test
	public void test() {
		PaymentType paymentType=new PaymentType();
		
		paymentType.setId(3);
		paymentType.setName("Tipo de pago");
		
		PaymentTypeDTO paymentTypeDTO=TransferObjectAssembler.getInstance().getInstance().assembleTO(PaymentTypeDTO.class,paymentType);
		System.out.println(paymentTypeDTO);
	}

}
