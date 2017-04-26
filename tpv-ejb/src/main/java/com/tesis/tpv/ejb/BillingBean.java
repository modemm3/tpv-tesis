package com.tesis.tpv.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tesis.remote.BillingRemote;
import com.tesis.tpv.dto.BillingDTO;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Billing;
import com.tesis.tpv.jpa.BillingData;
import com.tesis.tpv.jpa.Sale;

@Stateless (mappedName="BillingBean")
@TransactionManagement (TransactionManagementType.CONTAINER)
public class BillingBean implements BillingRemote{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public MessageResponseDTO save(BillingDTO billingDTO) {
		// TODO Auto-generated method stub
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		Billing billing=new Billing();
		BillingData billingData=new BillingData();
		Sale sale=new Sale();
		
		try{
			billingData.setId(billingDTO.getBillingDataId());
			sale.setId(billingDTO.getSaleId());
			
			
			billing.setBillingData(billingData);
			System.out.println("fecha desde el BEAN: "+ billingDTO.getBillingDate());
			billing.setBillingDate(billingDTO.getBillingDate());
			billing.setSale(sale);
			billing.setSubtotal(billingDTO.getSubtotal());
			billing.setTotal(billingDTO.getTotal());
			entityManager.persist(billing);
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
	public MessageResponseDTO update(BillingDTO billingDTO) {
		// TODO Auto-generated method stub
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		Billing billing=new Billing();
		BillingData billingData;
		Sale sale;
		
		try{
			billing=entityManager.find(billing.getClass(), billingDTO.getId());
		
			if(billing!=null){
				billingData=new BillingData();
				billingData.setId(billingDTO.getBillingDataId());
				sale=new Sale();
				sale.setId(billingDTO.getSaleId());
				billing.setId(billingDTO.getId());
				billing.setBillingData(billingData);
				billing.setBillingDate(billingDTO.getBillingDate());
				billing.setSale(sale);
				billing.setSubtotal(billingDTO.getSubtotal());
				billing.setTotal(billingDTO.getTotal());
				entityManager.persist(billing);
				
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("No se encuentra el registro con id: "+ billingDTO.getId());
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		return messageResponseDTO;
	}

	@Override
	public MessageResponseDTO delete(Integer idBilling) {
		// TODO Auto-generated method stub
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		Billing billing=new Billing();
		
		try{
			billing=entityManager.find(billing.getClass(), idBilling);
			
			if(billing!=null){
				entityManager.remove(billing);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("No se encontro el registro con id: "+idBilling);
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		
		return messageResponseDTO;
	}

	@Override
	public BillingDTO getBilling(Integer idBilling) {
		// TODO Auto-generated method stub
		BillingDTO billingDTO=new BillingDTO();
		Billing billing=new Billing();
		
		billing=entityManager.find(billing.getClass(), idBilling);
		
		if(billing!=null){
			billingDTO=TransferObjectAssembler.getInstance().assembleTO(billingDTO.getClass(), billing);
		}
		
		return billingDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BillingDTO> getListBilling() {
		// TODO Auto-generated method stub
		List<BillingDTO> billingDTOLst=new ArrayList<BillingDTO>();
		List<Billing> billingLst=new ArrayList<Billing>();
		BillingDTO billingDTO;
		
		billingLst=entityManager.createQuery("SELECT b FROM Billing b").getResultList();
		
		if(billingLst!=null && billingLst.size()>0){
			billingDTO=new BillingDTO();
			for(Billing billing:billingLst){
				billingDTO=TransferObjectAssembler.getInstance().assembleTO(billingDTO.getClass(), billing);
				billingDTOLst.add(billingDTO);
			}
		}
		return billingDTOLst;
	}

}
