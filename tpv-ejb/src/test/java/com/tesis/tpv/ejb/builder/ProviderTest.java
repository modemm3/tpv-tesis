package com.tesis.tpv.ejb.builder;

import org.junit.Test;
import com.tesis.tpv.dto.ProviderDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Provider;

public class ProviderTest {

	@Test
	public void test() {
		Provider provider= new Provider();
		
		provider.setId(1);
		provider.setAddress("direccion de Provider");
		provider.setBusinessName("Business Provider");
		provider.setMaternalName("Maternal Provider");
		provider.setName("name Provider");
		provider.setPaternalName("paternal Provider");
		provider.setRfc("rfc provider");
		provider.setTelephone("telefono Provider");
		provider.setWebPage("webpage provider");
		
		ProviderDTO providerDTO=TransferObjectAssembler.getInstance().assembleTO(ProviderDTO.class, provider);
		System.out.println(providerDTO);
	}

}
