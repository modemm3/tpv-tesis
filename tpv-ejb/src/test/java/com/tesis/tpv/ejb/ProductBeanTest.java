package com.tesis.tpv.ejb;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.reflections.Reflections;
import org.reflections.adapters.MetadataAdapter;
import org.reflections.scanners.Scanner;
import org.reflections.serializers.Serializer;
import org.reflections.util.Utils;
import org.reflections.vfs.Vfs;

import com.tesis.remote.ProductRemote;
import com.tesis.tpv.dto.ProductDTO;
import com.tesis.tpv.ejb.builder.CategoryBuilder;
import com.tesis.tpv.ejb.builder.DepartmentBuilder;
import com.tesis.tpv.ejb.builder.ProductBuilder;
import com.tesis.tpv.ejb.builder.config.TransferObjectAssembler;
import com.tesis.tpv.jpa.Product;

@RunWith  (Arquillian.class)
public class ProductBeanTest {
	@EJB (mappedName="ProductBean")
	ProductRemote product;
	
	@Deployment
	public static JavaArchive createDeployment(){
		return ShrinkWrap.create(JavaArchive.class, "testEJB.jar")
				.addClasses(ProductBean.class, ProductRemote.class)
				.addPackage(Product.class.getPackage())
				.addPackage(ProductDTO.class.getPackage())
				.addPackage(ProductBuilder.class.getPackage())
				.addPackage(CategoryBuilder.class.getPackage())
				.addPackage(DepartmentBuilder.class.getPackage())
				.addPackage(Utils.class.getPackage())
				.addPackage(Scanner.class.getPackage())
				.addPackage(Serializer.class.getPackage())
				.addPackage(Vfs.class.getPackage())
				.addPackage(MetadataAdapter.class.getPackage())
				.addPackage(TransferObjectAssembler.class.getPackage())
				.addPackage(Reflections.class.getPackage())
				.addAsResource("META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void test() {
		//guardar
		ProductDTO productDTO;
		
		/*productDTO=new ProductDTO();
		productDTO.setBuyPrice(new BigDecimal(44.214));
		productDTO.setCategoryId(45);
		productDTO.setCode("9876012354");
		productDTO.setDepartmentId(7);
		productDTO.setName("JABON FLORA");
		productDTO.setProductId(3);
		productDTO.setPublicPrice(new BigDecimal(45.098));
		productDTO.setStock(new BigDecimal(12.34521));
		product.save(productDTO);
		
		productDTO=new ProductDTO();
		productDTO.setBuyPrice(new BigDecimal(89.456));
		productDTO.setCategoryId(45);
		productDTO.setCode("9876012354");
		productDTO.setDepartmentId(7);
		productDTO.setName("CERVEZA INDIO");
		productDTO.setProductId(4);
		productDTO.setPublicPrice(new BigDecimal(1234.454));
		productDTO.setStock(new BigDecimal(13));
		product.save(productDTO);
		
		productDTO=new ProductDTO();
		productDTO.setBuyPrice(new BigDecimal(56.456));
		productDTO.setCategoryId(46);
		productDTO.setCode("9876012354");
		productDTO.setDepartmentId(9);
		productDTO.setName("PAÑAL CHICOLASTIK");
		productDTO.setProductId(5);
		productDTO.setPublicPrice(new BigDecimal(66.567));
		productDTO.setStock(new BigDecimal(1234));
		product.save(productDTO);
		
		productDTO=new ProductDTO();
		productDTO.setBuyPrice(new BigDecimal(23.654));
		productDTO.setCategoryId(50);
		productDTO.setCode("456789012");
		productDTO.setDepartmentId(14);
		productDTO.setName("VINO ROJO");
		productDTO.setProductId(6);
		productDTO.setPublicPrice(new BigDecimal(675.4534));
		productDTO.setStock(new BigDecimal(12));
		product.save(productDTO);*/
		
		//update
		/*productDTO=new ProductDTO();
		productDTO.setId(2);
		productDTO.setBuyPrice(new BigDecimal(256.78));
		productDTO.setCategoryId(49);
		productDTO.setCode("34125676");
		productDTO.setDepartmentId(13);
		productDTO.setName("CAJA DE SARDINAS");
		productDTO.setPublicPrice(new BigDecimal(54.231));
		productDTO.setStock(new BigDecimal(12));
		product.update(productDTO);	*/
		
		//delete
		//product.delete(2);
		
		//get Product
		/*productDTO=product.getProduct(9);
		System.out.println(productDTO);*/
		
		//get ListProduct
		
		List<ProductDTO> productDTOLst=new ArrayList<ProductDTO>();
		
		productDTOLst=product.getProductList();
		
		for(ProductDTO prod: productDTOLst){
			System.out.println(prod);
		}
	}

}
