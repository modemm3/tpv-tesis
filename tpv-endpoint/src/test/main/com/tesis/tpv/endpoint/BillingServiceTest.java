package com.tesis.tpv.endpoint;

import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import com.tesis.tpv.dto.BillingDTO;
import com.tesis.tpv.dto.MessageResponseDTO;

@WarpTest
@RunWith (Arquillian.class)
public class BillingServiceTest {
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
		WebResource webResource=client.resource(contextPath + "services/billingService/getBillingLst");
		
		List<BillingDTO> response= webResource
				.accept(MediaType.APPLICATION_JSON_TYPE)
				.type(MediaType.APPLICATION_JSON_TYPE)
				.post(new GenericType<List<BillingDTO>>(){});
		
		System.out.println(response.toString());
	}
	
	/*public Date returnDate(){
		
		Calendar c = Calendar.getInstance();
		c.clear();									
		c.set(2000, 1, 29, 21, 30, 30);
		Date fech = c.getTime();
		System.out.println("BillingServiceTest: "+fech);
		return fech;
	}*/
	
	public BillingDTO createBilling(){
		
		
		Calendar c = Calendar.getInstance();
		c.clear();									
		c.set(2000, 1, 29, 21, 30, 30);
		Date fech = c.getTime();
		
		/*SimpleDateFormat formatoFecha=new SimpleDateFormat("yyyy-MM-dd");
		String fecha="2015-05-03";
		Date fech=new Date();
		try{
			fech=formatoFecha.parse(fecha);
		}catch(Exception e){
			e.printStackTrace();
		}*/
		
		System.out.println("Fecha inidicial: "+fech); 
		BillingDTO billing=new BillingDTO();
		
		billing.setBillingDate(fech);
		billing.setBillingDataId(20);
		billing.setSaleId(5);
		billing.setSubtotal(new BigDecimal(600));
		billing.setTotal(new BigDecimal(600));
		
		return billing;
	}
	
}
