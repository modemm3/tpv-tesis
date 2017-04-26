package com.tesis.remote;

import java.util.List;

import javax.ejb.Remote;

import com.tesis.tpv.dto.ClientDTO;
import com.tesis.tpv.dto.MessageResponseDTO;

@Remote
public interface ClientRemote {
	
	public MessageResponseDTO save(ClientDTO clientDTO);
	public MessageResponseDTO update(ClientDTO clientDTO);
	public MessageResponseDTO delete(Integer idClient);
	public ClientDTO getClient(Integer idClient);
	public List<ClientDTO> getListClient();

}
