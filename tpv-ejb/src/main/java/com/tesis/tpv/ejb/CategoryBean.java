package com.tesis.tpv.ejb;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.transaction.TransactionalException;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tesis.remote.CategoryRemote;
import com.tesis.tpv.dto.CategoryDTO;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Category;

@Stateless(mappedName="CategoryBean")
@TransactionManagement(TransactionManagementType.CONTAINER)

public class CategoryBean implements CategoryRemote {
	
	private static Logger log=LoggerFactory.getLogger(CategoryBean.class);
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public MessageResponseDTO save(final CategoryDTO categoryDTO) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		try{
			Category category=new Category();
			category.setName(categoryDTO.getName());
			category.setDescription(categoryDTO.getDescription());
			entityManager.persist(category);
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
	public MessageResponseDTO delete(final Integer idCategory) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO(); 
		try
		{
			Category category=new Category();
			
			category=entityManager.find(Category.class, idCategory);
			if(category!=null){
				entityManager.remove(category);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
				messageResponseDTO.setIdTransaction(idCategory);
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("No existe el registro especificado para eliminar");
			}
		}catch(TransactionRequiredException e){
			e.printStackTrace();
			log.error("Ocurrio 	 error al eliminar el registro");
			messageResponseDTO=new MessageResponseDTO(); 
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("El dato no puede ser eliminado, verifique que no se este utilizando");
		}
		
		return messageResponseDTO;
	}

	@Override
	public MessageResponseDTO update(final CategoryDTO categoryDTO) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		try{
			Category category=new Category();
			category=entityManager.find(category.getClass(), categoryDTO.getId());
			if(category!=null){
				category.setName(categoryDTO.getName());
				category.setDescription(categoryDTO.getDescription());
				entityManager.merge(category);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("No existe el registro especificado para actualizar");
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("failed");
		}
		return messageResponseDTO;
	}

	
	public CategoryDTO getCategory(final Integer idCategory) {
		Category category=new Category();
		CategoryDTO categoryDTO=new CategoryDTO();
	
		category=entityManager.find(category.getClass(), idCategory);
		
		if(category!=null){
			categoryDTO=TransferObjectAssembler.getInstance().assembleTO(categoryDTO.getClass(), category);
		}

		return categoryDTO;
	}
	
/*	@Override
	public MessageResponseDTO getCategory(Integer idCategory) {
		MessageResponseDTO message=new MessageResponseDTO();
		Category category=new Category();
		CategoryDTO categoryDTO=new CategoryDTO();
		
		category=entityManager.find(category.getClass(), idCategory);
		if(category!=null){
			categoryDTO=TransferObjectAssembler.getInstance().assembleTO(categoryDTO.getClass(), category);
		}

		message.setCode(1);
		message.setMessage("hola");
		return message;
	}*/

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryDTO> getList() {

		CategoryDTO categoryDTO=new CategoryDTO();
		List<CategoryDTO> categoryLstDTO=new ArrayList<CategoryDTO>();
		
		List<Category> categoryLst= entityManager.createQuery("Category.getAll").getResultList();
		
		if(categoryLst!=null && categoryLst.size()>0){
			for(Category category: categoryLst){
				categoryDTO=TransferObjectAssembler.getInstance().assembleTO(categoryDTO.getClass(), category);
				categoryLstDTO.add(categoryDTO);
			}
		}
		 return categoryLstDTO;
	}

	@Override
	public List<CategoryDTO> getPagination(final Integer pageInit,final Integer pageEnd) {
		CategoryDTO categoryDTO=new CategoryDTO();
		List<CategoryDTO> categoryDTOLst=new ArrayList<CategoryDTO>();
		Query query=entityManager.createNamedQuery("Category.getAll");
		query.setMaxResults(pageEnd);
		query.setFirstResult(pageInit);
		List<Category> categoryLst=query.getResultList();
		
		if(categoryLst!=null && categoryLst.size()>0){
			for(Category category:categoryLst){
				categoryDTO=TransferObjectAssembler.getInstance().assembleTO(categoryDTO.getClass(), category);
				categoryDTOLst.add(categoryDTO);
			}
		}
		
		System.out.println("Se imprime la lista de categorias en paginacion: "+ categoryDTOLst);
		return categoryDTOLst;
	}

	@Override
	public Integer numberRecords() {
		Query query;
		Integer count=0;
		query=entityManager.createNamedQuery("Category.getCount");
		count=((Long)query.getSingleResult()).intValue();
		return count;
	}
}
