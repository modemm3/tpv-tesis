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
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import javax.ws.rs.core.UriInfo;
import com.tesis.remote.EmployeeCategoryRemote;
import com.tesis.tpv.dto.EmployeeCategoryDTO;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.RequestDTO;
import com.tesis.tpv.dto.ResponseDTO;

@Path ("/employeeCategoryService")
public class EmployeeCategoryService {
	@javax.ws.rs.core.Context
	private UriInfo context;
	@EJB (mappedName="EmployeeCategoryBean")
	private EmployeeCategoryRemote employeeCategory;
	
	private static final Logger log=LoggerFactory.getLogger(EmployeeCategoryService.class);

	@Path ("/saveEmployeeCategory")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response saveEmployeeCategory(final RequestDTO requestDTO){
		Integer numberRecords;
		Gson gson=new Gson();
		ResponseDTO<EmployeeCategoryDTO> response=new ResponseDTO<EmployeeCategoryDTO>();
		List<EmployeeCategoryDTO> employeeCategoryLst=new ArrayList<EmployeeCategoryDTO>();
		
		String dataObject=requestDTO.getDataTO();
		EmployeeCategoryDTO employeeCategoryDTO=gson.fromJson(dataObject, EmployeeCategoryDTO.class);
		
		MessageResponseDTO messageResponseDTO=employeeCategory.save(employeeCategoryDTO);
		response.setMessageResponseDTO(messageResponseDTO);
		
		if(messageResponseDTO.getCode()==1){
			numberRecords=employeeCategory.numberRecords();
			Integer modulo=numberRecords%requestDTO.getEndPage();
			Integer cociente=(Integer) numberRecords/requestDTO.getEndPage();
			response.setPageEnd(requestDTO.getEndPage());
			
			if(modulo!=0){
				response.setNumberPage(cociente+1);
				response.setPageInit(cociente*requestDTO.getEndPage());
			}else{
				response.setNumberPage(cociente);
				response.setPageInit((cociente-1)*requestDTO.getEndPage());
			}
			
			employeeCategoryLst=employeeCategory.getPagination(response.getPageInit(), response.getPageEnd());
			response.setNumberRecords(numberRecords);
			
			GenericEntity<List<EmployeeCategoryDTO>> list=new GenericEntity<List<EmployeeCategoryDTO>>(employeeCategoryLst){};
			response.setNumberRecords(numberRecords);
			String strGson=gson.toJson(list.getEntity());
			response.setDtoLst(strGson);
		}
		
		return Response.status(Status.OK).entity(response).build();
	}
	
	@Path ("/updateEmployeeCategory")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response updateEmployeeCategory(final RequestDTO requestDTO){
		Gson gson=new Gson();
		String dataObject=requestDTO.getDataTO();
		ResponseDTO<EmployeeCategoryDTO> response=new ResponseDTO<EmployeeCategoryDTO>();
		
		EmployeeCategoryDTO employeeCategoryDTO=gson.fromJson(dataObject, EmployeeCategoryDTO.class);
		MessageResponseDTO messageResponseDTO=employeeCategory.update(employeeCategoryDTO);
		response.setMessageResponseDTO(messageResponseDTO);

		List<EmployeeCategoryDTO> employeeCategoryLst=employeeCategory.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
		GenericEntity<List<EmployeeCategoryDTO>> list=new GenericEntity<List<EmployeeCategoryDTO>>(employeeCategoryLst){};
		response.setNumberRecords(employeeCategory.numberRecords());
		String strGson=gson.toJson(list.getEntity());
		response.setDtoLst(strGson);
		
		return Response.status(Status.OK).entity(response).build();
	}
	
	@Path ("/deleteEmployeeCategory")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response deleteEmployeeCategory(final RequestDTO requestDTO){
		String dataObject=requestDTO.getDataTO();
		Gson dataJson=new Gson();
		
		EmployeeCategoryDTO employeeCategoryDTO=dataJson.fromJson(dataObject, EmployeeCategoryDTO.class);
		ResponseDTO<EmployeeCategoryDTO> response=new ResponseDTO<EmployeeCategoryDTO>();
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();

		try{
			messageResponseDTO=employeeCategory.delete(employeeCategoryDTO.getId());
		}catch(Exception e){
			log.error("Error en EmployeeCategoryService"+ e);
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("El dato no puede ser eliminado, verifique que no se este utilizando");
		}
		response.setMessageResponseDTO(messageResponseDTO);
		
		if(messageResponseDTO.getCode()==1){
			List<EmployeeCategoryDTO> employeeCategoryDTOLst=employeeCategory.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
			GenericEntity<List<EmployeeCategoryDTO>> list=new GenericEntity<List<EmployeeCategoryDTO>>(employeeCategoryDTOLst){};
			response.setNumberRecords(employeeCategory.numberRecords());
			String strGson=dataJson.toJson(list.getEntity());
			response.setDtoLst(strGson);
			return Response.status(Status.OK).entity(response).build();
		}else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
	}
	
	@Path ("/getEmployeeCategory/{idEmployeeCategory}")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public EmployeeCategoryDTO getEmployeeCategory(@PathParam("idEmployeeCategory")final Integer idEmployeeCategory){
		EmployeeCategoryDTO employeeCategoryDTO=new EmployeeCategoryDTO();
		employeeCategoryDTO=employeeCategory.getEmployeeCategory(idEmployeeCategory);
		return employeeCategoryDTO;
	}
	
	@Path ("/getListEmployeeCategory")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public List<EmployeeCategoryDTO> getListEmployeeCategory(){
		List<EmployeeCategoryDTO> employeeCategoryLst=new ArrayList<EmployeeCategoryDTO>();
		employeeCategoryLst=employeeCategory.getListEmployeeCategory();
		return employeeCategoryLst;
	}
	
	@Path("/pagination")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response getPagination(final RequestDTO requestDTO){
		Gson gson=new Gson();
		ResponseDTO<EmployeeCategoryDTO> response=new ResponseDTO<EmployeeCategoryDTO>();
		List<EmployeeCategoryDTO> employeeCategoryDTOLst=new ArrayList<EmployeeCategoryDTO>();
		
		employeeCategoryDTOLst=employeeCategory.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
		
		GenericEntity<List<EmployeeCategoryDTO>> list=new GenericEntity<List<EmployeeCategoryDTO>>(employeeCategoryDTOLst){};
		response.setNumberRecords(employeeCategory.numberRecords());
		String strGson=gson.toJson(list.getEntity());
		response.setDtoLst(strGson);
		return Response.status(Status.OK).entity(response).build();
	}
}
