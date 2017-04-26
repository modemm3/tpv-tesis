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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.PaymentTypeDTO;

@WarpTest
@RunWith (Arquillian.class)
public class PaymentTypeServiceTest {
	@ArquillianResource
	private URL contextPath;
	
	@Drone
	WebDriver browser;
	
	private Client client;
	private static final Logger LOG=LoggerFactory.getLogger(PaymentTypeServiceTest.class);
	
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
		/*WebResource webResource=client.resource(contextPath + "services/paymentTypeService/updatePaymentType");
		
		MessageResponseDTO response =webResource
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.post(MessageResponseDTO.class, updatePaymentType());*/
		
		/*WebResource webResource=client.resource(contextPath + "services/paymentTypeService/deletePaymentType/19");
		
		MessageResponseDTO response=webResource
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.post(MessageResponseDTO.class);
		
		System.out.println("Se elimino paymentType->ID"+ response);*/
		
		/*WebResource webResource=client.resource(contextPath+ "services/paymentTypeService/getPaymentType/16");
		
		PaymentTypeDTO response=webResource
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.post(PaymentTypeDTO.class);
		
		System.out.println("EL registro solicitado es: "+ response);*/
		
		WebResource webResource=client.resource(contextPath + "services/paymentTypeService/getListPaymentType");
		
		List<PaymentTypeDTO> response=webResource
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.post(new GenericType<List<PaymentTypeDTO>>(){});
		
		for(PaymentTypeDTO paymentType:response){
			System.out.println("Lista de registros paymentType: "+ paymentType);
		}
				
	}
	
	private PaymentTypeDTO createPaymentType(){
		PaymentTypeDTO paymentTypeDTO=new PaymentTypeDTO();
		paymentTypeDTO.setName("Efectivo 1");
		return paymentTypeDTO;
	}
	
	private PaymentTypeDTO updatePaymentType(){
		PaymentTypeDTO paymentTypeDTO = new PaymentTypeDTO();
		
		paymentTypeDTO.setId(15);
		paymentTypeDTO.setName("Debito");
		
		return paymentTypeDTO;
	}
}
