package com.tesis.tpv.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.tesis.remote.ProductRemote;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.ProductDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Category;
import com.tesis.tpv.jpa.Department;
import com.tesis.tpv.jpa.Product;

@Stateless (mappedName="ProductBean")
@TransactionManagement (TransactionManagementType.CONTAINER)
public class ProductBean implements ProductRemote{
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public MessageResponseDTO save(ProductDTO productDTO) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		Product product=new Product();
		Category category=new Category();
		Department department=new Department();
		Product productAux=new Product();
		
		try{
			product.setBuyPrice(productDTO.getBuyPrice());
			category.setId(productDTO.getCategoryId());
			product.setCategory(category);
			product.setCode(productDTO.getCode());
			department.setId(productDTO.getDepartmentId());
			product.setDepartment(department);
			product.setName(productDTO.getName());
			if(productDTO.getProductId()!=null){
				productAux.setId(productDTO.getProductId());
				product.setProduct(productAux);
			}
			product.setPublicPrice(productDTO.getPublicPrice());
			product.setStock(productDTO.getStock());
			
			entityManager.persist(product);
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
	public MessageResponseDTO update(ProductDTO productDTO) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		Product product=new Product();
		Category category;
		Department department;
		Product productAux;
		
		try{
			product=entityManager.find(product.getClass(), productDTO.getId());
			
			if(product!=null){
				product.setId(productDTO.getId());
				product.setBuyPrice(productDTO.getBuyPrice());
				category=new Category();
				category.setId(productDTO.getCategoryId());
				product.setCategory(category);
				product.setCode(productDTO.getCode());
				department=new Department();
				department.setId(productDTO.getDepartmentId());
				product.setDepartment(department);
				product.setName(productDTO.getName());
				if(productDTO.getProductId()!=null){
					productAux=new Product();
					productAux.setId(productDTO.getProductId());
					product.setProduct(productAux);
				}
				product.setPublicPrice(productDTO.getPublicPrice());
				product.setStock(productDTO.getStock());
				
				entityManager.persist(product);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("failed");
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("El registro no se logro actualizar");
		}
		
		return messageResponseDTO;
	}

	@Override
	public MessageResponseDTO delete(Integer idProduct) {
		MessageResponseDTO messageResponseDTO=new MessageResponseDTO();
		Product product=new Product();
		
		try{
			product=entityManager.find(product.getClass(), idProduct);
			
			if(product!=null){
				entityManager.remove(product);
				messageResponseDTO.setCode(1);
				messageResponseDTO.setMessage("success");
			}else{
				messageResponseDTO.setCode(-1);
				messageResponseDTO.setMessage("failed");
			}
		}catch(Exception e){
			e.printStackTrace();
			messageResponseDTO.setCode(-1);
			messageResponseDTO.setMessage("El registro no puede ser eliminado");
		}
		
		return messageResponseDTO;
	}

	@Override
	public ProductDTO getProduct(Integer idProduct) {
		ProductDTO productDTO=new ProductDTO();
		Product product=new Product();
		
		product=entityManager.find(product.getClass(), idProduct);
		
		if(product!=null){
			productDTO=TransferObjectAssembler.getInstance().assembleTO(productDTO.getClass(), product);
		}
		
		return productDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductDTO> getProductList() {
		List<ProductDTO> productDTOLst=new ArrayList<ProductDTO>();
		List<Product> productLst=new ArrayList<Product>();
		ProductDTO productDTO=new ProductDTO();
		
		productLst=entityManager.createNamedQuery("Product.getAll").getResultList();
		
		if(productLst!=null && productLst.size()>0){
			for(Product product:productLst){
				productDTO=TransferObjectAssembler.getInstance().assembleTO(ProductDTO.class, product);
				productDTOLst.add(productDTO);
			}
		}
		return productDTOLst;
	}

	@Override
	public ProductDTO getProductByCode(String code) {

		ProductDTO productDTO=new ProductDTO();
		Product product=new Product();
		Query query=entityManager.createNamedQuery("Product.findByCode");
		query.setParameter("code", code);
		product=(Product) query.getSingleResult();
		if(product!=null){
			productDTO=TransferObjectAssembler.getInstance().assembleTO(productDTO.getClass(), product);
		}
		return productDTO;
	
	}

	@Override
	public List<ProductDTO> getPagination(Integer pageInit, Integer pageEnd) {
		ProductDTO productDTO=new ProductDTO();
		List<ProductDTO> productDTOLst=new ArrayList<ProductDTO>();
		Query query=entityManager.createNamedQuery("Product.getAll");
		query.setMaxResults(pageEnd);
		query.setFirstResult(pageInit);
		List<Product> productLst=query.getResultList();
		
		if(productLst!=null && productLst.size()>0){
			for(Product product: productLst){
				productDTO=TransferObjectAssembler.getInstance().assembleTO(productDTO.getClass(),product);
				productDTOLst.add(productDTO);
			}
		}
		return productDTOLst;
	}

	@Override
	public Integer numberRecords() {
		Query query;
		Integer count=0;
		query=entityManager.createNamedQuery("Product.getCount");
		count=((Long) query.getSingleResult()).intValue();
		return count;
	}


}
