package com.tesis.tpv.ejb.builder;

import com.tesis.tpv.dto.BaseDTO;
import com.tesis.tpv.dto.ProductDTO;
import com.tesis.tpv.dto.SailDetailDTO;
import com.tesis.tpv.dto.SaleDTO;
import com.tesis.tpv.ejb.builder.config.AbstractDTOBuilder;
import com.tesis.tpv.ejb.builder.config.BuilderConfiguration;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.BaseEntity;
import com.tesis.tpv.jpa.SailDetail;

@BuilderConfiguration(dtoClass=SailDetailDTO.class, entityClass=SailDetail.class)
public class SailDetailBuilder extends AbstractDTOBuilder {

	@Override
	public BaseDTO createDTO(BaseEntity entity) {
		final SailDetailDTO sailDetailDTO=new SailDetailDTO();
		final SailDetail sailDetail=(SailDetail)entity;
		
		sailDetailDTO.setId(sailDetail.getId());
		sailDetailDTO.setProductId(TransferObjectAssembler.getInstance().assembleTO(ProductDTO.class, sailDetail.getProduct()).getId());
		sailDetailDTO.setQuantity(sailDetail.getQuantity());
		sailDetailDTO.setSaleId(TransferObjectAssembler.getInstance().assembleTO(SaleDTO.class, sailDetail.getSale()).getId());
		
		return sailDetailDTO;
	}

}
