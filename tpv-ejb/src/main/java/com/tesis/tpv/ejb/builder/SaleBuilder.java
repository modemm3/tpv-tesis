 package com.tesis.tpv.ejb.builder;

import com.tesis.tpv.dto.BaseDTO;
import com.tesis.tpv.dto.EmployeeDTO;
import com.tesis.tpv.dto.PaymentTypeDTO;
import com.tesis.tpv.dto.SaleDTO;
import com.tesis.tpv.ejb.builder.config.AbstractDTOBuilder;
import com.tesis.tpv.ejb.builder.config.BuilderConfiguration;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.BaseEntity;
import com.tesis.tpv.jpa.Sale;

@BuilderConfiguration(dtoClass=SaleDTO.class, entityClass=Sale.class)
public class SaleBuilder extends AbstractDTOBuilder {

	@Override
	public BaseDTO createDTO(BaseEntity entity) {
		final SaleDTO saleDTO=new SaleDTO();
		final Sale sale=(Sale)entity;
		
		saleDTO.setId(sale.getId());
		saleDTO.setAmount(sale.getAmount());
		saleDTO.setDiscount(sale.getDiscount());
		saleDTO.setEmployeeId(TransferObjectAssembler.getInstance().assembleTO(EmployeeDTO.class, sale.getEmployee()).getId());
		saleDTO.setPaymentTypeId(TransferObjectAssembler.getInstance().assembleTO(PaymentTypeDTO.class, sale.getPaymentType()).getId());
		return saleDTO;
	}

}
