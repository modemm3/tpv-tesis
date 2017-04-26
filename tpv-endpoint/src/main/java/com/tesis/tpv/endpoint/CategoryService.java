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
import com.tesis.remote.CategoryRemote;
import com.tesis.tpv.dto.CategoryDTO;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.RequestDTO;
import com.tesis.tpv.dto.ResponseDTO;

@Path("/categoryService")
public class CategoryService {
	@javax.ws.rs.core.Context
	private UriInfo context;
	
	private static final Logger log=LoggerFactory.getLogger(CategoryService.class);

	@EJB(mappedName="CategoryBean")
	private CategoryRemote category;
	
	@Path ("/saveCategory") 
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(final RequestDTO requestDTO){
		Integer numberRecords;
		Gson gson=new Gson();
		ResponseDTO<CategoryDTO> response=new ResponseDTO<CategoryDTO>();
		List<CategoryDTO> categoryLst=new ArrayList<CategoryDTO>();
		
		String dataObject=requestDTO.getDataTO();
		CategoryDTO categoryDTO=gson.fromJson(dataObject, CategoryDTO.class);
		
		MessageResponseDTO messageResponseDTO=category.save(categoryDTO);
		response.setMessageResponseDTO(messageResponseDTO);
		
		if(messageResponseDTO.getCode()==1){
			numberRecords=category.numberRecords();
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
			
			categoryLst=category.getPagination(response.getPageInit(), response.getPageEnd());
			response.setNumberRecords(numberRecords);
			
			GenericEntity<List<CategoryDTO>>list=new GenericEntity<List<CategoryDTO>>(categoryLst){};
			String strGson=gson.toJson(list.getEntity());
			response.setDtoLst(strGson);
		}
		
		return Response.status(Status.OK).entity(response).build();
	}
	
	@Path("/deleteCategory")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(final RequestDTO requestDTO){
		String dataObject=requestDTO.getDataTO();
		Gson dataJson=new Gson();
		
		CategoryDTO categoryDTO=dataJson.fromJson(dataObject, CategoryDTO.class);
		ResponseDTO<CategoryDTO>response=new ResponseDTO<CategoryDTO>();
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		try{
			messageResponseDTO=category.delete(categoryDTO.getId());
		}catch(Exception e){
			log.error("Error en CategoryService"+e);
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("El dato no puede ser eliminado, verifique que no se este utilizando");
		}
		response.setMessageResponseDTO(messageResponseDTO);
		if(messageResponseDTO.getCode()==1){
			List<CategoryDTO> categoryLst=category.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
			GenericEntity<List<CategoryDTO>> list=new GenericEntity<List<CategoryDTO>>(categoryLst){};
			response.setNumberRecords(category.numberRecords());
			String strGson=dataJson.toJson(list.getEntity());
			response.setDtoLst(strGson);
			return Response.status(Status.OK).entity(response).build();
		}else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();

		}
	}
	
	@Path("/updateCategory")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(final RequestDTO requestDTO){
		Gson gson=new Gson();
		String dataObject=requestDTO.getDataTO();
		ResponseDTO<CategoryDTO> response=new ResponseDTO<CategoryDTO>();
		
		CategoryDTO categoryDTO=gson.fromJson(dataObject, CategoryDTO.class);
		MessageResponseDTO messageResponseDTO=category.update(categoryDTO);
		response.setMessageResponseDTO(messageResponseDTO);
		
		List<CategoryDTO> categoryLst=category.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
		GenericEntity<List<CategoryDTO>> list=new GenericEntity<List<CategoryDTO>>(categoryLst){};
		response.setNumberRecords(category.numberRecords());
		String strGson=gson.toJson(list.getEntity());
		response.setDtoLst(strGson);
		
		return Response.status(Status.OK).entity(response).build();
	}
	
	@Path("/getCategory/{idCategory}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategory(@PathParam("idCategory")final Integer idCategory){
		log.info("id Category:{}", idCategory );
		System.out.println("id category: "+ idCategory);
		CategoryDTO categoryDTO=new CategoryDTO();
		categoryDTO=category.getCategory(idCategory);
		
		System.out.println("CATEGORYDTO: "+ categoryDTO);
		log.info("CategoryDTO SERVICE:{}", categoryDTO);

		return Response.status(Response.Status.OK).entity(categoryDTO).build();
	}
	
	@Path("/getListCategory")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<CategoryDTO> getListCategory(){
		List<CategoryDTO> categoryList=new ArrayList<CategoryDTO>();
		categoryList=category.getList();
		return categoryList;	
	}
	
	@Path("/pagination")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getPagination(final RequestDTO requestDTO){
		Gson gson=new Gson();
		ResponseDTO<CategoryDTO> response=new ResponseDTO<CategoryDTO>();
		List<CategoryDTO> categoryLst=new ArrayList<CategoryDTO>();
		
		categoryLst=category.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
		
		GenericEntity<List<CategoryDTO>> list=new GenericEntity<List<CategoryDTO>>(categoryLst){};
		response.setNumberRecords(category.numberRecords());
		String strGson=gson.toJson(list.getEntity());
		response.setDtoLst(strGson);
		return Response.status(Status.OK).entity(response).build();
	}
}
