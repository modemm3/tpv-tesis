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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tesis.remote.ProviderProductRemote;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.ProviderProductDTO;

@Path ("/providerProductService")
public class ProviderProductService {
	@javax.ws.rs.core.Context
	private UriInfo context;
	
	private static final Logger LOG=LoggerFactory.getLogger(ProviderProductService.class);
	
	@EJB (mappedName="ProviderProductBean")
	private ProviderProductRemote providerProduct;
	
	@Path ("/save")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public MessageResponseDTO save(ProviderProductDTO providerProductDTO){
		MessageResponseDTO message=new MessageResponseDTO();
		System.out.println("Recibe");
		message=providerProduct.save(providerProductDTO);
		return message;
	}
	
	@Path ("/updateProviderProduct")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public MessageResponseDTO updateProviderProduct(ProviderProductDTO providerProductDTO){
		MessageResponseDTO message=new MessageResponseDTO();
		message=providerProduct.update(providerProductDTO);
		return message;
	}
	
	@Path ("/deleteProviderProduct/{idProviderProduct}")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public MessageResponseDTO deleteProviderProduct(@PathParam("idProviderProduct")Integer idProviderProduct){
		MessageResponseDTO message=new MessageResponseDTO();
		message=providerProduct.delete(idProviderProduct);
		return message;
	}
	
	@Path ("/getProviderProduct/{idProviderProduct}")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public ProviderProductDTO getProviderProduct(@PathParam("idProviderProduct")Integer idProviderProduct){
		ProviderProductDTO providerProductDTO=new ProviderProductDTO();
		providerProductDTO=providerProduct.getProviderProduct(idProviderProduct);
		return providerProductDTO;
	}
	
	@Path ("/getProviderProductLst")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public List<ProviderProductDTO> getProviderProductLst(){
		List<ProviderProductDTO> providerProductLst=new ArrayList<ProviderProductDTO>();
		
		providerProductLst=providerProduct.getListProviderProduct();
		return providerProductLst;
	}

}
