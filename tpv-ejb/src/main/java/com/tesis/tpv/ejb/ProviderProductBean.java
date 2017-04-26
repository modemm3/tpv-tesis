package com.tesis.tpv.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.tesis.remote.ProviderProductRemote;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.ProviderProductDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Product;
import com.tesis.tpv.jpa.Provider;
import com.tesis.tpv.jpa.ProviderProduct;

@Stateless (mappedName="ProviderProductBean")
@TransactionManagement (TransactionManagementType.CONTAINER)
public class ProviderProductBean implements ProviderProductRemote {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public MessageResponseDTO save(ProviderProductDTO providerProductDTO) {
		// TODO Auto-generated method stub
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		ProviderProduct providerProduct=new ProviderProduct();
		Product product=new Product();
		Provider provider=new Provider();
		
		try{
			product.setId(providerProductDTO.getProductId());
			provider.setId(providerProductDTO.getProviderId());
			providerProduct.setBuyDate(providerProductDTO.getBuyDate());
			providerProduct.setPrice(providerProductDTO.getPrice());
			providerProduct.setProduct(product);
			providerProduct.setProvider(provider);
			
			entityManager.persist(providerProduct);
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
	public MessageResponseDTO update(ProviderProductDTO providerProductDTO) {
		// TODO Auto-generated method stub
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		ProviderProduct providerProduct=new ProviderProduct();
		Product product;
		Provider provider;
		
		try{
			providerProduct=entityManager.find(providerProduct.getClass(), providerProductDTO.getId());
			
			if(providerProduct!=null){
				product=new Product();
				provider=new Provider();
				product.setId(providerProductDTO.getProductId());
				provider.setId(providerProductDTO.getProviderId());
				providerProduct.setBuyDate(providerProductDTO.getBuyDate());
				providerProduct.setId(providerProductDTO.getId());
				providerProduct.setPrice(providerProductDTO.getPrice());
				providerProduct.setProduct(product);
				providerProduct.setProvider(provider);
				
				entityManager.persist(providerProduct);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("No se encontro el registro con id: "+ providerProductDTO.getId());
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		
		return messageResponseDTO;
	}

	@Override
	public MessageResponseDTO delete(Integer idProviderProduct) {
		// TODO Auto-generated method stub
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		ProviderProduct providerProduct=new ProviderProduct();
		
		try{
			providerProduct=entityManager.find(providerProduct.getClass(), idProviderProduct);
			
			if(providerProduct!=null){
				entityManager.remove(providerProduct);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("No se encontro el registro con id: "+ idProviderProduct);
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		return messageResponseDTO;
	}

	@Override
	public ProviderProductDTO getProviderProduct(Integer idProviderProduct) {
		// TODO Auto-generated method stub
		ProviderProductDTO providerProductDTO=new ProviderProductDTO();
		ProviderProduct providerProduct=new ProviderProduct();
		
		providerProduct=entityManager.find(providerProduct.getClass(), idProviderProduct);
		
		if(providerProduct!=null){
			providerProductDTO=TransferObjectAssembler.getInstance().assembleTO(providerProductDTO.getClass(), providerProduct);
		}
		return providerProductDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProviderProductDTO> getListProviderProduct() {
		// TODO Auto-generated method stub
		List<ProviderProductDTO> providerProductDTOLst=new ArrayList<ProviderProductDTO>();
		List<ProviderProduct> providerProductLst=new ArrayList<ProviderProduct>();
		ProviderProductDTO providerProductDTO;
		
		providerProductLst=entityManager.createQuery("SELECT pp FROM ProviderProduct pp").getResultList();
		
		if(providerProductLst!=null && providerProductLst.size()>0){
			providerProductDTO=new ProviderProductDTO();
			for(ProviderProduct providerProduct:providerProductLst){
				providerProductDTO=TransferObjectAssembler.getInstance().assembleTO(providerProductDTO.getClass(), providerProduct);
				providerProductDTOLst.add(providerProductDTO);
			}
		}
		return providerProductDTOLst;
	}

}
