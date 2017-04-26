package com.tesis.tpv.ejb;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.tesis.remote.EmployeeCategoryRemote;
import com.tesis.tpv.dto.EmployeeCategoryDTO;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.EmployeeCategory;

@Stateless(mappedName="EmployeeCategoryBean")
@TransactionManagement(TransactionManagementType.CONTAINER)

public class EmployeeCategoryBean implements EmployeeCategoryRemote{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public MessageResponseDTO save(final EmployeeCategoryDTO employeeCategoryDTO) {
		EmployeeCategory employeeCategory=new EmployeeCategory();
		MessageResponseDTO messageResponseDTO= new MessageResponseDTO();
		
		try{
			employeeCategory.setName(employeeCategoryDTO.getName());
			
			entityManager.persist(employeeCategory);
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
	public MessageResponseDTO delete(final Integer idEmployeeCategory) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
	    EmployeeCategory employeeCategory=new EmployeeCategory();
	    
	    try{
		    employeeCategory=entityManager.find(employeeCategory.getClass(), idEmployeeCategory);
		    
		    if(employeeCategory!=null){
		    	entityManager.remove(employeeCategory);
		    	messageResponseDTO.setCode(1);
		    	messageResponseDTO.setMessage("success");
		    	messageResponseDTO.setIdTransaction(idEmployeeCategory);
		    }else{
		    	messageResponseDTO.setCode(-1);
		    	messageResponseDTO.setMessage("No existe el registro: "+ idEmployeeCategory);
		    }
	    }catch(Exception e){
	    	e.printStackTrace();
	    	messageResponseDTO.setCode(-1);
	    	messageResponseDTO.setMessage("failed");
	    }
		return messageResponseDTO;
	}

	@Override
	public MessageResponseDTO update(final EmployeeCategoryDTO employeeCategoryDTO) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		EmployeeCategory employeeCategory=new EmployeeCategory();

		try{
			employeeCategory=entityManager.find(employeeCategory.getClass(), employeeCategoryDTO.getId());
			
			if(employeeCategory!=null){
				employeeCategory.setId(employeeCategoryDTO.getId());
				employeeCategory.setName(employeeCategoryDTO.getName());
				entityManager.persist(employeeCategory);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("No existe el registro");
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(1);
			messageResponseDTO.setMessage("failed");
		}
		return messageResponseDTO;
	}

	@Override
	public EmployeeCategoryDTO getEmployeeCategory(final Integer idEmployeeCategory) {
		EmployeeCategory employeeCategory=new EmployeeCategory();
		EmployeeCategoryDTO employeeCategoryDTO=new EmployeeCategoryDTO();
		
		employeeCategory=entityManager.find(EmployeeCategory.class, idEmployeeCategory);
		
		if(employeeCategory!=null){
			employeeCategoryDTO=TransferObjectAssembler.getInstance().assembleTO(employeeCategoryDTO.getClass(), employeeCategory);
		}
		
		return employeeCategoryDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeCategoryDTO> getListEmployeeCategory() {
		EmployeeCategoryDTO employeeCategoryDTO=new EmployeeCategoryDTO();
		List<EmployeeCategory> employeeCategoryLst=new ArrayList<EmployeeCategory>();
		List<EmployeeCategoryDTO> employeeCategoryDTOLst=new ArrayList<EmployeeCategoryDTO>();
		
		employeeCategoryLst=entityManager.createQuery("EmployeeCategory.getAll").getResultList();
		
		for(EmployeeCategory ec:employeeCategoryLst){
			employeeCategoryDTO=TransferObjectAssembler.getInstance().assembleTO(employeeCategoryDTO.getClass(), ec);
			employeeCategoryDTOLst.add(employeeCategoryDTO);
		}
		
		return employeeCategoryDTOLst;
	}

	@Override
	public List<EmployeeCategoryDTO> getPagination(Integer pageInit, Integer pageEnd) {
		EmployeeCategoryDTO employeeCategoryDTO=new EmployeeCategoryDTO();
		List<EmployeeCategoryDTO> employeeCategoryDTOLst=new ArrayList<EmployeeCategoryDTO>();
		Query query=entityManager.createNamedQuery("EmployeeCategory.getAll");
		query.setMaxResults(pageEnd);
		query.setFirstResult(pageInit);
		List<EmployeeCategory> employeeCategoryLst=query.getResultList();
				
		if(employeeCategoryLst!=null && employeeCategoryLst.size()>0){
			for(EmployeeCategory empCategory:employeeCategoryLst){
				employeeCategoryDTO=TransferObjectAssembler.getInstance().assembleTO(employeeCategoryDTO.getClass(), empCategory);
				employeeCategoryDTOLst.add(employeeCategoryDTO);
			}
		}
		return employeeCategoryDTOLst;
	}

	@Override
	public Integer numberRecords() {
		Query query;
		Integer count=0;
		query=entityManager.createNamedQuery("EmployeeCategory.getCount");
		count=((Long)query.getSingleResult()).intValue();
		return count;
	}

}
