package com.tesis.tpv.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.tesis.remote.BillingDataRemote;
import com.tesis.tpv.dto.BillingDataDTO;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.BillingData;

@Stateless (mappedName="BillingDataBean")
@TransactionManagement (TransactionManagementType.CONTAINER)

public class BillingDataBean implements BillingDataRemote {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public MessageResponseDTO save(final BillingDataDTO billingDataDTO) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		BillingData billingData=new BillingData();
		
		try{
			billingData.setName(billingDataDTO.getName());
			billingData.setAddress(billingDataDTO.getAddress());
			billingData.setRfc(billingDataDTO.getRfc());
			billingData.setTelephone(billingDataDTO.getTelephone());
			billingData.setZipCode(billingDataDTO.getZipCode());
			
			entityManager.persist(billingData);
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
	public MessageResponseDTO update(final BillingDataDTO billingDataDTO) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		BillingData billingData=new BillingData();
		
		try{
			billingData=entityManager.find(billingData.getClass(), billingDataDTO.getId());
			
			if(billingData!=null){
				billingData.setAddress(billingDataDTO.getAddress());
				billingData.setName(billingDataDTO.getName());
				billingData.setRfc(billingDataDTO.getRfc());
				billingData.setTelephone(billingDataDTO.getTelephone());
				billingData.setZipCode(billingDataDTO.getZipCode());
				
				entityManager.persist(billingData);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("El registro con id: "+ billingDataDTO.getId()+" no existe");
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
			
		}
		return messageResponseDTO;
	}

	@Override
	public MessageResponseDTO delete(final Integer idBillingData) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		BillingData billingData=new BillingData();
		try{
			billingData=entityManager.find(billingData.getClass(), idBillingData);
			
			if(billingData!=null){
				entityManager.remove(billingData);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
				messageResponseDTO.setIdTransaction(idBillingData);
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("No existe el registro con id: "+ idBillingData);
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		
		return messageResponseDTO;
	}

	@Override
	public BillingDataDTO getBillingData(final Integer idBillingData) {
		BillingDataDTO billingDataDTO=new BillingDataDTO();
		BillingData billingData=new BillingData();
		
		billingData=entityManager.find(billingData.getClass(), idBillingData);
		
		if(billingData!=null){
			billingDataDTO=TransferObjectAssembler.getInstance().assembleTO(billingDataDTO.getClass(), billingData);
		}
		
		return billingDataDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BillingDataDTO> getListBillingData() {
		List<BillingData> billingDataLst=new ArrayList<BillingData>();
		List<BillingDataDTO> billingDataDTOLst=new ArrayList<BillingDataDTO>();
		BillingDataDTO billingDataDTO=new BillingDataDTO();
		
		billingDataLst=entityManager.createQuery("BillingData.getAll").getResultList();
		
		if(billingDataLst!=null && billingDataLst.size()>0){
			for(BillingData billingData: billingDataLst){
				billingDataDTO=TransferObjectAssembler.getInstance().assembleTO(billingDataDTO.getClass(), billingData);
				billingDataDTOLst.add(billingDataDTO);
			}
		}
		return billingDataDTOLst;
	}

	@Override
	public List<BillingDataDTO> getPagination(final Integer pageInit, final Integer pageEnd) {
		BillingDataDTO billingDataDTO=new BillingDataDTO();
		List<BillingDataDTO> billingDataDTOLst=new ArrayList<BillingDataDTO>();
		Query query=entityManager.createNamedQuery("BillingData.getAll");
		query.setMaxResults(pageEnd);
		query.setFirstResult(pageInit);
		List<BillingData> billingDataLst=query.getResultList();
		
		if(billingDataLst!=null && billingDataLst.size()>0){
			for(BillingData billingData:billingDataLst){
				billingDataDTO=TransferObjectAssembler.getInstance().assembleTO(billingDataDTO.getClass(), billingData);
				billingDataDTOLst.add(billingDataDTO);
			}
		}
		return billingDataDTOLst;
	}

	@Override
	public Integer numberRecords() {
		Query query;
		Integer count=0;
		query=entityManager.createNamedQuery("BillingData.getCount");
		count=((Long)query.getSingleResult()).intValue();
		return count;
	}
}
