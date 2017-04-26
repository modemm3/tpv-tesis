package com.tesis.tpv.endpoint;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.print.attribute.standard.Media;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.tesis.remote.DiscountRemote;
import com.tesis.tpv.dto.DepartmentDTO;
import com.tesis.tpv.dto.DiscountDTO;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.ResponseDTO;
import com.tesis.tpv.dto.RequestDTO;

@Path ("discountService")
public class DiscountService {
	@javax.ws.rs.core.Context
	private UriInfo context;
	
	@EJB (mappedName="DiscountBean")
	private DiscountRemote discount;
	
	private static final Logger log=LoggerFactory.getLogger(DiscountService.class);
	
	@Path ("/saveDiscount")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response saveDiscount(RequestDTO requestDTO){
		Gson gson=new Gson();
		Integer numberRecords;
		ResponseDTO<DiscountDTO> response=new ResponseDTO<DiscountDTO>();
		List<DiscountDTO> discountDTOLst=new ArrayList<DiscountDTO>();
		
		String dataObject=requestDTO.getDataTO();
		DiscountDTO discountDTO=gson.fromJson(dataObject, DiscountDTO.class);
		
		MessageResponseDTO messageResponseDTO=discount.save(discountDTO);
		response.setMessageResponseDTO(messageResponseDTO);
		if(messageResponseDTO.getCode()==1){
			numberRecords=discount.numberRecords();
			Integer modulo=numberRecords%requestDTO.getEndPage();
			Integer cociente=numberRecords/requestDTO.getEndPage();
			response.setPageEnd(requestDTO.getEndPage());
			if(modulo!=0){
				response.setNumberPage(cociente+1);
				response.setPageInit(cociente*requestDTO.getEndPage());
			}else{
				response.setNumberPage(cociente);
				response.setPageInit((cociente-1)*requestDTO.getEndPage());
			}
			
			discountDTOLst=discount.getPagination(response.getPageInit(),  response.getPageEnd());
			response.setNumberRecords(numberRecords);
			
			GenericEntity<List<DiscountDTO>> list=new GenericEntity<List<DiscountDTO>>(discountDTOLst){};
			String strGson=gson.toJson(list.getEntity());
			response.setDtoLst(strGson);
		}
		return Response.status(Status.OK).entity(response).build();
	}
	
	@Path ("/updateDiscount")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response updateDiscount(RequestDTO requestDTO){
		Gson gson=new Gson();
		String dataObject=requestDTO.getDataTO();
		ResponseDTO<DiscountDTO> response=new ResponseDTO<DiscountDTO>();
		DiscountDTO discountDTO=gson.fromJson(dataObject, DiscountDTO.class);
		
		MessageResponseDTO messageResponseDTO=discount.update(discountDTO);
		response.setMessageResponseDTO(messageResponseDTO);
		
		List<DiscountDTO> discountDTOLst=discount.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
		GenericEntity<List<DiscountDTO>> list=new GenericEntity<List<DiscountDTO>>(discountDTOLst){};
		response.setNumberRecords(discount.numberRecords());
		String strGson=gson.toJson(list.getEntity());
		response.setDtoLst(strGson);
		return Response.status(Status.OK).entity(response).build();
	}
	
	@Path ("/deleteDiscount")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public Response deleteDiscountId(RequestDTO requestDTO){
		String dataObject=requestDTO.getDataTO();
		Gson dataJson=new Gson();
		
		DiscountDTO discountDTO=dataJson.fromJson(dataObject, DiscountDTO.class);
		ResponseDTO<DiscountDTO> response=new ResponseDTO<DiscountDTO>();
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		try{
			messageResponseDTO=discount.delete(discountDTO.getId());
		}catch(Exception e){
			log.error("Error en DiscountService"+ e);
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("El dato no puede ser eliminado, verifique que no se este utilizando");
		}
		
		if(messageResponseDTO.getCode()==1){
			List<DiscountDTO> discountDTOLst=discount.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
			GenericEntity<List<DiscountDTO>> list=new GenericEntity<List<DiscountDTO>>(discountDTOLst){};
			response.setNumberRecords(discount.numberRecords());
			String strGson=dataJson.toJson(list.getEntity());
			response.setDtoLst(strGson);
			return Response.status(Status.OK).entity(response).build();
		}else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}
	
	@Path ("/getDiscountId/{idDiscount}")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public DiscountDTO getDiscountId(@PathParam("idDiscount")Integer idDiscount){
		DiscountDTO discountDTO=new DiscountDTO();
		discountDTO=discount.getDiscount(idDiscount);
		return discountDTO;
	}
	
	@Path ("/getDiscountLst")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public List<DiscountDTO> getDiscountLst(){
		List<DiscountDTO> discountLst=new ArrayList<DiscountDTO>();
		discountLst=discount.getListDiscount();
		return discountLst;
	}
	
	@Path("/pagination")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getPagination(RequestDTO requestDTO){
		Gson gson=new Gson();
		ResponseDTO<DiscountDTO> response=new ResponseDTO<DiscountDTO>();
		List<DiscountDTO> discountDTOLst=new ArrayList<DiscountDTO>();
		discountDTOLst=discount.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
		GenericEntity<List<DiscountDTO>> list=new GenericEntity<List<DiscountDTO>>(discountDTOLst){};
		response.setNumberRecords(discount.numberRecords());
		String strGson=gson.toJson(list.getEntity());
		response.setDtoLst(strGson);
		return Response.status(Status.OK).entity(response).build();
	}

}
