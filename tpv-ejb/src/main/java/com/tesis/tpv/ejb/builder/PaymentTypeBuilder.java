package com.tesis.tpv.ejb.builder;

import com.tesis.tpv.dto.BaseDTO;
import com.tesis.tpv.dto.PaymentTypeDTO;
import com.tesis.tpv.ejb.builder.config.AbstractDTOBuilder;
import com.tesis.tpv.ejb.builder.config.BuilderConfiguration;
import com.tesis.tpv.jpa.BaseEntity;
import com.tesis.tpv.jpa.PaymentType;

@BuilderConfiguration(dtoClass=PaymentTypeDTO.class, entityClass=PaymentType.class)
public class PaymentTypeBuilder extends AbstractDTOBuilder {

	@Override
	public BaseDTO createDTO(BaseEntity entity) {
		final PaymentTypeDTO paymentTypeDTO=new PaymentTypeDTO();
		final PaymentType paymentType=(PaymentType)entity;
		
		paymentTypeDTO.setId(paymentType.getId());
		paymentTypeDTO.setName(paymentType.getName());
		
		return paymentTypeDTO;
	}

}
