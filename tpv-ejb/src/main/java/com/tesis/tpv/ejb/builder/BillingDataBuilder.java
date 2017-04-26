package com.tesis.tpv.ejb.builder;

import com.tesis.tpv.dto.BaseDTO;
import com.tesis.tpv.dto.BillingDataDTO;
import com.tesis.tpv.ejb.builder.config.AbstractDTOBuilder;
import com.tesis.tpv.ejb.builder.config.BuilderConfiguration;
import com.tesis.tpv.jpa.BaseEntity;
import com.tesis.tpv.jpa.BillingData;

@BuilderConfiguration(dtoClass=BillingDataDTO.class, entityClass=BillingData.class)
public class BillingDataBuilder extends AbstractDTOBuilder {

	@Override
	public BaseDTO createDTO(BaseEntity entity) {
		final BillingDataDTO billingDataDTO=new BillingDataDTO();
		final BillingData billingData=(BillingData)entity;
		
		billingDataDTO.setId(billingData.getId());
		billingDataDTO.setName(billingData.getName());
		billingDataDTO.setRfc(billingData.getRfc());
		billingDataDTO.setTelephone(billingData.getTelephone());
		billingDataDTO.setZipCode(billingData.getZipCode());
		billingDataDTO.setAddress(billingData.getAddress());
		return billingDataDTO;
	}

}
