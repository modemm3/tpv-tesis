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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.tesis.remote.ProductRemote;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.ProductDTO;
import com.tesis.tpv.dto.RequestDTO;
import com.tesis.tpv.dto.ResponseDTO;

@Path ("/productService")
public class ProductService {
	@javax.ws.rs.core.Context
	private UriInfo context;
	private static final Logger log= LoggerFactory.getLogger(ProductService.class);
	@EJB (mappedName="ProductBean")
	ProductRemote product;
	
	@Path ("/saveProduct")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveProduct(RequestDTO requestDTO){
		Gson gson=new Gson();
		Integer numberRecords;
		ResponseDTO<ProductDTO> response=new ResponseDTO<ProductDTO>();
		List<ProductDTO> productDTOLst=new ArrayList<ProductDTO>();
		
		String dataObject=requestDTO.getDataTO();
		ProductDTO productDTO=gson.fromJson(dataObject, ProductDTO.class);
		
		MessageResponseDTO messageResponseDTO=product.save(productDTO);
		response.setMessageResponseDTO(messageResponseDTO);
		
		if(messageResponseDTO.getCode()==1){
			numberRecords=product.numberRecords();
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
			
			productDTOLst=product.getPagination(response.getPageInit(), response.getPageEnd());
			response.setNumberRecords(numberRecords);
			
			GenericEntity<List<ProductDTO>> list=new GenericEntity<List<ProductDTO>>(productDTOLst){};
			String strGson=gson.toJson(list.getEntity());
			response.setDtoLst(strGson);
		}
		
		return Response.status(Status.OK).entity(response).build();
	}
	
	@Path ("/updateProduct")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response updateProduct(RequestDTO requestDTO){
		Gson gson=new Gson();
		String dataObject=requestDTO.getDataTO();
		ResponseDTO<ProductDTO> response=new ResponseDTO<ProductDTO>();
		
		ProductDTO productDTO=gson.fromJson(dataObject, ProductDTO.class);
		
		MessageResponseDTO messageResponseDTO=product.update(productDTO);
		response.setMessageResponseDTO(messageResponseDTO);
		
		List<ProductDTO> productDTOLst=product.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
		GenericEntity<List<ProductDTO>> list=new GenericEntity<List<ProductDTO>>(productDTOLst){};
		response.setNumberRecords(product.numberRecords());
		String strGson=gson.toJson(list.getEntity());
		response.setDtoLst(strGson);
		return Response.status(Status.OK).entity(response).build();
	}
	
	@Path ("/deleteProduct/{idProduct}")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public Response deleteProduct(RequestDTO requestDTO){
		String dataObject=requestDTO.getDataTO();
		Gson dataJson=new Gson();
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		ProductDTO productDTO=dataJson.fromJson(dataObject, ProductDTO.class);
		ResponseDTO<ProductDTO> response=new ResponseDTO<ProductDTO>();
		try{
			messageResponseDTO=product.delete(productDTO.getId());
		}catch(Exception e){
			log.error("error en ProductService "+ e);
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("El dato no puede ser eliminado, verifique que no se este utilizando");
		}
		
		response.setMessageResponseDTO(messageResponseDTO);
		
		if(messageResponseDTO.getCode()==1){
			List<ProductDTO> productDTOLst=product.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
			GenericEntity<List<ProductDTO>> list=new GenericEntity<List<ProductDTO>>(productDTOLst){};
			response.setNumberRecords(product.numberRecords());
			String strGson=dataJson.toJson(list.getEntity());
			response.setDtoLst(strGson);
			return Response.status(Status.OK).entity(response).build();
		}else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}
		
	}
	
	@Path ("/getProduct")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public ProductDTO getProduct(RequestDTO requestDTO){
		
		log.info("RequestTO:{}",requestDTO);
		ProductDTO prodDTO=new ProductDTO();
		String dataObject=requestDTO.getDataTO();
		Gson dataJson= new Gson();
		ProductDTO productDTO=dataJson.fromJson(dataObject, ProductDTO.class);
		log.info("ProductTO:{}",productDTO);
		prodDTO=product.getProductByCode(productDTO.getCode());
		return prodDTO;
	}
	
	@Path ("/getListProduct")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public List<ProductDTO> getProductList(){
		List<ProductDTO> productLst=new ArrayList<ProductDTO>();
		productLst=product.getProductList();
		return productLst;
	}
	
	@Path ("/pagination")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public Response getPagination(RequestDTO requestDTO){
		Gson gson=new Gson();
		ResponseDTO<ProductDTO> response=new ResponseDTO<ProductDTO>();
		List<ProductDTO> productDTOLst=new ArrayList<ProductDTO>();
		
		productDTOLst=product.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
		GenericEntity<List<ProductDTO>> list=new GenericEntity<List<ProductDTO>>(productDTOLst){};
		response.setNumberRecords(product.numberRecords());
		String strGson=gson.toJson(list.getEntity());
		response.setDtoLst(strGson);
		return Response.status(Status.OK).entity(response).build();
	}

}
