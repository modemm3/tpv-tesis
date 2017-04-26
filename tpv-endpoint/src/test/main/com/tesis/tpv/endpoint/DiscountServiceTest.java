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
import com.tesis.tpv.dto.DiscountDTO;

@WarpTest
@RunWith (Arquillian.class)
public class DiscountServiceTest {
	@ArquillianResource
	private URL contextPath;
	
	 Client client;
	
	@Deployment
	public static Archive<?> createDeployment(){
		return Deployments.createDeployment();
	}
	
	@Before
	public void setUp(){
		client=Client.create(new DefaultClientConfig());
	}

	@Test
	@RunAsClient
	public void test() {
		WebResource webresource=client.resource(contextPath + "services/discountService/getDiscountLst");
		
		List<DiscountDTO> response=webresource
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.post(new GenericType<List<DiscountDTO>>(){});
		
		System.out.println(response);
	}
	
	private DiscountDTO createDiscount(){
		DiscountDTO discountDTO=new DiscountDTO();
		discountDTO.setId(2);
		discountDTO.setAmount(new BigDecimal(999.999));
		discountDTO.setName("Descuento de testUNO");
		discountDTO.setProductId(10);
		discountDTO.setStatus(false);
		discountDTO.setTypeDiscountId(16);
		return discountDTO;
	}

}
