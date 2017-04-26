package com.tesis.tpv.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tesis.remote.SailDetailRemote;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.SailDetailDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Product;
import com.tesis.tpv.jpa.SailDetail;
import com.tesis.tpv.jpa.Sale;

@Stateless (mappedName="SailDetailBean")
@TransactionManagement (TransactionManagementType.CONTAINER)
public class SailDetailBean implements SailDetailRemote{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public MessageResponseDTO save(SailDetailDTO sailDetailDTO) {
		// TODO Auto-generated method stub
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		SailDetail sailDetail=new SailDetail();
		Product product=new Product();
		Sale sale=new Sale();
		
		try{
			product.setId(sailDetailDTO.getProductId());
			sale.setId(sailDetailDTO.getSaleId());
			sailDetail.setProduct(product);
			sailDetail.setQuantity(sailDetailDTO.getQuantity());
			sailDetail.setSale(sale);
			
			entityManager.persist(sailDetail);
			messageResponseDTO.setCode(1);
			messageResponseDTO.setMessage("success");
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		return null;
	}

	@Override
	public MessageResponseDTO update(SailDetailDTO sailDetailDTO) {
		// TODO Auto-generated method stub
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		SailDetail sailDetail=new SailDetail();
		Product product;
		Sale sale;
		
		try{
			sailDetail=entityManager.find(sailDetail.getClass(), sailDetailDTO.getId());
			
			if(sailDetail!=null){
				product=new Product();
				sale=new Sale();
				product.setId(sailDetailDTO.getProductId());
				sale.setId(sailDetailDTO.getSaleId());
				sailDetail.setId(sailDetailDTO.getId());
				sailDetail.setProduct(product);
				sailDetail.setQuantity(sailDetailDTO.getQuantity());
				sailDetail.setSale(sale);
				
				entityManager.persist(sailDetail);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("No se encuentra el registro con id: "+ sailDetailDTO.getId());
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		
		return messageResponseDTO;
	}

	@Override
	public MessageResponseDTO delete(Integer idSailDetail) {
		// TODO Auto-generated method stub
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		SailDetail sailDetail=new SailDetail();
		
		try{
			sailDetail=entityManager.find(sailDetail.getClass(), idSailDetail);
			
			if(sailDetail!=null){
				entityManager.remove(sailDetail);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("No se encuentra el registro con id: "+ idSailDetail);
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		return messageResponseDTO;
	}

	@Override
	public SailDetailDTO getSailDetail(Integer idSailDetail) {
		// TODO Auto-generated method stub
		SailDetailDTO sailDetailDTO=new SailDetailDTO();
		SailDetail sailDetail=new SailDetail();
		
		sailDetail=entityManager.find(sailDetail.getClass(), idSailDetail);
		
		if(sailDetail!=null){
			sailDetailDTO=TransferObjectAssembler.getInstance().assembleTO(sailDetailDTO.getClass(), sailDetail);
		}
		return sailDetailDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SailDetailDTO> getListSailDetail() {
		// TODO Auto-generated method stub
		List<SailDetailDTO> sailDetailDTOLst=new ArrayList<SailDetailDTO>();
		List<SailDetail> sailDetailLst=new ArrayList<SailDetail>();
		SailDetailDTO sailDetailDTO;
		
		sailDetailLst=entityManager.createQuery("SELECT sd FROM SailDetail sd").getResultList();
		
		if(sailDetailLst!=null && sailDetailLst.size()>0){
			sailDetailDTO=new SailDetailDTO();
			for(SailDetail sailDetail:sailDetailLst){
				sailDetailDTO=TransferObjectAssembler.getInstance().assembleTO(sailDetailDTO.getClass(), sailDetail);
				sailDetailDTOLst.add(sailDetailDTO);
			}
		}
		
		return sailDetailDTOLst;
	}

}
