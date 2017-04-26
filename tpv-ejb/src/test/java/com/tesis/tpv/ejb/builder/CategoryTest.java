package com.tesis.tpv.ejb.builder;

import org.junit.Test;

import com.tesis.tpv.dto.CategoryDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Category;

public class CategoryTest {

	@Test
	public void test() {
		Category category=new Category();
		
		category.setId(3);
		category.setName("CategoriaTest");
		category.setDescription("Descripcion categoria Test");
		
		CategoryDTO categoryDTO=TransferObjectAssembler.getInstance().assembleTO(CategoryDTO.class, category);
		System.out.println(categoryDTO);
		
	}

}
