package com.tesis.tpv.ejb.builder;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import com.tesis.tpv.dto.ProviderProductDTO;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Category;
import com.tesis.tpv.jpa.Department;
import com.tesis.tpv.jpa.Product;
import com.tesis.tpv.jpa.Provider;
import com.tesis.tpv.jpa.ProviderProduct;

public class ProviderProductTest {

	@Test
	public void test() {
		ProviderProduct providerProduct=new ProviderProduct();
		Product product=new Product();
		Provider provider=new Provider();
		Category category=new Category();
		SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
		Department department=new Department();
		
		providerProduct.setId(1);
		try {
			providerProduct.setBuyDate(formato.parse("15/06/2019"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		product.setId(13);
		product.setProduct(null);
		category.setId(45);
		department.setId(3333);
		product.setDepartment(department);
		product.setCategory(category);
		providerProduct.setPrice(BigDecimal.valueOf(234.12));
		providerProduct.setProduct(product);
		provider.setId(2);
		providerProduct.setProvider(provider);
		
		ProviderProductDTO providerProductDTO=TransferObjectAssembler.getInstance().assembleTO(ProviderProductDTO.class, providerProduct);
		System.out.println(providerProductDTO);
	}

}
