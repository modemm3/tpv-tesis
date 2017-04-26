package com.tesis.tpv.endpoint;

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
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.TypeDiscountDTO;

@WarpTest
@RunWith (Arquillian.class)
public class TypeDiscountServiceTest {
	@ArquillianResource
	private URL contextPath;
	
	private Client client;
	
	/*@Drone
	WebDriver browser;*/
	
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
	public void testStockCreate() {
		/*WebResource webResource=client.resource(contextPath + "services/typeDiscountService/getListTypeDiscount");
		
		List<TypeDiscountDTO> response=webResource
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.post(new GenericType<List<TypeDiscountDTO>>(){});
		
		for(TypeDiscountDTO typeDiscount:response){
			System.out.println("TYPEDISCOUNTDTO: "+typeDiscount);
		}*/
	}
	
	private TypeDiscountDTO createTypeDiscount(){
		TypeDiscountDTO typeDiscountDTO=new TypeDiscountDTO();
		typeDiscountDTO.setName("Agosto");
		typeDiscountDTO.setDescription("Economico");
		return typeDiscountDTO;
	}
	
	private TypeDiscountDTO createUpdateTypeDiscount(){
		TypeDiscountDTO typeDiscountDTO=new TypeDiscountDTO();
		typeDiscountDTO.setId(17);
		typeDiscountDTO.setName("Otoño");
		typeDiscountDTO.setDescription("Se deshojan las plantas");
		return typeDiscountDTO;
	}

}
