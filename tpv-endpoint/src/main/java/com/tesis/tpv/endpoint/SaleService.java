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

import com.tesis.remote.SaleRemote;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.SaleDTO;

@Path("saleService")
public class SaleService {
	@javax.ws.rs.core.Context
	private UriInfo context;
	@EJB (mappedName="SaleBean")
	private SaleRemote sale;
	
	@Path("/saveSale")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public MessageResponseDTO saveSale(SaleDTO saleDTO){
		MessageResponseDTO messageDTO=new MessageResponseDTO();
		messageDTO=sale.save(saleDTO);
		return messageDTO;
	}
	
	@Path("/updateSale")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public MessageResponseDTO updateSale(SaleDTO saleDTO){
		MessageResponseDTO messageDTO=new MessageResponseDTO();
		messageDTO=sale.update(saleDTO);
		return messageDTO;
	}
	
	@Path("/deleteSale/{idSale}")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public MessageResponseDTO deleteSale(@PathParam("idSale") Integer idSale){
		MessageResponseDTO messageDTO=new MessageResponseDTO();
		messageDTO=sale.delete(idSale);
		return messageDTO;
	}
	
	@Path ("/getSale/{idSale}")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public SaleDTO getSale(@PathParam("idSale")Integer idSale){
		SaleDTO saleDTO=new SaleDTO();
		saleDTO=sale.getSale(idSale);
		return saleDTO;
	}

	@Path ("/getListSale")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public List<SaleDTO> getListSale(){
		List<SaleDTO> saleLst=new ArrayList<SaleDTO>();
		saleLst=sale.getListSale();
		return saleLst;
	}
}
