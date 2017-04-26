package com.tesis.tpv.ejb.builder;

import com.tesis.tpv.dto.BaseDTO;
import com.tesis.tpv.dto.TypeDiscountDTO;
import com.tesis.tpv.ejb.builder.config.AbstractDTOBuilder;
import com.tesis.tpv.ejb.builder.config.BuilderConfiguration;
import com.tesis.tpv.jpa.BaseEntity;
import com.tesis.tpv.jpa.TypeDiscount;

@BuilderConfiguration(dtoClass=TypeDiscountDTO.class,entityClass=TypeDiscount.class)
public class TypeDiscountBuilder extends AbstractDTOBuilder{

	@Override
	public BaseDTO createDTO(BaseEntity entity) {
		final TypeDiscountDTO typeDiscDTO=new TypeDiscountDTO();
		final TypeDiscount typeDis=(TypeDiscount) entity;
		
		typeDiscDTO.setId(typeDis.getId());
		typeDiscDTO.setName(typeDis.getName());
		typeDiscDTO.setDescription(typeDis.getDescription());
		
		// TODO Auto-generated method stub
		return typeDiscDTO;
	}
	
}
