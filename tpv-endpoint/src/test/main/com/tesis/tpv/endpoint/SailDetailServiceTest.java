package com.tesis.tpv.endpoint;

import java.math.BigDecimal;
import java.net.URL;

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
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.SailDetailDTO;

@WarpTest
@RunWith (Arquillian.class)
public class SailDetailServiceTest {
	@ArquillianResource
	private URL contextPath;
	
	Client client;
	
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
		WebResource webResource=client.resource(contextPath + "services/sailDetailService/saveSailDetail");
		
		MessageResponseDTO response=webResource
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.post(MessageResponseDTO.class, createSailDetail());
		
		System.out.println(response);
		
	}
	
	private SailDetailDTO createSailDetail(){
		SailDetailDTO sailDetailDTO=new SailDetailDTO();
		sailDetailDTO.setProductId(9);
		sailDetailDTO.setQuantity(new BigDecimal(456.234));
		sailDetailDTO.setSaleId(5);
		return sailDetailDTO;
	}

}
