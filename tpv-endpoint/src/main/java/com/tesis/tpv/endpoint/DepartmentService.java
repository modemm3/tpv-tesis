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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;
import com.google.gson.Gson;
import com.tesis.remote.DepartmentRemote;
import com.tesis.tpv.dto.DepartmentDTO;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.RequestDTO;
import com.tesis.tpv.dto.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path ("/departmentService")
public class DepartmentService {
	@javax.ws.rs.core.Context
	private UriInfo context;
	
	@EJB (mappedName="DepartmentBean")
	DepartmentRemote department;
	
	private static final Logger log=LoggerFactory.getLogger(DepartmentService.class);
	
	@Path ("/saveDepartment")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response saveDepartment(RequestDTO requestDTO){
		Gson gson=new Gson();
		Integer numberRecords;
		ResponseDTO<DepartmentDTO> response=new ResponseDTO<DepartmentDTO>();
		List<DepartmentDTO> departmentDTOLst=new ArrayList<DepartmentDTO>();
		
		String dataObject=requestDTO.getDataTO();
		DepartmentDTO departmentDTO=gson.fromJson(dataObject,DepartmentDTO.class);
		
		MessageResponseDTO messageResponseDTO=department.save(departmentDTO);
		response.setMessageResponseDTO(messageResponseDTO);
		if(messageResponseDTO.getCode()==1){
			numberRecords=department.numberRecords();
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
			
			departmentDTOLst=department.getPagination(response.getPageInit(), response.getPageEnd());
			response.setNumberRecords(numberRecords);
			
			GenericEntity<List<DepartmentDTO>> list=new GenericEntity<List<DepartmentDTO>>(departmentDTOLst){};
			String strGson=gson.toJson(list.getEntity());
			response.setDtoLst(strGson);
		}
		return Response.status(Status.OK).entity(response).build();
	}
	
	@Path ("/updateDepartment")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response updateDepartment(RequestDTO requestDTO){
		Gson gson=new Gson();
		String dataObject=requestDTO.getDataTO();
		ResponseDTO<DepartmentDTO> response=new ResponseDTO<DepartmentDTO>();
		
		DepartmentDTO departmentDTO=gson.fromJson(dataObject, DepartmentDTO.class);
		MessageResponseDTO messageResponseDTO=department.update(departmentDTO);
		response.setMessageResponseDTO(messageResponseDTO);
		
		List<DepartmentDTO> departmentDTOLst=department.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
		GenericEntity<List<DepartmentDTO>> list=new GenericEntity<List<DepartmentDTO>>(departmentDTOLst){};
		response.setNumberRecords(department.numberRecords());
		String strGson=gson.toJson(list.getEntity());
		response.setDtoLst(strGson);
		return Response.status(Status.OK).entity(response).build();
	}
	
	@Path ("/deleteDepartment")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response deleteDepartment(RequestDTO requestDTO){
		String dataObject=requestDTO.getDataTO();
		Gson dataJson=new Gson();
		
		DepartmentDTO departmentDTO=dataJson.fromJson(dataObject, DepartmentDTO.class);
		ResponseDTO<DepartmentDTO> response=new ResponseDTO<DepartmentDTO>();
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		try{
			messageResponseDTO=department.delete(departmentDTO.getId());
		}catch(Exception e){
			log.error("Error en DepartmentService"+ e);
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("El dato no puede ser eliminado, verifique que no se este utilizando");
		}
		response.setMessageResponseDTO(messageResponseDTO);
		
		if(messageResponseDTO.getCode()==1){
			List<DepartmentDTO> departmentDTOLst=department.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
			GenericEntity<List<DepartmentDTO>> list=new GenericEntity<List<DepartmentDTO>>(departmentDTOLst){};
			response.setNumberRecords(department.numberRecords());
			String strGson=dataJson.toJson(list.getEntity());
			response.setDtoLst(strGson);
			return Response.status(Status.OK).entity(response).build();
		}else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}
	
	@Path ("/getDepartment/{idDepartment}")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public DepartmentDTO getDepartment(@PathParam("idDepartment")Integer idDepartment){
		DepartmentDTO departmentDTO=new DepartmentDTO();
		departmentDTO=department.getDepartment(idDepartment);
		return departmentDTO;
	}
	
	@Path("getListDepartment")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public List<DepartmentDTO> getListDepartment(){
		List<DepartmentDTO> departmentLst=new ArrayList<DepartmentDTO>();
		departmentLst=department.getListDepartment();
		return departmentLst;
	}
	
	@Path ("/pagination")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response getPagination(RequestDTO requestDTO){
		Gson gson=new Gson();
		ResponseDTO<DepartmentDTO> response= new ResponseDTO<DepartmentDTO>();
		List<DepartmentDTO> departmentDTOLst=new ArrayList<DepartmentDTO>();
		departmentDTOLst=department.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
		GenericEntity<List<DepartmentDTO>> list=new GenericEntity<List<DepartmentDTO>>(departmentDTOLst){};
		response.setNumberRecords(department.numberRecords());
		String strGson=gson.toJson(list.getEntity());
		response.setDtoLst(strGson);
		return Response.status(Status.OK).entity(response).build();
	}

}
