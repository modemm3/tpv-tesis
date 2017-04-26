package com.tesis.tpv.endpoint;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.warp.WarpTest;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.tesis.tpv.dto.ProductDTO;

@WarpTest
@RunWith (Arquillian.class)
public class ProductServiceTest {
	@ArquillianResource
	private URL contextPath;
	
	private Client client;
	
	@Deployment
	public static Archive<?> createTestArchive(){
		return Deployments.createDeployment();
	}
	
	@Before
	public void setUp(){
		client=Client.create(new DefaultClientConfig());
	}

	@Test
	@RunAsClient
	public void test() {
		WebResource webResource=client.resource(contextPath+ "services/productService/getListProduct");
		
		List<ProductDTO> response=webResource
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.post(new GenericType<List<ProductDTO>>(){});
		
		System.out.println(response);
		
	}
	
	public ProductDTO createProduct(){
		ProductDTO product=new ProductDTO();
		
		product.setId(3);
		product.setName("Cerveza Modelo");
		product.setBuyPrice(new BigDecimal (123.45));
		product.setStock(new BigDecimal(23));
		product.setCategoryId(53);
		product.setDepartmentId(8);
		//product.setProductId(productId);
		product.setPublicPrice(new BigDecimal(50.000));
		product.setCode("1234567899");
		
		return product;
	}

}
