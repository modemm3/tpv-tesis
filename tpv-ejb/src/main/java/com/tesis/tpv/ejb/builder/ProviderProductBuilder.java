package com.tesis.tpv.ejb.builder;

import com.tesis.tpv.dto.BaseDTO;
import com.tesis.tpv.dto.ProductDTO;
import com.tesis.tpv.dto.ProviderDTO;
import com.tesis.tpv.dto.ProviderProductDTO;
import com.tesis.tpv.ejb.builder.config.AbstractDTOBuilder;
import com.tesis.tpv.ejb.builder.config.BuilderConfiguration;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.BaseEntity;
import com.tesis.tpv.jpa.ProviderProduct;

@BuilderConfiguration(dtoClass=ProviderProductDTO.class, entityClass=ProviderProduct.class)
public class ProviderProductBuilder extends AbstractDTOBuilder {

	@Override
	public BaseDTO createDTO(BaseEntity entity) {
		final ProviderProductDTO providerProductDTO=new ProviderProductDTO();
		final ProviderProduct providerProduct=(ProviderProduct)entity;
		
		providerProductDTO.setId(providerProduct.getId());
		providerProductDTO.setBuyDate(providerProduct.getBuyDate());
		providerProductDTO.setPrice(providerProduct.getPrice());
		providerProductDTO.setProductId(TransferObjectAssembler.getInstance().assembleTO(ProductDTO.class,providerProduct.getProduct()).getId());
		providerProductDTO.setProviderId(TransferObjectAssembler.getInstance().assembleTO(ProviderDTO.class, providerProduct.getProvider()).getId());
		return providerProductDTO;
	}

}
