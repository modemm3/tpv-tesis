package com.tesis.remote;

import java.util.List;

import javax.ejb.Remote;

import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.ProviderProductDTO;

@Remote
public interface ProviderProductRemote {
	
	public MessageResponseDTO save(ProviderProductDTO providerProductDTO);
	public MessageResponseDTO update(ProviderProductDTO providerProductDTO);
	public MessageResponseDTO delete(Integer idProviderProduct);
	public ProviderProductDTO getProviderProduct(Integer idProviderProduct);
	public List<ProviderProductDTO> getListProviderProduct();

}
