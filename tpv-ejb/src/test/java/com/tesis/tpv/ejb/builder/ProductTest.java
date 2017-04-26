package com.tesis.tpv.ejb.builder;

import java.math.BigDecimal;
import org.junit.Test;
import com.tesis.tpv.dto.ProductDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Category;
import com.tesis.tpv.jpa.Department;
import com.tesis.tpv.jpa.Product;

public class ProductTest {

	@Test
	public void test() {
		Product product=new Product();
		Category category=new Category();
		Department department=new Department();
		Product productUNO=new Product();
		Department departmentUNO=new Department();
		Category categoryUNO=new Category();
		
		product.setBuyPrice(BigDecimal.valueOf(8970.56));
		category.setId(76);
		product.setCategory(category);
		product.setCode("23333");
		department.setId(67);
		product.setDepartment(department);
		product.setId(345);
		product.setName("chiles envinagres");
		productUNO.setId(32124);
		departmentUNO.setId(34);
		productUNO.setDepartment(departmentUNO);
		categoryUNO.setId(90);
		productUNO.setCategory(categoryUNO);
		productUNO.setProduct(null);
		product.setProduct(productUNO);;
		product.setPublicPrice(BigDecimal.valueOf(23567.897));
		product.setStock(BigDecimal.valueOf(2190.098));
		
		ProductDTO productDTO=TransferObjectAssembler.getInstance().assembleTO(ProductDTO.class, product);
		System.out.println(productDTO);
	}

}
