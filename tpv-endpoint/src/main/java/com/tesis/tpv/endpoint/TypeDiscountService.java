package com.tesis.tpv.endpoint;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import javax.ejb.EJB;
import com.tesis.tpv.dto.RequestDTO;
import com.tesis.tpv.dto.ResponseDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.tesis.remote.TypeDiscountRemote;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.TypeDiscountDTO;

@Path ("/typeDiscountService")
public class TypeDiscountService {
	@javax.ws.rs.core.Context
	private UriInfo context;
	@EJB (mappedName="TypeDiscountBean")
	TypeDiscountRemote typeDiscount;
	
	@Path ("/saveTypeDiscount")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response saveTypeDiscount(final RequestDTO requestDTO){
		Integer numberRecords;
		Gson gson=new Gson();
		ResponseDTO<TypeDiscountDTO> response=new ResponseDTO<TypeDiscountDTO>();
		String dataObject=requestDTO.getDataTO();
		TypeDiscountDTO typeDiscountDTO=gson.fromJson(dataObject, TypeDiscountDTO.class);
		List<TypeDiscountDTO> typeDiscountLst=new ArrayList<TypeDiscountDTO>();
		
		MessageResponseDTO messageResponseDTO=typeDiscount.save(typeDiscountDTO);
		response.setMessageResponseDTO(messageResponseDTO);
		
		if(messageResponseDTO.getCode()==1){
			numberRecords=typeDiscount.numberRecords();
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
			
			typeDiscountLst=typeDiscount.getPagination(response.getPageInit(), response.getPageEnd());
			response.setNumberRecords(numberRecords);
			
			GenericEntity<List<TypeDiscountDTO>> list=new GenericEntity<List<TypeDiscountDTO>>(typeDiscountLst){};
			String strGson=gson.toJson(list.getEntity());
			response.setDtoLst(strGson);
		}
		
		return Response.status(Status.OK).entity(response).build();
	}
	
	@Path ("/updateTypeDiscount")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response updateTypeDiscount(final RequestDTO requestDTO){
		Gson gson=new Gson();
		String dataObject=requestDTO.getDataTO();
		ResponseDTO<TypeDiscountDTO> response=new ResponseDTO<TypeDiscountDTO>();
		
		TypeDiscountDTO typeDiscountDTO=gson.fromJson(dataObject, TypeDiscountDTO.class);
		MessageResponseDTO messageResponseDTO=typeDiscount.update(typeDiscountDTO);
		response.setMessageResponseDTO(messageResponseDTO);
		
		List<TypeDiscountDTO> typeDiscountLst=typeDiscount.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
		GenericEntity<List<TypeDiscountDTO>> list=new GenericEntity<List<TypeDiscountDTO>>(typeDiscountLst){};
		response.setNumberRecords(typeDiscount.numberRecords());
		String strGson=gson.toJson(list.getEntity());
		response.setDtoLst(strGson);
		return Response.status(Status.OK).entity(response).build();
	}
	
	@Path ("/deleteTypeDiscount")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response deleteTypeDiscount(final RequestDTO requestDTO){
		String dataObject=requestDTO.getDataTO();
		Gson dataJson=new Gson();
		
		TypeDiscountDTO typeDiscountDTO=dataJson.fromJson(dataObject, TypeDiscountDTO.class);
		ResponseDTO<TypeDiscountDTO> response=new ResponseDTO<TypeDiscountDTO>();
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		try{
			messageResponseDTO=typeDiscount.delete(typeDiscountDTO.getId());
		}catch(Exception e){
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("El dato no puede ser eliminado, verifique que no se este utilizando");
		}
		response.setMessageResponseDTO(messageResponseDTO);
		
		if(messageResponseDTO.getCode()==1){
			List<TypeDiscountDTO> typeDiscountLst=typeDiscount.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
			GenericEntity<List<TypeDiscountDTO>> list=new GenericEntity<List<TypeDiscountDTO>>(typeDiscountLst){};
			response.setNumberRecords(typeDiscount.numberRecords());
			String strGson=dataJson.toJson(list.getEntity());
			response.setDtoLst(strGson);
			return Response.status(Status.OK).entity(response).build();
		}else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}
	
	@Path ("/getTypeDiscount/{idTypeDiscount}")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public TypeDiscountDTO getTypeDiscount(@PathParam("idTypeDiscount")final Integer idTypeDiscount){
		TypeDiscountDTO typeDiscountDTO=new TypeDiscountDTO();
		typeDiscountDTO=typeDiscount.getTypeDiscount(idTypeDiscount);
		return typeDiscountDTO;
	}
	
	@Path ("/getListTypeDiscount")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public List<TypeDiscountDTO> getListTypeDiscount(){
		List<TypeDiscountDTO> typeDiscountLst=new ArrayList<TypeDiscountDTO>();
		typeDiscountLst=typeDiscount.getListTypeDiscount();
		return typeDiscountLst;
	}
	
	@Path ("/pagination")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response getPagination(final RequestDTO requestDTO){
		Gson gson=new Gson();
		ResponseDTO<TypeDiscountDTO> response=new ResponseDTO<TypeDiscountDTO>();
		List<TypeDiscountDTO> typeDiscountDTOLst=new ArrayList<TypeDiscountDTO>();
		typeDiscountDTOLst=typeDiscount.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
		GenericEntity<List<TypeDiscountDTO>> list=new GenericEntity<List<TypeDiscountDTO>>(typeDiscountDTOLst){};
		response.setNumberRecords(typeDiscount.numberRecords());
		String strGson=gson.toJson(list.getEntity());
		response.setDtoLst(strGson);
		return Response.status(Status.OK).entity(response).build();
	}
}
