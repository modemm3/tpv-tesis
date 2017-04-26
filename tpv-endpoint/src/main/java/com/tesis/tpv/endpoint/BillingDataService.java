package com.tesis.tpv.endpoint;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.tesis.remote.BillingDataRemote;
import com.tesis.tpv.dto.BillingDataDTO;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.RequestDTO;
import com.tesis.tpv.dto.ResponseDTO;

@Path("/billingDataService")
public class BillingDataService {
	@javax.ws.rs.core.Context
	private UriInfo context;
	@EJB (mappedName="BillingDataBean")
	private BillingDataRemote billingData;
	
	private static final Logger log=LoggerFactory.getLogger(BillingDataService.class);
	
	@Path ("/saveBillingData")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response saveBillingData(final RequestDTO requestDTO){
		Integer numberRecords;
		Gson gson=new Gson();
		ResponseDTO<BillingDataDTO> response=new ResponseDTO<BillingDataDTO>();
		List<BillingDataDTO> billingDataDTOLst=new ArrayList<BillingDataDTO>();
		
		String dataObject=requestDTO.getDataTO();
		BillingDataDTO billingDataDTO=gson.fromJson(dataObject, BillingDataDTO.class);
		
		MessageResponseDTO messageResponseDTO=billingData.save(billingDataDTO);
		response.setMessageResponseDTO(messageResponseDTO);
		
		if(messageResponseDTO.getCode()==1){
			numberRecords=billingData.numberRecords();
			Integer modulo=numberRecords%requestDTO.getEndPage();
			Integer cociente=(Integer)numberRecords/requestDTO.getEndPage();
			response.setPageEnd(requestDTO.getEndPage());
			
			if(modulo!=0){
				response.setNumberPage(cociente+1);
				response.setPageInit(cociente*requestDTO.getEndPage());
			}else{
				response.setNumberPage(cociente);
				response.setPageInit((cociente-1)*requestDTO.getEndPage());
			}
			
			billingDataDTOLst=billingData.getPagination(response.getPageInit(), response.getPageEnd());
			response.setNumberRecords(numberRecords);
			
			GenericEntity<List<BillingDataDTO>> list=new GenericEntity<List<BillingDataDTO>>(billingDataDTOLst){};
			response.setNumberRecords(numberRecords);
			String strGson=gson.toJson(list.getEntity());
			response.setDtoLst(strGson);
		}
		return Response.status(Status.OK).entity(response).build();
	}
	
	@Path ("/updateBillingData")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response updateBillingData(final RequestDTO requestDTO){
		Gson gson=new Gson();
		String dataObject=requestDTO.getDataTO();
		ResponseDTO<BillingDataDTO> response=new ResponseDTO<BillingDataDTO>();
		
		BillingDataDTO billingDataDTO=gson.fromJson(dataObject, BillingDataDTO.class);
		MessageResponseDTO messageResponseDTO=billingData.update(billingDataDTO);
		response.setMessageResponseDTO(messageResponseDTO);
		
		List<BillingDataDTO> billingDataDTOLst=billingData.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
		GenericEntity<List<BillingDataDTO>> list=new GenericEntity<List<BillingDataDTO>>(billingDataDTOLst){};
		response.setNumberRecords(billingData.numberRecords());
		String strGson=gson.toJson(list.getEntity());
		response.setDtoLst(strGson);
		
		return Response.status(Status.OK).entity(response).build();
	}
	
	@Path ("/deleteBillingData")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response deleteBillingData(final RequestDTO requestDTO){
		String dataObject=requestDTO.getDataTO();
		Gson gson=new Gson();
		
		BillingDataDTO billingDataDTO=gson.fromJson(dataObject, BillingDataDTO.class);
		ResponseDTO<BillingDataDTO> response=new ResponseDTO<BillingDataDTO>();
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		
		try{
			messageResponseDTO=billingData.delete(billingDataDTO.getId());
		}catch(Exception e){
			log.error("error en BillingDataService"+ e);
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("El dato no puede ser eliminado, verifique que no se este utilizando");
		}
		response.setMessageResponseDTO(messageResponseDTO);
		
		if(messageResponseDTO.getCode()==1){
			List<BillingDataDTO> billingDataDTOLst=billingData.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
			GenericEntity<List<BillingDataDTO>> list=new GenericEntity<List<BillingDataDTO>>(billingDataDTOLst){};
			response.setNumberRecords(billingData.numberRecords());
			String strGson=gson.toJson(list.getEntity());
			response.setDtoLst(strGson);
			return Response.status(Status.OK).entity(response).build();
		}else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}
	
	@Path ("/getBillingData/{idBillingData}")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public BillingDataDTO getBillingData(@PathParam("idBillingData")final Integer idBillingData){
		BillingDataDTO billingDataDTO=new BillingDataDTO();
		billingDataDTO=billingData.getBillingData(idBillingData);
		return billingDataDTO;
	}
	
	@Path("/getListBillingData")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public List<BillingDataDTO> getListBillingData(){
		List<BillingDataDTO> billingDataDTOLst=new ArrayList<BillingDataDTO>();
		billingDataDTOLst=billingData.getListBillingData();
		return billingDataDTOLst;
	}
	
	@Path("/pagination")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response getPagination(final RequestDTO requestDTO){
		Gson gson=new Gson();
		ResponseDTO<BillingDataDTO> response=new ResponseDTO<BillingDataDTO>();
		List<BillingDataDTO> billingDataDTOLst=new ArrayList<BillingDataDTO>();
		
		billingDataDTOLst=billingData.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
		
		GenericEntity<List<BillingDataDTO>> list=new GenericEntity<List<BillingDataDTO>>(billingDataDTOLst){};
		response.setNumberRecords(billingData.numberRecords());
		String strGson=gson.toJson(list.getEntity());
		response.setDtoLst(strGson);
		return Response.status(Status.OK).entity(response).build();
	}
}
