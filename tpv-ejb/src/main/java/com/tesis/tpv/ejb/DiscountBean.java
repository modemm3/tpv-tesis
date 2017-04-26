package com.tesis.tpv.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.tesis.remote.DiscountRemote;
import com.tesis.tpv.dto.DiscountDTO;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Discount;
import com.tesis.tpv.jpa.Product;
import com.tesis.tpv.jpa.TypeDiscount;

@Stateless (mappedName="DiscountBean")
@TransactionManagement (TransactionManagementType.CONTAINER)
public class DiscountBean implements DiscountRemote{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public MessageResponseDTO save(DiscountDTO discountDTO) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		Discount discount=new Discount();
		Product product=new Product();
		TypeDiscount typeDiscount=new TypeDiscount();
		
		try{
			product.setId(discountDTO.getProductDTO().getId());
			typeDiscount.setId(discountDTO.getTypeDiscountDTO().getId());
			discount.setAmount(discountDTO.getAmount());
			discount.setEndDate(discountDTO.getEndDate());
			discount.setName(discountDTO.getName());
			discount.setProduct(product);
			discount.setStartDate(discountDTO.getStartDate());
			discount.setStatus(discountDTO.getStatus());
			discount.setTypeDiscount(typeDiscount);
			
			entityManager.persist(discount);
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
	public MessageResponseDTO update(DiscountDTO discountDTO) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		Discount discount=new Discount();
		Product product;
		TypeDiscount typeDiscount;
		
		try{
			discount=entityManager.find(discount.getClass(), discountDTO.getId());
			
			if(discount!=null){
				product=new Product();
				typeDiscount=new TypeDiscount();
				product.setId(discountDTO.getProductDTO().getId());
				typeDiscount.setId(discountDTO.getTypeDiscountDTO().getId());
				discount.setId(discountDTO.getId());
				discount.setAmount(discountDTO.getAmount());
				discount.setEndDate(discountDTO.getEndDate());
				discount.setName(discountDTO.getName());
				discount.setProduct(product);
				discount.setStartDate(discountDTO.getStartDate());
				discount.setStatus(discountDTO.getStatus());
				discount.setTypeDiscount(typeDiscount);
				
				entityManager.persist(discount);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("No s encontro el registro con id: "+ discountDTO.getId());
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
			
		return messageResponseDTO;
	}

	@Override
	public MessageResponseDTO delete(Integer idDiscount) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		Discount discount=new Discount();
		
		try{
			discount=entityManager.find(discount.getClass(), idDiscount);
			
			if(discount!=null){
				entityManager.remove(discount);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("No se encontro el registro con id: "+ idDiscount);
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		return messageResponseDTO;
	}

	@Override
	public DiscountDTO getDiscount(Integer idDiscount) {
		DiscountDTO discountDTO=new DiscountDTO();
		Discount discount=new Discount();
		
		discount=entityManager.find(discount.getClass(), idDiscount);
		
		if(discount!=null){
			discountDTO=TransferObjectAssembler.getInstance().assembleTO(discountDTO.getClass(), discount);
		}
		
		return discountDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DiscountDTO> getListDiscount() {
		List<DiscountDTO> discountDTOLst=new ArrayList<DiscountDTO>();
		List<Discount> discountLst=new ArrayList<Discount>();
		DiscountDTO discountDTO;
		
		discountLst=entityManager.createNamedQuery("Discount.getAll").getResultList();
		
		if(discountLst!=null && discountLst.size()>0){
			discountDTO=new DiscountDTO();
			for(Discount discount:discountLst){
				discountDTO=TransferObjectAssembler.getInstance().assembleTO(discountDTO.getClass(), discount);
				discountDTOLst.add(discountDTO);
			}
		}
		return discountDTOLst;
	}

	@Override
	public List<DiscountDTO> getPagination(Integer pageInit, Integer pageEnd) {
		DiscountDTO discountDTO= new DiscountDTO();
		List<DiscountDTO> discountDTOLst=new ArrayList<DiscountDTO>();
		Query query=entityManager.createNamedQuery("Discount.getAll");
		query.setMaxResults(pageEnd);
		query.setFirstResult(pageInit);
		List<Discount> discountLst=query.getResultList();
		
		if(discountLst!=null && discountLst.size()>0){
			for(Discount discount:discountLst){
				discountDTO=TransferObjectAssembler.getInstance().assembleTO(discountDTO.getClass(), discount);
				discountDTOLst.add(discountDTO);
			}
		}
		return discountDTOLst;
	}

	@Override
	public Integer numberRecords() {
		Query query;
		Integer count=0;
		query=entityManager.createNamedQuery("Discount.getCount");
		count=((Long)query.getSingleResult()).intValue();
		return count;
	}

}
