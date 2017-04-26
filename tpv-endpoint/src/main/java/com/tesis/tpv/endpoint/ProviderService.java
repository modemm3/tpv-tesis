package com.tesis.tpv.endpoint;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.tesis.remote.ProviderRemote;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.ProviderDTO;

@Path("providerService")
public class ProviderService {
	@javax.ws.rs.core.Context
	private UriInfo context;
	@EJB(mappedName="ProviderBean")
	private ProviderRemote provider;
	
	@Path ("/saveProvider")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public MessageResponseDTO saveProvider(ProviderDTO providerDTO){
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		messageResponseDTO=provider.save(providerDTO);
		return messageResponseDTO;
	}
	
	@Path ("/updateProvider")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public MessageResponseDTO updateProvider(ProviderDTO providerDTO){
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		messageResponseDTO=provider.update(providerDTO);
		return messageResponseDTO;
	}
	
	@Path ("/deleteProvider/{idProvider}")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public MessageResponseDTO deleteProvider(@PathParam("idProvider")Integer idProvider){
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		messageResponseDTO=provider.delete(idProvider);
		return messageResponseDTO;
	}
	
	@Path ("/getProvider/{idProvider}")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public ProviderDTO getProvider(@PathParam("idProvider")Integer idProvider){
		ProviderDTO providerDTO=new ProviderDTO();
		providerDTO=provider.getProvider(idProvider);
		return providerDTO;
	}
	
	@Path ("/getListProvider")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public List<ProviderDTO> getListProvider(){
		List<ProviderDTO> providerLst=new ArrayList<ProviderDTO>();
		providerLst=provider.getListProvider();
		return providerLst;
	}
}
