package com.tesis.tpv.endpoint;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.warp.WarpTest;
import org.jboss.shrinkwrap.api.Archive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.tesis.tpv.dto.ProviderProductDTO;

@WarpTest
@RunWith (Arquillian.class)
public class ProviderProductServiceTest {
	@ArquillianResource
	private URL contextPath;
	
	@Drone
	WebDriver browser;
	
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
		WebResource webResource=client.resource(contextPath + "services/providerProductService/getProviderProductLst");
		
		List<ProviderProductDTO> response=webResource
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.post(new GenericType<List<ProviderProductDTO>>(){});
		
		System.out.println(response);
	}
	
	private ProviderProductDTO createProviderProduct(){
		ProviderProductDTO providerProductDTO=new ProviderProductDTO();
		providerProductDTO.setId(2);
		providerProductDTO.setPrice(new BigDecimal(333.334));
		providerProductDTO.setProductId(9);
		providerProductDTO.setProviderId(11);
		System.out.println("Se envia");
		return providerProductDTO;
	}

}
