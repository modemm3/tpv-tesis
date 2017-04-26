package com.tesis.tpv.ejb.builder;

import com.tesis.tpv.dto.BaseDTO;
import com.tesis.tpv.dto.DiscountDTO;
import com.tesis.tpv.dto.ProductDTO;
import com.tesis.tpv.dto.TypeDiscountDTO;
import com.tesis.tpv.ejb.builder.config.AbstractDTOBuilder;
import com.tesis.tpv.ejb.builder.config.BuilderConfiguration;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.BaseEntity;
import com.tesis.tpv.jpa.Discount;

@BuilderConfiguration(dtoClass=DiscountDTO.class, entityClass=Discount.class)
public class DiscountBuilder extends AbstractDTOBuilder {

	@Override
	public BaseDTO createDTO(BaseEntity entity) {
		final DiscountDTO discountDTO= new DiscountDTO();
		final Discount discount= (Discount) entity;
		
		discountDTO.setId(discount.getId());
		discountDTO.setName(discount.getName());
		discountDTO.setStartDate(discount.getStartDate());
		discountDTO.setAmount(discount.getAmount());
		discountDTO.setEndDate(discount.getEndDate());
		discountDTO.setStatus(discount.getStatus());
		discountDTO.setProductDTO(TransferObjectAssembler.getInstance().assembleTO(ProductDTO.class, discount.getProduct()));
		discountDTO.setTypeDiscountDTO(TransferObjectAssembler.getInstance().assembleTO(TypeDiscountDTO.class, discount.getTypeDiscount()));
		
		return discountDTO;
	}

}
