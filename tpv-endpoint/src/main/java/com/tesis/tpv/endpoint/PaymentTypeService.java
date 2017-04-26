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
import com.tesis.remote.PaymentTypeRemote;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.PaymentTypeDTO;
import com.tesis.tpv.dto.RequestDTO;
import com.tesis.tpv.dto.ResponseDTO;

@Path("/paymentTypeService")
public class PaymentTypeService extends ApplicationConfig{
	@javax.ws.rs.core.Context
	private UriInfo context;
	
	private static final Logger log=LoggerFactory.getLogger(PaymentTypeService.class);
	@EJB(mappedName="PaymentTypeBean")
	private PaymentTypeRemote paymentType;
	
	@Path ("/savePaymentType")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(RequestDTO requestDTO){
		Integer numberRecords;
		Gson gson=new Gson();
		ResponseDTO<PaymentTypeDTO> response=new ResponseDTO<PaymentTypeDTO>();
		List<PaymentTypeDTO> paymentTypeLst=new ArrayList<PaymentTypeDTO>();
		System.out.println("Se imprime el objeto: "+requestDTO);
		String dataObject=requestDTO.getDataTO();
		PaymentTypeDTO paymentTypeDTO=gson.fromJson(dataObject, PaymentTypeDTO.class);
		MessageResponseDTO messageResponseDTO=paymentType.save(paymentTypeDTO);
		response.setMessageResponseDTO(messageResponseDTO);
		System.out.println("Se imprime el mensaje de respuesta: "+ messageResponseDTO);
		if(messageResponseDTO.getCode()==1){
			numberRecords=paymentType.numberRecords();
			Integer modulo=numberRecords%requestDTO.getEndPage();
			Integer cociente=(Integer)numberRecords/requestDTO.getEndPage();
			response.setPageEnd(requestDTO.getEndPage());
			log.info("----> Modulo:{} "+ modulo + "----> cociente: "+ cociente+ "----> endPage: "+ requestDTO.getEndPage() + 
					"-----> Number of records: "+ numberRecords);
			
			if(modulo!=0){
				response.setNumberPage(cociente+1);
				response.setPageInit(cociente*requestDTO.getEndPage());
			}else{
				response.setNumberPage(cociente);
				response.setPageInit((cociente-1)*requestDTO.getEndPage());
			}
			paymentTypeLst=paymentType.getPagination(response.getPageInit(), response.getPageEnd());
			response.setNumberRecords(numberRecords);
			System.out.println("------>Modulo: "+modulo+ "---->cociente: "+ cociente + "----->endPage: "+ requestDTO.getEndPage()
			+"--->Numero de registros: "+ numberRecords + "----> InitPage: "+ requestDTO.getInitPage());

			GenericEntity<List<PaymentTypeDTO>>list=new GenericEntity<List<PaymentTypeDTO>>(paymentTypeLst){};
			String strGson=gson.toJson(list.getEntity());
			response.setDtoLst(strGson);
		
		}
		
		return Response.status(Status.OK).entity(response).build();
	}
	
	@Path("/updatePaymentType")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(RequestDTO requestDTO){
		Gson gson=new Gson();
		String dataObject=requestDTO.getDataTO();
		ResponseDTO<PaymentTypeDTO>response=new ResponseDTO<PaymentTypeDTO>();
		
		PaymentTypeDTO paymentTypeDTO=gson.fromJson(dataObject, PaymentTypeDTO.class);
		MessageResponseDTO messageResponseDTO=paymentType.update(paymentTypeDTO);
		response.setMessageResponseDTO(messageResponseDTO);
		
		List<PaymentTypeDTO> paymentTypeLst=paymentType.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
		GenericEntity<List<PaymentTypeDTO>> list=new GenericEntity<List<PaymentTypeDTO>>(paymentTypeLst){};
		response.setNumberRecords(paymentType.numberRecords());
		String strGson=gson.toJson(list.getEntity());
		response.setDtoLst(strGson);
		
		return Response.status(Status.OK).entity(response).build();
	}
	
	@Path("/deletePaymentType")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(RequestDTO requestDTO){
		String dataObject=requestDTO.getDataTO();
		Gson dataJson=new Gson();
		PaymentTypeDTO paymentTypeDTO=dataJson.fromJson(dataObject, PaymentTypeDTO.class);
		ResponseDTO<PaymentTypeDTO> response=new ResponseDTO<PaymentTypeDTO>();
		System.out.println("Se imprime el dato a eliminar: "+ paymentTypeDTO);
		MessageResponseDTO messageResponseDTO=paymentType.delete(paymentTypeDTO.getId());
		response.setMessageResponseDTO(messageResponseDTO);
		
		List<PaymentTypeDTO> paymentTypeLst=paymentType.getPagination(requestDTO.getInitPage(), requestDTO.getEndPage());
		GenericEntity<List<PaymentTypeDTO>> list=new GenericEntity<List<PaymentTypeDTO>>(paymentTypeLst){};
		response.setNumberRecords(paymentType.numberRecords());
		String strGson=dataJson.toJson(list.getEntity());
		response.setDtoLst(strGson);
		
		return Response.status(Status.OK).entity(response).build();
	}
	
	@Path("/getPaymentType/{idPaymentType}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public PaymentTypeDTO getPaymentType(@PathParam("idPaymentType")Integer idPaymentType){
		PaymentTypeDTO paymentTypeDTO=new PaymentTypeDTO();
		paymentTypeDTO=paymentType.getPaymentType(idPaymentType);
		return paymentTypeDTO;
	}
	
	@Path("/getListPaymentType")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public List<PaymentTypeDTO> getListPaymentType(){
		log.info("<---Se inicia el servicio para obtener la lista de tipos de pagos--->");
		List<PaymentTypeDTO> paymentTypeList=new ArrayList<PaymentTypeDTO>();
		paymentTypeList=paymentType.getListPaymentType();
		System.out.println("se regresa la list"+paymentTypeList);
		return paymentTypeList;
	}
	
	@Path("/pagination")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getPagination(RequestDTO requestTO){
		Gson gson= new Gson();

		log.info("<----Se inicia el servicion de paginación en PaymentTypeService----> Inicial"+ requestTO.getInitPage() +"--->Final "+ requestTO.getEndPage());
		ResponseDTO<PaymentTypeDTO> response=new ResponseDTO<PaymentTypeDTO>();
		List<PaymentTypeDTO> paymentTypeLst=new ArrayList<PaymentTypeDTO>();
		paymentTypeLst=paymentType.getPagination(requestTO.getInitPage(), requestTO.getEndPage());
		System.out.println(paymentTypeLst);
		GenericEntity<List<PaymentTypeDTO>> list = new GenericEntity<List<PaymentTypeDTO>>(paymentTypeLst){};
		response.setNumberRecords(paymentType.numberRecords());
		String strGson=gson.toJson(list.getEntity());
		response.setDtoLst(strGson);
		return Response.status(Status.OK).entity(response).build();
	}
}
