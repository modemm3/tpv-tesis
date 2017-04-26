package com.tesis.tpv.ejb.builder;
import org.junit.Test;

import com.tesis.tpv.dto.TypeDiscountDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.TypeDiscount;

public class TypeDiscountBuilderTest {

	@Test
	public void test() {
		TypeDiscount typeDiscount=new TypeDiscount();
		typeDiscount.setId(1);
		typeDiscount.setName("melina");
		typeDiscount.setDescription("Es Buena onda");
		
		TypeDiscountDTO typeDiscountDTO=TransferObjectAssembler.getInstance().assembleTO(TypeDiscountDTO.class, typeDiscount);
		System.out.println(typeDiscountDTO);
	}

}
