package com.tesis.tpv.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tesis.remote.SaleRemote;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.SaleDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Employee;
import com.tesis.tpv.jpa.PaymentType;
import com.tesis.tpv.jpa.Sale;

@Stateless (mappedName="SaleBean")
@TransactionManagement (TransactionManagementType.CONTAINER)
public class SaleBean implements SaleRemote{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public MessageResponseDTO save(SaleDTO saleDTO) {
		// TODO Auto-generated method stub
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		Sale sale=new Sale();
		Employee employee=new Employee();
		PaymentType paymentType=new PaymentType();
		
		try{
			paymentType.setId(saleDTO.getPaymentTypeId());
			employee.setId(saleDTO.getEmployeeId());
			sale.setAmount(saleDTO.getAmount());
			sale.setDiscount(saleDTO.getDiscount());
			sale.setEmployee(employee);
			sale.setPaymentType(paymentType);
			entityManager.persist(sale);
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
	public MessageResponseDTO update(SaleDTO saleDTO) {
		// TODO Auto-generated method stub
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		Sale sale=new Sale();
		Employee employee;
		PaymentType paymentType;
		
		try{
			sale=entityManager.find(sale.getClass(), saleDTO.getId());
			
			if(sale!=null){
				employee=new Employee();
				paymentType=new PaymentType();
				paymentType.setId(saleDTO.getPaymentTypeId());
				employee.setId(saleDTO.getEmployeeId());
				sale.setAmount(saleDTO.getAmount());
				sale.setDiscount(saleDTO.getDiscount());
				sale.setEmployee(employee);
				sale.setId(saleDTO.getId());
				sale.setPaymentType(paymentType);
				
				entityManager.persist(sale);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("No existe el registro con id: "+ saleDTO.getId());
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		
		return messageResponseDTO;
	}

	@Override
	public MessageResponseDTO delete(Integer idSave) {
		// TODO Auto-generated method stub
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		Sale sale=new Sale();
		
		try{
		sale=entityManager.find(sale.getClass(), idSave);
		
		if(sale!=null){
			entityManager.remove(sale);
			messageResponseDTO.setCode(1);
			messageResponseDTO.setMessage("success");
		}else{
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("No existe el registro con id: "+ idSave);
		}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		return messageResponseDTO;
	}

	@Override
	public SaleDTO getSale(Integer idSave) {
		// TODO Auto-generated method stub
		SaleDTO saleDTO=new SaleDTO();
		Sale sale=new Sale();
		
		sale=entityManager.find(sale.getClass(), idSave);
		
		if(sale!=null){
			saleDTO=TransferObjectAssembler.getInstance().assembleTO(saleDTO.getClass(), sale);
		}
		
		return saleDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SaleDTO> getListSale() {
		// TODO Auto-generated method stub
		List<Sale> saleList=new ArrayList<Sale>();
		List<SaleDTO> saleDTOList=new ArrayList<SaleDTO>();
		SaleDTO saleDTO;
		
		saleList=entityManager.createQuery("SELECT s FROM Sale s").getResultList();
		
		if(saleList!=null && saleList.size()>0){
			saleDTO=new SaleDTO();
			for(Sale sale: saleList){
				saleDTO=TransferObjectAssembler.getInstance().assembleTO(saleDTO.getClass(),sale);
				saleDTOList.add(saleDTO);
			}
		}
		return saleDTOList;
	}
	
}
