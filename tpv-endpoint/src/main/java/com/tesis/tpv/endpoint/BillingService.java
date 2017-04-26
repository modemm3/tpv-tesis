package com.tesis.tpv.endpoint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.tesis.remote.BillingRemote;
import com.tesis.tpv.dto.BillingDTO;
import com.tesis.tpv.dto.MessageResponseDTO;

@Path ("billingService")
public class BillingService {
	@javax.ws.rs.core.Context
	private UriInfo context;
	@EJB (mappedName="BillingBean")
	private BillingRemote billing;
	
	@Path("/saveBilling")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public MessageResponseDTO saveBilling(BillingDTO billingDTO){
		System.out.println("Fecha del servicio billing: "+ billingDTO.getBillingDate());
		MessageResponseDTO message=new MessageResponseDTO();
		message=billing.save(billingDTO);
		return message;
	}
	
	@Path ("/updateBilling")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public MessageResponseDTO updateBilling(BillingDTO billingDTO){
		MessageResponseDTO message=new MessageResponseDTO();
		message=billing.update(billingDTO);
		return message;
	}
	
	@Path ("/deleteBilling/{idBilling}")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public MessageResponseDTO deleteBilling(@PathParam("idBilling")Integer idBilling){
		MessageResponseDTO message=new MessageResponseDTO();
		message=billing.delete(idBilling);
		return message;
	}
	
	@Path ("/getBilling/{idBilling}")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public BillingDTO getBilling(@PathParam("idBilling")Integer idBilling){
		BillingDTO billing=new BillingDTO();
		billing=this.billing.getBilling(idBilling);
		return billing;
	}
	
	@Path ("/getBillingLst")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public List<BillingDTO> getBillingLst(){
		List<BillingDTO> billingLst=new ArrayList<BillingDTO>();
		billingLst=billing.getListBilling();
		return billingLst;
	}

	@Path ("/date")
	@POST
	@Produces (MediaType.TEXT_PLAIN)
	public String date(Date actual){
		System.out.println("Date actual: "+actual);
		return "exito";
	}
}
