package com.tesis.tpv.ejb.builder;

import com.tesis.tpv.dto.BaseDTO;
import com.tesis.tpv.dto.CategoryDTO;
import com.tesis.tpv.ejb.builder.config.AbstractDTOBuilder;
import com.tesis.tpv.ejb.builder.config.BuilderConfiguration;
import com.tesis.tpv.jpa.BaseEntity;
import com.tesis.tpv.jpa.Category;

@BuilderConfiguration(dtoClass=CategoryDTO.class, entityClass=Category.class)
public class CategoryBuilder extends AbstractDTOBuilder {

	@Override
	public BaseDTO createDTO(BaseEntity entity) {
		final CategoryDTO categoryDTO= new CategoryDTO();
		final Category category=(Category)entity;
		
		categoryDTO.setId(category.getId());
		categoryDTO.setName(category.getName());
		categoryDTO.setDescription(category.getDescription());
		
		return categoryDTO;
	}

}
