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

import com.tesis.remote.SailDetailRemote;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.SailDetailDTO;

@Path ("sailDetailService")
public class SailDetailService {
	@javax.ws.rs.core.Context
	private UriInfo context;
	
	@EJB (mappedName="SailDetailBean")
	private SailDetailRemote sailDetail;
	
	@Path ("/saveSailDetail")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public MessageResponseDTO saveSailDetail (SailDetailDTO sailDetailDTO){
		MessageResponseDTO message=new MessageResponseDTO();
		//message=sailDetail.save(sailDetailDTO);
		return message;
	}
	
	@Path ("/updateSailDetail")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes (MediaType.APPLICATION_JSON)
	public MessageResponseDTO updateSailDetail(SailDetailDTO sailDetailDTO){
		MessageResponseDTO message=new MessageResponseDTO();
		message=sailDetail.update(sailDetailDTO);
		return message;
	}
	
	@Path ("/deleteSailDetailId/{idSailDetail}")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public MessageResponseDTO deleteSailDetailId(@PathParam("idSailDetail")Integer idSailDetail){
		MessageResponseDTO message=new MessageResponseDTO();
		message=sailDetail.delete(idSailDetail);
		return message;
	}
	
	@Path ("/getsailDetailId/{idSailDetail}")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public SailDetailDTO getSailDetailId(@PathParam("idSailDetail")Integer idSailDetail){
		SailDetailDTO sailDetailDTO=new SailDetailDTO();
		sailDetailDTO=sailDetail.getSailDetail(idSailDetail);
		return sailDetailDTO;
	}
	
	@Path ("/getSailDetailLst")
	@POST
	@Produces (MediaType.APPLICATION_JSON)
	public List<SailDetailDTO> getSailDetailLst(){
		List<SailDetailDTO> sailDetailLst=new ArrayList<SailDetailDTO>();
		sailDetailLst=sailDetail.getListSailDetail();
		return sailDetailLst;
	}
}
