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
import com.tesis.tpv.dto.MessageResponseDTO;
import com.tesis.tpv.dto.SaleDTO;

@WarpTest
@RunWith (Arquillian.class)
public class SaleServiceTest {
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
		WebResource webResource=client.resource(contextPath + "services/saleService/getListSale");
		
		List<SaleDTO> responseLst=webResource
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.post(new GenericType<List<SaleDTO>>(){});
		
		for(SaleDTO response: responseLst){
			System.out.println("Id: "+response.getId()+ " Amount: "+response.getAmount()+ " paymentType: "+ response.getPaymentTypeId()
			+" EmployeeID: "+ response.getEmployeeId()+ " Discount: "+response.getDiscount());
		}
	}
	
	private SaleDTO createSale(){
		SaleDTO sale=new SaleDTO();
		sale.setAmount(new BigDecimal(9.8080));
		sale.setDiscount(new BigDecimal(8.8080));
		sale.setEmployeeId(6);
		sale.setPaymentTypeId(17);
		return sale;
	}
	
	private SaleDTO updateSale(){
		SaleDTO sale=new SaleDTO();
		sale.setId(3);
		sale.setAmount(new BigDecimal(980.00));
		sale.setDiscount(new BigDecimal(999.000));
		sale.setEmployeeId(2);
		sale.setPaymentTypeId(16);
		return sale;
	}

}
