package com.tesis.tpv.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tesis.remote.PaymentTypeRemote;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.PaymentTypeDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.PaymentType;

@Stateless (mappedName="PaymentTypeBean")
@TransactionManagement (TransactionManagementType.CONTAINER)

public class PaymentTypeBean implements PaymentTypeRemote, Serializable {
	
	/**
	 * 
	 */
	private static Logger log=LoggerFactory.getLogger(PaymentTypeBean.class);
	private static final long serialVersionUID = 1L;
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public MessageResponseDTO save(PaymentTypeDTO paymentTypeDTO) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		PaymentType paymentType=new PaymentType();
		try{
			paymentType.setName(paymentTypeDTO.getName());
			entityManager.persist(paymentType);
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
	public MessageResponseDTO delete(Integer idPaymentType) {
		log.info("----- El id a eliminar ------------> "+ idPaymentType);
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		PaymentType paymentType=new PaymentType();
		
		try{
			paymentType=entityManager.find(PaymentType.class, idPaymentType);
			
			if(paymentType!=null){
				entityManager.remove(paymentType);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("No existe registro con el id: "+ idPaymentType);
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		
		return messageResponseDTO;
	}

	@Override
	public MessageResponseDTO update(PaymentTypeDTO paymentTypeDTO) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		PaymentType paymentType=new PaymentType();
		
		try{
			paymentType=entityManager.find(PaymentType.class, paymentTypeDTO.getId());
			
			if(paymentType!=null){
				paymentType.setName(paymentTypeDTO.getName());
				entityManager.merge(paymentType);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("No existe el registro con el id: "+ paymentTypeDTO.getId());
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		
		return messageResponseDTO;
	}

	@Override
	public PaymentTypeDTO getPaymentType(Integer idPaymentType) {
		PaymentType paymentType=new PaymentType();
		PaymentTypeDTO paymentTypeDTO=new PaymentTypeDTO();

		paymentType=entityManager.find(PaymentType.class, idPaymentType);
		
		if(paymentType!=null){
			paymentTypeDTO=TransferObjectAssembler.getInstance().assembleTO(paymentTypeDTO.getClass(), paymentType);
		}
		
		return paymentTypeDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PaymentTypeDTO> getListPaymentType() {
		System.out.println("Entra al metodo getListPaymentType");

		PaymentTypeDTO paymentTypeDTO=new PaymentTypeDTO();
		List<PaymentTypeDTO> paymentTypeDTOLst=new ArrayList<PaymentTypeDTO>();
		List<PaymentType> paymentTypeLst=new ArrayList<PaymentType>();
		
		paymentTypeLst=entityManager.createNamedQuery("PaymentType.getAll").getResultList();
		System.out.println("Antes de que agregar a la lista");
		
		if(paymentTypeLst!=null && paymentTypeLst.size()>0){
			System.out.println("la lista es diferente a nula");
			for(PaymentType paymentType: paymentTypeLst){
				paymentTypeDTO=TransferObjectAssembler.getInstance().assembleTO(paymentTypeDTO.getClass(), paymentType);
				System.out.println("objeto 1---> "+ paymentTypeDTO);
				paymentTypeDTOLst.add(paymentTypeDTO);
			}
		}
		return paymentTypeDTOLst;
	}

	@Override
	public List<PaymentTypeDTO> getPagination(Integer pageInit, Integer pageEnd) {
		log.info("<----Se inicia el servicion de paginación en PaymentTypeBean----> Inicial"+ pageInit+"--->Final "+pageEnd);
		System.out.println("<----Se inicia el servicion de paginación----> Inicial"+ pageInit+"--->Final "+pageEnd);
		PaymentTypeDTO paymentTypeDTO=new PaymentTypeDTO();
		List<PaymentTypeDTO> paymentTypeDTOLst=new ArrayList<PaymentTypeDTO>();
		List<PaymentType> paymentTypeLst=new ArrayList<PaymentType>();
		Query query;
		query=entityManager.createNamedQuery("PaymentType.getAll");
		query.setMaxResults(pageEnd);
		query.setFirstResult(pageInit);
		paymentTypeLst=query.getResultList();
		if(paymentTypeLst!=null && paymentTypeLst.size()>0){
			System.out.println("la lista es diferente a nula");
			for(PaymentType paymentType: paymentTypeLst){
				paymentTypeDTO=TransferObjectAssembler.getInstance().assembleTO(paymentTypeDTO.getClass(), paymentType);
				System.out.println("objeto 1---> "+ paymentTypeDTO);
				paymentTypeDTOLst.add(paymentTypeDTO);
			}
		}
		
		return paymentTypeDTOLst;
	}

	@Override
	public Integer numberRecords() {
		Query query;
		Integer count=0;
		query=entityManager.createNamedQuery("PaymentType.getCount");
		count=((Long) query.getSingleResult()).intValue();
		return count;
	}

}
