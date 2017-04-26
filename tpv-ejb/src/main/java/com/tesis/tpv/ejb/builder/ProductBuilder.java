package com.tesis.tpv.ejb.builder;

import com.tesis.tpv.dto.BaseDTO;
import com.tesis.tpv.dto.CategoryDTO;
import com.tesis.tpv.dto.DepartmentDTO;
import com.tesis.tpv.dto.ProductDTO;
import com.tesis.tpv.ejb.builder.config.AbstractDTOBuilder;
import com.tesis.tpv.ejb.builder.config.BuilderConfiguration;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.BaseEntity;
import com.tesis.tpv.jpa.Category;
import com.tesis.tpv.jpa.Product;

@BuilderConfiguration(dtoClass=ProductDTO.class, entityClass=Product.class)
public class ProductBuilder extends AbstractDTOBuilder {

	@Override
	public BaseDTO createDTO(BaseEntity entity) {
		final ProductDTO productDTO=new ProductDTO();
		final Product product=(Product)entity;
		
		if(product.getProduct()!=null){
			
			productDTO.setId(product.getId());
			productDTO.setName(product.getName());
			productDTO.setProductId(TransferObjectAssembler.getInstance().assembleTO(ProductDTO.class, product.getProduct()).getId());
			productDTO.setPublicPrice(product.getPublicPrice());
			productDTO.setStock(product.getStock());
			productDTO.setBuyPrice(product.getBuyPrice());
			productDTO.setCategoryId(TransferObjectAssembler.getInstance().assembleTO(CategoryDTO.class, product.getCategory()).getId());
			productDTO.setCode(product.getCode());
			productDTO.setDepartmentId(TransferObjectAssembler.getInstance().assembleTO(DepartmentDTO.class, product.getDepartment()).getId());
	}
		else if(product.getId()!=null){
			productDTO.setId(product.getId());
			productDTO.setName(product.getName());
			productDTO.setProductId(null);
			productDTO.setPublicPrice(product.getPublicPrice());
			productDTO.setStock(product.getStock());
			productDTO.setBuyPrice(product.getBuyPrice());
			productDTO.setCategoryId(TransferObjectAssembler.getInstance().assembleTO(CategoryDTO.class, product.getCategory()).getId());
			productDTO.setCode(product.getCode());
			productDTO.setDepartmentId(TransferObjectAssembler.getInstance().assembleTO(DepartmentDTO.class, product.getDepartment()).getId());
			
		}
		return productDTO;
	}

}
