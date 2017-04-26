package com.tesis.tpv.ejb;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.postgresql.util.PSQLException;

import com.tesis.remote.ProviderRemote;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.ProviderDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Provider;

@Stateless (mappedName="ProviderBean")
@TransactionManagement (TransactionManagementType.CONTAINER)
public class ProviderBean implements ProviderRemote{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public MessageResponseDTO save(ProviderDTO providerDTO) {
		// TODO Auto-generated method stub
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		Provider provider=new Provider();
		try{
			provider.setName(providerDTO.getName());
			provider.setAddress(providerDTO.getAddress());
			provider.setBusinessName(providerDTO.getBusinessName());
			provider.setMaternalName(providerDTO.getMaternalName());
			provider.setPaternalName(providerDTO.getPaternalName());
			provider.setRfc(providerDTO.getRfc());
			provider.setTelephone(providerDTO.getTelephone());
			provider.setWebPage(providerDTO.getWebPage());
			entityManager.persist(provider);
			
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
	public MessageResponseDTO update(ProviderDTO providerDTO) {
		// TODO Auto-generated method stub
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		Provider provider=new Provider();
		
		try{
			provider=entityManager.find(provider.getClass(), providerDTO.getId());
			
			if(provider!=null){
				provider.setName(providerDTO.getName());
				provider.setAddress(providerDTO.getAddress());
				provider.setBusinessName(providerDTO.getBusinessName());
				provider.setMaternalName(providerDTO.getMaternalName());
				provider.setPaternalName(providerDTO.getPaternalName());
				provider.setRfc(providerDTO.getRfc());
				provider.setTelephone(providerDTO.getTelephone());
				provider.setWebPage(providerDTO.getWebPage());
				
				entityManager.persist(provider);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("El registro con: "+ providerDTO.getId()+ " no se encuentra");
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}

		return messageResponseDTO;
	}

	@Override
	public MessageResponseDTO delete(Integer idProvider) {
		// TODO Auto-generated method stub
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		Provider provider=new Provider();
		
		try{
			provider=entityManager.find(provider.getClass(), idProvider);
			
			if(provider!=null){
				entityManager.remove(provider);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("No se encuentra el id: "+idProvider);
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		
		return messageResponseDTO;
	}

	@Override
	public ProviderDTO getProvider(Integer idProvider) {
		// TODO Auto-generated method stub
		Provider provider=new Provider();
		ProviderDTO providerDTO=new ProviderDTO();
		
		provider=entityManager.find(provider.getClass(), idProvider);
		
		if(provider!=null){
			providerDTO=TransferObjectAssembler.getInstance().assembleTO(providerDTO.getClass(), provider);
		}
		
		return providerDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProviderDTO> getListProvider() {
		// TODO Auto-generated method stub
		List<ProviderDTO> providerDTOLst=new ArrayList<ProviderDTO>();
		List<Provider> providerLst=new ArrayList<Provider>();
		ProviderDTO providerDTO=new ProviderDTO();
		
		providerLst=entityManager.createQuery("SELECT prov FROM Provider prov").getResultList();
		
		if(providerLst!=null && providerLst.size()>0){
			for(Provider provider:providerLst){
				providerDTO=TransferObjectAssembler.getInstance().assembleTO(providerDTO.getClass(), provider);
				providerDTOLst.add(providerDTO);
			}
		}
		
		return providerDTOLst;
	}

}
