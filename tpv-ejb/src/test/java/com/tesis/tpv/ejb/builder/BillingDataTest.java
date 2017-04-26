package com.tesis.tpv.ejb.builder;

import org.junit.Test;
import com.tesis.tpv.dto.BillingDataDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.BillingData;

public class BillingDataTest {

	@Test
	public void test() {
		BillingData billingData=new BillingData();
		billingData.setId(4);
		billingData.setName("Billing Data");
		billingData.setRfc("rfc uno dos");
		billingData.setTelephone("456434");
		billingData.setZipCode("zip uno dos");
		billingData.setAddress("calle uno");
		
		BillingDataDTO billingDataDTO=TransferObjectAssembler.getInstance().assembleTO(BillingDataDTO.class, billingData);
		System.out.println(billingDataDTO);
	}

}
