package com.tesis.tpv.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.tesis.remote.TypeDiscountRemote;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.TypeDiscountDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.TypeDiscount;

@Stateless(mappedName="TypeDiscountBean")
@TransactionManagement(TransactionManagementType.CONTAINER)

public class TypeDiscountBean implements TypeDiscountRemote {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public MessageResponseDTO save(final TypeDiscountDTO typeDiscountDTO) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		TypeDiscount typeDiscount= new TypeDiscount();
		try{
			typeDiscount.setName(typeDiscountDTO.getName());
			typeDiscount.setDescription(typeDiscountDTO.getDescription());
			
			entityManager.persist(typeDiscount);
			messageResponseDTO.setCode(1);
			messageResponseDTO.setMessage("success");
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		
		return messageResponseDTO;
	}

	@Override
	public MessageResponseDTO delete(final Integer idTypeDiscount) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		TypeDiscount typeDiscount=new TypeDiscount();
		
		try{
			typeDiscount=entityManager.find(TypeDiscount.class, idTypeDiscount);
			
			if(typeDiscount!=null){
				entityManager.remove(typeDiscount);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
				messageResponseDTO.setIdTransaction(idTypeDiscount);
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("No existe el registro: "+idTypeDiscount);
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		
		return messageResponseDTO;
	}

	@Override
	public MessageResponseDTO update(final TypeDiscountDTO typeDiscountDTO) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		TypeDiscount typeDiscount=new TypeDiscount();
		
		try{
			typeDiscount=entityManager.find(TypeDiscount.class, typeDiscountDTO.getId());
			
			if(typeDiscount!=null){
				typeDiscount.setId(typeDiscountDTO.getId());
				typeDiscount.setName(typeDiscountDTO.getName());
				typeDiscount.setDescription(typeDiscountDTO.getDescription());
				entityManager.persist(typeDiscount);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("No existe el registro para el id: "+ typeDiscountDTO.getId());
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		return messageResponseDTO;
	}

	@Override
	public TypeDiscountDTO getTypeDiscount(final Integer idTypeDiscount) {
		TypeDiscount typeDiscount=new TypeDiscount();
		TypeDiscountDTO typeDiscountDTO=new TypeDiscountDTO();
		
		typeDiscount=entityManager.find(TypeDiscount.class, idTypeDiscount);
		
		if(typeDiscount!=null){
			typeDiscountDTO=TransferObjectAssembler.getInstance().assembleTO(typeDiscountDTO.getClass(), typeDiscount);
		}
		
		return typeDiscountDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TypeDiscountDTO> getListTypeDiscount() {
		TypeDiscountDTO typeDiscountDTO=new TypeDiscountDTO();
		List<TypeDiscountDTO> typeDiscountDTOLst=new ArrayList<TypeDiscountDTO>();
		List<TypeDiscount> typeDiscountLst=new ArrayList<TypeDiscount>();
		
		Query query=entityManager.createNamedQuery("TypeDiscount.getAll");
	
		typeDiscountLst=query.getResultList();
		
		if(typeDiscountLst!=null && typeDiscountLst.size()>0){
			for(TypeDiscount typeDiscount:typeDiscountLst){
				typeDiscountDTO=TransferObjectAssembler.getInstance().assembleTO(typeDiscountDTO.getClass(), typeDiscount);
				typeDiscountDTOLst.add(typeDiscountDTO);
			}
		}
		return typeDiscountDTOLst;
	}

	@Override
	public List<TypeDiscountDTO> getPagination(final Integer pageInit, final Integer pageEnd) {
		TypeDiscountDTO typeDiscountDTO=new TypeDiscountDTO();
		List<TypeDiscountDTO> typeDiscountDTOLst=new ArrayList<TypeDiscountDTO>();
		Query query=entityManager.createNamedQuery("TypeDiscount.getAll");
		query.setMaxResults(pageEnd);
		query.setFirstResult(pageInit);
		List<TypeDiscount> typeDiscountLst=query.getResultList();
		
		if(typeDiscountLst!=null && typeDiscountLst.size()>0){
			for(TypeDiscount typeDiscount:typeDiscountLst){
				typeDiscountDTO=TransferObjectAssembler.getInstance().assembleTO(typeDiscountDTO.getClass(), typeDiscount);
				typeDiscountDTOLst.add(typeDiscountDTO);
			}
		}
		return typeDiscountDTOLst;
	}

	@Override
	public Integer numberRecords() {
		Query query;
		Integer count=0;
		query=entityManager.createNamedQuery("TypeDiscount.getCount");
		count=((Long)query.getSingleResult()).intValue();
		return count;
	}
}
