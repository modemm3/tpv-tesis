package com.tesis.tpv.ejb.builder;

import com.tesis.tpv.dto.BaseDTO;
import com.tesis.tpv.dto.ProviderDTO;
import com.tesis.tpv.ejb.builder.config.AbstractDTOBuilder;
import com.tesis.tpv.ejb.builder.config.BuilderConfiguration;
import com.tesis.tpv.jpa.BaseEntity;
import com.tesis.tpv.jpa.Provider;

@BuilderConfiguration(dtoClass=ProviderDTO.class, entityClass=Provider.class)
public class ProviderBuilder extends AbstractDTOBuilder {

	@Override
	public BaseDTO createDTO(BaseEntity entity) {
		final ProviderDTO providerDTO=new ProviderDTO();
		final Provider provider=(Provider)entity;
		
		providerDTO.setId(provider.getId());
		providerDTO.setMaternalName(provider.getMaternalName());
		providerDTO.setName(provider.getName());
		providerDTO.setAddress(provider.getAddress());
		providerDTO.setBusinessName(provider.getBusinessName());
		providerDTO.setPaternalName(provider.getPaternalName());
		providerDTO.setRfc(provider.getRfc());
		providerDTO.setTelephone(provider.getTelephone());
		providerDTO.setWebPage(provider.getWebPage());
		
		return providerDTO;
	}

}
