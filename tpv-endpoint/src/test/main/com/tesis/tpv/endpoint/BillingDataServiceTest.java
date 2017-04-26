package com.tesis.tpv.endpoint;


import static org.junit.Assert.*;

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
import com.tesis.tpv.dto.BillingDataDTO;
import com.tesis.tpv.dto.MessageResponseDTO;

@WarpTest
@RunWith (Arquillian.class)
public class BillingDataServiceTest {
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
	public void testStockCreate() {
		/*WebResource webResource=client.resource(contextPath+ "services/billingDataService/updateBillingData");
		
		MessageResponseDTO response=webResource
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.post(MessageResponseDTO.class, updateBillingData());
		System.out.println(response);*/
		
		/*WebResource webResource=client.resource(contextPath+ "services/billingDataService/getListBillingData");
		
		List<BillingDataDTO> response=webResource
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.post(new GenericType<List<BillingDataDTO>>(){});
		
		for(BillingDataDTO billingData: response){
			System.out.println("Lista de registro :"+ billingData);
		}*/
		
	}
	
	private BillingDataDTO createBillingData(){
		BillingDataDTO billingDataDTO=new BillingDataDTO();
		billingDataDTO.setName("TIA ROSA S.A. DE C.V.");
		billingDataDTO.setAddress("CALLE SERVICE TIA ROSA COLONIA SERVICE NUM 23");
		billingDataDTO.setRfc("TIAROSA675621");
		billingDataDTO.setTelephone("234589");
		billingDataDTO.setZipCode("9999");
		
		return billingDataDTO;
	}
	
	private BillingDataDTO updateBillingData(){
		BillingDataDTO billingDataDTO=new BillingDataDTO();
		billingDataDTO.setId(19);
		billingDataDTO.setName("SABRITAS");
		billingDataDTO.setRfc("SABRITAS9999");
		billingDataDTO.setTelephone("222222");
		billingDataDTO.setZipCode("2345");
		billingDataDTO.setAddress("CALLE SABRITAS UNO COLONIA SABRITAS DOS");
		return billingDataDTO;
	}

}
