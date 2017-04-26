package com.tesis.remote;

import java.util.List;

import javax.ejb.Remote;

import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.ProviderDTO;

@Remote
public interface ProviderRemote {
	public MessageResponseDTO save(ProviderDTO providerDTO);
	public MessageResponseDTO update(ProviderDTO providerDTO);
	public MessageResponseDTO delete(Integer idProvider);
	public ProviderDTO getProvider(Integer idProvider);
	public List<ProviderDTO> getListProvider();

}
