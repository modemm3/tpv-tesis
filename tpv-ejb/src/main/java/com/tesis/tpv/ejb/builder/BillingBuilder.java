package com.tesis.tpv.ejb.builder;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.tesis.tpv.dto.BaseDTO;
import com.tesis.tpv.dto.BillingDTO;
import com.tesis.tpv.dto.BillingDataDTO;
import com.tesis.tpv.dto.SaleDTO;
import com.tesis.tpv.ejb.builder.config.AbstractDTOBuilder;
import com.tesis.tpv.ejb.builder.config.BuilderConfiguration;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.BaseEntity;
import com.tesis.tpv.jpa.Billing;

@BuilderConfiguration(dtoClass=BillingDTO.class, entityClass=Billing.class)
public class BillingBuilder extends AbstractDTOBuilder {

	@Override
	public BaseDTO createDTO(BaseEntity entity) {
		final BillingDTO billingDTO=new BillingDTO();
		final Billing billing=(Billing)entity;
		
		billingDTO.setId(billing.getId());
		billingDTO.setSaleId(TransferObjectAssembler.getInstance().assembleTO(SaleDTO.class, billing.getSale()).getId());
		billingDTO.setSubtotal(billing.getSubtotal());
		billingDTO.setTotal(billing.getTotal());
		billingDTO.setBillingDataId(TransferObjectAssembler.getInstance().assembleTO(BillingDataDTO.class,billing.getBillingData()).getId());
		billingDTO.setBillingDate(billing.getBillingDate());
		
		return billingDTO;
	}

}
